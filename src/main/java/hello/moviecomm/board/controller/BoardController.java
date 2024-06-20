package hello.moviecomm.board.controller;

import hello.moviecomm.board.domain.Board;
import hello.moviecomm.board.dto.*;
import hello.moviecomm.board.service.BoardService;
import hello.moviecomm.board.service.PostService;
import hello.moviecomm.member.domain.Member;
import hello.moviecomm.member.dto.CustomUserDetails;
import hello.moviecomm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/{boardNo}")
    public String list(@PathVariable("boardNo") Integer boardNo, Model model) {
        List<Board> boards = boardService.findAllBoard();
        //  boards의 boardNo 중에 boardNo가 있는지 확인
        boolean isExist = boards.stream().anyMatch(board -> board.getBoardNo().equals(boardNo));
        if (!isExist) {
            return "redirect:/";
        }
        String boardName = boardService.findBoardNameByNo(boardNo);
        List<ListPostDto> postList = postService.findAll(boardNo);
        model.addAttribute("boardNo", boardNo);
        model.addAttribute("boardName", boardName);
        model.addAttribute("postList", postList);
        return "board/list";
    }

    @GetMapping("/{boardNo}/detail")
    public String detail(@PathVariable("boardNo") Integer boardNo, @RequestParam("postNo") Integer postNo, Model model) {
        DbPostDto post = postService.findByNo(postNo);
        String boardName = boardService.findBoardNameByNo(boardNo);
        String memberName = memberService.findByNo(post.getMemberNo()).getName();
        DetailPostDto detailPost = DetailPostDto.builder()
                .postNo(post.getPostNo())
                .title(post.getTitle())
                .content(post.getContent())
                .createAt(post.getCreateAt())
                .fileName(post.getFileName())
                .storeFileName(post.getStoreFileName())
                .memberName(memberName)
                .build();
        log.info("detailPost: {}", detailPost);
        model.addAttribute("boardName", boardName);
        model.addAttribute("boardNo", boardNo);
        model.addAttribute("post", detailPost);
        return "board/detail";
    }

    @GetMapping("/write")
    public String writeForm(@RequestParam("boardNo") Integer boardNo, Model model){
        WritePostDto post = new WritePostDto();
        model.addAttribute("boardNo", boardNo);
        model.addAttribute("post", post);
        return "board/write";
    }

    @PostMapping("/write/{boardNo}")
    public String write(@ModelAttribute WritePostDto post, @AuthenticationPrincipal CustomUserDetails user, @PathVariable("boardNo") Integer boardNo) throws IOException {
        Integer memberNo = user.getMember().getMemberNo();
        post.setMemberNo(memberNo);
        post.setBoardNo(boardNo);
        postService.save(post);
        return "redirect:/board/" + post.getBoardNo();
    }

}
