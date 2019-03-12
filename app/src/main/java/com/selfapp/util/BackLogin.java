package com.selfapp.util;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

import com.selfapp.activity.HomeActivity;

public class BackLogin extends CountDownTimer {

    private Context context;

    /**
     * 两个参数（均已毫秒为单位）
     * millisInFuture 倒计的总时间
     * countDownInterval 倒计的时间间隔
     */
    public BackLogin(long millisInFuture, long countDownInterval, Context context) {
        super(millisInFuture, countDownInterval);
        this.context = context;
    }


    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {
        context.startActivity(new Intent(context, HomeActivity.class));
    }


}
