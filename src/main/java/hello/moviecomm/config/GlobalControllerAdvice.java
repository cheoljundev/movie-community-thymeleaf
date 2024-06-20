package hello.moviecomm.config;

import hello.moviecomm.board.domain.Board;
import hello.moviecomm.board.service.BoardService;
import hello.moviecomm.error.exception.BoardNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    private final BoardService boardService;
    @ModelAttribute("allBoard")
    public List<Board> allBoard() {
        List<Board> allBoard = boardService.findAllBoard();
        return allBoard;
    }

    @ModelAttribute("uri")
    public String currentUri() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getRequestURI();
    }

    @ExceptionHandler(BoardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleBoardNotFoundException(BoardNotFoundException ex, Model model) {
        model.addAttribute("allBoard", allBoard());
        model.addAttribute("uri", currentUri());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/404";
    }
}
