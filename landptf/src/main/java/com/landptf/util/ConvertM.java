package com.landptf.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by liyulong on 2016/10/31.
 * 转换工具类
 */
public class ConvertM {
    /**
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
