package com.zyzs.ewin.experiment_3eyueshenghuo.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zyzs.ewin.experiment_3eyueshenghuo.R;
import com.zyzs.ewin.experiment_3eyueshenghuo.utils.StatusbarUtils;

public abstract class BaseActivity extends AutoLayoutActivity {

    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        //去activity的标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //setContentView(R.layout.activity_base);
        //StatusbarUtils.enableTranslucentStatusbar(this);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

//        if (isImmersionBarEnabled()){
//            initImmersionBar();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    protected void initImmersionBar() {
        //BaseFloorActivity初始化
        mImmersionBar = ImmersionBar.with(this);
        //mImmersionBar.fitsSystemWindows(true);
        //mImmersionBar.init();
        /*mImmersionBar
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.c_3D3D3D)
                .init();*/

        mImmersionBar
                .fitsSystemWindows(false)  //使用该属性,必须指定状态栏颜色
                .statusBarDarkFont(false)   //状态栏字体是深色，不写默认为亮色
                .init();
    }

    protected abstract void onInitView(); //初始化View
    protected abstract void onInitData(); //初始化数据
    protected abstract void onAddEventListener(); //添加事件

    /**
     * 初始化
     */
    protected void onCreatInit(){
        onInitView();
        onInitData();
        onAddEventListener();
    }

    /**
     * 跳转Activity
     * @param clz
     */
    protected void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 吐司
     * @param msg
     */
    protected void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 返回操作
     * 动画：←←←←←←
     */
    public void back(){
        finish();
        //overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
