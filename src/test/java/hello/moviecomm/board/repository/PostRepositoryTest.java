package hello.moviecomm.board.repository;

import hello.moviecomm.board.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    void save() {
        Post post = Post.builder()
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

    @Test
    void remove() {
        postRepository.remove(1);
        Post post = postRepository.findByNo(1);
        Assertions.assertThat(post).isNull();
    }

    @Test
    void modify() {
        Post updatedPost = Post.builder()
                .title("수정 제목")
                .content("수정 내용")
                .fileName(null)
                .storeFileName(null)
                .build();
        postRepository.modify(updatedPost,1);
        Post post = postRepository.findByNo(1);
        Assertions.assertThat(post.getTitle()).isEqualTo("수정 제목");
        Assertions.assertThat(post.getContent()).isEqualTo("수정 내용");
        Assertions.assertThat(post.getFileName()).isNull();
        Assertions.assertThat(post.getStoreFileName()).isNull();
    }
}