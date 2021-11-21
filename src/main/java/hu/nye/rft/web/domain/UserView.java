package hu.nye.rft.web.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Web layer representation of a user.
 */
@Data
@Builder
public class UserView implements Comparable<UserView>{

    private Long id;
    private String userName;
    private String emailAddress;
    private String password;

    @Override
    public int compareTo(UserView o) {
        return userName.compareTo(o.userName);
    }
}
