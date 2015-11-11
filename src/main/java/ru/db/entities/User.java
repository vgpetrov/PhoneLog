package ru.db.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;
import ru.utils.SecurityUtils;

import javax.persistence.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

/**
 * Created by vitaly on 23/02/15.
 */
@Entity
@DynamicUpdate
@Table(name = "users")
public class User extends BaseEntity {

    private Long id;
    private String username;
    private String password;
    private String token;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.setCreateDate(new Date());
        this.setUpdateDate(new Date());
        this.setToken(SecurityUtils.MD5(username + password));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_name")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "token")
    @JsonIgnore
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
