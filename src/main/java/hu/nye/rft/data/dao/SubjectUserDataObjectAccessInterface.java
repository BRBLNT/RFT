package hu.nye.rft.data.dao;


import java.util.Collection;

import hu.nye.rft.data.domain.SubjectUserEntity;
import hu.nye.rft.data.domain.UserEntity;

/**
 * DAO interface to access to {@link SubjectUserEntity}.
 */
public interface SubjectUserDataObjectAccessInterface {

    SubjectUserEntity getSubjectUserById(Long userId);

    Collection<SubjectUserEntity> getAllSubjectUser();

    void addSubjectUser(SubjectUserEntity entity);

    void deleteSubjectUserById(Long id);

}
