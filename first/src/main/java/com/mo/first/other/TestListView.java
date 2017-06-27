package com.mo.first.other;

import android.os.Bundle;

import com.mo.first.R;
import com.mo.first.adapter.MainAdapter;
import com.mo.first.base.BaseActivity;
import com.mo.first.view.PullShowHeaderListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mo on 2017/6/27.
 */

public class TestListView extends BaseActivity {
    public PullShowHeaderListView listView;
    private MainAdapter adapter;
    private List<String> mDatas = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_test);
        listView = (PullShowHeaderListView) findViewById(R.id.list_view);
        listView.initHeaderView(mContext, R.layout.activity_listview_head);
        initData();
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
