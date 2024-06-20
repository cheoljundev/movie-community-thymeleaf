package hello.moviecomm.board.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
public class WritePostDto {
    private Integer postNo;
    private String title;
    private String content;
    private Date createAt;
    private MultipartFile file;
    private Integer memberNo;
    private Integer boardNo;
}
