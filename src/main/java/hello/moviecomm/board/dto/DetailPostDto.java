package hello.moviecomm.board.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class DetailPostDto {
    private final Integer postNo;
    private final String title;
    private final String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final Date createAt;
    private final String fileName;
    private final String storeFileName;
    private final String memberName;
}
