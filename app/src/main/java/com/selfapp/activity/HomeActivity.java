package com.selfapp.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.selfapp.R;
import com.selfapp.activity.base.BaseAppCompatActivity;
import com.selfapp.fragment.FindFragment;
import com.selfapp.fragment.HomeFragment;
import com.selfapp.fragment.MyFragment;
import com.selfapp.fragment.base.BaseFragment;
import com.selfapp.util.AppShortCutUtil;
import com.selfapp.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *  主页 Activity
 */
public class HomeActivity extends BaseAppCompatActivity {

    private FragmentTabHost mTabHost;
    private ViewPager mViewPager;
    private List<BaseFragment> mFragmentList;
    private Class mClass[] = {FindFragment.class, HomeFragment.class, MyFragment.class};
    private BaseFragment mFragment[] = {new FindFragment(), new HomeFragment(), new MyFragment()};
    private int tabIndex = 1;
    private String mTitles[] = {"发现","主页","我的"};

    //选中
    private int imageSelect[] = {
            R.drawable.icon_find_select,
            R.drawable.icon_home_select,
            R.drawable.icon_my_select
    };

    //未选中
    private int imageNoSelect[] = {
            R.drawable.icon_find,
            R.drawable.icon_home,
            R.drawable.icon_my
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //初始化
        init();

        //展示当前的页面
        changePage(tabIndex+"");

    }

    /**
     * 展示当前的页面
     * @param i
     */
    public void changePage(String i){
        mTabHost.onTabChanged(i);
    }


    private void init() {

        initView();

        initEvent();

        //upload();

    }

    private void initView() {
        mTabHost = findViewById(android.R.id.tabhost);
        mViewPager = findViewById(R.id.view_pager);

        mFragmentList = new ArrayList<BaseFragment>();

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0;i < mFragment.length;i++){
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(""+i).setIndicator(getTabView(i));
            mTabHost.addTab(tabSpec,mClass[i],null);
            mFragmentList.add(mFragment[i]);
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });
    }

    private View getTabView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.tab_item, null);

        ImageView image = view.findViewById(R.id.image);
        TextView title = view.findViewById(R.id.title);

        image.setImageResource(imageNoSelect[index]);
        title.setText(mTitles[index]);

        return view;
    }

    private void initEvent() {

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int index = Integer.valueOf(tabId) ;//获取当前索引
                mViewPager.setCurrentItem(index);
                tabIndex = index ;

                //获得当前所有的TabWidget
                TabWidget widget = HomeActivity.this.mTabHost.getTabWidget() ;
                int num = widget.getChildCount() ;
                for(int i=0;i<num;i++){
                    ImageView imageView = widget.getChildAt(i).findViewById(R.id.image);
                    TextView title = widget.getChildAt(i).findViewById(R.id.title);
                    if(tabIndex==i){
                        //如果某个tab被选中，则更换背景图片
                        imageView.setImageResource(imageSelect[i]) ;
                        title.setTextColor(HomeActivity.this.getResources().getColor(R.color.color_f0592a));
                    }else{
                        //未选中的，则使用默认背景图
                        imageView.setImageResource(imageNoSelect[i]) ;
                        title.setTextColor(HomeActivity.this.getResources().getColor(R.color.color_666666));
                    }
                }

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected String getColorTransparent() {
        return "";
    }

}
