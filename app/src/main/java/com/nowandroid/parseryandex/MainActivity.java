package com.nowandroid.parseryandex;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import HelpParse.ParsingClass;
import HelpParse.PostBaseAdapter;
import HelpParse.PostValue;
import fullinfo.NextActivity;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<PostValue> postValueArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.listview);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(postValueArrayList != null && postValueArrayList.size() > 0){
//                    Intent intentShowPost = new Intent(Intent.ACTION_VIEW, Uri.parse(postValueArrayList.get(position).getLink()));
//                    startActivity(intentShowPost);
                    String v = String.valueOf(Uri.parse(postValueArrayList.get(position).getLink()));
                    Intent intentShowPost = new Intent(getApplicationContext(), NextActivity.class);
                    intentShowPost.putExtra("postValue", v);
                    startActivity(intentShowPost);
                }
            }
        });
        new MyTask().execute();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;
        ParsingClass pc;

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
            PostBaseAdapter postBaseAdapter = new PostBaseAdapter(MainActivity.this, postValueArrayList);
            list.setAdapter(postBaseAdapter);
            dialog.dismiss();
        }
    }

}


