package ru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.db.entities.CallHistory;
import ru.models.security.UserModel;
import ru.service.CallHistoryService;

import java.util.List;

/**
 * Created by vitaly on 01/11/15.
 */
@Component
@Controller
public class ListCallsController {

    @Autowired
    @Qualifier("callHistoryService")
    CallHistoryService callHistoryService;

    @RequestMapping(value = {"/web/list"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CallHistory> list() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel principal = (UserModel) auth.getPrincipal();
        List<CallHistory> list = callHistoryService.list(principal.getUser());
        return list;
    }
}
