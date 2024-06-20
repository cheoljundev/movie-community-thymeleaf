package hello.moviecomm.board.service;

import hello.moviecomm.board.dto.DbPostDto;
import hello.moviecomm.board.dto.WritePostDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    void save() throws IOException {
        WritePostDto post = WritePostDto.builder()
                .boardNo(1)
                .title("title")
                .content("content")
                .memberNo(1)
                .build();
        postService.save(post);
        Assertions.assertThat(post.getTitle()).isEqualTo("title");
    }

    @Test
    void findByNo() {
        postService.findByNo(1);
        Assertions.assertThat(postService.findByNo(1).getTitle()).isEqualTo("테스트글");
    }

    @Test
    void findAll() {
        postService.findAll(1);
        Assertions.assertThat(postService.findAll(1).size()).isEqualTo(1);
    }
}