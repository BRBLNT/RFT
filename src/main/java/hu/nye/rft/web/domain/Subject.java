package hu.nye.rft.web.domain;

import lombok.Builder;
import lombok.Data;

/**
 * Web layer representation of a subject.
 */
@Builder
@Data
public class Subject {
    private String name;
    private int teacherId;
    private String date; // DDD#ST:ST#ET:ET  -> MON#11:00#12:00  Every monday from 11:00 to 12:00 24h form
    private String classroom; // BUILDING#NUMBEROFCLASSROOM ->  A#123
    private int maxParticipants;
    private int participants;

}
