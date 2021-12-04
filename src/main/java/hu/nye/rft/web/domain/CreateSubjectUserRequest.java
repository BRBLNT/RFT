package hu.nye.rft.web.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateSubjectUserRequest {

    private String userId;
    private String subjectId;
}
