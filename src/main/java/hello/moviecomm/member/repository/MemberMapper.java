package hello.moviecomm.member.repository;

import hello.moviecomm.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MemberMapper {
    void save(MemberDto memberDto);

    void saveRole(@Param("memberNo") Integer memberNo, @Param("authorityCode") Integer authorityCode);

    MemberDto findById(String memberId);

    MemberDto findByNo(Integer memberNo);

    List<MemberDto> findAll();

}
