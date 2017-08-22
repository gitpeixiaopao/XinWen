package com.example.mywangyixinwen;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Blank2Fragment extends Fragment implements View.OnTouchListener {
    private ImageView denglu1;
    private TextView denglu2;
    private TextView denglu3;
    private TextView yuedu;
    private TextView shoucang;
    private TextView gentie;
    private TextView julebu;
    private TextView huodong;
    private TextView yingyong;
    private TextView youxi;
    private TextView shangtoutiao;
    private TextView xiaoxi;
    private TextView youjian;
    private TextView gongyi;
    private TextView caipiao;
    private UMShareAPI mShareAPI = null;
    private SHARE_MEDIA platform = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank2, container, false);

        initView(view);
        mShareAPI = UMShareAPI.get( getActivity() );
        denglu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UMAuthListener umAuthListener = new UMAuthListener() {
                    /**
                     * @desc 授权开始的回调
                     * @param platform 平台名称
                     */
                    @Override
                    public void onStart(SHARE_MEDIA platform) {
                    }
                    /**
                     * @desc 授权成功的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     * @param data 用户资料返回
                     */
                    @Override
                    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                            String profile_image_url = data.get("profile_image_url");
                            //这步骤是用imageloder加载图片的
                            DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                                    .cacheInMemory(true)
                                    .cacheOnDisk(true)  .build();
                            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getActivity())
                                    .defaultDisplayImageOptions(displayImageOptions)
                                    .build();
                            ImageLoader.getInstance().init(config);
                            ImageLoader.getInstance().displayImage(profile_image_url, denglu1);
                        }
                    /**
                     * @desc 授权失败的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     * @param t 错误原因
                     */
                    @Override
                    public void onError(SHARE_MEDIA platform, int action, Throwable t) {

                        Toast.makeText(getActivity(), "失败：" + t.getMessage(),                                  Toast.LENGTH_LONG).show();
                    }

                    /**
                     * @desc 授权取消的回调
                     * @param platform 平台名称
                     * @param action 行为序号，开发者用不上
                     */
                    @Override
                    public void onCancel(SHARE_MEDIA platform, int action) {
                        Toast.makeText(getActivity(), "取消了", Toast.LENGTH_LONG).show();
                    }
                };

                mShareAPI.getPlatformInfo(getActivity(),SHARE_MEDIA.QQ,umAuthListener);


            }
        });

        return view;
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
        denglu1 = (ImageView) view.findViewById(R.id.denglu1);
        denglu2 = (TextView) view.findViewById(R.id.denglu2);
        denglu3 = (TextView) view.findViewById(R.id.denglu3);
        yuedu = (TextView) view.findViewById(R.id.yuedu);
        shoucang = (TextView) view.findViewById(R.id.shoucang);
        gentie = (TextView) view.findViewById(R.id.gentie);
        julebu = (TextView) view.findViewById(R.id.julebu);
        huodong = (TextView) view.findViewById(R.id.huodong);
        yingyong = (TextView) view.findViewById(R.id.yingyong);
        youxi = (TextView) view.findViewById(R.id.youxi);
        shangtoutiao = (TextView) view.findViewById(R.id.shangtoutiao);
        xiaoxi = (TextView) view.findViewById(R.id.xiaoxi);
        youjian = (TextView) view.findViewById(R.id.youjian);
        gongyi = (TextView) view.findViewById(R.id.gongyi);
        caipiao = (TextView) view.findViewById(R.id.caipiao);
    }
}
