package com.selfapp.activity.base;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.selfapp.R;
import com.selfapp.util.BackLogin;
import com.umeng.analytics.MobclickAgent;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 *
 */
public class BaseAppCompatActivity extends AppCompatActivity {
    /**
     *
     */
    public BaseAppCompatActivity() {

    }

    View workingAge_view;
    View workingAge_view1;
    PopupWindow popupWindow;
    String cpassword;
    private boolean isActive = true;

    private Long nowDate;

    private BackLogin backLogin;
    private Handler mHandler = new Handler();


    /**
     * 如果需要内容紧贴着StatusBar
     * 应该在对应的xml布局文件中，设置根布局fitsSystemWindows=true。
     */
    private View contentViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(StringUtils.equals(getColorTransparent(),"w")){
            setStatusBarFullTransparent();
        }else{
            setStatusBarFullTransparentBLACK();

        }


        init();



    }


    /**
     * 全透状态栏 白色文字
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * 全透状态栏 黑色文字
     */
    protected void setStatusBarFullTransparentBLACK() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            //实现状态栏图标和文字颜色为暗色
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN );
//        }

    }

    /**
     * 半透明状态栏
     */
    protected void setHalfTransparent() {

        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }



    protected void setFitSystemWindow(boolean fitSystemWindow) {
        if (contentViewGroup == null) {
            contentViewGroup = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        }
        contentViewGroup.setFitsSystemWindows(fitSystemWindow);
    }

//    /**
//     * 为了兼容4.4的抽屉布局->透明状态栏
//     */
//    protected void setDrawerLayoutFitSystemWindow() {
//        if (Build.VERSION.SDK_INT == 19) {//19表示4.4
//            int statusBarHeight = getStatusHeight(this);
//            if (contentViewGroup == null) {
//                contentViewGroup = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
//            }
//            if (contentViewGroup instanceof DrawerLayout) {
//                DrawerLayout drawerLayout = (DrawerLayout) contentViewGroup;
//                drawerLayout.setClipToPadding(true);
//                drawerLayout.setFitsSystemWindows(false);
//                for (int i = 0; i < drawerLayout.getChildCount(); i++) {
//                    View child = drawerLayout.getChildAt(i);
//                    child.setFitsSystemWindows(false);
//                    child.setPadding(0,statusBarHeight, 0, 0);
//                }
//
//            }
//        }
//    }


    private void init() {
        // TODO: 2017/8/29 五分钟无人操作回主页面
        //backMain = new BackMain(1000 * 60 * 5, 1000, this);
        backLogin = new BackLogin(1000  * 60 * 5 , 1000, this);
    }


    @Override
    protected void onResume() {

//        setFitSystemWindow(true);

        timeReStart();
        super.onResume();

        MobclickAgent.onResume(this);

        if (!isActive) {

            if(System.currentTimeMillis() -  1000 * 60  * 5 > nowDate ){
                backLogin.onFinish();
            }

            //app 从后台唤醒，进入前台
            isActive = true;


        }

        //
        initData();
    }


    /**
     * 初始化数据
     */
    protected void initData() {




    }


    //region 无操作 返回登录页面
    private void timeReStart() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(backLogin != null){
                    backLogin.cancel();
                    backLogin.start();
                }

            }
        });
    }

    @Override
    public void onUserInteraction(){
        timeReStart();
    }
//
//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//
//        return super.dispatchKeyEvent(event);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);

        backLogin.cancel();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

        if (!isAppOnForeground()) {
            //app 进入后台
            backLogin.cancel();
            //
            nowDate = System.currentTimeMillis();
            //全局变量
            isActive = false;
            //记录当前已经进入后台
        }
    }


    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public void showPopuWindow(int id, String content) {
        String   title =   this.getResources().getString(id);
        showPopuWindow(title,content,this.getWindow().getDecorView());
    }

    public void showPopuWindow(String title, String content) {
        showPopuWindow(title,content,this.getWindow().getDecorView());
    }


    public void showPopuWindow(int id, String content, View textView) {
        String   title =   this.getResources().getString(id);
        showPopuWindow(title,content,textView);
    }

    //错误提示框
    public void showPopuWindow(String title, String content, View textView) {

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha = 0.5f;
        getWindow().setAttributes(attributes);
        final PopupWindow popupwindowMovie = new PopupWindow(textView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fail_layout, null);
        popupwindowMovie.setContentView(view);
        popupwindowMovie.setFocusable(true);
        popupwindowMovie.setOutsideTouchable(true);
        popupwindowMovie.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        popupwindowMovie.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupwindowMovie.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes1 = getWindow().getAttributes();
                attributes1.alpha = 1f;
                getWindow().setAttributes(attributes1);
            }
        });

        TextView popupwindowtitle = view.findViewById(R.id.title);
        TextView popupwindowcontent = view.findViewById(R.id.content);
        TextView popupwindowsure = view.findViewById(R.id.sure);

        popupwindowtitle.setText(title);
        popupwindowcontent.setText(content);
        popupwindowsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupwindowMovie.dismiss();
            }
        });

    }


    public void back(View view) {
        finish();
    }


    /**
     * 设置状态栏颜色
     * @return
     */
    protected String getColorTransparent() {
        return "w";
    }

    /**
     * 广告
     *
     * 自定义布局
     * setView()只会覆盖AlertDialog的Title与Button之间的那部分，而setContentView()则会覆盖全部，
     * setContentView()必须放在show()的后面
     */
    private void dialogShowAdvertisement() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.dialogNoBg);
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

}
