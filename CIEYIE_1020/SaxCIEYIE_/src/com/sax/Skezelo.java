package com.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class Skezelo implements ContentHandler{
    private Locator loc;
    public Skezelo(){}
    public void setDocumentLocator(Locator lloc){
        this.loc=lloc;
    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        System.out.println("sor:" +this.loc.getLineNumber());
        System.out.println("elemkezdet:" +localName);
        for(int i=0; i<atts.getLength();i++){
            System.out.println(atts.getLocalName(i)+":"+atts.getValue(i));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("sor:"+this.loc.getLineNumber());
        System.out.println("elem vege:"+localName +" 0");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String ered = new String();
        for (int i=start; i<start+length;i++){
            ered +=ch[i];
        }
        System.out.println("sor:" +this.loc.getLineNumber());
        System.out.println(ered);
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }
}
