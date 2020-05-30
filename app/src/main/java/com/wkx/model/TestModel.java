package com.wkx.model;

import com.wkx.bean.TestBean;
import com.wkx.fragme.ApiConfig;
import com.wkx.fragme.ApiService;
import com.wkx.fragme.ICommentModel;
import com.wkx.fragme.ICommentPresenter;
import com.wkx.fragme.NetManger;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestModel implements ICommentModel {
    @Override
    public void getData(final ICommentPresenter mCommentPresenter, final int witchApi, Object[] params) {
        NetManger netManger = NetManger.getInstance();
        switch (witchApi) {
            case ApiConfig.TEST_GET:
                netManger.netWork(netManger.getService().getTestInfo((Map) params[1], (int) params[2]), mCommentPresenter, witchApi, (int) params[0]);
                break;
        }
    }
}
