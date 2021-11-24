package hu.nye.rft.data.domain;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a subject in the database.
 */
@Entity
@Table(name = "subject")
@NoArgsConstructor
@Data
public class SubjectEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "classroom", nullable = false)
    private String classroom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
