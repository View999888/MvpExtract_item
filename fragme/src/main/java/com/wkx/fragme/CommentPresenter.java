package com.wkx.fragme;

import java.lang.ref.SoftReference;
import java.util.ArrayList;

import io.reactivex.disposables.Disposable;

public class CommentPresenter<V extends ICommentView, M extends ICommentModel> implements ICommentPresenter {

    private SoftReference<V> mCommentView;
    private SoftReference<M> mModel;
    ArrayList<Disposable> disposablesList;

    //防止内存溢出
    public CommentPresenter(V pCommentView, M pModel) {
        disposablesList = new ArrayList<>();
        mCommentView = new SoftReference<>(pCommentView);
        mModel = new SoftReference<>(pModel);
    }

    //成功数据
    @Override
    public void OnSuccessFul(int witchApi, int loadType, Object... pD) {
        if (mCommentView != null && mCommentView.get() != null)
            mCommentView.get().OnSuccessFul(witchApi, loadType, pD);
    }

    //失败数据
    @Override
    public void OnFail(int witchApi, Throwable throwable) {
        if (mCommentView != null && mCommentView.get() != null)
            mCommentView.get().OnFail(witchApi, throwable);
    }

    //获取m层传过来的数据给 v层
    @Override
    public void getData(int witchApi, Object... pD) {
        if (mModel != null && mModel.get() != null) mModel.get().getData(this, witchApi, pD);
    }

    @Override
    public void addObserver(Disposable disposable) {
        if (disposable != null) return;
        disposablesList.add(disposable);
    }

    public void clear() {
        for (Disposable disposable : disposablesList) {
            if (disposable != null && !disposable.isDisposed()) disposable.dispose();
        }
        if (mCommentView != null) {
            mCommentView.clear();
            mCommentView = null;
        }
        if (mModel != null) {
            mModel.clear();
            mModel = null;
        }
    }
}
