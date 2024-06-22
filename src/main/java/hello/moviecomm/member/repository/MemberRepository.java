package hello.moviecomm.member.repository;

import hello.moviecomm.member.dto.MemberDto;
import hello.moviecomm.member.domain.Member;

import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    MemberDto findById(String memberId);
    MemberDto findByNo(Integer memberNo);
    List<MemberDto> findAll();
}
