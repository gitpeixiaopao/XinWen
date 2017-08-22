package com.example.mywangyixinwen;

import java.util.ArrayList;

/**
 * Created by peiyan on 2017/8/17.
 */



public class XFBean {
    public ArrayList<WS> ws;
    public class WS{
        public ArrayList<CW> cw;
    }
    public class CW{
        public String w;
    }
}
