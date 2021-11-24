package hu.nye.rft.web.service;

import java.util.List;

import hu.nye.rft.web.domain.CreateSubjectRequest;
import hu.nye.rft.web.domain.SubjectEntryView;


public interface SubjectServiceInterface  {

    SubjectEntryView getSubjectById(Long id);

    List<SubjectEntryView> getAllSubject();

    void addSubject(CreateSubjectRequest request);

    void deleteSubjectById(Long id);
}
