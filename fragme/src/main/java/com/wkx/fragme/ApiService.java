package com.wkx.fragme;

import com.wkx.bean.TestBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    @GET(".")
    Observable<TestBean> getTestInfo(@QueryMap Map<String,Object> params, @Query("pageid") int pageId);
}
