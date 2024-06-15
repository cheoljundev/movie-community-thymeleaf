package hello.moviecomm.repository;

import hello.moviecomm.domain.authority.Authority;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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