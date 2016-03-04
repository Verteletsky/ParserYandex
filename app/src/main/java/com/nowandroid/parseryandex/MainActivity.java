package com.nowandroid.parseryandex;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fullinfo.FullNews;

public class MainActivity extends AppCompatActivity {

    Fragment fragment;

    ListView list;
    ArrayList<PostValue> postValueArrayList;

    MyTask mt;
    PostBaseAdapter listViewAdapter;
    ProgressDialog dialog;
    ParsingClass pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new FullNews();

        list = (ListView)findViewById(R.id.listview);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(postValueArrayList != null && postValueArrayList.size() > 0){
                    Intent intentShowPost = new Intent(Intent.ACTION_VIEW, Uri.parse(postValueArrayList.get(position).getLink()));
                    startActivity(intentShowPost);
                }
            }
        });
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Загрузка...");
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            pc = new ParsingClass();
            pc.get();
            postValueArrayList = pc.getPostsList();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (dialog.isShowing()){
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Список новостей загружен", Toast.LENGTH_LONG).show();
            }
            listViewAdapter = new PostBaseAdapter(MainActivity.this, pc.title, pc.pubDate, pc.description, pc.link);
            list.setAdapter(listViewAdapter);
        }
    }

}


