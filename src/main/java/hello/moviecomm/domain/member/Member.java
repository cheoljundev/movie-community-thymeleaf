package hello.moviecomm.domain.member;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class Member {
    private final Integer  memberNo;
    private final String memberId;
    private final String name;
    private final String password;
    private final Date createAt;
}
