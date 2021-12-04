package hu.nye.rft.data.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a user-subject in the database.
 */
@Entity
@Table(name = "subject_user")
@NoArgsConstructor
@Data
public class SubjectUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String subjectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
}
