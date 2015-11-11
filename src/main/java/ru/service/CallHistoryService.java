package ru.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.db.entities.CallHistory;
import ru.db.entities.User;

import java.util.List;

/**
 * Created by vitaly on 15/03/15.
 */
@Service("callHistoryService")
public class CallHistoryService extends BaseService {

    /**
     * Saves call
     *
     * @param number
     * @param userId
     *
     * @return {@link ru.db.entities.CallHistory}
     */
    public CallHistory save(@NotNull String number, @NotNull Long userId) {
        User user = getUserDao().findUserById(userId);
        CallHistory call = new CallHistory(number, user);
        CallHistory callHistory = getCallDao().create(call);
        return callHistory;
    }


    /**
     * List calls by user
     *
     * @param user
     *
     * @return {@link java.util.List<ru.db.entities.CallHistory>}
     */
    public List<CallHistory> list(@NotNull User user) {
        List<CallHistory> byUser = getCallDao().findByUser(user);
        return byUser;
    }

    /**
     * Finds by id
     *
     * @param id
     *
     * @return {@link ru.db.entities.CallHistory}
     */
    public CallHistory getById(@NotNull Long id) {
        return getCallDao().getById(id);
    }

}
