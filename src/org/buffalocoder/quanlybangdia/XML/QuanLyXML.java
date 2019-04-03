package org.buffalocoder.quanlybangdia.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;

public class QuanLyXML {

//    Đọc file properties và file themes
    private Document document = DocGhiFileXML.docFileXML("properties.xml");
    private Document docColors = DocGhiFileXML.docFileXML("themes.xml");
//    Lấy Element
    private DocGhiFileXML dg = new DocGhiFileXML();
    private NodeList dsColors = docColors.getElementsByTagName("Template");
    private NodeList dsPrimary = docColors.getElementsByTagName("primary");
    private NodeList dsSecondary = docColors.getElementsByTagName("secondary");
    private NodeList dsDark = docColors.getElementsByTagName("dark");
    private NodeList dsBackground = docColors.getElementsByTagName("background");
    public QuanLyXML(){

    }
    public void hienThiDSXML(){
        NodeList dstk = document.getElementsByTagName("username");
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
            dg.docFileXML("properties.xml");
        }catch (Exception e){

        }
        Element tk = document.createElement("username");
        tk.setTextContent(taikhoan);
        Element pass = document.createElement("password");
        pass.setTextContent(password);
//        Lấy phần tử root của NodeList
        Element goc = document.getDocumentElement();
        goc.insertBefore(pass, goc.getElementsByTagName("username").item(0));
        goc.insertBefore(tk, goc.getElementsByTagName("username").item(0));
        dg.ghiXML(document, "properties.xml");
    }
    public  void xoaXML() throws TransformerException, IOException {
        try {
            dg.docFileXML("properties.xml");
        }catch (Exception e){

        }
        NodeList dstk = document.getElementsByTagName("username");
        NodeList dspw = document.getElementsByTagName("password");
        for (int i = 0; i<dstk.getLength(); i++){
            Element tk = (Element) dstk.item(i);
            Element pw = (Element) dspw.item(i);
//            Xóa node con trên nodelist
            document.getDocumentElement().removeChild(tk);
            document.getDocumentElement().removeChild(pw);
        }
        dg.ghiXML(document, "properties.xml");
    }
//    Color
    public int timMau(String id){
        dg.docFileXML("themes.xml");
        for (int i = 0; i< dsColors.getLength();i++){
            if(id.equals(dsColors.item(i).getAttributes().getNamedItem("id").getTextContent()))
                return i;
        }
        return -1;
    }
    public String getColorSelected(String id){
        if(timMau(id)!=-1)
            return String.valueOf(dsColors.item(0).getTextContent());
        else
            return null;
    }
    public void setRememberColor(String id) throws TransformerException, IOException {
        document.getElementsByTagName("Color").item(0).setTextContent(id);
        dg.ghiXML(document, "properties.xml");
    }
    public String getRememberColor(){
        return document.getElementsByTagName("Color").item(0).getTextContent();
    }
    public String getPrimaryColor(String id){
        if(timMau(id)!=-1)
            return String.valueOf(dsPrimary.item(timMau(id)).getTextContent());
        else
        {
            System.out.println("KO tìm thấy màu");
            return null;
        }
    }
    public String getSecondary(String id){
        if(timMau(id)!=-1)
            return String.valueOf(dsSecondary.item(timMau(id)).getTextContent());
        else
        {
            System.out.println("KO tìm thấy màu");
            return null;
        }
    }
    public String getDark(String id){
        if(timMau(id)!=-1)
            return String.valueOf(dsDark.item(timMau(id)).getTextContent());
        else
        {
            System.out.println("KO tìm thấy màu");
            return null;
        }
    }
    public String getBackground(String id){
        if(timMau(id)!=-1)
            return String.valueOf(dsBackground.item(timMau(id)).getTextContent());
        else
        {
            System.out.println("KO tìm thấy màu");
            return null;
        }
    }
//    Account
    public String getTextContentTK(){
        return document.getElementsByTagName("username").item(0).getTextContent();
    }
    public String getTextContentPW(){
        return document.getElementsByTagName("password").item(0).getTextContent();
    }
//    remember
    public void ghiNhoAccount(String tk, String pass) throws TransformerException, IOException {
    xoaXML();
    themXML(tk, pass);
}
    public int getRemember(){
        return Integer.parseInt(document.getElementsByTagName("remember").item(0).getTextContent());
    }
    public void setRemember(int i){
        document.getElementsByTagName("remember").item(0).setTextContent(String.valueOf(i));
    }
}
