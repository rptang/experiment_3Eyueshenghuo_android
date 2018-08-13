package com.zyzs.ewin.experiment_3eyueshenghuo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Experiment_3Eyueshenghuo
 * com.zyzs.ewin.experiment_3eyueshenghuo.receiver
 * MyReceiver
 * <p>
 * Created by Stiven on 2018/6/26.
 * Copyright © 2018 ZYZS-TECH. All rights reserved.
 */

public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
    }
}
