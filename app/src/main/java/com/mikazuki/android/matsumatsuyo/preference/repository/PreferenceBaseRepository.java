package com.mikazuki.android.matsumatsuyo.preference.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by matsuMac on 2015/09/12.
 */
public class PreferenceBaseRepository {

    private Context mContext;
    protected SharedPreferences mSharedPreferences;

    public PreferenceBaseRepository(Context context) {
        mContext = context;
        this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    protected Context getmContext() {
        return mContext;
    }
}

