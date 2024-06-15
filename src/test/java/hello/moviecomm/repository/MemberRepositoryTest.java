package hello.moviecomm.repository;

import hello.moviecomm.domain.member.AuthorityMember;
import hello.moviecomm.domain.member.Member;
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
        Member member = Member.builder()
                .memberId("user01")
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
                .memberId("user01")
                .name("김철수")
                .password("1234")
                .build();
        memberRepository.save(member);
        AuthorityMember user02 = memberRepository.findById("user01");
        assertThat(user02.getName()).isEqualTo("김철수");
    }

    @Test
    void findAll() {
        Member member1 = Member.builder()
                .memberId("user02")
                .name("김철수")
                .password("1234")
                .build();
        Member member2 = Member.builder()
                .memberId("user03")
                .name("김영희")
                .password("1234")
                .build();
        memberRepository.save(member1);
        memberRepository.save(member2);
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }
}