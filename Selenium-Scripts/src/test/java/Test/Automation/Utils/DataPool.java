package Test.Automation.Utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;
import java.util.List;

public class DataPool {

	public static HashMap<String, String> readExcelData(String excelFileName, String sheetName, String rowToMatch)
			throws IOException {


		InputStream excelFileTestdata = new FileInputStream("src/" + excelFileName + ".xlsx");

		// Create the XSSF workbook and sheet object
		XSSFWorkbook workbook = new XSSFWorkbook(excelFileTestdata);
		XSSFSheet currentSheet = workbook.getSheet(sheetName);

		// Get the Last ROW and Column number
		XSSFRow titleRow = currentSheet.getRow(0);
		int lastCol = titleRow.getLastCellNum();
		int lastRow = currentSheet.getLastRowNum();

		Object[][] Testdata2DArray = new Object[1][2];
		HashMap<String, String> rowTestdataHashMap = new HashMap<String, String>();

		int rowTestdata = 0;
		for (int row = 1; row <= lastRow; row++) {
			if (currentSheet.getRow(row).getCell(0) == null) {
				rowTestdata = 10000;
			} else if (currentSheet.getRow(row).getCell(0).toString().equals(rowToMatch)) {
				rowTestdata = row;
			}
		}

		XSSFRow currentRow = currentSheet.getRow(rowTestdata);
		Cell firstCell = currentRow.getCell(0);

		for (int col = 0; col < lastCol - 1; col++) {

			if (currentRow.getCell(col + 1) == null) {
				rowTestdataHashMap.put(titleRow.getCell(col + 1).getStringCellValue(), "null");
			} else {
				rowTestdataHashMap.put(titleRow.getCell(col + 1).getStringCellValue(),
						currentRow.getCell(col + 1).getStringCellValue());
			}
		}

		Testdata2DArray[0][0] = firstCell.getStringCellValue();
		Testdata2DArray[0][1] = rowTestdataHashMap.clone();

		workbook.close();
		excelFileTestdata.close();
		return rowTestdataHashMap;
	}

	public static HashMap<String, String> readExcelData(String rowToMatch) throws IOException {

		InputStream excelFileTestdata = new FileInputStream("src/Testdata.xlsx");
		String currentExcelSheet = "login_credentials";

		// Create the XSSF workbook and sheet object
		XSSFWorkbook workbook = new XSSFWorkbook(excelFileTestdata);
		XSSFSheet currentSheet = workbook.getSheet(currentExcelSheet);

		// Get the Last ROW and Column number
		XSSFRow titleRow = currentSheet.getRow(0);
		int lastCol = titleRow.getLastCellNum();
		int lastRow = currentSheet.getLastRowNum();

		Object[][] Testdata2DArray = new Object[1][2];
		HashMap<String, String> rowTestdataHashMap = new HashMap<String, String>();

		int rowTestdata = 0;
		for (int row = 1; row <= lastRow; row++) {
			if (currentSheet.getRow(row).getCell(0) == null) {
				rowTestdata = 10000;
			} else if (currentSheet.getRow(row).getCell(0).toString().equals(rowToMatch)) {
				rowTestdata = row;
			}
		}

		XSSFRow currentRow = currentSheet.getRow(rowTestdata);
		Cell firstCell = currentRow.getCell(0);

		for (int col = 0; col < lastCol - 1; col++) {

			if (currentRow.getCell(col + 1) == null) {
				rowTestdataHashMap.put(titleRow.getCell(col + 1).getStringCellValue(), "null");
			} else {
				rowTestdataHashMap.put(titleRow.getCell(col + 1).getStringCellValue(),
						currentRow.getCell(col + 1).getStringCellValue());
			}
		}

		Testdata2DArray[0][0] = firstCell.getStringCellValue();
		Testdata2DArray[0][1] = rowTestdataHashMap.clone();

		workbook.close();
		excelFileTestdata.close();
		return rowTestdataHashMap;
	}



	public static void writeInExcelData(String dataToUpdate, Integer rowNumber, Integer cellNumber, String sheetName) throws IOException {

		InputStream excelFileTestdata = new FileInputStream("src/Result.xlsx");

		// Create the XSSF workbook and sheet object
		XSSFWorkbook workbook = new XSSFWorkbook(excelFileTestdata);
		XSSFSheet currentSheet = workbook.getSheet(sheetName);
		
        Cell cell2Update = currentSheet.getRow(rowNumber).getCell(cellNumber);
        cell2Update.setCellValue(dataToUpdate);

        excelFileTestdata.close();
        FileOutputStream outputStream = new FileOutputStream("src/Result.xlsx");
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
	}

