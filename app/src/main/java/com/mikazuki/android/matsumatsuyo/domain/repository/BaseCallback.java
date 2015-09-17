package com.mikazuki.android.matsumatsuyo.domain.repository;

/**
 * Created by matsuMac on 2015/09/12.
 */
public interface BaseCallback<T> {
    public void onResponse(T t);
}
