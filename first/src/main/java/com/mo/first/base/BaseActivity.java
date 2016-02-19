package com.mo.first.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.mo.first.AppManager;
import com.mo.first.MyApplication;
import com.mo.first.R;

/**
 * Created by mo on 2016/2/19.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener {

    protected Context mContext;
    public TextView tv_title;
    public LinearLayout ll_left;//左边layout
    public TextView tv_left;//左标题
    public ImageView iv_right;//右图标
    public LinearLayout ll_right;
    public MyApplication app;
    public LinearLayout llContent;
    public View contentView;

    public BaseActivity() {
        mContext = BaseActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        app = (MyApplication) this.getApplication();
        setContentView(R.layout.activity_base);
        AppManager.getAppManager().addActivity(this);
       setpView();

    }

    /**
     * 通过泛型来简化 findViewById 时的类型转换
     */
    protected <T extends View> T initViewById(int id) {
        //return返回view时,加上泛型T
        return (T) contentView.findViewById(id);
    }


    public void setpView() {
        llContent = (LinearLayout) findViewById(R.id.content);
        ll_left = (LinearLayout) findViewById(R.id.ll_left);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_title = (TextView) findViewById(R.id.tv_title);//标题
        ll_left.setOnClickListener(this);

    }

    /***
     * 设置内容区域
     *
     * @param
     */
    public void setContentLayout(int layoutResId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(layoutResId, null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT);
        contentView.setLayoutParams(layoutParams);
        if (null != llContent) {
            llContent.addView(contentView);
        }
    }

    /***
     * 设置内容区域
     *
     * @param view
     */
    public void setContentLayout(View view) {
        if (null != llContent) {
            llContent.addView(view);
        }
    }

    /**
     * 得到内容的View
     *
     * @return
     */
    public View getLyContentView() {
        return contentView;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (null != tv_title) {
            tv_title.setText(title);
        }
    }

    public void showToast(String msg){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_left:
                finish();
                break;
        }
    }
}
