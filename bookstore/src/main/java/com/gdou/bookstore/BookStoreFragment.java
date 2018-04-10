package com.gdou.bookstore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018-03-30.
 */

public class BookStoreFragment extends Fragment implements MyRecyclerViewOnclickInterface{

    private RecyclerView recyclerView;
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private List<String> stringList;

    public BookStoreFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookstore,container,false);
        ButterKnife.bind(this,view);
        recyclerView = (RecyclerView) view.findViewById(R.id.rlv_bookstore);
        initData();
        return view;
    }

    private void initData(){
        //填充数据
        stringList = new ArrayList<>();
        for(int i=0;i<20;i++){
            stringList.add(String.valueOf(i));
        }

        //设置适配器
        myRecycleViewAdapter = new MyRecycleViewAdapter(getActivity(),stringList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myRecycleViewAdapter);

        myRecycleViewAdapter.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(),"点击"+stringList.get(position),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(getActivity(),"长按"+stringList.get(position),Toast.LENGTH_SHORT).show();
    }
}
