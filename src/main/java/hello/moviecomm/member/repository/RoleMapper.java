package hello.moviecomm.member.repository;

import hello.moviecomm.member.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<Role> findByMemberNo(Integer memberNo);;
}
