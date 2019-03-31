package org.buffalocoder.quanlybangdia.XML;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class DocGhiFileXML {

    public static Document docFileXML(){
        Document document = null;
        File f = new File("src/Account.xml");

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }
    public void ghiXML(Document doc) throws TransformerException, IOException {
        File f = new File("src/Account.xml");
        DOMSource ds = new DOMSource((Node)doc);
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
//        transformer.setOutputProperties("{http://xml.apache.org/xslt}indent-amount", "4");
//        transformer.setOutputProperties(OutputKeys.INDENT, "yes");
        FileWriter fileWriter = new FileWriter(f);
        StreamResult sr = new StreamResult(fileWriter);
        Source dOMSource;
        transformer.transform(ds, sr);
    }
}
