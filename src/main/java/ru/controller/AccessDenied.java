package ru.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by vitaly on 02/03/15.
 */
@Component
@Controller
@Deprecated
public class AccessDenied {

    @RequestMapping(value = { "/accessDenied" })
    public String accessDenied() {
        return "templates/accessDenied.html";
    }

}
