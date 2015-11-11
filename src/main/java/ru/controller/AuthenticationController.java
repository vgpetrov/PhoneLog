package ru.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.models.security.AuthModel;

/**
 * Created by vitaly on 21/04/15.
 */
@Component
@Controller
public class AuthenticationController {

    @RequestMapping(value = {"/check/auth" }, method = RequestMethod.GET)
    @ResponseBody
    public AuthModel checkAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AuthModel result = new AuthModel(auth);
        return result;
    }
}
