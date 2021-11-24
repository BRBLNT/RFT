package hu.nye.rft.web.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SubjectEntryView {

    private Long id;
    private String name;
    private Long teacherId;
    private String date;
    private String classroom;
}
