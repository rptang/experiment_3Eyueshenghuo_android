package com.zyzs.ewin.experiment_3eyueshenghuo.model.data.https;

/**
 * Experiment_3Eyueshenghuo
 * com.zyzs.ewin.experiment_3eyueshenghuo.model.data.https
 * Fault
 * <p>
 * Created by Stiven on 2018/6/4.
 * Copyright © 2018 ZYZS-TECH. All rights reserved.
 */

/**
 * 异常处理类，将异常包装成一个 Fault ,抛给上层统一处理
 * Created by zhouwei on 16/11/17.
 */
public class Fault extends RuntimeException {

    private int errorCode;

    public Fault(int errorCode,String message){
        super(message);
        errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
