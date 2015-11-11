package ru.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by vitaly on 12/03/15.
 */
@Component
@Controller
public class HomeController {

    @RequestMapping(value = {"/" })
    public String home() {
        SecurityContextHolder.getContext().getAuthentication();
        return "templates/main/homeTemplate.html";
    }
}
