package com.nowandroid.parseryandex;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class BindingData extends BaseAdapter {
    ArrayList<String> title;
    ArrayList<String> date;
    ArrayList<String> about;
    LayoutInflater inflater;

    public BindingData() {}

    public BindingData(Activity act, ArrayList<String> tit, ArrayList<String> dat, ArrayList<String> abo) {
        this.title = tit;
        this.date = dat;
        this.about = abo;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView == null){
            holder = new Holder();
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.title);
            holder.txtDate = (TextView)convertView.findViewById(R.id.date);
            holder.txtAbout = (TextView)convertView.findViewById(R.id.about);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.txtTitle.setText(Html.fromHtml("Title: " + title.get(position)));
        holder.txtTitle.setText(Html.fromHtml("Date: " + date.get(position)));
        holder.txtTitle.setText(Html.fromHtml("About: " + about.get(position)));

        return convertView;
    }

    public class Holder{
        TextView txtTitle, txtDate, txtAbout;
    }
}
