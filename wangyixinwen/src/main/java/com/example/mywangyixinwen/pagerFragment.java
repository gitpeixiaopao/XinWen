package com.example.mywangyixinwen;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mylibrary.XListView;
import com.google.gson.Gson;
import com.youth.banner.Banner;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static com.example.mywangyixinwen.R.id.banner_toutiao;


/**
 * A simple {@link Fragment} subclass.
 */
public class pagerFragment extends Fragment implements XListView.IXListViewListener {

    private XinWenBean xinWenBean;
    private static final int FristType=1;
    private static final int TwoType=2;
    private static final int ThreeType=3;
    private static final int FourType=4;
    private XListView xlist_toutiao;
    public List<List<String>> lists;
    private List<XinWenBean.ResultBean.DataBean>list;
    private Image im;

    public pagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_pager, container, false);
        xlist_toutiao = (XListView) inflate.findViewById(R.id.xlist_toutiao);
        //开始加载更多
        xlist_toutiao.setPullLoadEnable(true);
        //开始下拉刷新
        xlist_toutiao.setPullRefreshEnable(true);
        //设置XListView的接口并实现两个方法
        xlist_toutiao.setXListViewListener(this);
        //第一个Fragment，实现新闻的数据
        xlist_toutiao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getActivity(),Main3Activity.class);
                intent.putExtra("url",list.get(i).url);
                startActivity(intent);

            }
        });
        
        RequestParams params = new RequestParams("http://v.juhe.cn/toutiao/index?type=top&key=e76b62dbe5ce78645516fe866dc7058b");

        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                list = new ArrayList<XinWenBean.ResultBean.DataBean>();
                Gson gson = new Gson();
                xinWenBean = gson.fromJson(result, XinWenBean.class);
                list = xinWenBean.result.data;
                lists = new ArrayList<List<String>>();
                for (int i = 0; i < list.size(); i++) {
                    List<String> list_str=new ArrayList<String>();
                    if (list.get(i).thumbnail_pic_s!=null){
                        String s1 = list.get(i).thumbnail_pic_s;
                        list_str.add(s1);
                    }
                    if (list.get(i).thumbnail_pic_s02!=null){
                        String s1 = list.get(i).thumbnail_pic_s02;
                        list_str.add(s1);
                    }
                    if (list.get(i).thumbnail_pic_s03!=null){
                        String s1 = list.get(i).thumbnail_pic_s03;
                        list_str.add(s1);
                    }
                    lists.add(list_str);
                }
                xlist_toutiao.setAdapter(new MyListAdapter());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });
        return inflate;
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    class MyListAdapter extends BaseAdapter {

        @Override
        public int getItemViewType(int i) {
            if (i==0){
                return FristType;

            }else if (lists.get(i).size()==1){
                return TwoType;
            }else if (lists.get(i).size()==2){
                return ThreeType;
            }else if (lists.get(i).size()==3){
                return FourType;
            }
            return FourType;
        }

        @Override
        public int getViewTypeCount() {
            return 10;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }


        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder1 holder1=null;
            ViewHolder2 holder2=null;
            ViewHolder3 holder3=null;
            ViewHolder4 holder4=null;
            int type=getItemViewType(i);
            if (view==null){
                switch (type){
                    case 1:
                        holder1=new ViewHolder1();
                        view=View.inflate(getActivity(),R.layout.fragment01_item,null);
                        holder1.banner_toutiao=(Banner)view.findViewById(banner_toutiao);
                        view.setTag(holder1);
                        break;
                    case 2:
                        holder2=new ViewHolder2();
                        view=View.inflate(getActivity(),R.layout.fragment_item1,null);
                        holder2.tv_item1=(TextView)view.findViewById(R.id.tv_item1);
                        holder2.image_item1=(ImageView)view.findViewById(R.id.image_item1);
                        view.setTag(holder2);
                        break;
                    case 3:
                        holder4=new ViewHolder4();
                        view=View.inflate(getActivity(),R.layout.fragment_item3,null);
                        holder4.image1_item3=(ImageView) view.findViewById(R.id.image1_item3);
                        holder4.image2_item3=(ImageView) view.findViewById(R.id.image2_item3);
                        view.setTag(holder4);
                        break;
                    case 4:
                        holder3=new ViewHolder3();
                        view=View.inflate(getActivity(),R.layout.fragment_item2,null);
                        holder3.image1_item2=(ImageView)view.findViewById(R.id.image1_item2);
                        holder3.image2_item2=(ImageView)view.findViewById(R.id.image2_item2);
                        holder3.image3_item2=(ImageView)view.findViewById(R.id.image3_item2);
                        view.setTag(holder3);
                        break;
                }
            }else {
                //取值
                switch (type){
                    case 1:
                        holder1=(ViewHolder1) view.getTag();
                        break;
                    case 2:
                        holder2=(ViewHolder2) view.getTag();
                        break;
                    case 3:
                        holder4=(ViewHolder4) view.getTag();
                        break;
                    case 4:
                        holder3=(ViewHolder3) view.getTag();
                        break;
                }
            }
            im=new Image();
            switch (type){
                case 1:
                    List<String> list1=new ArrayList<>();
                    for (int j = 0; j <5 ; j++) {
                        list1.add(list.get(j).thumbnail_pic_s);

                    }
                    holder1.banner_toutiao.setImageLoader(new ImageBanner());
                    holder1.banner_toutiao.setImages(list1);
                    holder1.banner_toutiao.start();
                    break;
                case 2:
                    im.setImageView(lists.get(i).get(0),getActivity(),holder2.image_item1);
                    holder2.tv_item1.setText(list.get(i).title);
                    break;
                case 3:
                    im.setImageView(lists.get(i).get(0),getActivity(),holder4.image1_item3);
                    im.setImageView(lists.get(i).get(1),getActivity(),holder4.image2_item3);
                    break;
                case 4:
                    im.setImageView(lists.get(i).get(0),getActivity(),holder3.image1_item2);
                    im.setImageView(lists.get(i).get(1),getActivity(),holder3.image2_item2);
                    im.setImageView(lists.get(i).get(2),getActivity(),holder3.image3_item2);
                    break;
            }
            return view;
        }
        class ViewHolder1{
            Banner banner_toutiao;
        }
        class ViewHolder2{
            TextView tv_item1;
            ImageView image_item1;
        }
        class ViewHolder3{
            ImageView image1_item2;
            ImageView image2_item2;
            ImageView image3_item2;
        }
        class ViewHolder4{
            ImageView image1_item3;
            ImageView image2_item3;
        }
    }
}
