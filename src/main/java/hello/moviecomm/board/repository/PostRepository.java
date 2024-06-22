package hello.moviecomm.board.repository;

import hello.moviecomm.board.domain.Post;
import hello.moviecomm.board.dto.PostListDto;
import hello.moviecomm.board.dto.PostModifyDto;

import java.util.List;

public interface PostRepository {
    void save(Post post);
    Post findByNo(Integer postNo);
    List<PostListDto> findAll(Integer boardNo);
    void remove(Integer postNo);
    void modify(PostModifyDto postModifyDto, Integer postNo);
}
