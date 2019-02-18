package com.selfapp;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.selfapp.adapter.DemoAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener{

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
        setContentView(R.layout.activity_main);

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
    public void onClick(View v) {

    }

}
