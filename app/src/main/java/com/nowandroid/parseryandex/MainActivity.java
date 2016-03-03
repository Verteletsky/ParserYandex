package com.nowandroid.parseryandex;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {

    Fragment fragment;
    private ListView list;
    MyTask mt;
    BindingData listViewAdapter;
    Context context;
    ProgressDialog dialog;
    ParsingClass pc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new FullNews();
        list = (ListView)findViewById(R.id.listview);

        refreshDate();
    }

    public void refreshDate() {
        mt = new MyTask();
        mt.execute();
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
            try {
                SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                SAXParser parser = saxParserFactory.newSAXParser();
                XMLReader xmlReader = parser.getXMLReader();

                URL url = new URL("https://news.yandex.ru/hardware.rss");

                pc = new ParsingClass();
                xmlReader.setContentHandler(pc);
                xmlReader.parse(new InputSource(url.openStream()));

                Log.i("FUCK!!!!!!!!!", String.valueOf(url));
            } catch (Exception e){
                e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (dialog.isShowing()){
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Список новостей загружен", Toast.LENGTH_LONG).show();
            }

            listViewAdapter = new BindingData(MainActivity.this, pc.title, pc.pubDate, pc.description);
            list.setAdapter(listViewAdapter);
        }
    }

}


