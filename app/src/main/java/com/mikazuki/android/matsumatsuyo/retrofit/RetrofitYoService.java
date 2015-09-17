package com.mikazuki.android.matsumatsuyo.retrofit;

import com.mikazuki.android.matsumatsuyo.domain.entity.Message;
import com.mikazuki.android.matsumatsuyo.domain.entity.Yo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by matsuMac on 2015/09/12.
 */
public interface RetrofitYoService {
    final String USER_PATH = "/yo";

    @GET(USER_PATH)
    public void index(@Query("user_id") int id, Callback<List<String>> cb);

    @FormUrlEncoded
    @POST(USER_PATH)
    public void sendYo(@Field("user_id") int userId, @Field("opponent_name") String opponentName, Callback<Message> cb);
}
