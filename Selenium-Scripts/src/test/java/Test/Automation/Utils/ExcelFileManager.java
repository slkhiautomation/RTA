package Test.Automation.Utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.SQLException;

public class ExcelFileManager extends DriverFactory {
    public static PrintWriter writer;
    protected static FileInputStream myxlsFile;
    protected static XSSFWorkbook book;
    protected static XSSFSheet excelSheet;
    protected static Row excelrow;
    protected static XSSFCellStyle fail;
    protected static XSSFCellStyle wrapText;
    protected static XSSFCellStyle pass;
    public ExcelFileManager() throws IOException, SQLException, ClassNotFoundException {
    }

    protected static void createFile(String filename,String Sheet) throws IOException {
        myxlsFile=new FileInputStream("src/" + filename + ".xlsx");
        book= new XSSFWorkbook(myxlsFile);
        excelSheet= book.getSheet(Sheet);
        initializeStyleExcel();

    }
    protected static void initializeStyleExcel(){
        fail=book.createCellStyle();
        pass=book.createCellStyle();
        wrapText=book.createCellStyle();
        fail.setFillForegroundColor(IndexedColors.ROSE.getIndex());
        pass.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        fail.setFillPattern(CellStyle.SOLID_FOREGROUND);
        pass.setFillPattern(CellStyle.SOLID_FOREGROUND);
        fail.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        pass.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        wrapText.setWrapText(true);
        wrapText.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    }
    public static String readFromCell(String fileToRead,String sheetName,int column,String rowToMatch) throws IOException {
        createFile(fileToRead,sheetName);
        int lastrow=excelSheet.getLastRowNum();
        for (int i = 1; i <= lastrow; i++) {
            if (excelSheet.getRow(i).getCell(0) == null) {
                int astrow = 10000;
            } else if (excelSheet.getRow(i).getCell(0).toString().equals(rowToMatch)) {
                excelrow = excelSheet.getRow(i);
            }
        }
        return excelrow.getCell(column).getStringCellValue();

    }
    public static String readCellvalue(String fileToRead, String sheetName, int column, int rowNo) throws IOException {
        createFile(fileToRead,sheetName);

        excelrow=excelSheet.getRow(rowNo);

        return excelrow.getCell(column).getStringCellValue();

    }


//    public static String rowcount(String fileToRead,String sheetName,int column) throws IOException {
//        createFile(fileToRead,sheetName);
//
//        int rowNum = excelSheet.getLastRowNum();
//        System.out.println("Total Number of Rows in the excel is : "+rowNum);
//        return rowNum;
//    }
    public static int getRowCount(String fileToRead, String sheetName) throws IOException{
        createFile(fileToRead,sheetName);
         int rowCount= excelSheet.getLastRowNum();
         return rowCount;

    }
    public static int getColumnCount(String fileToRead, String sheetName) throws IOException{
        createFile(fileToRead,sheetName);
        int columnCount= excelSheet.getRow(0).getLastCellNum();
        return columnCount;

    }

    protected static String readfromExcel(String filename,String Sheetname, int col,String rowToMatch) throws Exception{
        createFile(filename,Sheetname);
        int lastrow = excelSheet.getLastRowNum();
        for(int i = 0; i<= lastrow; i++){
            if(excelSheet.getRow(i).getCell(0)==null){
                excelrow = excelSheet.getRow(i);
            }else if (excelSheet.getRow(i).getCell(0).toString().equals(rowToMatch)) {
                excelrow = excelSheet.getRow(i);
            }
        }
        return excelrow.getCell(col).getStringCellValue();
    }


    public static void writeInCell(String fileToWrite, String sheetName, int row, int column, String message) throws IOException {
        createFile(fileToWrite,sheetName);
        excelrow=excelSheet.getRow(row);
        if(excelrow==null){
            excelrow = excelSheet.createRow(row);
        }
        excelrow.createCell(column).setCellValue(message);
        excelrow.getCell(column).setCellStyle(wrapText);
        closeExcel(fileToWrite);
    }

    public static void writeexcelvalue(String fileToWrite,String sheetName,int row,int column,String message) throws IOException {
        createFile(fileToWrite,sheetName);
        excelrow=excelSheet.createRow(row);
        excelrow.createCell(column).setCellValue(message);
        excelrow.getCell(column).setCellStyle(wrapText);
        closeExcel(fileToWrite);
    }

    public static void compareValues(String fileToWrite,String sheetName,int row,int column,String message) throws IOException{
        createFile(fileToWrite,sheetName);
        excelrow=excelSheet.getRow(row);
        String expected=excelrow.getCell(column-1).getStringCellValue();
        if(expected.equals(message)){
            excelrow.createCell(column+1).setCellValue("Pass");
            excelrow.getCell(column+1).setCellStyle(pass);
        }
        else{
            excelrow.createCell(column+1).setCellValue("Fail");
            excelrow.getCell(column+1).setCellStyle(fail);
        }
        closeExcel(fileToWrite);
    }
    protected static void clearSheet(String fileToWrite,String sheetName) throws IOException {
        createFile(fileToWrite,sheetName);
        for(int i=1;i<=excelSheet.getLastRowNum();i++){

            excelrow = excelSheet.getRow(i);
            excelSheet.removeRow(excelrow);
        }
        closeExcel(fileToWrite);

    }
    public static void addImage(String fileToWrite,String sheetName,int row,String image) throws IOException {
        createFile(fileToWrite,sheetName);
        excelrow=excelSheet.getRow(row);
        excelrow.createCell(4).setCellType(XSSFCell.CELL_TYPE_FORMULA);
        excelrow.getCell(4).setCellFormula("HYPERLINK(\""+image+"\")");
        excelrow.getCell(4).setCellValue("Click Here");
        closeExcel(fileToWrite);

    }

    public static void addGreen(String fileToWrite,String sheetName,int row,int column) throws IOException {
        createFile(fileToWrite,sheetName);
        excelrow=excelSheet.getRow(row);
        excelrow.getCell(column).setCellStyle(pass);
        closeExcel(fileToWrite);
    }
    public static void addRed(String fileToWrite,String sheetName,int row,int column) throws IOException {
        createFile(fileToWrite,sheetName);
        excelrow=excelSheet.getRow(row);
        excelrow.getCell(column).setCellStyle(fail);
        closeExcel(fileToWrite);
    }

    public static void closeExcel(String filename) throws IOException {
        myxlsFile.close();
        FileOutputStream output_file =new FileOutputStream(new File(System.getProperty("user.dir")+"/src/" + filename + ".xlsx"));
        book.write(output_file);
        output_file.close();
    }
}
