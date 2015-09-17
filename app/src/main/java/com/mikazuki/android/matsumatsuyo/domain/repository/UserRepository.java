package com.mikazuki.android.matsumatsuyo.domain.repository;

import com.mikazuki.android.matsumatsuyo.domain.entity.User;

/**
 * Created by matsuMac on 2015/09/12.
 */
public interface UserRepository {

    public void createUser(String name, String gcm_token, String password, BaseCallback<User> cb);

    public void updateToken(int id, String token, BaseCallback<User> cb);

    public void login(String name, String gcm_token, String password, BaseCallback<User> cb);

}
