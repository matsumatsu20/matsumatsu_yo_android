package com.mikazuki.android.matsumatsuyo.retrofit.repository;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mikazuki.android.matsumatsuyo.domain.entity.User;
import com.mikazuki.android.matsumatsuyo.domain.repository.BaseCallback;
import com.mikazuki.android.matsumatsuyo.domain.repository.UserRepository;
import com.mikazuki.android.matsumatsuyo.retrofit.ApiUtil;
import com.mikazuki.android.matsumatsuyo.retrofit.RetrofitUserService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by matsuMac on 2015/09/12.
 */
public class RetrofitUserRepository implements UserRepository {

    final private String TAG = "RetrofitUserRepository";

    private Context mContext = null;
    RetrofitUserService mAPI;

    public RetrofitUserRepository(Context context) {
        this.mContext = context;
        buildAPI();
    }

    private void buildAPI() {
        Gson GSON = new GsonBuilder()
                .create();

        RestAdapter REST_ADAPTER = new RestAdapter.Builder()
                .setEndpoint(ApiUtil.API_URL)
                .setConverter(new GsonConverter(GSON))
//                .setRequestInterceptor(new BaseRequestInterceptor(mContext))
                .build();
        mAPI = REST_ADAPTER.create(RetrofitUserService.class);
    }

    @Override
    public void createUser(String name, String token, String password, final BaseCallback<User> cb) {

        mAPI.createUser(name, token, password, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Log.d(TAG, "create!!");
                cb.onResponse(user);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
                cb.onResponse(null);
            }
        });

    }

    @Override
    public void updateToken(int id, String token, final BaseCallback<User> cb) {
        mAPI.updateToken(id, token, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Log.d(TAG, "update!!");
                cb.onResponse(user);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
                cb.onResponse(null);
            }
        });
    }

    @Override
    public void login(String name, String token, String password, final BaseCallback<User> cb) {

        mAPI.createUser(name, token, password, new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Log.d(TAG, "create!!");
                cb.onResponse(user);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
                cb.onResponse(null);
            }
        });

    }

}
