package hu.nye.rft.data.dao;

import java.util.Collection;

import hu.nye.rft.data.domain.SubjectEntryEntity;

/**
 * DAO interface to access to {@link SubjectEntryEntity}.
 */
public interface SubjectDataAccessObjectInterface {

    SubjectEntryEntity getSubjectById(Long subjectId);

    Collection<SubjectEntryEntity> getAllSubject();

    void addSubject(SubjectEntryEntity entity);

    void deleteSubjectById(Long id);
}
