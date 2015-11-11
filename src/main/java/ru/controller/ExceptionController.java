package ru.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by vitaly on 12/03/15.
 */
@Component
@Controller
public class ExceptionController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    // TODO: find proper way of redirecting on Angularjs routeprovider and Server, then return errorTemplate page
    @RequestMapping(value = {ERROR_PATH})
    public String errorHandler() {
//        return "templates/errorTemplate.html";
        return "templates/main/homeTemplate.html";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}