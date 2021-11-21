package hu.nye.rft.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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


    private static final String SUBJECT_SIDE = "/{userId}/subject/{id}";
    private static final String USER_PAGE_MAPPING = "/userPage/{id}";
    private static final String USER_PAGE_NAME = "UserPage";
    private static final String USER_LIST_PAGE_NAME = "UserListPage";
    private static final String USERS_MODEL_KEY = "users";
    private static final String USER_MODEL_KEY = "user";

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

}
