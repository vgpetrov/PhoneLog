package ru.models.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by vitaly on 21/04/15.
 */
public class AuthModel {

    private String name;
    private boolean authenticated;

    public AuthModel(Authentication auth) {
        this.name = auth.getName();

        if (auth.getPrincipal() instanceof UserModel) {
            this.authenticated = true;
        } else {
            this.authenticated = false;
        }
    }

    public String getName() {
        return name;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
