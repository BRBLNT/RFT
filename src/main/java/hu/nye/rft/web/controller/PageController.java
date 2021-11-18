package hu.nye.rft.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller that returns pages.
 */
@Controller
public class PageController {

    private static final String USER_PAGE_LOGIN = "/login";
    private static final String ROOT_MAPPING = "/";
    private static final String TEACHER_SIDE = "/teacherSide";
    private static final String STUDENT_SIDE = "/studentSide";
    private static final String SUBJECT_SIDE = "/subject/{id}";

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
