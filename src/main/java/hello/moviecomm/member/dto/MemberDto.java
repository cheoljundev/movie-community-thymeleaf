package hello.moviecomm.member.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MemberDto {
    private Integer memberNo;
    private  String memberId;
    private  String name;
    private  String password;
    private  Date createAt;
}
