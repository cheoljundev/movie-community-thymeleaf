package hello.moviecomm.board.repository;

import hello.moviecomm.board.dto.DbPostDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MyBatisPostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void save() {
        DbPostDto post = DbPostDto.builder()
                .boardNo(1)
                .title("title")
                .content("content")
                .memberNo(1)
                .build();
        postRepository.save(post);
        Assertions.assertThat(post.getTitle()).isEqualTo("title");
    }

    @Test
    void findByNo() {
        postRepository.findByNo(1);
        Assertions.assertThat(postRepository.findByNo(1).getTitle()).isEqualTo("테스트글");
    }

    @Test
    void findAll() {
        postRepository.findAll(1);
        Assertions.assertThat(postRepository.findAll(1).size()).isEqualTo(1);
    }
}