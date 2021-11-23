package hu.nye.rft.web.service;

import java.util.List;

import hu.nye.rft.web.domain.CreateUserRequest;
import hu.nye.rft.web.domain.UserView;

public interface UserServiceInterface {

    UserView getUserById(Long id);

    List<UserView> getAllUser();

    void addUser(CreateUserRequest request);

    void deleteUserById(Long id);
}
