package hu.nye.rft.web.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubjectUserView {

    private Long id;
    private Long userId;
    private Long subjectId;
}
