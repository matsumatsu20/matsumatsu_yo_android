package com.mikazuki.android.matsumatsuyo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.mikazuki.android.matsumatsuyo.domain.entity.Message;
import com.mikazuki.android.matsumatsuyo.domain.entity.Yo;
import com.mikazuki.android.matsumatsuyo.domain.repository.BaseCallback;
import com.mikazuki.android.matsumatsuyo.domain.repository.SettingRepository;
import com.mikazuki.android.matsumatsuyo.domain.repository.YoRepository;
import com.mikazuki.android.matsumatsuyo.preference.repository.PreferenceSettingRepository;
import com.mikazuki.android.matsumatsuyo.retrofit.repository.RetrofitYoRepository;
import com.mikazuki.android.matsumatsuyo.ui.adapter.YoListAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YoActivity extends Activity {

    List<String> items;
    private YoRepository mYoRepository;
    private SettingRepository mSettingRepository;
    Context mContext = this;

    @Bind(R.id.yoEdit)
    EditText yoEdit;

    @Bind(R.id.yoButton)
    Button yoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yo);

        ButterKnife.bind(this);
        mYoRepository = new RetrofitYoRepository(getApplicationContext());
        mSettingRepository = new PreferenceSettingRepository(getApplicationContext());

        final ListView mListView = (ListView)this.findViewById(R.id.listView);

        System.out.println(mSettingRepository.getUserId());

        mYoRepository.index(mSettingRepository.getUserId(), new BaseCallback<List<String>>() {
            @Override
            public void onResponse(List<String> yoList) {
                for(String yo: yoList) {
                    System.out.println(yo);
                }

                items = yoList;
                YoListAdapter madapter = new YoListAdapter(mContext, items);
                mListView.setAdapter(madapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_yo, menu);
        return true;
    }

    @OnClick(R.id.yoButton)
    public void onClickYoButton(View v) {
        mYoRepository.sendYo(mSettingRepository.getUserId(), yoEdit.getText().toString(), new BaseCallback<Message>() {
            @Override
            public void onResponse(Message message) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
