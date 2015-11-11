package ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.db.dao.UserDAO;
import ru.db.entities.User;
import ru.models.security.UserModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vitaly on 25/02/15.
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByName(username);

        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        authSet.add(new SimpleGrantedAuthority("ROLE_USER"));
        authSet.add(new SimpleGrantedAuthority("ROLE_ANON"));

        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(authSet);

        return buildUserForAuthentication(user, result);
    }

    private UserModel buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new UserModel(user.getUsername(), user.getPassword(), true, true, true, true, authorities, user);
    }

}
