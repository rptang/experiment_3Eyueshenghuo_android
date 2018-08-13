package com.zyzs.ewin.experiment_3eyueshenghuo.widget.mzbanner.holder;

/**
 * Experiment_3Eyueshenghuo
 * com.zyzs.ewin.experiment_3eyueshenghuo.widget.mzbanner.holder
 * MZHolderCreator
 * <p>
 * Created by Stiven on 2018/6/4.
 * Copyright © 2018 ZYZS-TECH. All rights reserved.
 */

public interface MZHolderCreator<VH extends MZViewHolder> {

    /**
     * 创建ViewHolder
     * @return
     */
    public VH createViewHolder();
}
