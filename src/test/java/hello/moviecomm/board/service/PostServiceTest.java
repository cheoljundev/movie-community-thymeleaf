package hello.moviecomm.board.service;

import hello.moviecomm.board.dto.DbPostDto;
import hello.moviecomm.board.dto.WritePostDto;
import hello.moviecomm.board.exception.AccessDeniedException;
import hello.moviecomm.member.domain.Authority;
import hello.moviecomm.member.domain.Member;
import hello.moviecomm.member.dto.CustomUserDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    void remove_ok() {
        // given
        List<Authority> roles = new ArrayList<>();
        roles.add(
                Authority.builder()
                        .authorityCode(1)
                        .name("ROLE_USER")
                        .description("일반 사용자 권한")
                        .build()
        );
        roles.add(
                Authority.builder()
                        .authorityCode(2)
                        .name("ROLE_ADMIN")
                        .description("관리자 권한")
                        .build()
        );
        Member member = Member.builder()
                .memberNo(999)
                .memberId("test")
                .name("test")
                .password("test")
                .auths(roles)
                .build();
        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        // when
        postService.remove(1, customUserDetails);

        // then
        Assertions.assertThat(postService.findByNo(1)).isNull();
    }

    @Test
    void remove_fail() {
        // given
        List<Authority> roles = new ArrayList<>();
        roles.add(
                Authority.builder()
                        .authorityCode(1)
                        .name("ROLE_USER")
                        .description("일반 사용자 권한")
                        .build()
        );
        Member member = Member.builder()
                .memberNo(999)
                .memberId("test")
                .name("test")
                .password("test")
                .auths(roles)
                .build();
        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        // then
        Assertions.assertThatThrownBy(() -> postService.remove(1, customUserDetails))
                .isInstanceOf(AccessDeniedException.class);
    }
}