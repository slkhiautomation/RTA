package Test.Automation.DataProvider;

import static Test.Automation.Utils.DataPool.readExcelData;

/**
 * Created by
 */
public class LoginData {

    String excelSheetName;
    String excelSheetTab;
    String caseID;

    public LoginData(String excelSheetName, String excelSheetTab, String caseID){
        this.excelSheetName = excelSheetName;
        this.excelSheetTab = excelSheetTab;
        this.caseID = caseID;
    }

    public String getUsername() throws Throwable{
        return readExcelData(excelSheetName,excelSheetTab,caseID).get("User").toString();
    }

    public String getPassword() throws Throwable{
        return readExcelData(excelSheetName,excelSheetTab,caseID).get("Password").toString();
    }

    public String getName() throws Throwable {
        return readExcelData(excelSheetName,excelSheetTab,caseID).get("name").toString();
    }
}
