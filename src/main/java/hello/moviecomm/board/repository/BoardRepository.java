package hello.moviecomm.board.repository;

import hello.moviecomm.board.domain.Board;

import java.util.List;

public interface BoardRepository {
    String findBoardNameByNo(Integer boardNo);
    List<Board> findAllBoard();
    Integer getPages(Integer boardNo, Integer maxPostView);
}
