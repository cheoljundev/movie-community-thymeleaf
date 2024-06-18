package hello.moviecomm.board.service;

import hello.moviecomm.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public String findBoardNameByNo(Integer boardNo) {
        return boardRepository.findBoardNameByNo(boardNo);
    }

}
