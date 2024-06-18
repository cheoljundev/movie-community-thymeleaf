package hello.moviecomm.board.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class DetailPostDto {
    private final Integer postNo;
    private final String title;
    private final String content;
    private final Date createAt;
    private final String fileName;
    private final String StoreFileName;
    private final String memberName;
}
