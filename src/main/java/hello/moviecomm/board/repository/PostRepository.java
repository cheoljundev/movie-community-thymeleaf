package hello.moviecomm.board.repository;

import hello.moviecomm.board.domain.Post;
import hello.moviecomm.board.dto.PostListDto;

import java.util.List;

public interface PostRepository {
    void save(Post post);
    Post findByNo(Integer postNo);
    List<PostListDto> findAll(Integer boardNo);
    List<PostListDto> findRange(Integer boardNo, Integer start, Integer end);
    void remove(Integer postNo);
    void modify(Post updatedPost, Integer postNo);
}
