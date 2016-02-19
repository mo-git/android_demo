package com.mo.first.activity_play;

import android.os.Bundle;
import com.mo.first.R;
import com.mo.first.base.BaseActivity;

/**
 * Created by mo on 2016/2/19.
 * 播放音/视频
 */
public class PlayActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_play);
        setTitle("播放音/视频");
    }
}
