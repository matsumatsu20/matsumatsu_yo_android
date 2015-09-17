package com.mikazuki.android.matsumatsuyo.retrofit;

import com.mikazuki.android.matsumatsuyo.domain.entity.User;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by matsuMac on 2015/09/12.
 */
public interface RetrofitUserService {

    final String USER_PATH = "/users";
    final String USER_PATH_WITH_ID = "/users/{id}";
    final String SESSION_PATH = "/sessions";

    @FormUrlEncoded
    @POST(USER_PATH)
    void createUser(@Field("name") String name, @Field("gcm_id") String token, @Field("password") String android, Callback<User> cb);

    @PUT(USER_PATH_WITH_ID)
    public void updateToken(@Path("id") int id, @Field("gcm_id") String token, Callback<User> cb);

    @POST(SESSION_PATH)
    void login(@Field("name") String name, @Field("gcm_id") String token, @Field("password") String android, Callback<User> cb);

}
