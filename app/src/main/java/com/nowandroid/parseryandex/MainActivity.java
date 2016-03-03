package com.nowandroid.parseryandex;

import android.app.Fragment;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

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
    URL url;
    ArrayList<String> title = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new FullNews();
        list = (ListView)findViewById(R.id.listview);

        bindDate();
    }

    private void bindDate(){
        try{
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = parser.getXMLReader();
            ParsingClass pc = new ParsingClass();
            xmlReader.setContentHandler(pc);
            url = new URL("https://news.yandex.ru/hardware.rss");
            InputSource inputSource = new InputSource(url.openStream());
            xmlReader.parse(inputSource);
            BindingData bindingData = new BindingData(this,pc.title, pc.date, pc.about);
            list.setAdapter(bindingData);
        } catch (Exception e){
            e.getMessage();
        }
    }
}


