package com.selfapp.fragment.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.selfapp.R;

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


}
