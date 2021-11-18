package hu.nye.rft.web.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Web layer representation of a user.
 */
@Data
@Builder
public class User {
    private Long id;
    private String userName;
    private String emailAddress;
    private boolean role;
    private SubjectList subjectList;
}
