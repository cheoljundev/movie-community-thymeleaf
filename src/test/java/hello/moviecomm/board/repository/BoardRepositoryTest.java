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
    BoardRepository boardRepository;

    @Test
    void findBoardNameByNo() {
        String boardName = boardRepository.findBoardNameByNo(1);
        Assertions.assertThat(boardName).isEqualTo("영화리뷰 게시판");
    }

    @Test
    void findAllBoard() {
        List<Board> boards = boardRepository.findAllBoard();
        String name = boards.get(0).getName();
        Assertions.assertThat(name).isEqualTo("영화리뷰 게시판");
    }

    @Test
    void getPages() {
        Integer pages = boardRepository.getPages(1, 10);
        Assertions.assertThat(pages).isEqualTo(1);
    }
}