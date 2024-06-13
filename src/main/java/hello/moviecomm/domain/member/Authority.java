package hello.moviecomm.domain.member;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Authority {
    private final Long authorityCode;
    private final String name;
    private final String description;
}
