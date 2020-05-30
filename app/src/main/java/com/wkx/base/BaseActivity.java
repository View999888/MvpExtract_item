package com.wkx.base;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.wkx.contextUtils.MyAppcation;
import com.wkx.fragme.LoadTypeConfig;
import com.wkx.interfaces.DataListener;
public class BaseActivity extends AppCompatActivity {
    public MyAppcation myAppcation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myAppcation= (MyAppcation) getApplication();
    }

    public void initRecycleView(RecyclerView PrecyclerView, SmartRefreshLayout pSmartRefreshLayout, DataListener pDataListener) {
        if (PrecyclerView != null) PrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (pSmartRefreshLayout != null && pDataListener != null) {
            pSmartRefreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {
                    pDataListener.dataType(LoadTypeConfig.MORE);//记载更多
                }

                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    pDataListener.dataType(LoadTypeConfig.REFRESH);//刷新
                }
            });
        }
    }

    public void showLog(Object showLog) {
        Log.e(TAG, showLog.toString());
    }

    public void showToast(Object content) {
        Toast.makeText(getApplicationContext(), content.toString(), Toast.LENGTH_SHORT).show();
    }

    private static final String TAG = "BaseActivity";
}
