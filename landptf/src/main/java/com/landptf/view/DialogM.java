package com.landptf.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;

import com.landptf.R;

/**
 * Created by landptf on 2017/02/24.
 * 自定义对话框
 */
public class DialogM extends Dialog{

    public static final int STYLE_ALERT         = 0;
    public static final int STYLE_TIP           = 1;


    public DialogM(Context context) {
        super(context);
    }

    public DialogM(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder{
        private Context mContext;
        private int style = STYLE_ALERT;
        //点击对话框外围是否可以取消
        private boolean cancel = true;
        //title
        private String title;
        private int titleBackColorResId;
        private int titleTextColorResId;
        //content
        private String content;
        private int contentBackColorResId;
        private int contentTextColorResId;
        //确认按钮
        private String alertButtonText;
        private int alertButtonBackColorResId;
        private int alertButtonBackColorPressResId;
        private int alertButtonTextColorResId;
        private int alertButtonTextColorPressResId;
        private OnClickListener onAlertClickListener;
        //确认按钮
        private String positiveButtonText;
        private int positiveButtonBackColorResId;
        private int positiveButtonBackColorPressResId;
        private int positiveButtonTextColorResId;
        private int positiveButtonTextColorPressResId;
        private OnClickListener onPositiveButtonClickListener;
        //取消按钮
        private String negativeButtonText;
        private int negativeButtonBackColorResId;
        private int negativeButtonBackColorPressResId;
        private int negativeButtonTextColorResId;
        private int negativeButtonTextColorPressResId;
        private OnClickListener onNegativeButtonClickListener;


        public Builder(Context context){
            mContext = context;
        }

        public Builder setStyle(int style){
            this.style = style;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean cancel){
            this.cancel = cancel;
            return this;
        }

        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        public Builder setTitleBackColor(int titleBackColorResId){
            this.titleBackColorResId = titleBackColorResId;
            return this;
        }

        public Builder setTitleTextColor(int titleTextColorResId){
            this.titleTextColorResId = titleTextColorResId;
            return this;
        }

        public Builder setContent(String content){
            this.content = content;
            return this;
        }

        public Builder setContentBackColor(int contentBackColorResId){
            this.contentBackColorResId = contentBackColorResId;
            return this;
        }

        public Builder setContentTextColor(int contentTextColorResId){
            this.contentTextColorResId = contentTextColorResId;
            return this;
        }

        public Builder setAlertButton(String alertButtonText, OnClickListener onAlertClickListener){
            this.alertButtonText = alertButtonText;
            this.onAlertClickListener = onAlertClickListener;
            return this;
        }

        public Builder setAlertButtonBackColor(int alertButtonBackColorResId){
            this.alertButtonBackColorResId = alertButtonBackColorResId;
            return this;
        }

        public Builder setAlertButtonBackColorPress(int alertButtonBackColorPressResId){
            this.alertButtonBackColorPressResId = alertButtonBackColorPressResId;
            return this;
        }

        public Builder setAlertButtonTextColor(int alertButtonTextColorResId){
            this.alertButtonTextColorResId = alertButtonTextColorResId;
            return this;
        }

        public Builder setAlertButtonTextColorPress(int alertButtonTextColorPressResId){
            this.alertButtonTextColorPressResId = alertButtonTextColorPressResId;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, OnClickListener onPositiveButtonClickListener){
            this.positiveButtonText = positiveButtonText;
            this.onPositiveButtonClickListener = onPositiveButtonClickListener;
            return this;
        }

        public Builder setPositiveButtonBackColor(int positiveButtonBackColorResId){
            this.positiveButtonBackColorResId = positiveButtonBackColorResId;
            return this;
        }

        public Builder setPositiveButtonBackColorPress(int positiveButtonBackColorPressResId){
            this.positiveButtonBackColorPressResId = positiveButtonBackColorPressResId;
            return this;
        }

        public Builder setPositiveButtonTextColor(int positiveButtonTextColorResId){
            this.positiveButtonTextColorResId = positiveButtonTextColorResId;
            return this;
        }

        public Builder setPositiveButtonTextColorPress(int positiveButtonTextColorPressResId){
            this.positiveButtonTextColorPressResId = positiveButtonTextColorPressResId;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, OnClickListener onNegativeButtonClickListener){
            this.negativeButtonText = negativeButtonText;
            this.onNegativeButtonClickListener = onNegativeButtonClickListener;
            return this;
        }

        public Builder setNegativeButtonBackColor(int negativeButtonBackColorResId){
            this.negativeButtonBackColorResId = negativeButtonBackColorResId;
            return this;
        }

        public Builder setNegativeButtonBackColorPress(int negativeButtonBackColorPressResId){
            this.negativeButtonBackColorPressResId = negativeButtonBackColorPressResId;
            return this;
        }

        public Builder setNegativeButtonTextColor(int negativeButtonTextColorResId){
            this.negativeButtonTextColorResId = negativeButtonTextColorResId;
            return this;
        }

        public Builder setNegativeButtonTextColorPress(int negativeButtonTextColorPressResId){
            this.negativeButtonTextColorPressResId = negativeButtonTextColorPressResId;
            return this;
        }

        public DialogM create(){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final DialogM dialog = new DialogM(mContext,R.style.DialogM);
            View layout = inflater.inflate(R.layout.view_dialog_m, null);

            TextView tvTitle = layout.findViewById(R.id.tv_title);
            if (!TextUtils.isEmpty(title)){
                tvTitle.setText(title);
            }
            if (titleBackColorResId != 0) {
                tvTitle.setBackgroundColor(titleBackColorResId);
            }
            if (titleTextColorResId != 0){
                tvTitle.setTextColor(titleTextColorResId);
            }
            TextView tvContent = layout.findViewById(R.id.tv_content);
            if (!TextUtils.isEmpty(content)){
                tvContent.setText(content);
            }
            if (contentBackColorResId != 0){
                tvContent.setBackgroundColor(contentBackColorResId);
            }
            if (contentTextColorResId != 0){
                tvContent.setTextColor(contentTextColorResId);
            }
            if (style == STYLE_ALERT){
                ViewStub vsAlert = layout.findViewById(R.id.vs_alert);
                vsAlert.inflate();
                ButtonM btmAlert = layout.findViewById(R.id.btm_alert);
                if (!TextUtils.isEmpty(alertButtonText)){
                    btmAlert.setText(alertButtonText);
                }
                if (alertButtonBackColorResId != 0){
                    btmAlert.setBackColor(alertButtonBackColorResId);
                }
                if (alertButtonBackColorPressResId != 0){
                    btmAlert.setBackColorPress(alertButtonBackColorPressResId);
                }
                if (alertButtonTextColorResId != 0){
                    btmAlert.setTextColor(alertButtonTextColorResId);
                }
                if (alertButtonTextColorPressResId != 0){
                    btmAlert.setTextColorPress(alertButtonTextColorPressResId);
                }
                if (onAlertClickListener != null){
                    btmAlert.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onAlertClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
            } else if (style == STYLE_TIP){
                ViewStub vsTip = layout.findViewById(R.id.vs_tip);
                vsTip.inflate();
                ButtonM btmPositive = layout.findViewById(R.id.btm_positive);
                if (!TextUtils.isEmpty(positiveButtonText)) {
                    btmPositive.setText(positiveButtonText);
                }
                if (positiveButtonBackColorResId != 0){
                    btmPositive.setBackColor(positiveButtonBackColorResId);
                }
                if (positiveButtonBackColorPressResId != 0){
                    btmPositive.setBackColorPress(positiveButtonBackColorPressResId);
                }
                if (positiveButtonTextColorResId != 0){
                    btmPositive.setTextColor(positiveButtonTextColorResId);
                }
                if (positiveButtonTextColorPressResId != 0){
                    btmPositive.setTextColorPress(positiveButtonTextColorPressResId);
                }
                if (onPositiveButtonClickListener != null){
                    btmPositive.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onPositiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                        }
                    });
                }
                ButtonM btmNegative = layout.findViewById(R.id.btm_negative);
                if (!TextUtils.isEmpty(negativeButtonText)) {
                    btmNegative.setText(negativeButtonText);
                }
                if (negativeButtonBackColorResId != 0){
                    btmNegative.setBackColor(negativeButtonBackColorResId);
                }
                if (negativeButtonBackColorPressResId != 0){
                    btmNegative.setBackColorPress(negativeButtonBackColorPressResId);
                }
                if (negativeButtonTextColorResId != 0){
                    btmNegative.setTextColor(negativeButtonTextColorResId);
                }
                if (negativeButtonTextColorPressResId != 0){
                    btmNegative.setTextColorPress(negativeButtonTextColorPressResId);
                }
                if (onNegativeButtonClickListener != null){
                    btmNegative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onNegativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                        }
                    });
                }
            }
            dialog.setCanceledOnTouchOutside(cancel);
            dialog.setContentView(layout);
            return dialog;
        }
    }
}
