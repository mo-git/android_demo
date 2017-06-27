package com.mo.app.activity_interface;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.mo.app.R;
import com.mo.app.adapter.MainAdapter;
import com.mo.app.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mo on 2016/2/19.
 * 常用界面
 */
public class InterfaceActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private MainAdapter adapter;
    private List<String> mDatas = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);

        initView();
        mDatas.add("111111");
        mDatas.add("222222");
        mDatas.add("333333");
        updatAdapter();
    }


    public void initView() {

        setTitle("常用界面");
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
        if(name.equals("111111")){
            showToast("111");
        }else if(name.equals("222222")){
            showToast("222");
        }else if(name.equals("333333")){
            showToast("333");
        }
    }
}

