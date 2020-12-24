package Test.Automation.Stepdefinitions;

import Test.Automation.Utils.DriverFactory;
import Test.Automation.Utils.ExcelFileManager;
import Test.Automation.Utils.PropertyReader;
import Test.Automation.Utils.UtilityMethods;
import com.cucumber.listener.Reporter;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;


public class StartingSteps extends DriverFactory {

    public StartingSteps() throws SQLException {
    }

    @Given("^Navigate to Application$")
    public void navigate_to_Application() throws Throwable {
        UtilityMethods.deleteCookies();
        String URL = new PropertyReader().readProperty("appURL");
        driver.manage().window().maximize();
        driver.get(URL);
        UtilityMethods.waitForPageLoadAndPageReady();
        String Actualtitlte = driver.getTitle();
        String ExpectedTitle = new PropertyReader().readProperty("Title");
        System.out.println(Actualtitlte);
        System.out.println(ExpectedTitle);
        if(!Actualtitlte.equals(ExpectedTitle)){
            WebElement advanced = driver.findElement(By.id("details-button"));
            advanced.click();
            UtilityMethods.waitForPageLoadAndPageReady();
            WebElement proceed = driver.findElement(By.id("proceed-link"));
            proceed.click();
            Reporter.addStepLog("Application URL: "+URL);
        }
    }

    @Given("^Navigate to Web Application$")
    public void navigate_to_web_Application() throws Throwable {
        UtilityMethods.deleteCookies();
        String URL = new PropertyReader().readProperty("appURL");
        driver.manage().window().maximize();
        driver.get(URL);
        UtilityMethods.waitForPageLoadAndPageReady();
        WebElement advanced = driver.findElement(By.id("details-button"));
        advanced.click();
        UtilityMethods.waitForPageLoadAndPageReady();
        WebElement proceed = driver.findElement(By.id("proceed-link"));
        proceed.click();
        Reporter.addStepLog("Application URL: "+URL);
    }
}
