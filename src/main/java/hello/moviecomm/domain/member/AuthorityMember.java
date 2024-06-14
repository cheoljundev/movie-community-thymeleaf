package hello.moviecomm.domain.member;

import hello.moviecomm.domain.authority.Authority;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class AuthorityMember {
    private final Integer  memberNo;
    private final String memberId;
    private final String name;
    private final String password;
    private final Date createAt;
    private final List<Authority> auths;
}
