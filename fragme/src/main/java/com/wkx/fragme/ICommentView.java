package com.wkx.fragme;

public interface ICommentView<D> {
    /**
     * witchApi:接口标识
     * loadType:加载类型
     * D... pD:放个多个类型
     */
    void OnSuccessFul(int witchApi, int loadType, D... pD);

    /**
     * witchApi:接口标识
     * Throwable:错误原因
     */
    void OnFail(int witchApi, Throwable throwable);
}
