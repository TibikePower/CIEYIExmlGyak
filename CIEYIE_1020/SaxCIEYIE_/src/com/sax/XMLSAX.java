package com.sax;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import java.io.IOException;

public class XMLSAX {
    public void kezelo(String args){
        try{
            XMLReader parser = new SAXParser();
            Skezelo Shand = new Skezelo();
            parser.setContentHandler(Shand);
            parser.parse(args);
        }catch (SAXException | IOException e){
            System.out.println(e.getMessage());
        }
    }
}
