package Test.Automation.Stepdefinitions;

import Test.Automation.Pages.CommonPage;
import Test.Automation.Stepdefinitions.StartingSteps;
import Test.Automation.Utils.DriverFactory;
import Test.Automation.Utils.PropertyReader;
import Test.Automation.Utils.UtilityMethods;
import com.cucumber.listener.Reporter;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static Test.Automation.Utils.ExcelFileManager.readFromCell;

public class DBStepDefs extends DriverFactory {
    public static String resultValue;
    public static int resultCount;
    public static String result;
    public static String Status;
    public static String DatafileName =(new PropertyReader().readProperty("appConfigFile"));

    private CommonPage commonPage;
    private StartingSteps startPage;

    public DBStepDefs() throws Exception {
        commonPage = new CommonPage(driver);
        startPage = new StartingSteps();
    }

    @Given("^Execute Query for \"([^\"]*)\" and Get Column \"([^\"]*)\"$")
    public void execute_Query_for_and_Get_Column(String Query, String Columns) throws Throwable {
        String column1 = Columns;
        String column2 = Columns;
        if(Columns.contains(",")){
            String string = Columns;
            String[] parts = string.split(",");
            String part1 = parts[0]; // 004
            String part2 = parts[1]; // 034556

            column1 = part1;
            column2 = part2;

            System.out.println(column1);
            System.out.println(column2);
        }

        String query = readFromCell(DatafileName,"DB",1,Query);
        if(query.contains("$Value$")){
            String s1=query;
            String replaceString=s1.replace("$Value$",resultValue);
            System.out.println(replaceString);
            query = replaceString;
        }
        System.out.println(query);
        Reporter.addStepLog("Executed Query "+query);
        if (conn == null) {
            System.out.println("Unable to create Connection");
        } else {
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            ResultSet rs=stmt.executeQuery(query);
            while (rs.next()){
                if(column2!=Columns){
                    result = rs.getString(column1);
                    Status = rs.getString(column2);
                    if(Status.equals(true)){
                        Status = "ACTIVATED";
                        System.out.println(column1+" is "+Status);

                    }else
                        Status = "DEACTIVATED";
                    if(Status.equals("DEACTIVATED")){
                        String Actualtitlte = driver.getTitle();
                        String ExpectedTitle = new PropertyReader().readProperty("Title");
                        System.out.println(Actualtitlte);
                        System.out.println(ExpectedTitle);
                        if(!Actualtitlte.equals(ExpectedTitle)){
                            startPage.navigate_to_Application();
                        }
                        driver.navigate().refresh();
                        UtilityMethods.wait3Seconds();
                        System.out.println(result+" is loging in");
                        commonPage.enterText("LoginPage","userName",result);
                        commonPage.enterText("LoginPage","password","passw0rd");
                        commonPage.Clickelemet("LoginPage","Signin");
                        UtilityMethods.waitForPageLoad();
                        Assert.assertTrue(true);
                        UtilityMethods.wait3Seconds();
                        UtilityMethods.TakeSnapShot();
                        Reporter.addStepLog("Executed Query Result of Column "+column1+" was "+result);
                        Reporter.addStepLog("Executed Query Result of Column "+column2+" was "+Status);
                        Reporter.addStepLog("Case Passed for User "+result);

                    }
                    else {
                        System.out.println(result+" Status is "+Status);
                        Assert.assertFalse(false);
                        Reporter.addStepLog("Executed Query Result of Column "+column1+" was "+result);
                        Reporter.addStepLog("Executed Query Result of Column "+column2+" was "+Status);
                        Reporter.addStepLog("Case Failed for User "+result);
                    }

                }else
                    System.out.println(rs.getString(column1));
                resultValue = rs.getString(column1);
            }
//            conn.close();
        }
    }

//    Query for Insert/Update/Delete
    @When("^Execute Query for \"([^\"]*)\"$")
    public void execute_Query_for(String Query) throws Throwable {
        String query = readFromCell(DatafileName,"DB",1,Query);
        Reporter.addStepLog("Executed Query "+query);
        if (conn == null) {
            System.out.println("Unable to create Connection");
        } else {
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            ResultSet rs=stmt.executeQuery(query);
            while (rs.next()){
                result = rs.getString("GROUP_NAME_EN");
                System.out.println("Executed Query result is "+result);
                Reporter.addStepLog("Executed Query Result was "+result);
            }
            String text = readFromCell(DatafileName,"ConfigurationPage",1,"DefaultNotification");
            System.out.println("Expected Message is "+text);
            String s1=text;
            String resultValue = DBStepDefs.result;
            String message=s1.replace("$Value$",resultValue);
            System.out.println("Expected Message is "+message);
            Reporter.addStepLog("Default Group Notification was "+message);

        }
    }

    @When("Verify Country Result inserted in Enum Table")
    public void verifyCountryResultInsertedInEnumTable() throws Throwable {
        String query = "";
        Reporter.addStepLog("Executed Query "+query);
        if (conn == null) {
            System.out.println("Unable to create Connection");
        } else {
            Statement stmt = conn.createStatement();
            stmt.execute(query);
            ResultSet rs=stmt.executeQuery(query);
            while (rs.next()){
                result = rs.getString(0);
                Reporter.addStepLog("LAStudentService had Country ID Inserted in ENUM Table "+result);
            }
        }
    }

}
