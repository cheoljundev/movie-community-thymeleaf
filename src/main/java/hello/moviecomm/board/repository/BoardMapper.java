package hello.moviecomm.board.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    String findBoardNameByNo(Integer boardNo);
}
