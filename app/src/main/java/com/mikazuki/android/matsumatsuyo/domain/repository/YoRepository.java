package com.mikazuki.android.matsumatsuyo.domain.repository;

import com.mikazuki.android.matsumatsuyo.domain.entity.Message;
import com.mikazuki.android.matsumatsuyo.domain.entity.Yo;

import java.util.List;

/**
 * Created by matsuMac on 2015/09/12.
 */
public interface YoRepository {
    public void index(int id, BaseCallback<List<String>> cb);

    public void sendYo(int userId, String opponentName, BaseCallback<Message> cb);
}
