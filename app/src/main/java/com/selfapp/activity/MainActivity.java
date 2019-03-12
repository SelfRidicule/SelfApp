package com.selfapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.selfapp.R;
import com.selfapp.permission.PermissionsActivity;
import com.selfapp.permission.PermissionsChecker;


public class MainActivity extends Activity {


    private final static int REQUEST_CODE = 11111;

    private final static Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    protected void onResume() {
        super.onResume();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                // 缺少权限时, 进入权限配置页面
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M && PermissionsChecker.lacksPermissions(MainActivity.this)) {

                    PermissionsActivity.startActivityForResult(MainActivity.this, REQUEST_CODE);

                }else{
                    updateApp();
                }
            }
        },100);
    }

    /**
     * 进入app
     */
    public void updateApp() {

        /**
         * 开启线程
         */
        new Thread(new Runnable() {
            public void run() {
                int alpha = 2;
                int b = 0;

                while (b < 2) {
                    try {
                        if (b == 0) {
                            Thread.sleep(10);
                        }
                        alpha -= 1;
                        if (alpha <= 0) {
                            b = 2;
                            Intent in = new Intent(MainActivity.this, HomeActivity.class);
                            //startActivity(new Intent(GuideActivity.this,LoginActivity.class));
                            MainActivity.this.startActivity(in);
                            MainActivity.this.finish();
                        }
                        /*
                         * 用于界面渐变，现不需要
                         */
                        // mHandler.sendMessage(mHandler.obtainMessage());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /**
     * 接收返回结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            finish();
        }

    }


}
