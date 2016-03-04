package com.nowandroid.parseryandex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class PostBaseAdapter extends BaseAdapter {
    ArrayList<String> title;
    ArrayList<String> date;
    ArrayList<String> about;
    ArrayList<String> link;
    LayoutInflater inflater;
    Context context;

    public PostBaseAdapter(Context context) {
        this.context = context;
    }

    public PostBaseAdapter(Activity act, ArrayList<String> tit, ArrayList<String> dat, ArrayList<String> abo, ArrayList<String> lin) {
        this.title = tit;
        this.date = dat;
        this.about = abo;
        this.link = lin;
        inflater = (LayoutInflater)act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null){
            holder = new Holder();
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.title);
            holder.txtDate = (TextView)convertView.findViewById(R.id.date);
            holder.txtAbout = (TextView)convertView.findViewById(R.id.about);
            holder.txtLink = (TextView)convertView.findViewById(R.id.link);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.txtTitle.setText(title.get(position));
        holder.txtDate.setText(date.get(position));
        holder.txtAbout.setText(about.get(position));
        holder.txtLink.setText(link.get(position));

        return convertView;
    }


    public class Holder{
        TextView txtTitle, txtDate, txtAbout, txtLink;
        RelativeLayout activityMainLayout;
    }
}
