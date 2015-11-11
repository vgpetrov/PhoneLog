package ru.models.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by vitaly on 01/11/15.
 */
public class UserModel extends User {

    private ru.db.entities.User user;

    public UserModel(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
                     boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, ru.db.entities.User user) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }

    public ru.db.entities.User getUser() {
        return user;
    }
}
