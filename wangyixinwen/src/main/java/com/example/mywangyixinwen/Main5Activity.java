package com.example.mywangyixinwen;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main5Activity extends Activity {


    private TextView mMain5Tv1;
    private TextView mMain5Tv2;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mMain5Tv1.setText(value);
            mMain5Tv2.setText(title);
        }
    };
    private String value;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        initView();

        new Thread(){
            @Override
            public void run() {
                try {
                    //从一个URL加载一个Document对象。
                    Document doc = Jsoup.connect("http://www.weather.com.cn/weather/101010100.shtml").get();
                    //集合
                    Elements elements = doc.select("div.c7d").select("input");
                    //遍历打印
                    Log.i("mytag",elements.get(0).attr("value"));
                    value = elements.get(0).attr("value");

                    Elements elements1 = doc.select("div.c7d").select("p");
                    Log.i("mytag",elements1.get(0).attr("title"));
                    title = elements1.get(0).attr("title");
                    handler.sendEmptyMessage(0);
                }catch(Exception e) {
                    Log.i("mytag", e.toString());
                }
            }
        }.start();


    }


    private void initView() {
        mMain5Tv1 = (TextView) findViewById(R.id.main5_tv1);
        mMain5Tv2 = (TextView) findViewById(R.id.main5_tv2);
    }
}
