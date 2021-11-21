package hu.nye.rft.data.dao;

import java.util.Collection;

import hu.nye.rft.data.domain.SubjectUserEntity;
import hu.nye.rft.data.repository.SubjectUserRepository;
import hu.nye.rft.error.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

public class DefaultSubjectUserDataAccessObject implements SubjectUserDataObjectAccessInterface{

    private SubjectUserRepository userRepository;

    @Autowired
    public DefaultSubjectUserDataAccessObject(SubjectUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public SubjectUserEntity getSubjectUserById(Long subjectUserId) {
        return userRepository.findById(subjectUserId).orElseThrow(() -> new NotFoundException("SubjectUser not found with id: " + subjectUserId));
    }

    @Override
    public Collection<SubjectUserEntity> getAllSubjectUser() {
        return userRepository.findAll();
    }

    @Override
    public void addSubjectUser(SubjectUserEntity entity) {
        try{
            userRepository.save(entity);
        }catch (DataIntegrityViolationException e){

        }catch (Exception e){
            throw new RuntimeException("Opps, something went wrong.");
        }
    }

    @Override
    public void deleteSubjectUserById(Long id) {
        try{
            userRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new NotFoundException("SubjectUser not found with id: " + id);
        }catch (Exception e){
            throw new RuntimeException("Opps, something went wrong.");
        }
    }
}
