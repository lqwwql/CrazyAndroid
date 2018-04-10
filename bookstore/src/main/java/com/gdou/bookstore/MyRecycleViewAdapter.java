package com.gdou.bookstore;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018-03-31.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {

    private Context context;
    private List<String> mDatas;

    private MyRecyclerViewOnclickInterface myRecyclerViewOnclickListener;

    public void setOnItemClickListener(MyRecyclerViewOnclickInterface myRecyclerViewOnclickListener){
        this.myRecyclerViewOnclickListener = myRecyclerViewOnclickListener;
    }

    public MyRecycleViewAdapter(Context context,List<String> mDatas){
        this.context = context;
        this.mDatas = mDatas;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycleview_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
        if(myRecyclerViewOnclickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    myRecyclerViewOnclickListener.onItemClick(v,position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    myRecyclerViewOnclickListener.onItemLongClick(v,position);
                    return true;
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public MyViewHolder(View view){
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_recycleview_item);
        }
    }

}


