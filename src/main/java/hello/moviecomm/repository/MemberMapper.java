package hello.moviecomm.repository;

import hello.moviecomm.domain.member.Authority;
import hello.moviecomm.domain.member.Member;
import hello.moviecomm.domain.member.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MemberMapper {
    void save(Member member);

    Member findById(String memberId);

    List<Member> findAll();

    List<Role> findRolesByNo(Integer memberNo);

    Authority findAuthorityByCode(Integer authorityCode);

}
