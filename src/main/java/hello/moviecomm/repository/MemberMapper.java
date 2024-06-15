package hello.moviecomm.repository;

import hello.moviecomm.domain.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MemberMapper {
    void save(Member member);

    void saveRole(@Param("memberNo") Integer memberNo, @Param("authorityCode") Integer authorityCode);

    Member findById(String memberId);

    List<Member> findAll();

}
