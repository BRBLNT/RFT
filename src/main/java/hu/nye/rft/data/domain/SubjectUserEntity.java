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

    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private Long userId;

    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Long subjectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
