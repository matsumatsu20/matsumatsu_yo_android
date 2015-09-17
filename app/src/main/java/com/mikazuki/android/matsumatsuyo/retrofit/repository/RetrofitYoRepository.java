package com.mikazuki.android.matsumatsuyo.retrofit.repository;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mikazuki.android.matsumatsuyo.domain.entity.Message;
import com.mikazuki.android.matsumatsuyo.domain.entity.Yo;
import com.mikazuki.android.matsumatsuyo.domain.repository.BaseCallback;
import com.mikazuki.android.matsumatsuyo.domain.repository.YoRepository;
import com.mikazuki.android.matsumatsuyo.retrofit.ApiUtil;
import com.mikazuki.android.matsumatsuyo.retrofit.RetrofitYoService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by matsuMac on 2015/09/12.
 */
public class RetrofitYoRepository implements YoRepository{

    final private String TAG = "RetrofitYoRepository";

    private Context mContext = null;
    RetrofitYoService mAPI;

    public RetrofitYoRepository(Context context) {
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
        mAPI = REST_ADAPTER.create(RetrofitYoService.class);
    }

    @Override
    public void index(int id, final BaseCallback<List<String>> cb) {

        mAPI.index(id, new Callback<List<String>>() {
            @Override
            public void success(List<String> yoList, Response response) {
                Log.d(TAG, "create!!");
                cb.onResponse(yoList);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
                cb.onResponse(null);
            }
        });

    }

    @Override
    public void sendYo(int userId, String opponentName, final BaseCallback<Message> cb) {
        mAPI.sendYo(userId, opponentName, new Callback<Message>() {
            @Override
            public void success(Message message, Response response) {
                Log.d(TAG, "create!!");
                cb.onResponse(message);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
                cb.onResponse(null);
            }
        });
    }

}

