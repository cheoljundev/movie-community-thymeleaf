package hello.moviecomm.repository;

import hello.moviecomm.domain.member.AuthorityMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Test
    void findByMemberId() {
        AuthorityMember user01 = memberRepository.findByMemberId("admin01");
        assertThat(user01.getAuths().size()).isEqualTo(2);
    }
}