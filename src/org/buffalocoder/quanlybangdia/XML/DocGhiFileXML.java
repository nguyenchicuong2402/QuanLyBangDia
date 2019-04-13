package org.buffalocoder.quanlybangdia.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;

public class DocGhiFileXML {
    public DocGhiFileXML(){

    }

    /**
     * Phương thức đọc file XML
     * @param filePath
     * @return
     * @throws Exception
     */
    public Document docFileXML(String filePath) throws Exception {
        Document document = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        try {
            File file = new File(filePath);
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(file);
        } catch (Exception e) {
            throw new Exception("Lỗi đọc file cấu hình");
        }

        return document;
    }

    /**
     * Phương thức ghi file XML
     * @param doc
     * @param filePath
     * @throws Exception
     */
    public void ghiFileXML(Document doc, String filePath) throws Exception {
        try{
            File file = new File(filePath);

            DOMSource domSource = new DOMSource((Node)doc);

            TransformerFactory factory = TransformerFactory.newInstance();

            Transformer transformer = factory.newTransformer();

            FileWriter fileWriter = new FileWriter(file);

            StreamResult sr = new StreamResult(fileWriter);

            transformer.transform(domSource, sr);
        }catch (Exception e){
            throw new Exception("Lỗi ghi file cấu hình");
        }
    }
}