	/**
	 * Returns a Node by attribute name and attribute value.
	 * @param nodeList  NodeList where we find the node
	 * @param attributeName Attribute name to search
	 * @param attributeValue Attribute name to search
	 * @return If exists a node with attributeName and attributeValue returns this Node,
	 *      if not exists, returns null
	 */
	private static Node returnNodeByValue(NodeList nodeList, String attributeName, String attributeValue) {
		for (int temp = 0; temp < nodeList.getLength(); temp++) {

			Node nNode = nodeList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) nNode;
				String returnAttribute = eElement.getAttribute(attributeName);
				if(returnAttribute.equals(attributeValue)){
					return nNode;
				}
			}

		}
		return null;
	}

	public static int tagCount(String filename,String tagName){
		int count = 0;
		String path = "src\\";
		String inputFile = path+filename;
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(inputFile);
			NodeList nodeList = document.getElementsByTagName(tagName);
			System.out.println("Number of elements with tag name "+tagName+" : " + nodeList.getLength());
			for (int temp = 0; temp < nodeList.getLength(); temp++){
				Node node = nodeList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) node;
					String nodename = eElement.getNodeName();
//					String tagName = eElement.getChildNodes()
					String tagvalue = eElement.getTextContent();
					System.out.println(eElement.getChildNodes());

					//System.out.println(" Node Name is "+nodename+": Value is "+tagvalue);
				}
			}
			count = nodeList.getLength();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException sae) {
			sae.printStackTrace();
		}
		return count;
	}

	public static String parseXML(String fileName,String tagName,String id) throws IOException, ParserConfigurationException, SAXException {
		String tagValue="";
//		try{
			String path = "src\\";
			String inputFile = path+fileName;
//			File file = new File("src/"+fileName);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(inputFile));
			document.getDocumentElement().normalize();
			NodeList nList = document.getElementsByTagName(tagName);
			//NodeList nList = document.getDocumentElement().getChildNodes();
			System.out.println("Loop will repeat for "+nList.getLength());
			for (int temp = 0; temp < nList.getLength(); temp++){
				Node node = nList.item(temp);
				if (node.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) node;
					try {
						tagValue = eElement.getAttributes().getNamedItem(id).getNodeValue();
						ExcelFileManager.writeexcelvalue("Result","productsample",temp+1,0,tagValue);
						System.out.println(tagValue);
					}catch (Exception e){
						tagValue = "Value of " + id + " not found";
						ExcelFileManager.writeexcelvalue("Result","productsample",temp+1,0,tagValue);
					}
					int loopcount = eElement.getChildNodes().getLength();
					System.out.println(eElement.getChildNodes().getLength());
					for(int i = 0; i<loopcount; i++){
						String rowtomatch = "ProductID";
						String childtagName = "";
						if(rowtomatch.equals("ProductID")){
							rowtomatch = "ProductID";

						}else{
							rowtomatch = readExcelData("Result","productsample",childtagName).toString();
						}
						try{
							childtagName = ExcelFileManager.readFromCell("Result","productsample",0,rowtomatch);
							System.out.println(childtagName);
							//childtagName = readExcelData("Result","productsample",rowtomatch).toString();
							tagValue = eElement.getElementsByTagName(childtagName).item(0).getTextContent();
							ExcelFileManager.writeInCell("Result", "productsample", temp + 1, i+1, tagValue);
							System.out.println(tagValue);

						}catch (Exception e) {
							childtagName = ExcelFileManager.readFromCell("Result","productsample",0,rowtomatch);
							System.out.println(childtagName);
							tagValue = "Value of " + childtagName + " not found";
							ExcelFileManager.writeInCell("Result", "productsample", temp + 1, i+1, tagValue);
							System.out.println(tagValue);
						}
					}

//					try{
//						tagValue = eElement.getElementsByTagName(added_Tag).item(0).getTextContent();
//						ExcelFileManager.writeInCell("Result","Product",temp+1,2,tagValue);
//						System.out.println(tagValue);
//
//					}catch (Exception e) {
//						tagValue = "Value of " + added_Tag + " not found";
//						ExcelFileManager.writeInCell("Result", "Product", temp + 1, 1, tagValue);
//					}
				}
			}
