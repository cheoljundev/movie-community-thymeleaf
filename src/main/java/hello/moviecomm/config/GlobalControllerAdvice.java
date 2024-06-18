package hello.moviecomm.config;

import hello.moviecomm.board.domain.Board;
import hello.moviecomm.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final BoardService boardService;
    @ModelAttribute
    public void globalAttribute(Model model) {
        List<Board> allBoard = boardService.findAllBoard();
        model.addAttribute("allBoard", allBoard);
    }
}
