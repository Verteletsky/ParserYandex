package com.nowandroid.parseryandex;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;


public class ParsingClass extends DefaultHandler {

    ArrayList<String> title = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();
    ArrayList<String> about = new ArrayList<String>();

    private String tempStore = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if(localName.equalsIgnoreCase("post_title")){
            tempStore = "";
        } else if (localName.equalsIgnoreCase("post_date")){
            tempStore = "";
        } else if (localName.equalsIgnoreCase("guid")){
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
        if(localName.equalsIgnoreCase("post_title")){
            title.add(tempStore);
        } else if (localName.equalsIgnoreCase("post_date")){
            date.add(tempStore);
        } else if (localName.equalsIgnoreCase("guid")) {
            about.add(tempStore);
        }
        tempStore = "";
    }
}
