package hu.nye.rft.data.dao;

import java.util.Collection;

import hu.nye.rft.data.domain.SubjectEntryEntity;
import hu.nye.rft.data.repository.SubjectRepository;
import hu.nye.rft.error.SubjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

@Component
public class DefaultSubjectDataAccessObject implements  SubjectDataAccessObjectInterface{

    private SubjectRepository userRepository;

    @Autowired
    public DefaultSubjectDataAccessObject(SubjectRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SubjectEntryEntity getSubjectById(Long subjectId) {
        return userRepository.findById(subjectId).orElseThrow(() -> new SubjectNotFoundException("Subject not found with id: " + subjectId));
    }

    @Override
    public Collection<SubjectEntryEntity> getAllSubject() {
        return userRepository.findAll();
    }

    @Override
    public void addSubject(SubjectEntryEntity entity) {
        try{
            userRepository.save(entity);
        }catch (DataIntegrityViolationException e){

        }catch (Exception e){
            throw new RuntimeException("Opps, something went wrong.");
        }
    }

    @Override
    public void deleteSubjectById(Long id) {
        try{
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new SubjectNotFoundException("Subject not found with id: " + id);
        }catch (Exception e){
            throw new RuntimeException("Opps, something went wrong.");
        }
    }
}
