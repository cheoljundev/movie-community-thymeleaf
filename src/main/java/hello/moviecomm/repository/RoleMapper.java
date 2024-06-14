package hello.moviecomm.repository;

import hello.moviecomm.domain.role.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    List<Role> findByMemberNo(Integer memberNo);;
}
