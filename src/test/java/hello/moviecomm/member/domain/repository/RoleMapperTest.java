package hello.moviecomm.member.domain.repository;

import hello.moviecomm.member.dto.MemberDto;
import hello.moviecomm.member.domain.Role;
import hello.moviecomm.member.repository.MemberRepository;
import hello.moviecomm.member.repository.RoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class RoleMapperTest {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    MemberRepository memberRepository;
    @Test
    void findByMemberNo() {
        MemberDto member = MemberDto.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build();
        memberRepository.save(member);
        Integer memberNo = memberRepository.findById("user").getMemberNo();
        List<Role> roles = roleMapper.findByMemberNo(memberNo);
        assertThat(roles.size()).isEqualTo(1);
    }
}