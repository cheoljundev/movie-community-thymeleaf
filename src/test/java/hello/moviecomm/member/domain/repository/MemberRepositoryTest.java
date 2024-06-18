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
        MemberDto member = MemberDto.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build();
        MemberDto savedMember = memberRepository.save(member);
        System.out.println("savedMember = " + savedMember);
        assertThat(savedMember.getName()).isEqualTo("김철수");
    }

    @Test
    void findById() {
        MemberDto member = MemberDto.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build();
        memberRepository.save(member);
        Member findMember = memberRepository.findById("user");
        assertThat(findMember.getName()).isEqualTo("김철수");
    }

    @Test
    void findAll() {
        MemberDto member2 = MemberDto.builder()
                .memberId("user02")
                .name("김철수")
                .password("1234")
                .build();
        MemberDto member3 = MemberDto.builder()
                .memberId("user03")
                .name("김영희")
                .password("1234")
                .build();
        memberRepository.save(member2);
        memberRepository.save(member3);
        assertThat(memberRepository.findAll().size()).isEqualTo(4); // 테스트 데이터 2개 + 기존 데이터 2개
    }
}