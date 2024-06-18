package hello.moviecomm.board.repository;

import hello.moviecomm.board.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardMapper boardMapper;

    @Test
    void findBoardNameByNo() {
        String boardName = boardMapper.findNameByNo(1);
        Assertions.assertThat(boardName).isEqualTo("영화리뷰 게시판");
    }

    @Test
    void findAllBoard() {
        List<Board> boards = boardMapper.findAll();
        String name = boards.get(0).getName();
        Assertions.assertThat(name).isEqualTo("영화리뷰 게시판");
    }
}