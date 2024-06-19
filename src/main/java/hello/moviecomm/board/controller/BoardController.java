package hello.moviecomm.board.controller;

import hello.moviecomm.board.dto.DbListPostDto;
import hello.moviecomm.board.dto.DbPostDto;
import hello.moviecomm.board.dto.DetailPostDto;
import hello.moviecomm.board.dto.ListPostDto;
import hello.moviecomm.board.service.BoardService;
import hello.moviecomm.board.service.PostService;
import hello.moviecomm.member.domain.Member;
import hello.moviecomm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/{boardNo}")
    public String list(@PathVariable("boardNo") Integer boardNo, Model model) {
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
                .memberName(memberName)
                .build();
        model.addAttribute("boardName", boardName);
        model.addAttribute("boardNo", boardNo);
        model.addAttribute("post", detailPost);
        return "board/detail";
    }

}
