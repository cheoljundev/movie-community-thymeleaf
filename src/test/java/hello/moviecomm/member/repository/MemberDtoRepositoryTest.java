package hello.moviecomm.member.repository;

import hello.moviecomm.member.dto.MemberDto;
import hello.moviecomm.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MemberDtoRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void save() {
        Member member = Member.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build();
        Member savedMember = memberRepository.save(member);
        System.out.println("savedMember = " + savedMember);
        assertThat(savedMember.getName()).isEqualTo("김철수");
    }

    @Test
    void findById() {
        Member member = Member.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build();
        memberRepository.save(member);
        MemberDto findMemberDto = memberRepository.findById("user");
        assertThat(findMemberDto.getName()).isEqualTo("김철수");
    }

    @Test
    void findAll() {
        Member member2 = Member.builder()
                .memberId("user02")
                .name("김철수")
                .password("1234")
                .build();
        Member member3 = Member.builder()
                .memberId("user03")
                .name("김영희")
                .password("1234")
                .build();
        memberRepository.save(member2);
        memberRepository.save(member3);
        assertThat(memberRepository.findAll().size()).isEqualTo(4); // 테스트 데이터 2개 + 기존 데이터 2개
    }
}