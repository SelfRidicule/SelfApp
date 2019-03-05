package com.selfapp.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        initView();
    }

    public void init(){
        demoAdapter = new DemoAdapter(list,this);
        listview.setAdapter(demoAdapter);
    }

    public void initView(){
        dialogShow();
    }

    /**
     * 自定义布局
     * setView()只会覆盖AlertDialog的Title与Button之间的那部分，而setContentView()则会覆盖全部，
     * setContentView()必须放在show()的后面
     */
    private void dialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.dialogNoBg);
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
