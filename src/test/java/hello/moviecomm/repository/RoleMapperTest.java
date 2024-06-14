package hello.moviecomm.repository;

import hello.moviecomm.domain.role.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleMapperTest {
    @Autowired
    RoleMapper roleMapper;
    @Test
    void findByMemberNo() {
        List<Role> roles = roleMapper.findByMemberNo(2);
        assertThat(roles.size()).isEqualTo(2);
    }
}