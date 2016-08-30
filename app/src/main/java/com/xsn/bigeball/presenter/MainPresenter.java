package com.xsn.bigeball.presenter;

import android.content.Context;

import com.xsn.bigeball.http.RetrofitClient;
import com.xsn.bigeball.model.AllType;
import com.xsn.bigeball.model.Type;
import com.xsn.bigeball.view.iView.IMainView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by XSN on 2016/8/30.
 */
public class MainPresenter extends BasePresenter<IMainView> {
    public MainPresenter(Context context, IMainView iView) {
        super(context, iView);
    }

    @Override
    public void release() {
        subscription.unsubscribe();
    }

    public void getData() {
        iView.showProgress();
        /************************************/
        /**********retrofit原生请求处理********/
        /*RetrofitClient.getGankRetrofitInstance().findAllTypes("test")
                .enqueue(new Callback<AllType>() {
                    @Override
                    public void onResponse(Call<AllType> call, Response<AllType> response) {
                        iView.hideProgress();
                        iView.showAllTypes(response.body());
                    }

                    @Override
                    public void onFailure(Call<AllType> call, Throwable t) {
                        iView.hideProgress();
                        iView.showErrorView();
                    }
                });*/

        /***********************************
         ******retrofit+rxJava处理方法*******
         ***********************************/
        subscription = RetrofitClient.getGankRetrofitInstance().findAllTypes("test")
                //时间产生在新线程，请求过程在新线程
                .subscribeOn(Schedulers.newThread())
                //指定时间消费在主线程 指定 Subscriber 的回调发生在主线程
                .observeOn(AndroidSchedulers.mainThread())
                /*.subscribe(new Observer<AllType>() {
                    @Override
                    public void onCompleted() {
                        iView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        iView.showErrorView();
                    }

                    @Override
                    public void onNext(AllType allType) {
                        iView.showAllTypes(allType);
                    }
                })*/
                .flatMap(new Func1<AllType, Observable<Type>>() {
                    @Override
                    public Observable<Type> call(AllType allTypes) {
                        return Observable.from(allTypes.getResultJson());
                    }
                })
                //指定一个观察者
                .subscribe(new Action1<Type>() {
                    @Override
                    public void call(Type type) {
                        iView.showAllNames(type.getTypeName());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        iView.showErrorView();
                    }
                });
    }
}
