package com.wkx.fragme;

/**
 * m层  获取网络数据的方法  执行耗时任务  不处理加载和刷新
 */
public interface ICommentModel<T> {
    void getData(ICommentPresenter mCommentPresenter, int witchApi, T... params);
}
