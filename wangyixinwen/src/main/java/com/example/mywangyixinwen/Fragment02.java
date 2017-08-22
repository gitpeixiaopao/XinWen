package com.example.mywangyixinwen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment02 extends Fragment implements View.OnClickListener {
    private LinearLayout mLlDingyue;
    private ListView mLvDingyue;
    private Button mButDingyue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment02, container, false);


        initView(view);
        return view;
    }

    private void initView(View view) {
        mLlDingyue = (LinearLayout) view.findViewById(R.id.ll_dingyue);
        mLvDingyue = (ListView) view.findViewById(R.id.lv_dingyue);
        mButDingyue = (Button) view.findViewById(R.id.but_dingyue);
        mLlDingyue.setOnClickListener(this);
        mButDingyue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_dingyue://查看今日订阅

                break;
            case R.id.but_dingyue://添加我的订阅

                break;
        }
    }
}
