package hu.nye.rft.web.service;

import java.util.List;

import hu.nye.rft.web.domain.CreateSubjectUserRequest;
import hu.nye.rft.web.domain.SubjectUserView;


public interface SubjectUserServiceInterface {

    SubjectUserView getSubjectUserById(Long id);

    List<SubjectUserView> getAllSubjectUser();

    void addSubjectUser(CreateSubjectUserRequest request);

    void deleteSubjectUserById(Long id);
}
