package com.wkx.fragme;

import io.reactivex.disposables.Disposable;

/**
 * p子层 继承 view父类 得到view层的方法
 * 1.p层是 v层与model层之间 传输数据的 中间桥梁
 */
public interface ICommentPresenter<P> extends ICommentView {
    /**
     * witchApi:用来区分网络数据接口的标识
     * D... pD:放个多个类型   根据网络数据的条件,要有顺序放置该参数
     */
    void getData(int witchApi, P... pD);

    void addObserver(Disposable disposable);
}
