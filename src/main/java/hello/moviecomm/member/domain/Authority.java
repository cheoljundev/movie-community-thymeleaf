package hello.moviecomm.member.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Authority {
    private final Integer authorityCode;
    private final String name;
    private final String description;
}
