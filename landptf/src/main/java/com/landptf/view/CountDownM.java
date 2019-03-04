package com.landptf.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.landptf.R;
import com.landptf.util.ConvertM;

/**
 * Created by landptf on 2017/03/15.
 * 获取验证码倒计时控件
 */
public class CountDownM extends ButtonM {

    private OnClickListener onClickListener = null;
    private CountDownTimer countDownTimer;
    /**
     * 倒计时总时长
     */
    private int countDownSeconds = 60;
    /**
     * 按钮的背景色
     */
    private int backColor = 0;

    public interface OnClickListener{
        void onClick(View v);
    }

    public CountDownM(Context context) {
        this(context, null);
    }

    public CountDownM(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownM(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.countDownM, defStyle, 0);
        if (a != null) {
            //设置背景色
            ColorStateList colorList = a.getColorStateList(R.styleable.countDownM_backColor);
            if (colorList != null) {
                backColor = colorList.getColorForState(getDrawableState(), 0);
                if (backColor != 0) {
                    setBackColor(backColor);
                }
            } else {
                setBackColor(getResources().getColor(R.color.mainColor));
            }
            //获取倒计时总时长
            int countDownSeconds = a.getInteger(R.styleable.countDownM_countDownSeconds, 60);
            setCountDownSeconds(countDownSeconds);
            a.recycle();
        }
        //设置文字颜色
        setTextColor(getResources().getColor(android.R.color.white));
        //设置位置相关属性
        setGravity(Gravity.CENTER);
        setPadding(ConvertM.dp2px(context, 8), 0, ConvertM.dp2px(context, 8), 0);
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(v);
                }
                //点击后开始计时
                start();
            }
        });
    }

    /**
     * 设置倒计时总时长
     * @param countDownSeconds
     */
    public void setCountDownSeconds(int countDownSeconds){
        this.countDownSeconds = countDownSeconds;
    }

    /**
     * 设置背景色
     * @param backColor
     */
    @Override
    public void setBackColor(int backColor) {
        this.backColor = backColor;
        super.setBackColor(backColor);
    }

    /**
     * 停止倒计时
     */
    public void stop(){
        countDownTimer.cancel();
        resetButton();
    }

    public void setOnClickListener(OnClickListener l){
        this.onClickListener = l;
    }

    private void start(){
        setEnabled(false);//设置不能点击
        countDownTimer = new CountDownTimer(countDownSeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setText(millisUntilFinished / 1000 + getResources().getString(R.string.count_down_m_recapture_after));//设置倒计时时间
                //设置按钮为灰色，这时是不能点击的
                CountDownM.super.setBackColor(Color.GRAY);
                Spannable span = new SpannableString(getText().toString());//获取按钮的文字
                span.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//讲倒计时时间显示为红色
                setText(span);
            }

            @Override
            public void onFinish() {
                resetButton();
            }
        };
        countDownTimer.start();
    }

    private void resetButton(){
        setText(getResources().getString(R.string.count_down_m_recapture));
        setEnabled(true);//重新获得点击
        if (backColor != 0) {
            setBackColor(backColor);//还原背景色
        } else {
            setBackColor(getResources().getColor(R.color.mainColor));//还原背景色
        }
    }
}
