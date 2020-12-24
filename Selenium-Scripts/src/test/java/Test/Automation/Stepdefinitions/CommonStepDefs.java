package Test.Automation.Stepdefinitions;

import Test.Automation.Pages.CommonPage;
import Test.Automation.Utils.DriverFactory;
import Test.Automation.Utils.PropertyReader;
import Test.Automation.Utils.UtilityMethods;
import com.cucumber.listener.Reporter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;

import static Test.Automation.Utils.ExcelFileManager.readFromCell;

public class CommonStepDefs extends DriverFactory {

    private CommonPage commonPage;
    public static String DatafileName =(new PropertyReader().readProperty("appConfigFile"));
    public static int count = 1;
    public static int EN_count = 1;
    public static int AR_count = 1;
    public static int DES_count = 1;

    public CommonStepDefs() throws Exception {
        commonPage = new CommonPage(driver);
    }

    @When("^user enter text \"([^\"]*)\" in \"([^\"]*)\" on \"([^\"]*)\"$")
    public void user_enter_text_in_on(String textKey,String keyName, String sheetName) throws Throwable {
        String text = readFromCell(DatafileName,sheetName,1,textKey);
//        if(textKey.equals("GroupNametxt-EN")){
//            text = text+"-"+EN_count;
//            EN_count=EN_count+1;
//        }
//        if(textKey.equals("GroupNametxt-AR")){
//            text = text+"-"+AR_count;
//            AR_count=AR_count+1;
//        }if(textKey.equals("Descriptiontxt")){
//            text = text+"-"+DES_count;
//            DES_count=DES_count+1;
//        }
        commonPage.enterText(sheetName,keyName,text);
        Reporter.addStepLog("Test Data "+text);
        Reporter.addStepLog("Entered "+text+" in "+keyName);
        Reporter.addStepLog("Action Performed on "+sheetName);
        UtilityMethods.TakeSnapShot();
    }

    @When("^user click on \"([^\"]*)\" button on \"([^\"]*)\"$")
    public void user_click_on_button_on(String keyName, String sheetName) throws Throwable {
        UtilityMethods.waitForPageLoadAndPageReady();
        UtilityMethods.waitForPageLoad();
        commonPage.Clickelemet(sheetName,keyName);
        Reporter.addStepLog("Click on "+keyName);
        Reporter.addStepLog("Action Performed on "+sheetName);
        UtilityMethods.TakeSnapShot();
    }

    @When("^user select \"([^\"]*)\" from \"([^\"]*)\" on \"([^\"]*)\"$")
    public void user_select_from_on(String textKey, String keyName, String sheetName) throws Throwable {
        String text = readFromCell(DatafileName,sheetName,1,textKey);
        commonPage.selectDDValue(sheetName,keyName,text);
        Reporter.addStepLog("Test Data Used "+text);
        Reporter.addStepLog("Selected "+text+" from "+keyName);
        Reporter.addStepLog("Action Performed on "+sheetName);
        UtilityMethods.TakeSnapShot();
    }

    @Then("^Assert that \"([^\"]*)\" appear on \"([^\"]*)\"$")
    public void assert_that_appear_on(String keyName, String sheetName) throws Throwable {
        commonPage.assertonPage(sheetName,keyName);
        String text = readFromCell(DatafileName,sheetName,1,keyName);
        Reporter.addStepLog("Validate Value "+text+" on "+sheetName);
        UtilityMethods.TakeSnapShot();
    }

    @Given("^user scroll to \"([^\"]*)\" on \"([^\"]*)\"$")
    public void user_scroll_to_on(String keyName, String sheetName) throws Throwable {
        commonPage.Scrollelemet(sheetName,keyName);
        Reporter.addStepLog("Click on "+keyName);
        Reporter.addStepLog("Action Performed on "+sheetName);
        UtilityMethods.TakeSnapShot();
    }

    @And("Assert that \"([^\"]*)\" is \"([^\"]*)\" on \"([^\"]*)\"")
    public void assertThatIsOn(String keyName, String attribute, String sheetName) throws Throwable {
        commonPage.elementAttribute(sheetName,attribute,keyName);
        String text = readFromCell(DatafileName,sheetName,1,keyName);
        Reporter.addStepLog("Validate "+text+" is "+attribute);
        UtilityMethods.TakeSnapShot();
    }

    @Then("Assert that Value \"([^\"]*)\" is appeared at \"([^\"]*)\" on \"([^\"]*)\"")
    public void assertThatValueIsAppearedAtOn(String text, String keyName, String sheetName) throws Throwable {
        text = readFromCell(DatafileName,sheetName,1,text);
        commonPage.ValidateValue(text,sheetName,keyName);
        Reporter.addStepLog("Expected Message "+text);
        UtilityMethods.TakeSnapShot();
    }
}
