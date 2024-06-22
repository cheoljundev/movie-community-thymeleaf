package hello.moviecomm.member.repository;

import hello.moviecomm.member.domain.Member;
import hello.moviecomm.member.domain.Role;
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
        Member member = Member.builder()
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