package com.mo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mo.app.R;

import java.util.List;

/**
 * Created by mo on 2016/2/19.
 */
public class MainAdapter extends BaseAdapter {
    private List<String> mdatas;
    private Context mContext;
    public MainAdapter(Context mContext,List<String> mdatas){
        this.mdatas = mdatas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mdatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mdatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_main_item,null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(mdatas.get(position));

        return convertView;
    }

    class ViewHolder{
        TextView name;
    }
}
