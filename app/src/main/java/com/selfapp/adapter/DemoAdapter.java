package com.selfapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.selfapp.R;

import java.util.List;

public class DemoAdapter extends BaseAdapter{

    List<String> dataBeanList;
    Context context;
    LayoutInflater inflater;


    public DemoAdapter(List<String> dataBeanList, Context context) {
        this.dataBeanList = dataBeanList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void DemoAdapter(List<String> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    @Override
    public int getCount() {
        return dataBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.demo_adapter, null);

            viewHolder.tv = convertView.findViewById(R.id.tv);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv.setText(dataBeanList.get(position));


        return convertView;
    }


    /**
     *
     */
    public class ViewHolder {
        TextView tv;
    }

}
