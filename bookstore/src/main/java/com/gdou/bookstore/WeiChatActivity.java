package com.gdou.bookstore;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WeiChatActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_title,tv_bookShelf,tv_bookStore,tv_myPage;
    private ViewPager mainViewPager;
    private BookShelfFragment bookShelfFragment;
    private BookStoreFragment bookStoreFragment;
    private MyPageFragment myPageFragment;
    private List<Fragment> fragmentList = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;

    String[] titles = new String []{"书架","书城","我"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//去除工具栏
        setContentView(R.layout.activity_wei_chat);
        initView();

        fragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(),fragmentList);
        mainViewPager.setOffscreenPageLimit(3);//三个界面
        mainViewPager.setAdapter(fragmentAdapter);//设置适配器
        mainViewPager.setCurrentItem(0);//设置第一页

        tv_bookShelf.setTextColor(Color.parseColor("#5DE100"));

        //添加viewpager滑动监听
        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tv_title.setText(titles[position]);
                changeNaviTextColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 初始化界面
     * */
    private void initView(){
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_bookShelf = (TextView) findViewById(R.id.tv_bookShelf);
        tv_bookStore = (TextView) findViewById(R.id.tv_bookStore);
        tv_myPage = (TextView) findViewById(R.id.tv_myPage);

        tv_bookShelf.setOnClickListener(this);
        tv_bookStore.setOnClickListener(this);
        tv_myPage.setOnClickListener(this);

        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);

        bookShelfFragment = new BookShelfFragment();
        bookStoreFragment = new BookStoreFragment();
        myPageFragment = new MyPageFragment();

        fragmentList.add(bookShelfFragment);
        fragmentList.add(bookStoreFragment);
        fragmentList.add(myPageFragment);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_bookShelf:
                mainViewPager.setCurrentItem(0,true);
                break;
            case R.id.tv_bookStore:
                mainViewPager.setCurrentItem(1,true);
                break;
            case R.id.tv_myPage:
                mainViewPager.setCurrentItem(2,true);
                break;
            default:
                break;
        }
    }

    //viewpager滑动时修改底部导航栏颜色
    private void changeNaviTextColor(int position){
        if(position == 0){
            tv_bookShelf.setTextColor(Color.parseColor("#5DE100"));
            tv_bookStore.setTextColor(Color.parseColor("#000000"));
            tv_myPage.setTextColor(Color.parseColor("#000000"));
        }else if(position == 1){
            tv_bookShelf.setTextColor(Color.parseColor("#000000"));
            tv_bookStore.setTextColor(Color.parseColor("#5DE100"));
            tv_myPage.setTextColor(Color.parseColor("#000000"));
        }else if(position == 2){
            tv_bookShelf.setTextColor(Color.parseColor("#000000"));
            tv_bookStore.setTextColor(Color.parseColor("#000000"));
            tv_myPage.setTextColor(Color.parseColor("#5DE100"));
        }
    }

}
