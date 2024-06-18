package hello.moviecomm.board.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final BoardMapper boardMapper;

    public String findBoardNameByNo(Integer boardNo) {
        return boardMapper.findNameByNo(boardNo);
    }
}
