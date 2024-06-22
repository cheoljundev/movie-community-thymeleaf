package hello.moviecomm.member.repository;

import hello.moviecomm.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MemberMapper {
    void save(Member member);

    void saveRole(@Param("memberNo") Integer memberNo, @Param("authorityCode") Integer authorityCode);

    Member findById(String memberId);

    Member findByNo(Integer memberNo);

    List<Member> findAll();

}
