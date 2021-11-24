package hu.nye.rft.web.domain;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateSubjectUserRequest {

    @NotBlank(message = "User ID is mandatory")
    private Long userId;
    @NotBlank(message = "Subject ID is mandatory")
    private Long subjectId;
}
