package com.selfapp.fragment.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.selfapp.R;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 *
 */
public class BaseFragment extends Fragment {

    /**
     *
     */
    public BaseFragment() {

    }

    //错误提示框
    public void showPopuWindow(String title, String content, View textView) {

        WindowManager.LayoutParams attributes = this.getActivity().getWindow().getAttributes();
        attributes.alpha = 0.5f;
        this.getActivity().getWindow().setAttributes(attributes);
        final PopupWindow popupwindowMovie = new PopupWindow(textView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fail_layout, null);
        popupwindowMovie.setContentView(view);
        popupwindowMovie.setFocusable(true);
        popupwindowMovie.setOutsideTouchable(true);
        popupwindowMovie.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popupwindowMovie.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupwindowMovie.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes1 = BaseFragment.this.getActivity().getWindow().getAttributes();
                attributes1.alpha = 1f;
                BaseFragment.this.getActivity().getWindow().setAttributes(attributes1);
            }
        });

        TextView popupwindowtitle = view.findViewById(R.id.title);
        TextView popupwindowcontent = view.findViewById(R.id.content);
        TextView popupwindowsure = view.findViewById(R.id.sure);

        popupwindowtitle.setText(title);
        popupwindowcontent.setText(content);
        popupwindowsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupwindowMovie.dismiss();
            }
        });

    }

    /**
     *  显示二维码
     */
    public void showMyCode(String value){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.dialogNoBg);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.my_code, null);
        ImageView iv = v.findViewById(R.id.iv);

        iv.setImageBitmap(CodeUtils.createImage(value, 400, 400, null));

        //builer.setView(v);//这里如果使用builer.setView(v)，自定义布局只会覆盖title和button之间的那部分
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(v);//自定义布局应该在这里添加，要在dialog.show()的后面
        //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    /**
     *  显示二维码 带 log
     */
    public void showMyCodeLog(String value){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.dialogNoBg);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View v = inflater.inflate(R.layout.my_code, null);
        ImageView iv = v.findViewById(R.id.iv);

        iv.setImageBitmap(CodeUtils.createImage(value, 400, 400, BitmapFactory.decodeResource(getResources(), R.drawable.cocos2dx)));

        //builer.setView(v);//这里如果使用builer.setView(v)，自定义布局只会覆盖title和button之间的那部分
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(v);//自定义布局应该在这里添加，要在dialog.show()的后面
        //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


}
