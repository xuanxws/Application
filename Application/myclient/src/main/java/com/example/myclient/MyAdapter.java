package com.example.myclient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/21.
 */

public class MyAdapter extends RecyclerView.Adapter {

    Context mContext;
    ArrayList<Person> mPersonArrayList = new ArrayList<>();
    LayoutInflater mInflater;

    public MyAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(mInflater.inflate(R.layout.recycle_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        Person person = mPersonArrayList.get(position);
        myHolder.tvName.setText(person.getName());
        myHolder.tvID.setText("编号:8975" + person.getId());
        myHolder.tvAge.setText("Q龄:" + person.getAge());
    }

    @Override
    public int getItemCount() {
        return mPersonArrayList.size();
    }

    public void addList(ArrayList<Person> list) {
        if (null != mPersonArrayList && null != list) {
            mPersonArrayList.clear();
            mPersonArrayList.addAll(list);
            notifyDataSetChanged();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvID;
        TextView tvAge;

        public MyHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvID = (TextView) itemView.findViewById(R.id.tv_id);
            tvAge = (TextView) itemView.findViewById(R.id.tv_age);
        }
    }
}
