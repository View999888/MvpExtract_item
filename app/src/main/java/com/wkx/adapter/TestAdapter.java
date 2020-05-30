package com.wkx.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wkx.bean.TestBean;
import com.wkx.mvpextracts_moudle.R;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {
    Context context;
    List<TestBean.DatasBean> datasBeans = new ArrayList<>();

    public TestAdapter(Context context) {
        this.context = context;
    }

    public void setDatasBeans(List<TestBean.DatasBean> datasBeans) {
        this.datasBeans.addAll(datasBeans);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recycle_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TestBean.DatasBean datasBean = datasBeans.get(i);

        Glide.with(context).load(datasBean.getThumbnail()).into(viewHolder.mImageView);
        viewHolder.mTextView.setText(datasBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return datasBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_img);
            mTextView = itemView.findViewById(R.id.tv_title);
        }
    }
}
