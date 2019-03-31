package org.buffalocoder.quanlybangdia.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class QuanLyXML {

    Document document = DocGhiFileXML.docFileXML();
    DocGhiFileXML dg = new DocGhiFileXML();
    public QuanLyXML(){

    }
    public void hienThiDSXML(){
        NodeList dstk = document.getElementsByTagName("taikhoan");
        NodeList dspw = document.getElementsByTagName("password");
        for (int i = 0; i < dstk.getLength(); i++)
        {
            String tk = dstk.item(i).getTextContent();
            String pass = dspw.item(i).getTextContent();
            System.out.println(tk);
            System.out.println(pass);
        }
    }
    public void themXML(String taikhoan, String password) throws TransformerException, IOException {
        try {
            dg.docFileXML();
        }catch (Exception e){

        }
        Element tk = document.createElement("taikhoan");
        tk.setTextContent(taikhoan);
        Element pass = document.createElement("password");
        pass.setTextContent(password);

        Element goc = document.getDocumentElement();
        goc.insertBefore(pass, goc.getElementsByTagName("taikhoan").item(0));
        goc.insertBefore(tk, goc.getElementsByTagName("taikhoan").item(0));
        dg.ghiXML(document);
        hienThiDSXML();
    }
    public  void xoaXML() throws TransformerException, IOException {
        try {
            dg.docFileXML();
        }catch (Exception e){

        }
        NodeList dstk = document.getElementsByTagName("taikhoan");
        NodeList dspw = document.getElementsByTagName("password");
        for (int i = 0; i<dstk.getLength(); i++){
            Element tk = (Element) dstk.item(i);
            Element pw = (Element) dspw.item(i);
            document.getDocumentElement().removeChild(tk);
            document.getDocumentElement().removeChild(pw);
        }
        dg.ghiXML(document);
    }
    public void ghiNhoAccount(String tk, String pass) throws TransformerException, IOException {
        xoaXML();
        themXML(tk, pass);
    }
    public String getTextContentTK(){
        return document.getElementsByTagName("taikhoan").item(0).getTextContent();
    }
    public String getTextContentPW(){
        return document.getElementsByTagName("password").item(0).getTextContent();
    }
    public int getTrangThai(){
        return Integer.parseInt(document.getElementsByTagName("trangthai").item(0).getTextContent());
    }
    public void setTrangThai(int i){
        document.getElementsByTagName("trangthai").item(0).setTextContent(String.valueOf(i));
    }

}
