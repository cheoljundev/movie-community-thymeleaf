package hello.moviecomm.repository;

import hello.moviecomm.domain.authority.Authority;
import hello.moviecomm.domain.member.Member;
import hello.moviecomm.domain.role.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;
    @Test
    void findById() {
        Member member = memberMapper.findMemberById("user01");
        assertThat(member.getName()).isEqualTo("홍길동");
    }

    @Test
    void  findAuthorityByCode() {
        Authority authority = memberMapper.findAuthorityByCode(2);
        assertThat(authority.getName()).isEqualTo("ROLE_ADMIN");
    }

}