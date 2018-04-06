package com.mo.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.mo.app.activity_gridview.GridViewActivity;
import com.mo.app.activity_interface.InterfaceActivity;
import com.mo.app.activity_listview.ListViewActivity;
import com.mo.app.activity_play.PlayActivity;
import com.mo.app.activity_recorder.RecorderVideoActivity;
import com.mo.app.adapter.MainAdapter;
import com.mo.app.base.BaseActivity;
import com.mo.app.other.TestListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private MainAdapter adapter;
    private List<String> mDatas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);
        initView();
//111111111
        mDatas.add("常用界面");
        mDatas.add("常用界面");
        mDatas.add("ListView");
        mDatas.add("GridView");
        mDatas.add("录制视频");
        mDatas.add("播放音/视频");
        mDatas.add("other");
        updatAdapter();
    }


    public void initView() {
        if(ll_left != null){
            ll_left.setVisibility(View.GONE);
        }
        setTitle("MainActivity");
       listView = initViewById(R.id.list);
        listView.setOnItemClickListener(this);
    }

    public void updatAdapter(){
        if(adapter == null){
            adapter = new MainAdapter(mContext,mDatas);
            listView.setAdapter(adapter);
        }else{
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = mDatas.get(position);
        if(name.equals("常用界面")){
            startActivity(new Intent(this, InterfaceActivity.class));
        }else if(name.equals("ListView")){
            startActivity(new Intent(this, ListViewActivity.class));
        }else if(name.equals("GridView")){
            startActivity(new Intent(this, GridViewActivity.class));
        }else if(name.equals("录制视频")){
            startActivity(new Intent(this, RecorderVideoActivity.class));
        }else if(name.equals("播放音/视频")){
            startActivity(new Intent(this, PlayActivity.class));
        }else if(name.equals("other")){
            startActivity(new Intent(this, TestListView.class));
        }
    }
}
