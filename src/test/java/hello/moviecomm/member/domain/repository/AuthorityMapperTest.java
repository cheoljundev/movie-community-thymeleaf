package hello.moviecomm.member.domain.repository;

import hello.moviecomm.member.domain.Authority;
import hello.moviecomm.member.repository.AuthorityMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthorityMapperTest {

    @Autowired
    AuthorityMapper authorityMapper;

    @Test
    void findByAuthorityCode() {
        Authority authority = authorityMapper.findByAuthorityCode(1);
        Assertions.assertThat(authority.getName()).isEqualTo("ROLE_MEMBER");
    }
}