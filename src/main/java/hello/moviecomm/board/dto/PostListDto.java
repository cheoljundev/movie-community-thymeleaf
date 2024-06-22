package hello.moviecomm.board.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class PostListDto {
    private final Integer postNo;
    private final String title;
    private final String memberName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final Date createAt;
}
