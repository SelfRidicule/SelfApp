package com.selfapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.selfapp.MainActivity;
import com.selfapp.R;
import com.selfapp.adapter.DemoAdapter;
import com.selfapp.permission.PermissionsActivity;
import com.selfapp.permission.PermissionsChecker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener{

    /**
     * 下拉刷新控件
     */
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.listview)
    ListView listview;

    List<String> list = new ArrayList<>();

    DemoAdapter demoAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        swipeRefreshLayout.setOnRefreshListener(this);

        init();
    }

    public void init(){
        demoAdapter = new DemoAdapter(list,this);
        listview.setAdapter(demoAdapter);
    }

    @Override
    public void onRefresh() {
        list.add("  1  ");
        demoAdapter.notifyDataSetChanged();


        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    @Override
    public void onClick(View v) {

    }

}
