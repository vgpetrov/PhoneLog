package ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.db.dao.CallHistoryDAO;
import ru.db.dao.UserDAO;

/**
 * Created by vitaly on 15/03/15.
 */
public abstract class BaseService {

    @Autowired
    protected UserDAO userDao;

    @Autowired
    protected CallHistoryDAO callDao;

    public UserDAO getUserDao() {
        return userDao;
    }

    public CallHistoryDAO getCallDao() {
        return callDao;
    }
}
