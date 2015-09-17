package com.mikazuki.android.matsumatsuyo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.mikazuki.android.matsumatsuyo.domain.repository.SettingRepository;
import com.mikazuki.android.matsumatsuyo.preference.SharedPreferencesUtil;
import com.mikazuki.android.matsumatsuyo.preference.repository.PreferenceSettingRepository;
import com.mikazuki.android.matsumatsuyo.ui.gcm.RegistrationIntentService;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    Intent yoActivity;

    @Bind(R.id.registrationButton)
    Button registrationButton;

    @Bind(R.id.name)
    EditText name;

    @Bind(R.id.password)
    EditText password;

    @Bind(R.id.button2)
    Button button2;

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";

    private SettingRepository mSettingRepository;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettingRepository = new PreferenceSettingRepository(getApplicationContext());

        yoActivity = new Intent(this, YoActivity.class);


        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (mSettingRepository.isRegistered()) {
            startActivity(yoActivity);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    @OnClick(R.id.registrationButton)
    public void onClearPreference(View v) {

       onRegister(name.getText().toString(), password.getText().toString());
    }

    @OnClick(R.id.button2)
    public void onClickButton2(View v) {
        startActivity(yoActivity);
    }


    public void onRegister(String name, String password) {
        if (checkPlayServices()) {
            Intent i = new Intent(this, RegistrationIntentService.class);
            i.putExtra("NAME", name);
            i.putExtra("PASSWORD", password);
            startService(i);

            mRegistrationBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (mSettingRepository.isRegistered()) {
                        startActivity(yoActivity);
                    } else {
                    }
                }
            };
            String intentTag = SharedPreferencesUtil.REGISTRATION_COMPLETE;
            LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
            manager.registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(intentTag));
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

}
