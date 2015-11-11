package ru.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.db.entities.CallHistory;
import ru.models.security.UserModel;
import ru.service.CallHistoryService;

/**
 * Created by vitaly on 15/03/15.
 */
@Component
@RestController
public class CallHistoryEndpoint {

    @Autowired
    @Qualifier("callHistoryService")
    CallHistoryService callHistoryService;

    @RequestMapping(value = "/api/calls/create",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CallHistory create(@RequestParam(value="number") String number) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel principal = (UserModel) auth.getPrincipal();
        return callHistoryService.save(number, principal.getUser().getId());
    }
}
