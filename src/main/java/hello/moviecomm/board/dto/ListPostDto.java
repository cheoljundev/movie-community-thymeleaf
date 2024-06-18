package hello.moviecomm.board.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ListPostDto {
    private final Integer postNo;
    private final String title;
    private final String content;
    private final String memberName;
    private final Date createAt;
}
