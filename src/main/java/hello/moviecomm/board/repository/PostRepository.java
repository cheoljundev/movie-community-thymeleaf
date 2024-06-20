package hello.moviecomm.board.repository;

import hello.moviecomm.board.dto.DbListPostDto;
import hello.moviecomm.board.dto.DbPostDto;
import hello.moviecomm.board.dto.ListPostDto;
import hello.moviecomm.board.dto.ModifyPostDto;

import java.util.List;

public interface PostRepository {
    void save(DbPostDto dbPostDto);
    DbPostDto findByNo(Integer postNo);
    List<ListPostDto> findAll(Integer boardNo);
    void remove(Integer postNo);
    void modify(ModifyPostDto modifyPostDto, Integer postNo);
}
