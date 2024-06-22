package hello.moviecomm.board.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostModifyDto {
    private String title;
    private String content;
    private String fileName;
    private String storeFileName;
}
