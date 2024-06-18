package hello.moviecomm.board.repository;

import hello.moviecomm.board.dto.DbListPostDto;
import hello.moviecomm.board.dto.DbPostDto;
import hello.moviecomm.board.dto.DetailPostDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    void save(DbPostDto dbPostDto);
    DbPostDto findByNo(Integer postNo);
    List<DbListPostDto> findAll(Integer boardNo);

}
