package com.mikazuki.android.matsumatsuyo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mikazuki.android.matsumatsuyo.R;
import com.mikazuki.android.matsumatsuyo.domain.entity.Yo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by matsuMac on 2015/09/12.
 */
public class YoListAdapter  extends BaseAdapter {
    public LayoutInflater mLayoutInflater;
    public Context mContext;
    public List<String> mItems;


    public YoListAdapter(Context context, List<String> items) {
        mContext = context;
        mItems = items;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
//        if (mItems == null) {
//            return 0;
//        }
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
//        if (mItems == null) {
//            return 0;
//        }
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
//        if (mItems == null) {
//            return 0;
//        }
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item, parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//        if (mItems == null) {
//            convertView = null;
//            return convertView;
//        }

        String item = mItems.get(position);

        holder.userName.setText(item);

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.userName)
        TextView userName;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}