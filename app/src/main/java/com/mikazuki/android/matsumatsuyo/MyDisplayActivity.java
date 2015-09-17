package com.mikazuki.android.matsumatsuyo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by matsuMac on 2015/09/13.
 */
public class MyDisplayActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        mTextView = (TextView) findViewById(R.id.unpo);

    }
}
