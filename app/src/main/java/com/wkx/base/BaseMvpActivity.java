package com.wkx.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.wkx.fragme.CommentPresenter;
import com.wkx.fragme.ICommentModel;
import com.wkx.fragme.ICommentView;

import butterknife.ButterKnife;

public abstract class BaseMvpActivity<M extends ICommentModel> extends BaseActivity implements ICommentView {

    private M mTestModel;
    public CommentPresenter mCommentPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initSetLayout());
        ButterKnife.bind(this);
        mTestModel = setModel();
        mCommentPresenter = new CommentPresenter(this, mTestModel);
        initSetView();
        initSetData();
    }


    protected abstract M setModel();//初始化布局

    protected abstract int initSetLayout();//初始化布局

    protected abstract void initSetView();//初始化布局和 获取数据

    protected abstract void initSetData();//获取网络数据

    protected abstract void netSuccessFul(int witchApi, int loadType, Object[] pD);//成功

    protected void netFail(int witchApi, Throwable pthrowable) {
    }//失败

    @Override
    public void OnSuccessFul(int witchApi, int loadType, Object[] pD) {
        netSuccessFul(witchApi, loadType, pD);
    }

    @Override
    public void OnFail(int witchApi, Throwable pThrowable) {
        showLog("net work error: " + witchApi + "error content" + pThrowable != null && !TextUtils.isEmpty(pThrowable.getMessage()) ? pThrowable.getMessage() : "不明错误类型");
        netFail(witchApi, pThrowable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCommentPresenter.clear();
    }
}
