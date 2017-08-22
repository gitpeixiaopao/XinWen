package com.example.mywangyixinwen;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.example.mylibrary.XListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment01 extends Fragment implements View.OnClickListener {
    private XListView xlist_toutiao;
    private String[] str = new String[]{"新闻", "头条", "热点", "军事", "社会"};

    //设置三种类型   来设置不同的item

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView mImagePopu;
    private PopupWindow popupWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment01, container, false);

        initView(view);
        initViewPaget();


        return view;
    }

    private void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mImagePopu = (ImageView) view.findViewById(R.id.image_popu);
        mImagePopu.setOnClickListener(this);
    }

    //初始化ViewPager;
    private void initViewPaget() {
        //首先创建一个集合来装Fragment
        List<Fragment> fragments = new ArrayList<>();
        //创建两个Fragment装入集合
        for (int i = 0; i < 5; i++) {
            pagerFragment fragment01 = new pagerFragment();
            fragments.add(fragment01);
        }
        //创建ViewPager适配器
        MyAdapter adapter = new MyAdapter(getActivity().getSupportFragmentManager(), str, fragments);
        //吧集合放入适配器

        //给ViewPager设置适配器
        viewPager.setAdapter(adapter);
        //////////////tabLayout/////////////
        //tabLayout指示器有几个就创建几个
        //给tabLayout设置文本信息
        for (int i = 0; i < fragments.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(str[i]));
        }
        //使tabLayout与ViewPager关联
        tabLayout.setupWithViewPager(viewPager);
    }
//点击弹出popupwindow
    int a=0;
    @Override
    public void onClick(View view) {
        if (a==0){
            LayoutInflater l=LayoutInflater.from(getActivity());
            View v=l.inflate(R.layout.popu, null);
            popupWindow = new PopupWindow(v, ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.showAsDropDown(mImagePopu);
            a=1;
        }else {
            popupWindow.dismiss();
            a=0;
        }

    }
}
