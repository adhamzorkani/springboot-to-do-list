package com.springboot.todolist.DTO;



import com.springboot.todolist.models.MyUserDetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserDTO {

    private String token;
    private String userName;
    private long id;
    private boolean active;
    private Collection<? extends GrantedAuthority> auth;

    public UserDTO() {
    }

    public UserDTO(MyUserDetails user, String token) {
        this.token = token;
        this.userName = user.getUsername();
        this.id = user.getId();
        this.active = user.isActive();;
        this.auth =  user.getAuthorities();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Collection<? extends GrantedAuthority> getAuth() {
        return auth;
    }

    public void setAuth(Collection<? extends GrantedAuthority> auth) {
        this.auth = auth;
    }

    

}
