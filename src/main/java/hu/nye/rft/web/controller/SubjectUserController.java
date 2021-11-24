package hu.nye.rft.web.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import hu.nye.rft.web.domain.CreateSubjectRequest;
import hu.nye.rft.web.domain.CreateSubjectUserRequest;
import hu.nye.rft.web.domain.SubjectEntryView;
import hu.nye.rft.web.domain.SubjectUserView;
import hu.nye.rft.web.service.SubjectUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectUserController {

    private static final String GET_SUBJECT_USER_MAPPING = "/subjectuser/{id}";
    private static final String GET_ALL_SUBJECT_USER_MAPPING = "/subjectuser/subjectusers";
    private static final String ADD_SUBJECT_USER = "/subjectuser/add";
    private static final String DELETE_SUBJECT_USER_MAPPING = "/subjectuser/delete/{id}";

    private SubjectUserServiceInterface subjectUserService;

    @Autowired
    public SubjectUserController(SubjectUserServiceInterface subjectUserService) {
        this.subjectUserService = subjectUserService;
    }

    @GetMapping(path = GET_SUBJECT_USER_MAPPING)
    public SubjectUserView getSubjectUser(@PathVariable @NotNull Long id){
        return subjectUserService.getSubjectUserById(id);
    }

    @GetMapping(path = GET_ALL_SUBJECT_USER_MAPPING)
    public List<SubjectUserView> getAllSubjectUser(){
        return subjectUserService.getAllSubjectUser();
    }

    @PostMapping(path = ADD_SUBJECT_USER)
    @ResponseStatus(HttpStatus.CREATED)
    public void addSubjectUser(@Valid @RequestBody CreateSubjectUserRequest request){
        subjectUserService.addSubjectUser(request);
    }

    @DeleteMapping(DELETE_SUBJECT_USER_MAPPING)
    public void deleteSubjectUser(@PathVariable @NotNull Long id){
        subjectUserService.deleteSubjectUserById(id);
    }
}
