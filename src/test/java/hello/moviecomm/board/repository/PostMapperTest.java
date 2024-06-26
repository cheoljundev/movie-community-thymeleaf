package hello.moviecomm.board.repository;

import hello.moviecomm.board.domain.Post;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class PostMapperTest {

    @Autowired PostMapper postMapper;

    @Test
    void save() {
        Post post = Post.builder()
                .title("테스트글")
                .content("테스트내용")
                .boardNo(1)
                .fileName(null)
                .storeFileName(null)
                .memberNo(1)
                .boardNo(1)
                .build();
        postMapper.save(post);
    }

    @Test
    void findByNo() {
        Post post = postMapper.findByNo(1);
        Assertions.assertThat(post.getTitle()).isEqualTo("테스트글 1");
    }

    @Test
    void findAll() {
        List<Post> list = postMapper.findAll(1);
        int size = list.size();
        Assertions.assertThat(size).isEqualTo(200);
    }

    @Test
    void remove() {
        postMapper.remove(1);
        Post post = postMapper.findByNo(1);
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
        postMapper.modify(updatedPost, 1);
        Post post = postMapper.findByNo(1);
        Assertions.assertThat(post.getTitle()).isEqualTo("수정 제목");
        Assertions.assertThat(post.getContent()).isEqualTo("수정 내용");
        Assertions.assertThat(post.getFileName()).isNull();
        Assertions.assertThat(post.getStoreFileName()).isNull();
    }

    @Test
    void findRange() {
        List<Post> list = postMapper.findRange(1, 11, 20);
        int size = list.size(); // 0개, 실제로 모든 게시글은 100개임
        Post postStart = list.get(0);
        Post postEnd = list.get(9);
        Assertions.assertThat(size).isEqualTo(10);
        Assertions.assertThat(postStart.getTitle()).isEqualTo("테스트글 190");
        Assertions.assertThat(postEnd.getTitle()).isEqualTo("테스트글 181");
    }
}