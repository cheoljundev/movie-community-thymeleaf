package hello.moviecomm.repository;

import hello.moviecomm.domain.member.Authority;
import hello.moviecomm.domain.member.Member;
import hello.moviecomm.domain.member.Role;
import hello.moviecomm.repository.MemberMapper;
import hello.moviecomm.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
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
        Member member = memberMapper.findById("user01");
        assertThat(member.getName()).isEqualTo("홍길동");
    }

    @Test
    void  findRolesByNo() {
        List<Role> roles = memberMapper.findRolesByNo(2);
        assertThat(roles.size()).isEqualTo(2);
    }

    @Test
    void  findAuthorityByCode() {
        Authority authority = memberMapper.findAuthorityByCode(2);
        assertThat(authority.getName()).isEqualTo("ROLE_ADMIN");
    }

}