package com.nowandroid.parseryandex;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;


public class ParsingClass extends DefaultHandler {

    ArrayList<String> title = new ArrayList<String>();
    ArrayList<String> link = new ArrayList<String>();
    ArrayList<String> description = new ArrayList<String>();
    ArrayList<String> pubDate = new ArrayList<String>();

    private String tempStore = "";

    boolean newItem = false;


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
        }
        tempStore = "";
    }
}
