package com.zyzs.ewin.experiment_3eyueshenghuo.widget.mzbanner.holder;

import android.content.Context;
import android.view.View;

/**
 * Experiment_3Eyueshenghuo
 * com.zyzs.ewin.experiment_3eyueshenghuo.widget.mzbanner.holder
 * MZViewHolder
 * <p>
 * Created by Stiven on 2018/6/4.
 * Copyright © 2018 ZYZS-TECH. All rights reserved.
 */

public interface MZViewHolder<T> {
    /**
     *  创建View
     * @param context
     * @return
     */
    View createView(Context context);

    /**
     * 绑定数据
     * @param context
     * @param position
     * @param data
     */
    void onBind(Context context, int position, T data);
}
