package ru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.db.entities.User;
import ru.service.UserService;

/**
 * Created by vitaly on 11/04/15.
 */
@Component
@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/registerUser" },
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User createUser(@RequestParam("name") String name,
                           @RequestParam("password") String password) {
        User user = userService.save(name, password);
        return user;
    }
}
