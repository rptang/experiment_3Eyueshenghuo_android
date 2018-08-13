package com.zyzs.ewin.experiment_3eyueshenghuo.adapter.recycler;

/**
 * SmartUHomeApplication
 * com.ulife.app.adapter.recycleadapter
 * MultiTypeSupport
 * <p>
 * Created by Stiven on 2018/6/12.
 * Copyright © 2018 ZYZS-TECH. All rights reserved.
 */

public interface MultiTypeSupport<T> {
    int getLayoutId(T item, int position);
}
