package com.selfapp.activity.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.selfapp.R;

/**
 *
 */
public class BaseAppCompatActivity extends AppCompatActivity {
    /**
     *
     */
    public BaseAppCompatActivity() {

    }


    /**
     * 自定义布局
     * setView()只会覆盖AlertDialog的Title与Button之间的那部分，而setContentView()则会覆盖全部，
     * setContentView()必须放在show()的后面
     */
    private void dialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialogNoBg);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.popup_layout, null);
        ImageView pic_iv = v.findViewById(R.id.pic_iv);
        ImageView close_iv = v.findViewById(R.id.close_iv);

        //builer.setView(v);//这里如果使用builer.setView(v)，自定义布局只会覆盖title和button之间的那部分
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(v);//自定义布局应该在这里添加，要在dialog.show()的后面
        //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        pic_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }


}
