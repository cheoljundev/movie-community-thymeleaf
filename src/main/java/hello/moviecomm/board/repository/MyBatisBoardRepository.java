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

    @Override
    public Integer getPages(Integer boardNo, Integer maxPostView) {
        Integer postsCount = boardMapper.findCountByNo(boardNo);
        return (int) Math.ceil((double) postsCount / maxPostView);
    }
}
