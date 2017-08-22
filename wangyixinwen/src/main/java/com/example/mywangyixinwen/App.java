package com.example.mywangyixinwen;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.umeng.socialize.PlatformConfig;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.xutils.x;

/**
 * Created by peiyan on 2017/8/10.
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        PlatformConfig.setQQZone("1106036236","mjFCi0oxXZKZEWJs");
        //初始化二维码工具类
        ZXingLibrary.initDisplayOpinion(this);

     // 将“12345678”替换成您申请的APPID，申请地址：http://open.voicecloud.cn
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=58155541");
    }
}
