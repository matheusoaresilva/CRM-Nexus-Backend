package com.matheus.crm.dto;

import com.matheus.crm.entity.UserModel;

import java.io.Serializable;
import java.util.Set;

public class UserModelDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long id;
    public String name;
    public String username;
    public String password;
    public Set<String> roles;


    public UserModelDTO() {
    }

    public UserModelDTO(Long id, String name, String username, String password, Set<String> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public UserModelDTO(UserModel entity){
        this.id= entity.getId();
        this.name = entity.getName();
        this.username = entity.getUsername();
        this.password = entity.getPassword();
        this.roles = entity.getRoles();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
