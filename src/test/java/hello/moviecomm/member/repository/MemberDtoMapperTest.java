package hello.moviecomm.member.repository;

import hello.moviecomm.member.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberDtoMapperTest {

    @Autowired
    private MemberMapper memberMapper;
    @Test
    void findById() {
        Member member = Member.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build();
        memberMapper.save(member);
        Member user = memberMapper.findById("user");
        assertThat(user.getName()).isEqualTo("김철수");
    }

    @Test
    void findByNo() {
        Member member = Member.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build();
        memberMapper.save(member);
        Integer memberNo = memberMapper.findById("user").getMemberNo();
        Member user = memberMapper.findByNo(memberNo);
        assertThat(user.getName()).isEqualTo("김철수");
    }

    @Test
    void save() {
        Member member = Member.builder()
                .memberId("user")
                .name("김철수")
                .password("1234")
                .build();
        memberMapper.save(member);
        assertThat(memberMapper.findById("user").getName()).isEqualTo("김철수");
    }

    @Test
    void saveRole() {
        Member member = Member.builder()
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
        Member member2 = Member.builder()
                .memberId("user02")
                .name("김철수")
                .password("1234")
                .build();

        Member member3 = Member.builder()
                .memberId("user03")
                .name("김영희")
                .password("1234")
                .build();

        Member member4 = Member.builder()
                .memberId("user04")
                .name("김영수")
                .password("1234")
                .build();

        memberMapper.save(member2);
        memberMapper.save(member3);
        memberMapper.save(member4);
        List<Member> members = memberMapper.findAll();
        assertThat(members.size()).isEqualTo(5); // 미리 세팅된 테스트 계정 2개 포함
    }

}