package hello.moviecomm.member.repository;

import hello.moviecomm.member.domain.Authority;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorityMapper {
    Authority findByAuthorityCode(Integer authorityCode);
}
