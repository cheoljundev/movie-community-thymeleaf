package hello.moviecomm.board.repository;

import hello.moviecomm.board.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    String findNameByNo(Integer boardNo);
    List<Board> findAll();
    Integer findCountByNo(Integer boardNo);
}
