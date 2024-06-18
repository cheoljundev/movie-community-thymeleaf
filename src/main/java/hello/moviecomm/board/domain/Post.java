package hello.moviecomm.board.domain;

import hello.moviecomm.member.domain.Member;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Post {
    private final Integer postNo;
    private final String title;
    private final String content;
    private final Date createAt;
    private final String fileName;
    private final String StoreFileName;
    private final Member member;
    private final Board board;
}
