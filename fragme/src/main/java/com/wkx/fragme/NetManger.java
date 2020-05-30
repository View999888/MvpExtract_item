package com.wkx.fragme;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetManger {
    private NetManger() {
    }

    private static volatile NetManger sNetManger;

    public static NetManger getInstance() {
        if (sNetManger == null) {
            synchronized (NetManger.class) {
                sNetManger = new NetManger();
            }
        }
        return sNetManger;
    }

    /**
     * @param t   如果传baseURL，用传过来，没传的话用默认
     * @param <T>
     * @return
     */
    public <T> ApiService getService(T... t) {
        String baseUrl = ServerAddressConfig.BASE_URL;
        if (t != null && t.length != 0) {
            baseUrl = (String) t[0];
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build()
                .create(ApiService.class);
    }


    /**
     * 使用observer观察者，抽取出网络请求及切换线程的过程
     *
     * @param localTestInfo
     * @param pPresenter
     * @param whichApi
     * @param dataType
     * @param o
     * @param <T>
     * @param <O>
     */
    public <T, O> void netWork(Observable<T> localTestInfo, final ICommentPresenter pPresenter, final int whichApi, final int dataType, O... o) {
        localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        pPresenter.addObserver(d);
                    }

                    @Override
                    protected void Onsuccess(Object o) {
                        pPresenter.OnSuccessFul(whichApi, dataType, o);
                    }

                    @Override
                    protected void OnFail(Throwable throwable) {
                        pPresenter.OnFail(whichApi, throwable);
                    }
                });
    }
}
