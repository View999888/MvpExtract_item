package com.wkx.mvpextracts_moudle;

import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wkx.adapter.TestAdapter;
import com.wkx.base.BaseMvpActivity;
import com.wkx.bean.TestBean;
import com.wkx.fragme.ApiConfig;
import com.wkx.fragme.ICommentModel;
import com.wkx.fragme.LoadTypeConfig;
import com.wkx.fragme.ParamHashMap;
import com.wkx.interfaces.DataListener;
import com.wkx.model.TestModel;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
public class MainActivity extends BaseMvpActivity {

    @BindView(R.id.recycle_test)
    RecyclerView recycleTest;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    int pageId = 0;
    private TestAdapter testAdapter;
    private Map<String, Object> params;


    @Override
    protected ICommentModel setModel() {
        return new TestModel();
    }

    @Override
    protected int initSetLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initSetView() {
        initPresenter();//p层的方法

        testAdapter = new TestAdapter(this);
        recycleTest.setAdapter(testAdapter);
    }

    private void initPresenter() {
        //用来拼接接口
        params = new ParamHashMap().add("c", "api").add("a", "getList");
        //加载监听
        initRecycleView(recycleTest, smart, new DataListener() {
            @Override
            public void dataType(int mode) {
                switch (mode) {
                    case LoadTypeConfig.MORE:
                        mCommentPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.MORE, params, pageId);
                        break;
                    case LoadTypeConfig.REFRESH:
                        mCommentPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.REFRESH, params, pageId);
                        break;
                }
            }
        });
    }

    @Override
    protected void initSetData() {
        /**
         * ApiConfig.TEST_GET  判断当前的接口的类型  用来区分多个接口的类型
         *  LoadTypeConfig.NORMAL  当前是分加载类型
         *  params参数集合 .add("c", "api").add("a", "getList")
         *  pageId  分页
         * */
        mCommentPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.NORMAL, params, pageId);
    }

    @Override
    protected void netSuccessFul(int witchApi, int loadType, Object[] pD) {
        switch (witchApi) {
            case ApiConfig.TEST_GET:
                List<TestBean.DatasBean> datas = ((TestBean) (pD[0])).getDatas();
                testAdapter.setDatasBeans(datas);

                if (loadType == LoadTypeConfig.REFRESH) {
                    smart.finishRefresh();
                } else if (loadType == LoadTypeConfig.MORE) {
                    smart.finishLoadmore();
                    datas.clear();//加载更多 清空集合
                }
                break;
        }
    }


    @Override
    public void OnFail(int witchApi, Throwable throwable) {
        Toast.makeText(this, "未知错误:" + throwable.getMessage() != null ? throwable.getMessage() : "错误", Toast.LENGTH_SHORT).show();
    }
}
