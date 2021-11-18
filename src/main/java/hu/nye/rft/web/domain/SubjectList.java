package hu.nye.rft.web.domain;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;

/**
 * Web layer representation of a subject list.
 */
@Builder
@Data
public class SubjectList {
    private ArrayList<Subject> subjects;
}
