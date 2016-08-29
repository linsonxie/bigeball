package com.xsn.bigeball.presenter;

import android.content.Context;

import com.xsn.bigeball.view.iView.IBaseView;

import rx.Subscription;

/**
 * Created by XSN on 2016/8/29.
 */
public abstract class BasePresenter <T extends IBaseView> {

    protected Subscription subscription;
    protected Context context;
    protected T iView;
    public BasePresenter(Context context,T iView){
        this.context=context;
        this.iView=iView;
    }

    public void init(){
        iView.initView();
    }

    public abstract void release();
}
