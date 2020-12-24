package Test.Automation.Stepdefinitions;

import Test.Automation.Utils.DriverFactory;
import Test.Automation.Utils.ExcelFileManager;
import Test.Automation.Utils.PropertyReader;
import Test.Automation.Utils.UtilityMethods;
//import com.cucumber.listener.Reporter;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

//import cucumber.TestContext;

public class Hooks extends DriverFactory {



    public Hooks() throws IOException, InterruptedException, SQLException, ClassNotFoundException {

    }

    public Hooks(WebDriver driver1) throws Exception {

    }

    @Before
    public void beforeScenario(Scenario scenario) {

        //Reporter.loadXMLConfig(new File("src\\extent-config.xml"));
        Reporter.assignAuthor(System.getProperty("user.name"));
        Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
        Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
        Reporter.setSystemInfo("Machine", System.getProperty("os.name"));
        Reporter.setSystemInfo("Selenium", "3.7.0");
        Reporter.setSystemInfo("Maven", "3.5.2");
        Reporter.setSystemInfo("Java Version", System.getProperty("java.version"));
    }


    @After(order = 1)
    public void afterScenario(Scenario scenario) throws URISyntaxException {
            if (scenario.isFailed()) {
                System.out.println(scenario.getName());
                String screenshotName = scenario.getName().replaceAll(" ", "_");
                try {
                    File sourcePath = ((TakesScreenshot) UtilityMethods.driver).getScreenshotAs(OutputType.FILE);
                    String screenshot=System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png";
                    File destinationPath = new File(screenshot);

                    Files.copy(sourcePath, destinationPath);
//                    Reporter.addScreenCaptureFromPath(destinationPath.toString());
                } catch (IOException e) {
                }
            }
        }

    @After(order = 0)
    public void AfterSteps() throws IOException {
//        quitDriver();
    }
}

