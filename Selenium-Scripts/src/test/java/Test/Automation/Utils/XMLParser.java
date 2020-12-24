package Test.Automation.Utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static Test.Automation.Utils.DataPool.parseXMLCoumnsExcel;

public class XMLParser {

    public static Map<String,String> convertNodesfromXMLfile(String XMLfileName) throws Exception{
//            File file = new File("src/" + XMLfileName);
            InputStream xmlfile = new ByteArrayInputStream(XMLfileName.getBytes());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(xmlfile);
            return createMap(document.getDocumentElement());
    }

    public static String XMLfiletoXMLString(String XMLfileName) throws Exception{
//        File xmlFile = new File(XMLfileName);
        //parseXMLCoumnsExcel(XMLfileName);
        File xmlFile = new File("src/" + XMLfileName);
        Reader fileReader = new FileReader(xmlFile);
        BufferedReader bufReader = new BufferedReader(fileReader);
        StringBuilder sb = new StringBuilder();
        String line = bufReader.readLine();
        while (line!= null){
            sb.append(line).append("\n");
            line = bufReader.readLine();
        }
        String xml = sb.toString();
        bufReader.close();
        //System.out.println(xml);
        return xml;
    }

    private static Map<String, String> createMap(Node node) throws IOException {

        Map<String, String> map = new HashMap<String, String>();
        NodeList nodeList = node.getChildNodes();
        System.out.println("<Length>"+nodeList.getLength()+"</Length>");
        for(int i =0; i< nodeList.getLength(); i++){
            Node currentNode = nodeList.item(i);
            if(currentNode.hasAttributes()){
                for(int j = 0;j<currentNode.getAttributes().getLength(); j++){
                    Node item = currentNode.getAttributes().item(i);
                    if(item != null){
                        map.put(item.getNodeName(),item.getTextContent());
                        System.out.println("<1"+item.getNodeName()+">"+item.getNodeName()+" - "+item.getTextContent()+"</"+item.getNodeName()+">");
                    }
                }
            }
            if(node.getFirstChild() != null && node.getFirstChild().getNodeType() == Node.ELEMENT_NODE){
                map.putAll(createMap(currentNode));
                System.out.println("<2"+currentNode.getNodeName()+">"+createMap(currentNode)+"</"+currentNode.getNodeName()+">");
            }else{
                map.put(node.getLocalName(),node.getTextContent());
                    ExcelFileManager.writeexcelvalue("Result", "productsample", i+1, i,node.getTextContent());
                    System.out.println("<4"+node.getNodeName()+">"+node.getLocalName()+" - "+node.getTextContent()+"</"+node.getNodeName()+">");
            }
        }
        return map;
    }
}
