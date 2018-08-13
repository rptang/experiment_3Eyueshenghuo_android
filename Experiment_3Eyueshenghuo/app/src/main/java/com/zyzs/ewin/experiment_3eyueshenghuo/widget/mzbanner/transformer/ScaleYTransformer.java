package com.zyzs.ewin.experiment_3eyueshenghuo.widget.mzbanner.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Experiment_3Eyueshenghuo
 * com.zyzs.ewin.experiment_3eyueshenghuo.widget.mzbanner.transformer
 * ScaleYTransformer
 * <p>
 * Created by Stiven on 2018/6/4.
 * Copyright © 2018 ZYZS-TECH. All rights reserved.
 */

public class ScaleYTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.7F;
    @Override
    public void transformPage(View page, float position) {

        if(position < -1){
            page.setScaleY(MIN_SCALE);
        }else if(position<= 1){
            //
            float scale = Math.max(MIN_SCALE,1 - Math.abs(position));
            page.setScaleY(scale);
            /*page.setScaleX(scale);

            if(position<0){
                page.setTranslationX(width * (1 - scale) /2);
            }else{
                page.setTranslationX(-width * (1 - scale) /2);
            }*/

        }else{
            page.setScaleY(MIN_SCALE);
        }
    }

}
