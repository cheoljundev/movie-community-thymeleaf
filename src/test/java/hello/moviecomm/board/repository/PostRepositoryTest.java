package hello.moviecomm.board.repository;

import hello.moviecomm.board.domain.Post;
import hello.moviecomm.board.dto.PostListDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Post post = postRepository.findByNo(1);
        Assertions.assertThat(post.getTitle()).isEqualTo("테스트글 1");
    }

    @Test
    void findAll() {
        List<PostListDto> list = postRepository.findAll(1);
        int size = list.size();
        Assertions.assertThat(size).isEqualTo(100);
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

    @Test
    void findRange() {
        List<PostListDto> list = postRepository.findRange(1, 1, 10);
        int size = list.size();
        PostListDto postStart = list.get(0);
        PostListDto postEnd = list.get(9);
        Assertions.assertThat(size).isEqualTo(10);
        Assertions.assertThat(postStart.getTitle()).isEqualTo("테스트글 100");
        Assertions.assertThat(postEnd.getTitle()).isEqualTo("테스트글 91");
    }
}