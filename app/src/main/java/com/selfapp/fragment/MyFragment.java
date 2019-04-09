package com.selfapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
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
 *  我的 fragment
 */
public class MyFragment extends BaseFragment {


    private static final String TAG = "MyFragment";
    private View mRootView;

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

    @OnClick({R.id.tv})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv:
                openDrawer();
                break;

            default:

                showPopuWindow("提示","尚在开发，敬请等待...",tv);
                break;
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
