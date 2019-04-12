package com.selfapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.selfapp.R;
import com.selfapp.fragment.base.BaseFragment;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  我的 fragment
 */
public class MyFragment extends BaseFragment {


    private static final String TAG = "MyFragment";
    private View mRootView;

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;



    //显示
    @BindView(R.id.tv)
    TextView tv;

    //显示
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;


    /**
     * 一定要在onCreate方法获取参数。在onCreateView中获取参数，而此方法在某些状态下并不会被调用，导致数据丢失。
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        //String value = bundle.getString("id");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null){
            mRootView = inflater.inflate(R.layout.fragment_my,container,false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null){
            parent.removeView(mRootView);
        }
        //注解
        ButterKnife.bind(this,mRootView);

        //初始化数据
        initData();


        return mRootView;
    }


    /**
     *  初始化数据
     */
    public void initData(){
        tv.setText("我的");
    }

    @OnClick({R.id.tv, R.id.scanning, R.id.my_code})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv:
                openDrawer();
                break;
            //扫一扫
            case R.id.scanning:
                ZXingLibrary.initDisplayOpinion(getContext());
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            //我的二维码
            case R.id.my_code:
                showMyCodeLog("123");
                break;

            default:
                showPopuWindow("提示","尚在开发，敬请等待...",tv);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    /**
     *  隐藏侧滑菜单
     */
    private void closeDrawer() {
        drawer_layout.closeDrawer(Gravity.START);
    }

    /**
     * 显示侧滑菜单
     */
    private void openDrawer() {
        drawer_layout.openDrawer(Gravity.LEFT);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
