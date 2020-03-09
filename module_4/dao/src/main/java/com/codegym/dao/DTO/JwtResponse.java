package com.codegym.dao.DTO;

import java.io.Serializable;

public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private String username;
    private String rolename;

    public JwtResponse(String jwttoken, String username, String rolename) {
        this.jwttoken = jwttoken;
        this.username = username;
        this.rolename = rolename;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }


    public String getRolename() {
        return rolename;
    }
}
