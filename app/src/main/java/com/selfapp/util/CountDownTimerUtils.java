package com.selfapp.util;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;


/**
 * ============================================================
 * <p/>
 * 版 权 ： Inspire Team 版权所有 (c) 2018
 * <p/>
 * 作 者 ：  wangganginspire@gmail.com
 * <p/>
 * 版 本 ： 1.0
 * <p/>
 *
 * <p/>
 * 创建日期 ： 2018/3/23
 * <p/>
 * 描 述 ：CountDownTimerUtils.java
 * <p/>
 * 包名 ：com.yyxme.obrao.pay.util
 * <p/>
 * 修订历史 ：实现手机获取验证码倒计时功能
 * <p/>
 * ============================================================
 **/
public class CountDownTimerUtils extends CountDownTimer {


    private TextView mTextView;


    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public CountDownTimerUtils(long millisInFuture, long countDownInterval, TextView textView) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
    }


    //点击后操作
    @Override
    public void onTick(long millisUntilFinished) {

        mTextView.setClickable(false); //设置不可点击 s
        mTextView.setText(millisUntilFinished / 1000 + "s");  //设置倒计时时间
//        mTextView.setBackgroundResource(R.drawable.bg_identify_code_press); //设置按钮为灰色，这时是不能点击的

        SpannableString spannableString = new SpannableString(mTextView.getText().toString());  //获取按钮上的文字
        ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);

        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//将倒计时的时间设置为红色
        mTextView.setText(spannableString);

    }

    //倒计时完成后 操作
    @Override
    public void onFinish() {

        mTextView.setText("获取验证码");
        mTextView.setClickable(true);//重新获得点击
//        mTextView.setBackgroundResource(R.drawable.bg_identify_code_normal);  //还原背景色

    }
}
