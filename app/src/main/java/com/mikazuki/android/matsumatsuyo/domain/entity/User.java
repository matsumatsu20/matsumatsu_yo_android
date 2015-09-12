package com.mikazuki.android.matsumatsuyo.domain.entity;

/**
 * Created by matsuMac on 2015/09/12.
 */
public class User {

    private int Id;
    private String Name;
    private String Password;
    private String Token;
    private String Gcm_id;

    public User(int id, String name, String password, String token, String gcm_id) {
        this.Id = id;
        this.Name = name;
        this.Password = password;
        this.Token = token;
        this.Gcm_id = gcm_id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public String getGcm_id() {
        return Gcm_id;
    }

    public void setGcm_id(String gcm_id) {
        Gcm_id = gcm_id;
    }
}
