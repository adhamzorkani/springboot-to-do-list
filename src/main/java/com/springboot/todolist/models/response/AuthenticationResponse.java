package com.springboot.todolist.models.response;

import java.io.Serializable;
import java.util.List;

public class AuthenticationResponse implements Serializable {

    private Long id;
    private String username;
    private boolean active;
    private List<String> roles;

    public AuthenticationResponse(Long id, String username, boolean active, List<String> roles) {
        this.id = id;
        this.username = username;
        this.active = active;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}