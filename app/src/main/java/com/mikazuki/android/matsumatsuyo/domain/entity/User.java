package com.mikazuki.android.matsumatsuyo.domain.entity;

/**
 * Created by matsuMac on 2015/09/12.
 */
public class User {

    public void setToken(String token) {
        this.token = token;
    }

    public String getGcm_id() {
        return gcm_id;
    }

    public void setGcm_id(String gcm_id) {
        this.gcm_id = gcm_id;
    }

    private int id;
    private String name;
    private String password;
    private String token;
    private String gcm_id;

    public User(int id, String name, String password, String token, String gcm_id) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.token = token;
        this.gcm_id = gcm_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

}
