package Test.Automation.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class WebDriverListeners extends AbstractWebDriverEventListener {
    private By lastFindBy;
    /*private WebElement lastElement;
    private String originalValue;*/

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
   /*     System.out.println("Before Navigating To : " + url + ", my url was: "
                + driver.getCurrentUrl());*/
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        /*System.out.println("After Navigating To: " + url + ", my url is: "
                + driver.getCurrentUrl());*/
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
/*        System.out.println("Before Navigating Back. I was at "
                + driver.getCurrentUrl());*/
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
/*        System.out.println("After Navigating Back. I'm at "
                + driver.getCurrentUrl());*/
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
/*        System.out.println("Before Navigating Forward. I was at "
                + driver.getCurrentUrl());*/
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
/*
        System.out.println("After Navigating Forward. I'm at "
                + driver.getCurrentUrl());
*/
    }

    @Override
    public void onException(Throwable throwable, WebDriver webdriver) {
       /* System.out.println("Caught Exception");
        File scrFile = ((TakesScreenshot) webdriver)
                .getScreenshotAs(OutputType.FILE);
        try {
            org.apache.commons.io.FileUtils.copyFile(scrFile, new File(
                    "C:\\data\\Testfailure.jpeg"));
        } catch (Exception e) {
            System.out.println("Unable to Save");
        }*/
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        lastFindBy = by;
        System.out.println("Trying to find: '" + lastFindBy + "'.");
        //System.out.println("Trying to find: " + by.toString()); // This is optional and an alternate way
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        /*lastFindBy = by;
        System.out.println("Found: '" + lastFindBy + "'.");*/
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        //System.out.println("Trying to click: '" + element + "'");
/*        // Highlight Elements before clicking
        for (int i = 0; i < 1; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(
                    "arguments[0].setAttribute('style', arguments[1]);",
                    element, "color: black; border: 3px solid black;");
        }*/
    }

    // Called after clicking an Element
    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println("Clicked Element : '" + element + "'");
    }


    /*@Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        lastElement = element;
        originalValue = element.getText();

        // What if the element is not visible anymore?
        if (originalValue.isEmpty()) {
            originalValue = element.getAttribute("value");
        }
    }

    // After Changing values
    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver) {
        lastElement = element;
        String changedValue = "";
        try {
            changedValue = element.getText();
        } catch (StaleElementReferenceException e) {
            System.out
                    .println("Could not log change of element, because of a stale"
                            + " element reference exception.");
            return;
        }
        // What if the element is not visible anymore?
        if (changedValue.isEmpty()) {
            changedValue = element.getAttribute("value");
        }

        System.out.println("Changing value in element found " + lastElement
                + " from '" + originalValue + "' to '" + changedValue + "'");
    }
*/

    /*
     * SCRIPT - this section will be modified ASAP
     */
    // Called before RemoteWebDriver.executeScript(java.lang.String, java.lang.Object[])
    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    // Called before RemoteWebDriver.executeScript(java.lang.String, java.lang.Object[])
    @Override
    public void afterScript(String script, WebDriver driver) {

    }
}