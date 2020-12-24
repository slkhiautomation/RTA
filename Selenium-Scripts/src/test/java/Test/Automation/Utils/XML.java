package Test.Automation.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XML {

    public static void xmltagName(String fileName, String Roottag, String ID,String resultFile,String sheetName) throws Exception {
        try {
                    //**** Read XML File ****//
            File file = new File("src/" + fileName);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element " + doc.getDocumentElement().getNodeName());
                //**** Read XML File ****//

                //**** Get All Tags ****//
            NodeList nodeList = doc.getElementsByTagName("*");
            ArrayList heading = new ArrayList<String>();
            HashMap duplicate = new HashMap();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String tagName = element.getNodeName();
                if(tagName.equals("custom-attribute") ){//Attribute List Name//
                    String nodeName = element.getNodeName();
                    String attributeName = element.getAttributes().item(0).getNodeValue();
                    tagName = nodeName + " || " +attributeName;
//                    String tagValue = element.getTextContent();
                }
                if (!duplicate.containsKey(tagName) && ! element.getNodeName().equals("custom-attributes") ) {//Tag of Attribute List Name//
                    duplicate.put(tagName, tagName);
                    heading.add(tagName);
                }

            }
                //**** Get All Tags ****//

            //**** Insert Columns and Values in Excel ****//
            int i = -1;
            int col = 0;
            System.out.println("Total Lop count: "+heading.size());

            for (Object head : heading) {
                String columnName = (String) head;
                if (!columnName.equals(Roottag)) {
                    ExcelFileManager.writeInCell(resultFile,sheetName,0,i,columnName);
                    ExcelFileManager.initializeStyleExcel();
                    String attributeName = "";
                    if(columnName.contains("custom-attribute || ")){//Attribute List Name//
                        attributeName = columnName.substring(20);
                        columnName = "custom-attribute";//Attribute List Name//
                    }

                    NodeList nList = doc.getElementsByTagName(columnName);
                        int row = 2;
                        int rowattr = 2;
                    System.out.println("Second Loop Count: "+nList.getLength());
                        for (int l = 0; l < nList.getLength(); l++) {
                            Node node = nList.item(l);
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                Element eElement = (Element) nList.item(l);
                                if(columnName.equals("product")){//Your List Tag Initiater name
                                    String tagValue = eElement.getAttributeNode(ID).getNodeValue();
                                    String dataType = dataType(tagValue);
                                    ExcelFileManager.writeInCell(resultFile,sheetName,1,col,dataType);
                                    ExcelFileManager.initializeStyleExcel();
                                    ExcelFileManager.writeInCell(resultFile,sheetName,row,col,tagValue);
                                    ExcelFileManager.initializeStyleExcel();
//                                    System.out.println("Excel Heading = "+columnName);
//                                    System.out.println("Excel Row = "+row);
//                                    System.out.println("Excel Column = "+col);
//                                    System.out.println("Column Value = "+tagValue);
                                }

                                else if(!columnName.equals("product") && !columnName.equals("custom-attribute")){
                                    String tagValue = eElement.getTextContent();
                                    String dataType = dataType(tagValue);
                                    ExcelFileManager.writeInCell(resultFile,sheetName,1,col,dataType);
                                    ExcelFileManager.initializeStyleExcel();
                                    ExcelFileManager.writeInCell(resultFile,sheetName,row,col,tagValue);
                                    ExcelFileManager.initializeStyleExcel();
//                                    System.out.println("Excel Heading = "+columnName);
//                                    System.out.println("Excel Row = "+row);
//                                    System.out.println("Excel Column = "+col);
//                                    System.out.println("Column Value = "+tagValue);
                                }

                                else if (columnName.equals("custom-attribute") ){

                                    if (eElement.getAttributes().item(0).getNodeValue().equals(attributeName)) {
                                        String tagValue =  eElement.getTextContent();
                                        String dataType = dataType(tagValue);
                                        ExcelFileManager.writeInCell(resultFile,sheetName,1,col,dataType);
                                        ExcelFileManager.initializeStyleExcel();
                                        ExcelFileManager.writeInCell(resultFile, sheetName, rowattr, col, tagValue);
                                        ExcelFileManager.initializeStyleExcel();
//                                        System.out.println("Excel Heading = "+columnName+" || "+attributeName);
//                                        System.out.println("Excel Row = "+rowattr);
//                                        System.out.println("Excel Column = "+col);
//                                        System.out.println("Column Value = "+tagValue);
                                        rowattr++;
                                    }
                                }
                            }
                            row++;
                        }
                        col++;
                }
                i++;
            }

            //**** Insert Columns and Values in Excel ****//
        } catch (Exception ex) {
            System.out.println("Re-Check your "+fileName);
            ex.printStackTrace();
        }
    }

    public boolean dataTypeVaidator(String tagvalue,String dataType) throws Exception{
        boolean isValid = false;
        try{
            String pattern = "";
            if(dataType.equals("String")){
                pattern = "//[^\\r\\n]*[\\r\\n]";
            }else if(dataType.equals("Int")){
                pattern = "^[0-9]+$";
            }else if(dataType.equals("boolean")){
                pattern = "^([Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee]|[Yy][Ee][Ss]|[Nn][Oo])$";
            }else if(dataType.equals("Email")){
                pattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
            }else if(dataType.equals("ZipCode")){
                pattern = "^((\\d{5}-\\d{4})|(\\d{5})|([A-Z]\\d[A-Z]\\s\\d[A-Z]\\d))$";
            }
            Pattern pat = Pattern.compile(pattern);
            Matcher mat = pat.matcher(tagvalue);
            if(mat.find()){
                System.out.println(pattern + " matches \"" +
                        mat.group(0) +
                        "\" in \"" + tagvalue + "\"");
            }else{
                System.out.println(tagvalue+" not matched with Data Type: "+dataType);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return isValid;
    }

    public static String dataType(String tagvalue) throws Exception{
        String Valid = "";
        try{
            if(!tagvalue.equals("")) {
                boolean isResult = false;
                String alphanumeric = "^[a-zA-Z0-9\\s.\\-]+$";
                String string = "^(a-z|A-Z|0-9)*[^#$%^&*()']*$";
                String phone = "^(1?(-?\\d{3})-?)?(\\d{3})(-?\\d{4})$";
                String Int = "^[0-9]+$";
                String Float = "^\\d*\\.?\\d*$";
                String Boolean = "^([Vv]+(erdade(iro)?)?|[Ff]+(als[eo])?|[Tt]+(rue)?|0|[\\+\\-]?1)$";
                String bool = "^([Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee]|[Yy][Ee][Ss]|[Nn][Oo])$";
                String email = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
                String ZipCode = "^((\\d{5}-\\d{4})|(\\d{5})|([A-Z]\\d[A-Z]\\s\\d[A-Z]\\d))$";
                Pattern pat;
                Matcher mat;
                if (isResult == false) {
                    pat = Pattern.compile(string);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "String";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!String";
                        isResult = false;
                    }
                }
                if (isResult == false) {
                    pat = Pattern.compile(alphanumeric);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "String";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!String";
                        isResult = false;
                    }
                }
                if (isResult == false) {
                    pat = Pattern.compile(phone);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "Valid Phone Number";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "Invalid Phone Number";
                        isResult = false;
                    }
                }
                if (isResult == false) {
                    pat = Pattern.compile(Int);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "Int";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!Int";
                        isResult = false;
                    }
                }
                if (isResult == true && Valid.equals("String") && tagvalue.matches("([0-9])")) {
                    pat = Pattern.compile(Int);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "Int";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!Int";
                        isResult = false;
                    }
                }
                if (isResult == true && Valid.equals("Int") && tagvalue.equals("0")) {
                    pat = Pattern.compile(Boolean);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "boolean";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!boolean";
                        isResult = true;
                    }
                }
                if (isResult == true && Valid.equals("Int") && tagvalue.equals("1")) {
                    pat = Pattern.compile(Boolean);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "boolean";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!boolean";
                        isResult = true;
                    }
                }
                if (isResult == true && Valid.equals("String") && tagvalue.matches("^\\d*\\.?\\d*$")) {
                    pat = Pattern.compile(Float);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "float";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!float";
                        isResult = false;
                    }
                }
                if (isResult == true && Valid.equals("Int") && tagvalue.matches("^\\d*\\.?\\d*$")) {
                    pat = Pattern.compile(Float);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "float";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!float";
                        isResult = false;
                    }
                }
                if (isResult == true && Valid.equals("String") && tagvalue.equals("true")) {
                    pat = Pattern.compile(bool);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "boolean";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!boolean";
                        isResult = true;
                    }
                }
                if (isResult == true && Valid.equals("String") && tagvalue.equals("false")) {
                    pat = Pattern.compile(bool);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "boolean";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!boolean";
                        isResult = true;
                    }
                }
                if (isResult == true && Valid.equals("String") && tagvalue.equals("Yes")) {
                    pat = Pattern.compile(bool);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "boolean";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!boolean";
                        isResult = true;
                    }
                }
                if (isResult == true && Valid.equals("String") && tagvalue.equals("No")) {
                    pat = Pattern.compile(bool);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "boolean";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!boolean";
                        isResult = true;
                    }
                }
                if (isResult == true && Valid.equals("String") && tagvalue.matches("^([Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee]|[Yy][Ee][Ss]|[Nn][Oo])$")) {
                    pat = Pattern.compile(bool);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "boolean";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "!boolean";
                        isResult = true;
                    }
                }
                if (isResult == false) {
                    pat = Pattern.compile(email);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "Valid Email";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "Invalid Email";
                        isResult = false;
                    }
                }
                if (isResult == false) {
                    pat = Pattern.compile(ZipCode);
                    mat = pat.matcher(tagvalue);
                    if (mat.find()) {
                        Valid = "Valid ZipCode";
                        isResult = true;
                        System.out.println(tagvalue + " is " + Valid);
                    } else {
                        Valid = "N/A";
                        isResult = false;
                    }
                }
            }else {
                Valid = "N/A";
            }

        }catch (Exception ex){
            ex.printStackTrace();
            Valid = "Unable to recognize Data Type";
        }
        return Valid;
    }
}