//		}
//		catch (Exception ex){
//			System.out.println(ex);
//		}
		return tagValue;
	}

	public static String readXML(String fileName,String nodeName ){
		String newvalue = "";
		try{
			File file = new File("src/"+fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);

			doc.getDocumentElement().normalize();

			NodeList nList = doc.getDocumentElement().getChildNodes();
			NodeList childrenOfColumns = null;
			NodeList childofchildrenOfColumns = null;
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					if(nNode.getNodeName().equals(nodeName)){
						Element eElement = (Element) nNode;
						childrenOfColumns = eElement.getChildNodes();
					}
				}
			}

			for (int temp=0; temp < childrenOfColumns.getLength(); temp++){
				Node nNode = childrenOfColumns.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					//System.out.println("\nCurrent Element :" + nNode.getNodeName());

					Element eElement = (Element) nNode;
					childofchildrenOfColumns = eElement.getChildNodes();
				}
			}

			System.out.println("++++++++++++++++++++++++++++");
			System.out.println(" Validate New Added Value ");
			System.out.println("++++++++++++++++++++++++++++");

			for (int temp = 0; temp < childofchildrenOfColumns.getLength(); temp++) {

				Node nNode = childofchildrenOfColumns.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					//System.out.println("\nCurrent Element :" + nNode.getNodeName());
					Element eElement = (Element) nNode;
					System.out.println("<"+nNode.getNodeName()+" attribute-id="+""+">"+eElement.getTextContent()+"</"+nNode.getNodeName()+">");
					newvalue = eElement.getTextContent();
				}
			}
		}
		catch (Exception e){

		}
		return newvalue;
	}

	public static void readXMLColumnName(String fileName) {
		try{
			File file = new File("src/"+fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			Element docEl = doc.getDocumentElement();
			Node childNode = docEl.getFirstChild();
			NodeList childrenOfColumns = null;
			while( childNode.getNextSibling()!=null ){
				childNode = childNode.getNextSibling();
				if (childNode.getNodeType() == Node.ELEMENT_NODE) {
					Element childElement = (Element) childNode;
					childrenOfColumns = childElement.getChildNodes();
					Element eElement = (Element) childrenOfColumns;
					System.out.println("NODE Name is:-" + eElement.getTagName() + "<br/>\n" );
					System.out.println("NODE Name:-" + childElement.getAttribute("num") + "<br/>\n" );
				}
			}


		}catch (Exception e){
			System.out.println(e);
		}

	}

	public static void parseXMLCoumnsExcel(String fileName,String id)  {
		try {
			File file = new File("src/" + fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("product").item(0).getChildNodes();
			nList.getLength();
			int col = 1;
			int row = 1;
			for (int j = 0; j < nList.getLength(); j++) {
				Node nNode = nList.item(j);
				String tagname = "";
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					String tagValue = "";
					Element eElement = (Element) nNode;
					if(row==10){
						System.out.println(id);
						tagValue = eElement.getAttributes().getNamedItem(id).getNodeValue();
//						tagValue = eElement.getAttributeNode(id).getValue();
						ExcelFileManager.writeInCell("Result", "productsample", 0, 0, "Product ID");
						ExcelFileManager.writeInCell("Result","productsample",1,0,tagValue);
						System.out.println(tagValue);
					}else{
						tagname = eElement.getNodeName();
						System.out.println(col + "-" + tagname);
						ExcelFileManager.writeInCell("Result", "productsample", row, col, tagname);

						try{
							tagValue = eElement.getTextContent();
							ExcelFileManager.writeInCell("Result","productsample",1,col,tagValue);
							System.out.println("Value of "+tagname + "-"+tagValue+" inserted in row "+row+" column "+ col);
						}catch (Exception ex){
							tagValue = "Not Found";
							ExcelFileManager.writeInCell("Result","productsample",1,col,tagValue);
							System.out.println("Value of "+tagname + "-"+tagValue+" inserted in row "+row+" column "+ col);
						}
					}
					col++;
				}
			}
			row++;
		}catch (Exception ex){
			ex.printStackTrace();
			System.out.println(ex);
		}
	}
}
