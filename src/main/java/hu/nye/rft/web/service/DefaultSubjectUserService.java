package hu.nye.rft.web.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import hu.nye.rft.data.dao.SubjectUserDataObjectAccessInterface;
import hu.nye.rft.data.domain.SubjectUserEntity;
import hu.nye.rft.web.domain.CreateSubjectUserRequest;
import hu.nye.rft.web.domain.SubjectUserView;
import hu.nye.rft.web.transformer.SubjectUserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultSubjectUserService implements SubjectUserServiceInterface{

    private SubjectUserDataObjectAccessInterface subjectUserDataAccessObject;
    private SubjectUserTransformer transformer;

    @Autowired
    public DefaultSubjectUserService(SubjectUserDataObjectAccessInterface subjectUserDataAccessObject, SubjectUserTransformer transformer) {
        this.subjectUserDataAccessObject = subjectUserDataAccessObject;
        this.transformer = transformer;
    }

    @Override
    public SubjectUserView getSubjectUserById(Long id) {
        SubjectUserEntity subjectUserEntity = subjectUserDataAccessObject.getSubjectUserById(id);
        return transformer.transformToView(subjectUserEntity);
    }

    @Override
    public List<SubjectUserView> getAllSubjectUser() {
        Collection<SubjectUserEntity> subjectUserEntities  = subjectUserDataAccessObject.getAllSubjectUser();
        List<SubjectUserView> users = transformer.transformToView(subjectUserEntities);
        return users.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public void addSubjectUser(CreateSubjectUserRequest request) {
        SubjectUserEntity subjectUserEntity = transformer.transform(request);
        subjectUserDataAccessObject.addSubjectUser(subjectUserEntity);
    }

    @Override
    public void deleteSubjectUserById(Long id) {
        subjectUserDataAccessObject.deleteSubjectUserById(id);
    }
}
