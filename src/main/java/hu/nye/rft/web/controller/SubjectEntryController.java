package hu.nye.rft.web.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import hu.nye.rft.web.domain.CreateSubjectRequest;
import hu.nye.rft.web.domain.CreateUserRequest;
import hu.nye.rft.web.domain.SubjectEntryView;
import hu.nye.rft.web.domain.UserView;
import hu.nye.rft.web.service.SubjectServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectEntryController {

    private static final String GET_SUBJECT_MAPPING = "/subject/{id}";
    private static final String GET_ALL_SUBJECT_MAPPING = "/subject/subjects";
    private static final String ADD_SUBJECT = "/subject/add";
    private static final String DELETE_SUBJECT_MAPPING = "/subject/delete/{id}";

    private SubjectServiceInterface subjectService;

    @Autowired
    public SubjectEntryController(SubjectServiceInterface subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(path = GET_SUBJECT_MAPPING)
    public SubjectEntryView getSubject(@PathVariable @NotNull Long id){
        return subjectService.getSubjectById(id);
    }

    @GetMapping(path = GET_ALL_SUBJECT_MAPPING)
    public List<SubjectEntryView> getAllSubject(){
        return subjectService.getAllSubject();
    }

    @PostMapping(path = ADD_SUBJECT)
    @ResponseStatus(HttpStatus.CREATED)
    public void addSubject(@Valid @RequestBody CreateSubjectRequest request){
        subjectService.addSubject(request);
    }

    @DeleteMapping(DELETE_SUBJECT_MAPPING)
    public void deleteSubject(@PathVariable @NotNull Long id){
        subjectService.deleteSubjectById(id);
    }
}
