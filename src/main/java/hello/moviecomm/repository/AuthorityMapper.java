package hello.moviecomm.repository;

import hello.moviecomm.domain.authority.Authority;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorityMapper {
    Authority findByAuthorityCode(Integer authorityCode);
}
