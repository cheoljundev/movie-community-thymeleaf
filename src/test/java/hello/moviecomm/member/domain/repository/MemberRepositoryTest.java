package hello.moviecomm.member.domain.repository;

import hello.moviecomm.member.domain.Member;
import hello.moviecomm.member.dto.MemberDto;
import hello.moviecomm.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void save() {
        MemberDto memberDto = MemberDto.builder()
                .memberId("user01")
                .name("김철수")
                .password("1234")
                .build();
        MemberDto savedMemberDto = memberRepository.save(memberDto);
        System.out.println("savedMember = " + savedMemberDto);
        assertThat(savedMemberDto.getName()).isEqualTo("김철수");
    }

    @Test
    void findById() {
        MemberDto memberDto = MemberDto.builder()
                .memberId("user01")
                .name("김철수")
                .password("1234")
                .build();
        memberRepository.save(memberDto);
        Member user02 = memberRepository.findById("user01");
        assertThat(user02.getName()).isEqualTo("김철수");
    }

    @Test
    void findAll() {
        MemberDto memberDto1 = MemberDto.builder()
                .memberId("user02")
                .name("김철수")
                .password("1234")
                .build();
        MemberDto memberDto2 = MemberDto.builder()
                .memberId("user03")
                .name("김영희")
                .password("1234")
                .build();
        memberRepository.save(memberDto1);
        memberRepository.save(memberDto2);
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }
}