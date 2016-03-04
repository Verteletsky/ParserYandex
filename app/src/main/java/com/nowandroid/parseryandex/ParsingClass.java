package com.nowandroid.parseryandex;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class ParsingClass extends DefaultHandler {

    private String URL = "https://news.yandex.ru/hardware.rss";

    ArrayList<String> title = new ArrayList<String>();
    ArrayList<String> link = new ArrayList<String>();
    ArrayList<String> description = new ArrayList<String>();
    ArrayList<String> pubDate = new ArrayList<String>();
    private ArrayList<PostValue> posts = new ArrayList<PostValue>();

    public String tempStore = "";
    boolean newItem = false;

    public ArrayList<PostValue> getPostsList() {
        return this.posts;
    }

    public void get(){
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = parser.getXMLReader();
            xmlReader.setContentHandler(this);
            InputStream inputStream = new URL(URL).openStream();
            xmlReader.parse(new InputSource(inputStream));
            Log.i("FUCK!!!!!!!!!", String.valueOf(URL));
        } catch (Exception e){
            e.getMessage();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equalsIgnoreCase("item"))
            newItem = true;
        if(localName.equalsIgnoreCase("title") && newItem){
            tempStore = "";
        } else if (localName.equalsIgnoreCase("pubDate") && newItem){
            tempStore = "";
        } else if (localName.equalsIgnoreCase("description") && newItem){
            tempStore = "";
        } else if (localName.equalsIgnoreCase("link") && newItem){
            tempStore = "";
        } else {
            tempStore = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        tempStore += new String(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(localName.equalsIgnoreCase("title") && newItem){
            title.add(tempStore);
        } else if (localName.equalsIgnoreCase("pubDate") && newItem){
            pubDate.add(tempStore);
        } else if (localName.equalsIgnoreCase("description") && newItem) {
            description.add(tempStore);
        } else if (localName.equalsIgnoreCase("link") && newItem){
            link.add(tempStore);
        }
        tempStore = "";
    }
}
