package hello.moviecomm.board.service;

import hello.moviecomm.board.repository.BoardRepository;
import hello.moviecomm.board.repository.MyBatisBoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    BoardService boardService;

    @Test
    void findBoardNameByNo() {
        String boardName = boardService.findBoardNameByNo(1);
        Assertions.assertThat(boardName).isEqualTo("영화리뷰 게시판");
    }

    @Test
    void findAllBoard() {
        String name = boardService.findAllBoard().get(0).getName();
        Assertions.assertThat(name).isEqualTo("영화리뷰 게시판");
    }

    @Test
    void getPages() {
        Integer pages = boardService.getPages(1, 10);
        Assertions.assertThat(pages).isEqualTo(20);
    }

    @Test
    void minPageLimit() {
        int minPageLimit1 = boardService.minPageLimit(1);
        int minPageLimit2 = boardService.minPageLimit(13);
        Assertions.assertThat(minPageLimit1).isEqualTo(1);
        Assertions.assertThat(minPageLimit2).isEqualTo(11);
    }

    @Test
    void maxPageLimit() {
        int maxPageLimit1 = boardService.maxPageLimit(1, 1, 10);
        int maxPageLimit2 = boardService.maxPageLimit(1, 11, 10);
        Assertions.assertThat(maxPageLimit1).isEqualTo(10);
        Assertions.assertThat(maxPageLimit2).isEqualTo(20); // 10이 나오는 이유? 20이 나와야 하는데
    }
}