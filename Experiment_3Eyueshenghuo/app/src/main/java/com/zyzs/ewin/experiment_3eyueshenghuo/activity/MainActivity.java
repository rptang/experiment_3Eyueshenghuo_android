package com.zyzs.ewin.experiment_3eyueshenghuo.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyzs.ewin.experiment_3eyueshenghuo.R;
import com.zyzs.ewin.experiment_3eyueshenghuo.base.BaseActivity;

public class MainActivity extends BaseActivity {

    // 卡片式轮播
    private LinearLayout ll_card_view;
    // 加载H5页面
    private LinearLayout ll_H5ActiveActivity;
    // ScrollView滚动页面
    private LinearLayout ll_home_Activity;
    //快捷方式
    private LinearLayout ll_ShortCut_Activity;
    //SwipeRefreshLayout
    private LinearLayout ll_SwipeRefreshLayout_Activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onCreatInit();
    }

    @Override
    protected void onInitView() {
        ll_card_view = findViewById(R.id.ll_card_view);
        ll_H5ActiveActivity = findViewById(R.id.ll_H5ActiveActivity);
        ll_home_Activity = findViewById(R.id.ll_Home_Activity);
        ll_ShortCut_Activity = findViewById(R.id.ll_ShortCut_Activity);
        ll_SwipeRefreshLayout_Activity = findViewById(R.id.ll_SwipeRefreshLayout_Activity);
    }

    @Override
    protected void onInitData() {

    }

    @Override
    protected void onAddEventListener() {

        ll_card_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    ll_card_view.setBackground(getResources().getDrawable(R.drawable.r0cdcdcdc_be1cdcdcdc));
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    ll_card_view.setBackground(getResources().getDrawable(R.drawable.r0cfff_be1cdcdcdc));
                    startActivity(BannerCardActivity.class);
                }
                return true;
            }
        });

        ll_H5ActiveActivity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    ll_H5ActiveActivity.setBackground(getResources().getDrawable(R.drawable.r0cdcdcdc_be1cdcdcdc));
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    ll_H5ActiveActivity.setBackground(getResources().getDrawable(R.drawable.r0cfff_be1cdcdcdc));
                    startActivity(H5ActiveActivity.class);
                }
                return true;
            }
        });

        ll_home_Activity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    ll_home_Activity.setBackground(getResources().getDrawable(R.drawable.r0cdcdcdc_be1cdcdcdc));
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    ll_home_Activity.setBackground(getResources().getDrawable(R.drawable.r0cfff_be1cdcdcdc));
                    startActivity(HomeActivity.class);
                }
                return true;
            }
        });

        ll_ShortCut_Activity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    ll_ShortCut_Activity.setBackground(getResources().getDrawable(R.drawable.r0cdcdcdc_be1cdcdcdc));
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    ll_ShortCut_Activity.setBackground(getResources().getDrawable(R.drawable.r0cfff_be1cdcdcdc));
                    startActivity(ShortCutActivity.class);
                }
                return true;
            }
        });

        ll_SwipeRefreshLayout_Activity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    ll_SwipeRefreshLayout_Activity.setBackground(getResources().getDrawable(R.drawable.r0cdcdcdc_be1cdcdcdc));
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    ll_SwipeRefreshLayout_Activity.setBackground(getResources().getDrawable(R.drawable.r0cfff_be1cdcdcdc));
                    startActivity(SwipeRefreshLayoutActivity.class);
                }
                return true;
            }
        });
    }
}
