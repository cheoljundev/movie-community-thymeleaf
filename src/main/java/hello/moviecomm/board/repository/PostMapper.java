package hello.moviecomm.board.repository;

import hello.moviecomm.board.domain.Post;
import hello.moviecomm.board.dto.PostModifyDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    void save(Post post);
    Post findByNo(Integer postNo);
    List<Post> findAll(Integer boardNo);

    void remove(Integer postNo);

    void modify(@Param("updatedPost") Post updatedPost, @Param("postNo") Integer postNo);

}
