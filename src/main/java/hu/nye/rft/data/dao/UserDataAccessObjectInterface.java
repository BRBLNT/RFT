package hu.nye.rft.data.dao;

import java.util.Collection;

import hu.nye.rft.data.domain.UserEntity;

/**
 * DAO interface to access to {@link UserEntity}.
 */
public interface UserDataAccessObjectInterface {

    UserEntity getUserById(Long userId);

    Collection<UserEntity> getAllUser();

    void addUser(UserEntity entity);

    void deleteUserById(Long id);
}
