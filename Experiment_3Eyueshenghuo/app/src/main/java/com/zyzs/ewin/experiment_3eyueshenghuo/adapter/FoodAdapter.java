package com.zyzs.ewin.experiment_3eyueshenghuo.adapter;

import android.content.Context;

import com.zyzs.ewin.experiment_3eyueshenghuo.R;
import com.zyzs.ewin.experiment_3eyueshenghuo.adapter.recycler.CommonRecycleAdapter;
import com.zyzs.ewin.experiment_3eyueshenghuo.adapter.recycler.CommonViewHolder;
import com.zyzs.ewin.experiment_3eyueshenghuo.adapter.recycler.MultiTypeSupport;
import com.zyzs.ewin.experiment_3eyueshenghuo.model.bean.Food;

import java.util.List;

/**
 * Experiment_3Eyueshenghuo
 * com.zyzs.ewin.experiment_3eyueshenghuo.adapter
 * FoodAdapter
 * <p>
 * Created by Stiven on 2018/7/19.
 * Copyright © 2018 ZYZS-TECH. All rights reserved.
 */

public class FoodAdapter extends CommonRecycleAdapter<Food> implements MultiTypeSupport<Food> {

    private CommonViewHolder.onItemCommonClickListener commonClickListener;

    public FoodAdapter(Context context, List<Food> dataList) {
        super(context, dataList, R.layout.item_food);
    }

    public FoodAdapter(Context context, List<Food> dataList, CommonViewHolder.onItemCommonClickListener commonClickListener) {
        super(context, dataList, R.layout.item_food);
        this.commonClickListener = commonClickListener;
        this.multiTypeSupport = this;
    }

    public void setCommonClickListener(CommonViewHolder.onItemCommonClickListener commonClickListener) {
        this.commonClickListener = commonClickListener;
    }

    @Override
    public void bindData(CommonViewHolder holder, Food data) {
        holder.setImageResource1(R.id.iv_food,data.getImageSrc());
    }

    @Override
    public int getLayoutId(Food item, int position) {
        return R.layout.item_food;
    }
}