package hello.moviecomm.board.repository;

import hello.moviecomm.board.dto.DbListPostDto;
import hello.moviecomm.board.dto.DbPostDto;
import hello.moviecomm.board.dto.ModifyPostDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostMapperTest {

    @Autowired PostMapper postMapper;

    @Test
    void save() {
        DbPostDto post = DbPostDto.builder()
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
        DbPostDto post = postMapper.findByNo(1);
        Assertions.assertThat(post.getTitle()).isEqualTo("테스트글");
    }

    @Test
    void findAll() {
        List<DbListPostDto> list = postMapper.findAll(1);
        DbListPostDto post = list.get(0);
        Assertions.assertThat(post.getTitle()).isEqualTo("테스트글");
    }

    @Test
    void remove() {
        postMapper.remove(1);
        DbPostDto post = postMapper.findByNo(1);
        Assertions.assertThat(post).isNull();
    }

    @Test
    void modify() {
        ModifyPostDto modifyPostDto = ModifyPostDto.builder()
                .title("수정된 제목")
                .content("수정된 내용")
                .fileName(null)
                .storeFileName(null)
                .build();
        postMapper.modify(modifyPostDto, 1);
        DbPostDto post = postMapper.findByNo(1);
        Assertions.assertThat(post.getTitle()).isEqualTo("수정된 제목");
        Assertions.assertThat(post.getContent()).isEqualTo("수정된 내용");
        Assertions.assertThat(post.getFileName()).isNull();
        Assertions.assertThat(post.getStoreFileName()).isNull();
    }
}