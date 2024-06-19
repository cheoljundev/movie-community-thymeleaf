package hello.moviecomm.config;

import hello.moviecomm.board.domain.Board;
import hello.moviecomm.board.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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

    @ModelAttribute("uri")
    public String currentUri() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getRequestURI();
    }
}
