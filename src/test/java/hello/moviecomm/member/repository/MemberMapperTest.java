package hello.moviecomm.member.repository;

import hello.moviecomm.member.dto.MemberDto;
import hello.moviecomm.member.repository.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;
    @Test
    void findById() {
        MemberDto member = MemberDto.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build();
        memberMapper.save(member);
        MemberDto user = memberMapper.findById("user");
        assertThat(user.getName()).isEqualTo("김철수");
    }

    @Test
    void save() {
        MemberDto member = MemberDto.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build();
        memberMapper.save(member);
        assertThat(memberMapper.findById("user").getName()).isEqualTo("김철수");
    }

    @Test
    void saveRole() {
        MemberDto member = MemberDto.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build();
        memberMapper.save(member);
        Integer memberNo = memberMapper.findById("user").getMemberNo();
        memberMapper.saveRole(memberNo, 1);
    }

    @Test
    void findAll() {
        MemberDto member2 = MemberDto.builder()
                .memberId("user02")
                .name("김철수")
                .password("1234")
                .build();

        MemberDto member3 = MemberDto.builder()
                .memberId("user03")
                .name("김영희")
                .password("1234")
                .build();

        MemberDto member4 = MemberDto.builder()
                .memberId("user04")
                .name("김영수")
                .password("1234")
                .build();

        memberMapper.save(member2);
        memberMapper.save(member3);
        memberMapper.save(member4);
        List<MemberDto> members = memberMapper.findAll();
        assertThat(members.size()).isEqualTo(5); // 미리 세팅된 테스트 계정 2개 포함
    }

}