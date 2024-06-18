package hello.moviecomm.board.repository;

import hello.moviecomm.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisBoardRepository implements BoardRepository{
    private final BoardMapper boardMapper;

    @Override
    public String findBoardNameByNo(Integer boardNo) {
        return boardMapper.findNameByNo(boardNo);
    }

    @Override
    public List<Board> findAllBoard() {
        return boardMapper.findAll();
    }
}
