package com.selfapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.selfapp.R;
import com.selfapp.activity.base.BaseAppCompatActivity;
import com.selfapp.util.AppShortCutUtil;

public class HomeActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
