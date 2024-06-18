package hello.moviecomm.board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@Builder
@ToString
public class DbListPostDto {
    private final Integer postNo;
    private final String title;
    private final Date createAt;
    private final Integer memberNo;
}
