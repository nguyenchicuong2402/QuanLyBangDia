package org.buffalocoder.quanlybangdia.utils;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XmlDocument {
    public static Document readDocument(String path){
        Document document = null;
        File file = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try{
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return document;
    }
}
