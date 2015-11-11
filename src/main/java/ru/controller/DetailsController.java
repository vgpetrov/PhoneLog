package ru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.db.entities.CallHistory;
import ru.service.CallHistoryService;

/**
 * Created by vitaly on 04/11/15.
 */
@Component
@Controller
public class DetailsController {

    @Autowired
    @Qualifier("callHistoryService")
    CallHistoryService callHistoryService;

    @RequestMapping(value = {"/web/details"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CallHistory details(@RequestParam("id") Long id) {
        return callHistoryService.getById(id);
    }
}
