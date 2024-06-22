package hello.moviecomm.board.service;

import hello.moviecomm.board.domain.Post;
import hello.moviecomm.board.dto.PostModifyDto;
import hello.moviecomm.board.dto.PostWriteDto;
import hello.moviecomm.board.exception.AccessDeniedException;
import hello.moviecomm.member.domain.Authority;
import hello.moviecomm.member.dto.MemberDto;
import hello.moviecomm.member.dto.CustomUserDetails;
import hello.moviecomm.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private MemberService memberService;

    @Test
    void save() throws IOException {
        PostWriteDto post = PostWriteDto.builder()
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
        MemberDto memberDto = MemberDto.builder()
                .memberNo(999)
                .memberId("test")
                .name("test")
                .password("test")
                .auths(roles)
                .build();
        CustomUserDetails customUserDetails = new CustomUserDetails(memberDto);

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
        MemberDto memberDto = MemberDto.builder()
                .memberNo(999)
                .memberId("test")
                .name("test")
                .password("test")
                .auths(roles)
                .build();
        CustomUserDetails customUserDetails = new CustomUserDetails(memberDto);

        // then
        Assertions.assertThatThrownBy(() -> postService.remove(1, customUserDetails))
                .isInstanceOf(AccessDeniedException.class);
    }

    @Test
    void modify_ok() throws IOException {
        // given
        MemberDto memberDto = memberService.findByNo(1);

        PostModifyDto postModifyDto = PostModifyDto.builder()
                .title("수정 제목")
                .content("수정 내용")
                .file(null)
                .build();

        CustomUserDetails customUserDetails = new CustomUserDetails(memberDto);

        // when
        postService.modify(postModifyDto, 1, customUserDetails);

        // then
        Post post = postService.findByNo(1);

        Assertions.assertThat(post.getTitle()).isEqualTo("수정 제목");
        Assertions.assertThat(post.getContent()).isEqualTo("수정 내용");
        Assertions.assertThat(post.getFileName()).isNull();
        Assertions.assertThat(post.getStoreFileName()).isNull();

    }

    @Test
    void modify_fail() {
        // given
        MemberDto memberDto = memberService.findByNo(2);

        PostModifyDto postModifyDto = PostModifyDto.builder()
                .title("수정 제목")
                .content("수정 내용")
                .file(null)
                .build();

        CustomUserDetails customUserDetails = new CustomUserDetails(memberDto);

        // then
        Assertions.assertThatThrownBy(() -> postService.modify(postModifyDto, 1, customUserDetails))
                .isInstanceOf(AccessDeniedException.class);
    }
}