package com.selfapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
 *  发现 fragment
 */
public class FindFragment extends BaseFragment {


    private static final String TAG = "FindFragment";
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
            mRootView = inflater.inflate(R.layout.fragment_find,container,false);
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
        tv.setText("发现");
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
