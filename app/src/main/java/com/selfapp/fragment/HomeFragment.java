package com.selfapp.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selfapp.R;
import com.selfapp.fragment.base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  主页 fragment
 */
public class HomeFragment extends BaseFragment {


    private static final String TAG = "HomeFragment";
    private View mRootView;

    //显示
    @BindView(R.id.tv)
    TextView tv;

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
            mRootView = inflater.inflate(R.layout.fragment_home,container,false);
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
        tv.setText("主页");
    }

    @OnClick({R.id.tv})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv:

                break;

            default:

                showPopuWindow("提示","尚在开发，敬请等待...",tv);
                break;
        }
    }


    /**
     * 跳转到拨号界面
     */
    public void toCallView(String phone) {

        if(request_Call_Phone_Permission()){
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phone));
            getContext().startActivity(intent);
        }

    }

    /**
     *  调用呼叫电话权限
     */
    private boolean request_Call_Phone_Permission() {

        //判断版本是否是6.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE);
            //没有权限则申请权限，否则执行操作
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.CALL_PHONE},1 );
                return false;
            }else {
                return true;
            }

        }else{
            return true;
        }

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
