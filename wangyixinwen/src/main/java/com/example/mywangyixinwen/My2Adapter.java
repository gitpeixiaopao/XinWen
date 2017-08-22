package com.example.mywangyixinwen;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by peiyan on 2017/8/19.
 */
public class My2Adapter extends BaseAdapter{
private Context context;
    private List<String> list;

    public My2Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
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
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=View.inflate(context,R.layout.blank2_item,null);
            holder.tv=(TextView)view.findViewById(R.id.plv_tv);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        holder.tv.setText(list.get(i));
        return view;
    }
    class  ViewHolder{
        TextView tv;
    }
}
