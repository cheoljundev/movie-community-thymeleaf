package hello.moviecomm.board.controller;

import hello.moviecomm.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{boardNo}")
    public String list(@PathVariable("boardNo") Integer boardNo, Model model) {
        model.addAttribute("boardName", boardService.findBoardNameByNo(boardNo));
        return "board/list";
    }

}
