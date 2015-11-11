package ru.service;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Service;
import ru.db.entities.User;

/**
 * Created by vitaly on 13/04/15.
 */
@Service("userService")
public class UserService extends BaseService {

    public User save(@NotNull String name, @NotNull String password) {
        User user = new User(name, password);
        return getUserDao().save(user);
    }
}
