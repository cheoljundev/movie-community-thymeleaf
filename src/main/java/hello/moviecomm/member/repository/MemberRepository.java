package hello.moviecomm.member.repository;

import hello.moviecomm.member.domain.Member;
import hello.moviecomm.member.dto.MemberDto;

import java.util.List;

public interface MemberRepository {
    MemberDto save(MemberDto memberDto);
    Member findById(String memberId);
    Member findByNo(Integer memberNo);
    List<Member> findAll();
}
