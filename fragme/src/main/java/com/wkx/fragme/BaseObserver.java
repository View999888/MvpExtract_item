package com.wkx.fragme;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver implements Observer {
    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {//订阅
        mDisposable = d;
    }

    @Override
    public void onNext(Object o) {
        Onsuccess(o);
        setmDisposable();
    }

    @Override
    public void onError(Throwable e) {
        OnFail(e);
        setmDisposable();
    }

    @Override
    public void onComplete() {
        setmDisposable();
    }

    protected abstract void Onsuccess(Object o);//成功

    protected abstract void OnFail(Throwable throwable);//失败

    //取消订阅
    public void setmDisposable() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
