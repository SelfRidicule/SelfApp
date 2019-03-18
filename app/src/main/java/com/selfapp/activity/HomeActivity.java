package com.selfapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.selfapp.R;
import com.selfapp.activity.base.BaseAppCompatActivity;
import com.selfapp.util.AppShortCutUtil;
import com.selfapp.util.SharedPreferencesUtil;

public class HomeActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String id = SharedPreferencesUtil.getStringPreference(getApplicationContext(), SharedPreferencesUtil.Self_ID,"");
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        SharedPreferencesUtil.setStringPreferences(getApplicationContext(),SharedPreferencesUtil.Self_ID,"1");

        String id1 = SharedPreferencesUtil.getStringPreference(getApplicationContext(), SharedPreferencesUtil.Self_ID,"");
        Toast.makeText(this, id1, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected String getColorTransparent() {
        return "";
    }

}
