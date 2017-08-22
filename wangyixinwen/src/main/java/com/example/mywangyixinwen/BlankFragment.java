package com.example.mywangyixinwen;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import static com.example.mywangyixinwen.R.id.plv;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements View.OnTouchListener, AdapterView.OnItemClickListener {
    private Fragment01 fragment01;
    private Fragment02 fragment02;
    private Fragment03 fragment03;
    private Fragment04 fragment04;
    private Fragment05 fragment05;
    //接口对象
    CallBackValue callBackValue;
    private ParallaxListView mPlv;
    private List<String> list;
    private View headerView;
    private ImageView header;
    //    private ImageView header;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        fragment01 = new Fragment01();
        fragment02 = new Fragment02();
        fragment03 = new Fragment03();
        fragment04 = new Fragment04();
        fragment05 = new Fragment05();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.zhanwei, fragment01).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.zhanwei, fragment02).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.zhanwei, fragment03).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.zhanwei, fragment04).commit();
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.zhanwei, fragment05).commit();
        getActivity().getSupportFragmentManager().beginTransaction().
                hide(fragment02).hide(fragment03).hide(fragment04).hide(fragment05).commit();
        initView(view);
        list = new ArrayList<>();
        list.add("新闻");
        list.add("订阅");
        list.add("图片");
        list.add("视频");
        list.add("跟帖");
        My2Adapter adapter=new My2Adapter(getActivity(),list);
        mPlv.setAdapter(adapter);

        //listView添加一个头布局
        View headerView= View.inflate(getActivity(), R.layout.blank_item, null);
        mPlv.addHeaderView(headerView);
        header = (ImageView) headerView.findViewById(R.id.blank_image);
        //等View界面全部绘制完毕的时候，去得到已经绘制完控件的宽和高；
        header.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println("1111111111111111111111111111111");
                //宽和高已经测量完毕
                mPlv.setHeader(header);
                //释放资源
                header.getViewTreeObserver().removeGlobalOnLayoutListener(this);

            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackValue = (CallBackValue) getActivity();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setOnTouchListener(this);
    }

    private void initView(View view) {
        mPlv = (ParallaxListView) view.findViewById(plv);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 1:
                callBackValue.SendManagerValue();
                getActivity().getSupportFragmentManager().beginTransaction().show(fragment01).
                        hide(fragment02).hide(fragment03).hide(fragment04).hide(fragment05).commit();
                break;
            case 2:
                callBackValue.SendManagerValue();
                getActivity().getSupportFragmentManager().beginTransaction().show(fragment02).
                        hide(fragment01).hide(fragment03).hide(fragment04).hide(fragment05).commit();
                break;
            case 3:
                callBackValue.SendManagerValue();
                getActivity().getSupportFragmentManager().beginTransaction().show(fragment03).
                        hide(fragment02).hide(fragment01).hide(fragment04).hide(fragment05).commit();
                break;
            case 4:
                callBackValue.SendManagerValue();
                getActivity().getSupportFragmentManager().beginTransaction().show(fragment04).
                        hide(fragment02).hide(fragment03).hide(fragment01).hide(fragment05).commit();
                break;
            case 5:
                callBackValue.SendManagerValue();
                getActivity().getSupportFragmentManager().beginTransaction().show(fragment05).
                        hide(fragment02).hide(fragment03).hide(fragment04).hide(fragment01).commit();
                break;
        }
    }
    //自定义接口
   public interface  CallBackValue {
        void SendManagerValue();
    }

}




