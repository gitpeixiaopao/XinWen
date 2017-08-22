package com.example.mywangyixinwen;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class Main3Activity extends Activity {

    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
        String url = getIntent().getStringExtra("url");
        web.loadUrl(url);
    }

    private void initView() {
        web = (WebView) findViewById(R.id.web);
    }
}
