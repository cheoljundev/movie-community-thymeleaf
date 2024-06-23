package hello.moviecomm.board.service;

import hello.moviecomm.board.domain.Board;
import hello.moviecomm.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public String findBoardNameByNo(Integer boardNo) {
        return boardRepository.findBoardNameByNo(boardNo);
    }

    public List<Board> findAllBoard() {
        return boardRepository.findAllBoard();
    }

    public Integer getPages(Integer boardNo, Integer maxPostView) {
        return boardRepository.getPages(boardNo, maxPostView);
    }
}
