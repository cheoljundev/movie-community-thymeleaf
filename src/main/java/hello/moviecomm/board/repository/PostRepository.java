package hello.moviecomm.board.repository;

import hello.moviecomm.board.dto.DbListPostDto;
import hello.moviecomm.board.dto.DbPostDto;
import hello.moviecomm.board.dto.ListPostDto;

import java.util.List;

public interface PostRepository {
    void save(DbPostDto dbPostDto);
    DbPostDto findByNo(Integer postNo);
    List<ListPostDto> findAll(Integer boardNo);
}
