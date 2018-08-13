package com.zyzs.ewin.experiment_3eyueshenghuo.activity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.zyzs.ewin.experiment_3eyueshenghuo.R;
import com.zyzs.ewin.experiment_3eyueshenghuo.adapter.FoodAdapter;
import com.zyzs.ewin.experiment_3eyueshenghuo.base.BaseActivity;
import com.zyzs.ewin.experiment_3eyueshenghuo.model.bean.Food;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshLayoutActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton btn_back;
    private TwinklingRefreshLayout refreshLayout;
    private RecyclerView recyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private FoodAdapter mFoodAdapter;

    private List<Food> foods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);

        onCreatInit();
    }

    @Override
    protected void onInitView() {
        btn_back = findViewById(R.id.btn_back);
        refreshLayout = findViewById(R.id.refresh);
        recyclerview = findViewById(R.id.recyclerview);

        SinaRefreshView headerView = new SinaRefreshView(this);
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        refreshLayout.setHeaderView(headerView);
        LoadingView loadingView = new LoadingView(this);
        refreshLayout.setBottomView(loadingView);

        foods = new ArrayList<>();
        mFoodAdapter = new FoodAdapter(this,foods);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // 设置布局管理器
        recyclerview.setLayoutManager(mLayoutManager);
        // 设置adapter
        recyclerview.setAdapter(mFoodAdapter);
    }

    @Override
    protected void onInitData() {

        refreshCard();
    }

    @Override
    protected void onAddEventListener() {
        btn_back.setOnClickListener(this);

        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefreshing();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadmore();
                    }
                }, 2000);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void refreshCard() {
        foods.add(new Food("Preparing Salmon Steak Close Up", "BY VIKTOR HANACEK", R.drawable.food1, R.drawable.avatar0));
        foods.add(new Food("Fresh & Healthy Fitness Broccoli Pie with Basil", "BY VIKTOR HANACEK", R.drawable.food2, R.drawable.avatar1));
//        foods.add(new Food("Enjoying a Tasty Burger", "BY VIKTOR HANACEK", R.drawable.food3, R.drawable.avatar2));
//        foods.add(new Food("Fresh Strawberries and Blackberries in Little Bowl", "BY VIKTOR HANACEK", R.drawable.food4, R.drawable.avatar3));
//        foods.add(new Food("Baked Healthy Fitness Broccoli Pie with Basil", "BY VIKTOR HANACEK", R.drawable.food5, R.drawable.avatar4));
        mFoodAdapter.notifyDataSetChanged();
        //foodAdapter.setDataList(foods);
    }

    private void loadMoreCard() {
        List<Food> foods = new ArrayList<>();
//        foods.add(new Food(R.drawable.food3));
//        foods.add(new Food(R.drawable.food2));
//        foods.add(new Food(R.drawable.food1));
        //foodAdapter.addItems(foods);
    }
}
