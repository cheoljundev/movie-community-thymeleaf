package hello.moviecomm.repository;

import hello.moviecomm.domain.member.AuthorityMember;
import hello.moviecomm.domain.member.Member;

import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    AuthorityMember findById(String memberId);
    List<AuthorityMember> findAll();
}
