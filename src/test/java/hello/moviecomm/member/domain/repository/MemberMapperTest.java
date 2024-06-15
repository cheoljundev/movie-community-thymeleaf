package hello.moviecomm.member.domain.repository;

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
        MemberDto memberDto = MemberDto.builder()
                .memberId("user01")
                .name("김철수")
                .password("1234")
                .build();
        memberMapper.save(memberDto);
        MemberDto findMemberDto = memberMapper.findById("user01");
        assertThat(findMemberDto.getMemberId()).isEqualTo(memberDto.getMemberId());
    }

    @Test
    void save() {
        MemberDto memberDto = MemberDto.builder()
                .memberId("user01")
                .name("김철수")
                .password("1234")
                .build();
        memberMapper.save(memberDto);
        assertThat(memberMapper.findById("user01").getName()).isEqualTo("김철수");
    }

    @Test
    void saveRole() {
        MemberDto memberDto = MemberDto.builder()
                .memberId("user01")
                .name("김철수")
                .password("1234")
                .build();
        memberMapper.save(memberDto);
        Integer memberNo = memberMapper.findById("user01").getMemberNo();
        memberMapper.saveRole(memberNo, 1);
    }

    @Test
    void findAll() {
        MemberDto memberDto1 = MemberDto.builder()
                .memberId("user02")
                .name("김철수")
                .password("1234")
                .build();

        MemberDto memberDto2 = MemberDto.builder()
                .memberId("user03")
                .name("김영희")
                .password("1234")
                .build();

        MemberDto memberDto3 = MemberDto.builder()
                .memberId("user04")
                .name("김영수")
                .password("1234")
                .build();

        memberMapper.save(memberDto1);
        memberMapper.save(memberDto2);
        memberMapper.save(memberDto3);
        List<MemberDto> members = memberMapper.findAll();
        assertThat(members.size()).isEqualTo(3);
    }

}