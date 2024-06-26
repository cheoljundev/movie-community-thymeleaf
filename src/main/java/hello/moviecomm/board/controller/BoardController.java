package hello.moviecomm.board.controller;

import hello.moviecomm.board.domain.Board;
import hello.moviecomm.board.domain.Post;
import hello.moviecomm.board.dto.*;
import hello.moviecomm.board.service.BoardService;
import hello.moviecomm.board.service.PostService;
import hello.moviecomm.error.exception.NotFoundException;
import hello.moviecomm.member.dto.CustomUserDetails;
import hello.moviecomm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;
    private final MemberService memberService;
    @Value("${file.dir}") String fileDir;


    @GetMapping("/{boardNo}")
    public String list(@PathVariable("boardNo") Integer boardNo,
                       Model model,
                       @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        List<Board> boards = boardService.findAllBoard();
        //  boards의 boardNo 중에 boardNo가 있는지 확인
        boolean isExist = boards.stream().anyMatch(board -> board.getBoardNo().equals(boardNo));
        if (!isExist) {
            throw new NotFoundException("게시판 번호 " + boardNo +" 게시판이 존재하지 않습니다.");
        }

        Integer pagesResult = boardService.getPages(boardNo, 10);
        List<Integer> pages = IntStream.rangeClosed(1, pagesResult).boxed().collect(Collectors.toList());


        String boardName = boardService.findBoardNameByNo(boardNo);
        List<PostListDto> posts = postService.findPosts(boardNo, pageNo, 10);

        int minPage = boardService.minPageLimit(pageNo);
        int maxPage = boardService.maxPageLimit(boardNo, minPage, 10);
        int prevPage = minPage - 1;
        int nextPage = maxPage + 1;
        boolean hasPrev = prevPage > 1;
        boolean hasNext = nextPage <= pagesResult;

        List<Integer> pagination = IntStream.rangeClosed(minPage, maxPage).boxed().collect(Collectors.toList());

        model.addAttribute("boardNo", boardNo);
        model.addAttribute("boardName", boardName);
        model.addAttribute("posts", posts);
        model.addAttribute("pages", pages);
        model.addAttribute("minPage", minPage);
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("prevPage", prevPage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("pagination", pagination);
        model.addAttribute("currentPageNo", pageNo);
        return "board/list";
    }

    @GetMapping("/{boardNo}/detail")
    public String detail(@PathVariable("boardNo") Integer boardNo, @RequestParam("postNo") Integer postNo, Model model) {
        Post post = postService.findByNo(postNo);
        String boardName = boardService.findBoardNameByNo(boardNo);
        String memberName = memberService.findByNo(post.getMemberNo()).getName();
        PostDetailDto detailPost = PostDetailDto.builder()
                .postNo(post.getPostNo())
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(post.getCreateAt())
                .fileName(post.getFileName())
                .storeFileName(post.getStoreFileName())
                .memberName(memberName)
                .build();
        model.addAttribute("boardName", boardName);
        model.addAttribute("boardNo", boardNo);
        model.addAttribute("post", detailPost);
        return "board/detail";
    }

    @GetMapping("/write")
    public String writeForm(@RequestParam("boardNo") Integer boardNo, Model model){
        PostWriteDto post = new PostWriteDto();
        model.addAttribute("boardNo", boardNo);
        model.addAttribute("post", post);
        return "board/write";
    }

    @PostMapping("/write/{boardNo}")
    public String write(@ModelAttribute PostWriteDto post, @AuthenticationPrincipal CustomUserDetails user, @PathVariable("boardNo") Integer boardNo) throws IOException {
        Integer memberNo = user.getMemberDto().getMemberNo();
        post.setMemberNo(memberNo);
        post.setBoardNo(boardNo);
        postService.save(post);
        return "redirect:/board/" + post.getBoardNo();
    }

    @GetMapping("/image/{fileName}")
    @ResponseBody
    public ResponseEntity<Resource> downloadImage(@PathVariable("fileName") String fileName)  {
        String directory = System.getProperty("user.dir") + fileDir;
        String filePath = directory + fileName;
        File file = new File(filePath);

        Resource resource = new FileSystemResource(file); // 파일을 리소스로 변환

        if (!file.exists() || !file.isFile()) { // 파일이 존재하지 않거나 파일이 아닌 경우
            throw new NotFoundException("파일을 찾을 수 없습니다.");
        }

        // 파일 확장자를 통해 MIME 타입을 결정
        String mimeType;
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (ext) {
            case "jpg":
            case "jpeg":
                mimeType = "image/jpeg";
                break;
            case "png":
                mimeType = "image/png";
                break;
            case "gif":
                mimeType = "image/gif";
                break;
            default:
                mimeType = "application/octet-stream";  // 일반적인 바이너리 파일
        }

        // Content-Type 헤더를 설정하고 파일을 반환
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, mimeType);

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    @GetMapping("/delete/{boardNo}/{postNo}")
    public String delete(@PathVariable("postNo") Integer postNo,
                         @PathVariable("boardNo") Integer boardNo,
                         @AuthenticationPrincipal CustomUserDetails user) {
        postService.remove(postNo, user);
        return "redirect:/board/" + boardNo;
    }

    @GetMapping("/modify/{boardNo}/{postNo}")
    public String modifyForm(
                            @PathVariable("boardNo") Integer boardNo,
                             @PathVariable("postNo") Integer postNo,
                             Model model) {
        Post post = postService.findByNo(postNo);
        PostModifyDto modifyPost = PostModifyDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .build();
        String boardName = boardService.findBoardNameByNo(boardNo);
        model.addAttribute("boardName", boardName);
        model.addAttribute("boardNo", boardNo);
        model.addAttribute("post", modifyPost);
        return "board/modify";
    }

    @PostMapping("/modify/{boardNo}/{postNo}")
    public String modify(
            @ModelAttribute PostModifyDto postModifyDto,
            @PathVariable("boardNo") Integer boardNo,
            @PathVariable("postNo") Integer postNo,
            @AuthenticationPrincipal CustomUserDetails user,
            RedirectAttributes redirectAttributes) throws IOException {

        postService.modify(postModifyDto, postNo, user);
        redirectAttributes.addAttribute("boardNo", boardNo);
        redirectAttributes.addAttribute("postNo", postNo);
        return "redirect:/board/{boardNo}/detail?postNo={postNo}";
    }
}
