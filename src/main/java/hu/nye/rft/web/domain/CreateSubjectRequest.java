package hu.nye.rft.web.domain;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

/**
 * Represents a CREATE request and contains necessary data about the subject.
 */
@Builder
@Data
public class CreateSubjectRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Teacher ID is mandatory")
    private Long teacherId;
    private String date;
    private String classroom;
}
