package org.buffalocoder.quanlybangdia.XML;

import org.buffalocoder.quanlybangdia.models.TaiKhoan;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class QuanLyXML {
    private static final String FILEPATH_PROPERTISE = "resources/properties.xml";
    private static final String FILEPATH_THEMES = "resources/themes.xml";

    private static DocGhiFileXML docGhiFileXML;
    private static Document document;

    public QuanLyXML(){
        docGhiFileXML = new DocGhiFileXML();
    }

    public String[] getConfigDatabase(){
        String config[] = new String[5];

        try{
            document = docGhiFileXML.docFileXML(FILEPATH_PROPERTISE);
            Element rootElement = document.getDocumentElement();
            Element sqlElement = (Element) rootElement.getElementsByTagName("SQL").item(0);

            config[0] = sqlElement.getElementsByTagName("server").item(0).getTextContent();
            config[1] = sqlElement.getElementsByTagName("port").item(0).getTextContent();
            config[2] = sqlElement.getElementsByTagName("database").item(0).getTextContent();
            config[3] = sqlElement.getElementsByTagName("username").item(0).getTextContent();
            config[4] = sqlElement.getElementsByTagName("password").item(0).getTextContent();

           return config;
        }catch (Exception e){
            return null;
        }
    }

    public TaiKhoan getGhiNhoTaiKhoan(){
        TaiKhoan taiKhoan = new TaiKhoan();

        try{
            document = docGhiFileXML.docFileXML(FILEPATH_PROPERTISE);
            Element rootElement = document.getDocumentElement();
            Element accountElement = (Element) rootElement.getElementsByTagName("Account").item(0);

            // kiểm tra xem người dùng có ghi nhớ mật khẩu không
            int remember = Integer.parseInt(accountElement.getAttributes().getNamedItem("remember").getTextContent());
            if (remember == 1){
                taiKhoan.setTenTaiKhoan(accountElement.getElementsByTagName("username")
                        .item(0).getTextContent());

                taiKhoan.setMatKhau(accountElement.getElementsByTagName("password")
                        .item(0).getTextContent());

                return taiKhoan;
            }
        }catch (Exception e){
            return null;
        }

        return null;
    }

    public boolean ghiNhoTaiKhoan(TaiKhoan taiKhoan){
        try{
            document = docGhiFileXML.docFileXML(FILEPATH_PROPERTISE);

            Element rootElement = document.getDocumentElement();
            Element accountElement = (Element) rootElement.getElementsByTagName("Account").item(0);

            if (taiKhoan != null){
                accountElement.getAttributes().getNamedItem("remember").setTextContent("1");
                accountElement.getElementsByTagName("username").item(0).setTextContent(taiKhoan.getTenTaiKhoan());
                accountElement.getElementsByTagName("password").item(0).setTextContent(taiKhoan.getMatKhau());
            }else{
                accountElement.getAttributes().getNamedItem("remember").setTextContent("0");
            }

            docGhiFileXML.ghiFileXML(document, FILEPATH_PROPERTISE);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean setIDColor(String id){
        try{
            document = docGhiFileXML.docFileXML(FILEPATH_PROPERTISE);

            Element rootElement = document.getDocumentElement();
            Element settingElement = (Element) rootElement.getElementsByTagName("Setting").item(0);

            settingElement.getElementsByTagName("themes").item(0).setTextContent(id);

            docGhiFileXML.ghiFileXML(document, FILEPATH_PROPERTISE);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getIDThemes(){
        try{
            document = docGhiFileXML.docFileXML(FILEPATH_PROPERTISE);
            Element rootElement = document.getDocumentElement();
            Element settingElement = (Element) rootElement.getElementsByTagName("Setting").item(0);

            return settingElement.getElementsByTagName("themes").item(0).getTextContent();
        }catch (Exception e){
            return "blue";
        }
    }

    public String getColorPrimary(String id){
        try{
            document = docGhiFileXML.docFileXML(FILEPATH_THEMES);
            Element rootElement = document.getDocumentElement();
            NodeList templateElement = rootElement.getElementsByTagName("Template");

            Element theme = timMauTheoID(templateElement, id);

            return theme.getElementsByTagName("primary").item(0).getTextContent();
        }catch (Exception e){
            return "#1976d2";
        }
    }

    public String getColorSecondary(String id){
        try{
            document = docGhiFileXML.docFileXML(FILEPATH_THEMES);
            Element rootElement = document.getDocumentElement();
            NodeList templateElement = rootElement.getElementsByTagName("Template");

            Element theme = timMauTheoID(templateElement, id);

            return theme.getElementsByTagName("secondary").item(0).getTextContent();
        }catch (Exception e){
            return "#2196f3";
        }
    }

    public String getColorDark(String id){
        try{
            document = docGhiFileXML.docFileXML(FILEPATH_THEMES);
            Element rootElement = document.getDocumentElement();
            NodeList templateElement = rootElement.getElementsByTagName("Template");

            Element theme = timMauTheoID(templateElement, id);

            return theme.getElementsByTagName("dark").item(0).getTextContent();
        }catch (Exception e){
            return "#0d47a1";
        }
    }

    public String getColorBackground(String id){
        try{
            document = docGhiFileXML.docFileXML(FILEPATH_THEMES);
            Element rootElement = document.getDocumentElement();
            NodeList templateElement = rootElement.getElementsByTagName("Template");

            Element theme = timMauTheoID(templateElement, id);

            return theme.getElementsByTagName("background").item(0).getTextContent();
        }catch (Exception e){
            return "#e1f5fe";
        }
    }

    public Element timMauTheoID(NodeList nodeList, String id){
        for (int i = 0; i < nodeList.getLength(); i++){
            if (nodeList.item(i).getAttributes().item(0).getTextContent().equals(id))
                return (Element)nodeList.item(i);
        }

        return null;
    }
}
