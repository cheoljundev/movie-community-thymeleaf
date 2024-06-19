package hello.moviecomm.member.service;

import hello.moviecomm.member.dto.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void save() {
        memberService.save(MemberDto.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build());
        Assertions.assertThat(memberService.findById("user").getName()).isEqualTo("김철수");
    }

    @Test
    void findById() {
        memberService.save(MemberDto.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build());
        Assertions.assertThat(memberService.findById("user").getName()).isEqualTo("김철수");
    }

    @Test
    void findByNo() {
        Assertions.assertThat(memberService.findByNo(1)).isNotNull();
    }

    @Test
    void findAll() {
        Assertions.assertThat(memberService.findAll()).isNotNull();
    }
}