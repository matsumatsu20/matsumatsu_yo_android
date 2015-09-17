package com.mikazuki.android.matsumatsuyo.domain.repository;

/**
 * Created by matsuMac on 2015/09/12.
 */
public interface SettingRepository {
    int getUserId();

    void setUserId(int id);

    int getOpponentId();

    void setOpponentId(int id);

    String getUserName();

    void setUserName(String name);

    String getOpponentName();

    void setOpponentName(String name);

    String getToken();

    void setToken(String token);

    String getGcmId();

    void setGcmId(String gcmId);

    boolean isRegistered();

    boolean isLinked();

    void clear();
}
