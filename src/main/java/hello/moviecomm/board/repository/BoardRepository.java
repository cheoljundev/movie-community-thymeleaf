package hello.moviecomm.board.repository;

import hello.moviecomm.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final BoardMapper boardMapper;

    public String findBoardNameByNo(Integer boardNo) {
        return boardMapper.findNameByNo(boardNo);
    }

    public List<Board> findAllBoard() {
        return boardMapper.findAll();
    }
}
