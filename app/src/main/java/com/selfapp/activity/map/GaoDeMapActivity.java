package com.selfapp.activity.map;

import android.os.Bundle;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.selfapp.R;
import com.selfapp.activity.base.BaseAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  高德地图
 */
public class GaoDeMapActivity extends BaseAppCompatActivity {

    //显示
    @BindView(R.id.maps)
    MapView mapView;

    AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaode_map);

        //绑定初始化ButterKnife
        ButterKnife.bind(this);

        mapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap = mapView.getMap();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}
