package hello.moviecomm.board.domain;

import lombok.*;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer postNo;
    private String title;
    private String content;
    private Date createAt;
    private String fileName;
    private String storeFileName;
    private Integer memberNo;
    private Integer boardNo;
}
