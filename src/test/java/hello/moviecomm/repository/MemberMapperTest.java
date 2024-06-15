package hello.moviecomm.repository;

import hello.moviecomm.domain.member.Member;
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
        Member member = Member.builder()
                .memberId("user01")
                .name("김철수")
                .password("1234")
                .build();
        memberMapper.save(member);
        Member findMember = memberMapper.findById("user01");
        assertThat(findMember.getMemberId()).isEqualTo(member.getMemberId());
    }

    @Test
    void save() {
        Member member = Member.builder()
                .memberId("user01")
                .name("김철수")
                .password("1234")
                .build();
        memberMapper.save(member);
        assertThat(memberMapper.findById("user01").getName()).isEqualTo("김철수");
    }

    @Test
    void saveRole() {
        Member member = Member.builder()
                .memberId("user01")
                .name("김철수")
                .password("1234")
                .build();
        memberMapper.save(member);
        Integer memberNo = memberMapper.findById("user01").getMemberNo();
        memberMapper.saveRole(memberNo, 1);
    }

    @Test
    void findAll() {
        Member member1 = Member.builder()
                .memberId("user02")
                .name("김철수")
                .password("1234")
                .build();

        Member member2 = Member.builder()
                .memberId("user03")
                .name("김영희")
                .password("1234")
                .build();

        Member member3 = Member.builder()
                .memberId("user04")
                .name("김영수")
                .password("1234")
                .build();

        memberMapper.save(member1);
        memberMapper.save(member2);
        memberMapper.save(member3);
        List<Member> members = memberMapper.findAll();
        assertThat(members.size()).isEqualTo(3);
    }

}