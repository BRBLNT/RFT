package hu.nye.rft.web.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import hu.nye.rft.data.dao.SubjectDataAccessObjectInterface;
import hu.nye.rft.data.domain.SubjectEntryEntity;
import hu.nye.rft.web.domain.CreateSubjectRequest;
import hu.nye.rft.web.domain.SubjectEntryView;
import hu.nye.rft.web.transformer.SubjectTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultSubjectEntryService implements SubjectServiceInterface {

    private SubjectDataAccessObjectInterface subjectDataAccessObject;
    private SubjectTransformer transformer;

    @Autowired
    public DefaultSubjectEntryService(SubjectDataAccessObjectInterface subjectDataAccessObject, SubjectTransformer transformer) {
        this.subjectDataAccessObject = subjectDataAccessObject;
        this.transformer = transformer;
    }

    @Override
    public SubjectEntryView getSubjectById(Long id) {
        SubjectEntryEntity subjectEntity = subjectDataAccessObject.getSubjectById(id);
        return transformer.transformToView(subjectEntity);
    }

    @Override
    public List<SubjectEntryView> getAllSubject() {
        Collection<SubjectEntryEntity> subjectEntities  = subjectDataAccessObject.getAllSubject();
        List<SubjectEntryView> users = transformer.transformToView(subjectEntities);
        return users.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void addSubject(CreateSubjectRequest request) {
        SubjectEntryEntity subjectEntity = transformer.transform(request);
        subjectDataAccessObject.addSubject(subjectEntity);
    }

    @Override
    public void deleteSubjectById(Long id) {
        subjectDataAccessObject.deleteSubjectById(id);
    }
}
