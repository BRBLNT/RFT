package hu.nye.rft.web.controller;

import hu.nye.rft.web.service.SubjectServiceInterface;
import hu.nye.rft.web.service.SubjectUserServiceInterface;
import hu.nye.rft.web.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller that returns pages.
 */
@Controller
public class PageController {


    private static final String ROOT_MAPPING = "/";
    private static final String USER_PAGE_LOGIN = "/login";
    private static final String TEACHER_SIDE = "/teacherSide";
    private static final String STUDENT_SIDE = "/studentSide";
    //private static final String SUBJECT_SIDE = "/{userId}/subject/{id}";


    private static final String USER_PAGE_MAPPING2 = "/users";
    private static final String USER_PAGE_MAPPING = "/userPage/{id}";
    private static final String USER_PAGE_NAME = "userPage";
    private static final String USER_LIST_PAGE_NAME = "userListPage";
    private static final String USERS_MODEL_KEY = "users";
    private static final String USER_MODEL_KEY = "user";

    private static final String SUBJECT_PAGE_MAPPING2 = "/subjects";
    private static final String SUBJECT_PAGE_MAPPING = "/subjectPage/{id}";
    private static final String SUBJECT_PAGE_NAME = "subjectPage";
    private static final String SUBJECT_LIST_PAGE_NAME = "subjectListPage";
    private static final String SUBJECTS_MODEL_KEY = "subjects";
    private static final String SUBJECT_MODEL_KEY = "subject";

    private static final String SUBJECT_USER_PAGE_MAPPING2 = "/subjectUsers";
    private static final String SUBJECT_USER_PAGE_MAPPING = "/subjectUserPage/{id}";
    private static final String SUBJECT_USER_PAGE_NAME = "subjectUserPage";
    private static final String SUBJECT_USER_LIST_PAGE_NAME = "subjectUserListPage";
    private static final String SUBJECT_USERS_MODEL_KEY = "subjectUsers";
    private static final String SUBJECT_USER_MODEL_KEY = "subjectUser";

    private UserServiceInterface userService;
    private SubjectServiceInterface subjectService;
    private SubjectUserServiceInterface subjectUserService;

    @Autowired
    public PageController(UserServiceInterface userService, SubjectServiceInterface subjectService, SubjectUserServiceInterface subjectUserService) {
        this.userService = userService;
        this.subjectService = subjectService;
        this.subjectUserService = subjectUserService;
    }

    @RequestMapping(USER_PAGE_LOGIN)
    public String login() {
        return "login";
    }

    @RequestMapping(TEACHER_SIDE)
    public String teacherSide() {
        return "teacherSide";
    }

    @RequestMapping(STUDENT_SIDE)
    public String studentSide() {
        return "studentSide";
    }

    @GetMapping(USER_PAGE_MAPPING2)
    public String userPage(Model model) {
        model.addAttribute(USERS_MODEL_KEY, userService.getAllUser());
        return USER_LIST_PAGE_NAME;
    }

    @GetMapping(SUBJECT_PAGE_MAPPING2)
    public String subjectPage(Model model) {
        model.addAttribute(SUBJECTS_MODEL_KEY, subjectService.getAllSubject());
        return SUBJECT_LIST_PAGE_NAME;
    }

    @GetMapping(SUBJECT_USER_PAGE_MAPPING2)
    public String subjectUserPage(Model model) {
        model.addAttribute(SUBJECT_USERS_MODEL_KEY, subjectUserService.getAllSubjectUser());
        return SUBJECT_USER_LIST_PAGE_NAME;
    }

}
