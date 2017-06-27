package com.mo.first.other;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.mo.first.R;
import com.mo.first.adapter.MainAdapter;
import com.mo.first.base.BaseActivity;
import com.mo.first.view.CustomSearchListView;
import com.mo.first.view.PullShowHeaderListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mo on 2017/6/27.
 */

public class TestListView extends BaseActivity {
    public CustomSearchListView listView;
    private MainAdapter adapter;
    private List<String> mDatas = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_test);
        listView = (CustomSearchListView) findViewById(R.id.list_view);
        initData();
        listView.setOnRefreshListener(new CustomSearchListView.OnRefreshListener() {
            @Override
            public void onDownPullRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        listView.hideHeaderView();
                    }
                }).start();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }


    private void initData() {
        for (int i = 0; i < 20; i++){
            mDatas.add("di"+ i + "个item");
        }
        updatAdapter();
    }

    public void updatAdapter(){
        if(adapter == null){
            adapter = new MainAdapter(mContext,mDatas);
            listView.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }

}
