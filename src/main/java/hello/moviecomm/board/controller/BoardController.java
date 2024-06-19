package hello.moviecomm.board.controller;

import hello.moviecomm.board.dto.DbListPostDto;
import hello.moviecomm.board.dto.ListPostDto;
import hello.moviecomm.board.service.BoardService;
import hello.moviecomm.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final PostService postService;

    @GetMapping("/{boardNo}")
    public String list(@PathVariable("boardNo") Integer boardNo, Model model) {
        String boardName = boardService.findBoardNameByNo(boardNo);
        List<ListPostDto> postList = postService.findAll(boardNo);
        model.addAttribute("boardNo", boardNo);
        model.addAttribute("boardName", boardName);
        model.addAttribute("postList", postList);
        return "board/list";
    }

}
