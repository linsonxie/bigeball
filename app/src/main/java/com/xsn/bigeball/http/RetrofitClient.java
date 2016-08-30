package com.xsn.bigeball.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by XSN on 2016/8/30.
 */
public class RetrofitClient {


    private static final String HOST = "http://124.172.174.130:11111/inspect/";
    private static MyRetrofit myRetrofit;
    protected static final Object monitor = new Object();
    private static Retrofit retrofit;

    static {
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    public static MyRetrofit getGankRetrofitInstance() {
        synchronized (monitor) {
            if (myRetrofit == null) {
                myRetrofit = retrofit.create(MyRetrofit.class);
            }
            return myRetrofit;
        }
    }
}
