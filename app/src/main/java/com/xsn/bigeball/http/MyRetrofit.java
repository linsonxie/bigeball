package com.xsn.bigeball.http;

import com.xsn.bigeball.model.AllType;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by XSN on 2016/8/30.
 */
public interface MyRetrofit {

    @POST("findAllTypes.sx")
    Observable<AllType> findAllTypes(@Query("param") String param);
}
