package hello.moviecomm.board.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board {
    private final Integer boardNo;
    private final String name;
}
