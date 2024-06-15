package hello.moviecomm.repository;

import hello.moviecomm.domain.authority.Authority;
import hello.moviecomm.domain.member.Member;
import hello.moviecomm.domain.role.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MemberMapper {
    void save(Member member);

    Member findMemberById(String memberId);

    List<Member> findMemberAll();

}
