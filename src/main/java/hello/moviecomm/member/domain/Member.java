package hello.moviecomm.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Integer memberNo;
    private  String memberId;
    private  String name;
    private  String password;
    private  Date createAt;
}
