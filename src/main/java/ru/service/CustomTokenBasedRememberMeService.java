package ru.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import ru.db.dao.UserDAO;
import ru.db.entities.User;
import ru.models.security.UserModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vitaly on 30/10/15.
 */
public class CustomTokenBasedRememberMeService extends TokenBasedRememberMeServices {

    private final String HEADER_SECURITY_TOKEN = "x-auth-token";

    @Autowired
    private UserDAO userDao;

    public CustomTokenBasedRememberMeService(String key, UserDetailsService userDetailsService) {
        super(key, userDetailsService);
    }

    /**
     * Locates the Spring Security remember me token in the request and returns its value.
     *
     * @param request the submitted request which is to be authenticated
     * @return the value of the request header (which was originally provided by the cookie - API expects it in header)
     */
    @Override
    protected String extractRememberMeCookie(HttpServletRequest request) {
        String token = request.getHeader(HEADER_SECURITY_TOKEN);
        if ((token == null) || (token.length() == 0)) {
            return null;
        }

        return token;
    }

    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) {
        User user = userDao.findUserByToken(request.getHeader(HEADER_SECURITY_TOKEN));

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
