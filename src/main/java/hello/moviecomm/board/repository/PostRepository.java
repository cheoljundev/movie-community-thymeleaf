package hello.moviecomm.board.repository;

import hello.moviecomm.board.dto.DbListPostDto;
import hello.moviecomm.board.dto.DbPostDto;

import java.util.List;

public interface PostRepository {
    void save(DbPostDto dbPostDto);
    DbPostDto findByNo(Integer postNo);
    List<DbListPostDto> findAll(Integer boardNo);
}
