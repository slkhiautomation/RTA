package Test.Automation.Utils;

import static Test.Automation.Utils.DataPool.readExcelData;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.*;
import java.io.*;

import com.google.common.io.Files;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;

import com.cucumber.listener.Reporter;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import com.google.common.base.Function;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.config.gui.ArgumentsPanel;
import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.control.gui.LoopControlPanel;
import org.apache.jmeter.control.gui.TestPlanGui;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.protocol.http.control.gui.HttpTestSampleGui;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestElement;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.threads.gui.ThreadGroupGui;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

public class UtilityMethods extends DriverFactory {

    private static final Wait<WebDriver> wait = new WebDriverWait(driver, 15);


    public UtilityMethods() throws IOException, SQLException, ClassNotFoundException {


    }

    public static void Close_1TabOfBrowser() throws InterruptedException {
        wait2Seconds();
        waitForPageLoadAndPageReady();
        driver.close();
    }

    public static void elementClick(String xPath) throws IOException {
        WebElement element = driver.findElement(By.xpath(xPath));
        element.click();
    }

    public static void elementSendKeys(String xPath, Keys key)
    {
        WebElement element = driver.findElement(By.xpath(xPath));
        element.sendKeys(key);
    }

    public static void elementSendKeys(String xPath, String text) {
        WebElement element = driver.findElement(By.xpath(xPath));
        element.sendKeys(text);
    }

    public static boolean AssertTitle  (String expectedResult){
        String actualResult = driver.getTitle();
        if(expectedResult.equals(actualResult)){
            return true;
        }else
            return false;
    }

    public static WebElement getElementByXpath(String xpath){
        WebElement element = driver.findElement(By.xpath(xpath));
        return element;
    }


    public static void RefreshBrowser() throws InterruptedException {

        waitForPageLoadAndPageReady();
        driver.navigate().refresh();
        waitForPageLoadAndPageReady();
    }

    public static boolean getElementWithText(String text) {
        try {
            try {
                driver.findElement(By.xpath("(//*[contains(text(),'" + text + "')])[1]"));
            } catch (Exception e) {
                driver.findElement(By.xpath("(//*[contains(text(),'" + text + "')])[2]"));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void getElementWithTextstr(String text) {

                driver.findElement(By.xpath("(//*[contains(text(),'" + text + "')])[1]"));

        }

    public static void TakeSnapShot() throws Throwable{
        String screenshotName = "";
        //DriverFactory obj = new DriverFactory.getDriver();
        File sourcePath = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        File destinationPath = new File( sourcePath + screenshotName + ".png");
        Files.copy(sourcePath,destinationPath);
        Reporter.addScreenCaptureFromPath(destinationPath.toString());
    }


    public static WebElement getElementWithTextProduct() throws IOException{


                String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
                String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
                String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"products");
                return driver.findElement(By.xpath(text));
        //*[@class='rfk_product'][i]
                //driver.findElement(By.xpath("(//*[contains(text(),'" + text + "')])[1]"));

    }
    public static WebElement getxpathsearch() throws IOException {
      //String text = ExcelFileManager.readCellvalue("CPOproducts","ElementXpath", 1,1);
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"searchBox");
      return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathsearchbutton() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"searchButton");

        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathadvertiseclose() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"adsClose");

        return driver.findElement(By.xpath(text));

    }
    public static WebElement getxpathLoadmore() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"loadmore");
        return driver.findElement(By.xpath(text));
    }

    public static WebElement getxpathproductdetail() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"product");
        return driver.findElement(By.xpath(text));
    }

    public static WebElement getxpathcont() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"continueshop");
        return driver.findElement(By.xpath(text));
    }

    public static WebElement getxpathaddtocart() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"addToCart");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathminiCart() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"miniCart");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathCheckout() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"checkout");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathCheckoutasguest() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"checkoutAsGuest");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathCheckoutFName() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"FirstName");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathCheckoutLName() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"LastName");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathCheckoutAddress() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"AddressO");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathCheckoutAddressCity() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"AddressC");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathCheckoutStatede() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"Statede");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathCheckoutStateselect() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"Statede");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathCheckoutZipcode() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"ZipCode");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathCheckoutphoneno() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"PhoneNo");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathCheckoutEmail() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"Email");
        return driver.findElement(By.xpath(text));
    }
    public static WebElement getxpathCheckoutSubmit() throws IOException {
        String Filename = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 1);
        String SheetName = ExcelFileManager.readCellvalue("Testdata", "XMLtoExcel", 1, 4);
        String text = ExcelFileManager.readFromCell(Filename,SheetName,1,"Submitcheckout");
        return driver.findElement(By.xpath(text));
    }





    public static void Clear_Cache() {
        driver.manage().deleteAllCookies();
    }

    public static void pageDown() throws AWTException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, 250);");
    }

    public static void pageUp() throws AWTException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(0, -250);");
    }

    public static void hoverElementAndClick(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
        action.click().build().perform();
    }

    public static String selectByVisibleTextAndReturnSelectedOption(String locator, String text)
            throws InterruptedException {
        Select select = new Select(driver.findElement(By.cssSelector(locator)));
        select.selectByVisibleText(text);
        return select.getFirstSelectedOption().getText();
    }
    public static WebElement findDropdownOption(String type) throws IOException {
        String ExcelName = new PropertyReader().readProperty("excelFileName");
        // System.out.println(androidDriver.findElement(By.xpath("//android.widget.CheckedTextView[@text='"+readExcelData(ExcelName,"FormFill",type).get("Value").toString()+"']")));
        return driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='"+readExcelData(ExcelName,"FormFill",type).get("Value").toString()+"']"));
    }

    public static String getSelectedOption(WebElement element) throws InterruptedException {
        WebElement selectedOption = new Select(element).getFirstSelectedOption();
        return selectedOption.getText();
    }
    public static WebElement findElementbyxpathvalue(String type) throws IOException {
        return driver.findElement(By.xpath("//*[@value='"+type+"']"));
    }
    public static WebElement findElementbyxpathtext(String type) throws IOException {
        return driver.findElement(By.xpath("//*[contains(text(), '"+type+"')]"));
    }

//    public static WebElement xpathfindbyjavascript(String Xpath) throws IOException {
//    JavascriptExecutor js = (JavascriptExecutor) driver;
//
//    //To click an element
//    WebElement element=driver.findElement(By.xpath(Xpath));
//    js.executeScript(("arguments[0].click();" ,element);
//
//    //To gettext
//
//    String theTextIWant = (String) js.executeScript("return arguments[0].value;",driver.findElement(By.xpath("//input[@id='display-name']")));
//    }
    public static void waitforminute(int min) throws InterruptedException {
        Thread.sleep(min*60000);
    }
    public static void wait1Seconds() throws InterruptedException {
        Thread.sleep(1000);
    }

    public static void wait2Seconds() throws InterruptedException {
        Thread.sleep(2000);
    }

    public static void wait3Seconds() throws InterruptedException {
        Thread.sleep(3000);
    }
    
    public static void wait10Seconds() throws InterruptedException {
        Thread.sleep(10000);
    }
    public static void wait12Seconds() throws InterruptedException {
        Thread.sleep(12000);
    }
    public static void wait50Seconds() throws InterruptedException {
        Thread.sleep(50000);
    }
    public static void wait6Seconds() throws InterruptedException {
        Thread.sleep(6000);
    }
    public static void doubleCLick(WebElement element){
        Actions action = new Actions(driver);
        action.doubleClick(element).perform();
    }
    public static void switchFrame(WebElement element){
        driver.switchTo().frame(element);
    }


    public static void switchFrameBack(){
        driver.switchTo().parentFrame();
    }
    public static void scrollToWebElement(WebElement scrollTo) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", scrollTo);
    }

    public static void clickLinkSidebar(WebElement sidebarLink) throws Exception {
        sidebarLink.click();
        waitForPageLoad();
    }

    public static String getTimeStampMMddHHmmss() {
        return new SimpleDateFormat("MMddHHmmss").format(new Date());
    }

    public static String getTimeStampddMMYYYY() {
        return new SimpleDateFormat("ddMMYYYY").format(new Date());
    }

    public static String getTimeStampYYYYHHmmss() {
        return new SimpleDateFormat("YYYYHHmmss").format(new Date());
    }

    public static String getDateWithFormatmmddyyyy() {
        return new SimpleDateFormat("MM/dd/yyyy").format(new Date());
    }

    public static void waitForPageLoad() {

        Wait<WebDriver> wait = new WebDriverWait(driver, 60);
        wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                ((JavascriptExecutor) driver).executeScript("return document.readyState");
                return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                        .equals("complete");
            }
        });
    }

    public static void waitForClickable(WebElement element) {
        new WebDriverWait(driver, 50).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForElementToBeClickable(WebElement element) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitForVisibility(WebElement element) {
        new WebDriverWait(driver, 110).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForVisibility10(WebElement element) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
    }




    public static void keyboardHide(){
        //androidDriver.hideKeyboard();
    }

    public static void swipeVertical(AppiumDriver android, double startPercentage, double finalPercentage, double anchorPercentage, int duration) throws Exception {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * finalPercentage);
        new TouchAction(android).press(anchor, startPoint).waitAction(duration).moveTo(anchor, endPoint).release().perform();
    }


    public static void clickOkAtPopUpAlert() {
        Alert alert = driver.switchTo().alert();
        System.out.println("alert.getText() = " + alert.getText());

        alert.accept();
        checkPageIsReady();
    }

    public static void checkPageIsReady() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String s = "";
        while (!s.equals("complete")) {
            s = (String) js.executeScript("return document.readyState");
            try {
                wait1Seconds();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void switchToParentWindow(String parentWindowHandler) {
        driver.switchTo().window(parentWindowHandler);
    }

    public static String switchToSubWindowAndReturnParentWindowHandler() {

        String parentWindowHandler = driver.getWindowHandle();
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles();

        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler);
        return parentWindowHandler;
    }

    public static void refreshPage() throws InterruptedException {
        driver.navigate().refresh();
        waitForPageLoadAndPageReady();
    }

    public static void moveToMainMenuAndClickSubMenuItem(WebElement mainMenu, WebElement subMenu)
            throws InterruptedException {
        UtilityMethods.waitForClickable(mainMenu);
        Actions action = new Actions(driver);
        waitForEnable1Sec(mainMenu);
        action.moveToElement(mainMenu).build().perform();
        // shoppingCartMenu.click();
        UtilityMethods.waitForVisibility(subMenu);
        action.moveToElement(subMenu).click().perform();
    }

    public static void moveToMainMenuandsubMenu(WebElement mainMenu, WebElement subMenu) throws InterruptedException {
        UtilityMethods.waitForVisibility(mainMenu);
        Actions action = new Actions(driver);
        waitForEnable1Sec(mainMenu);
        action.moveToElement(mainMenu).build().perform();
        waitForEnable1Sec(subMenu);
        action.moveToElement(subMenu).build().perform();
    }

    public static void waitForElementPresentInSec(List<WebElement> list, int waitForSeconds)
            throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= waitForSeconds)
                fail("timeout");
            try {
                if (!list.isEmpty())
                    break;
            } catch (NoSuchElementException e) {
            }
            wait1Seconds();
        }
    }

    public static WebElement waitForElementToBePresent(WebElement element) throws InterruptedException {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public static void moveToMenuAndClick(WebElement menu) throws InterruptedException {
        Actions action = new Actions(driver);

        UtilityMethods.waitForVisibility(menu);

        waitForEnable1Sec(menu);
        action.moveToElement(menu);
        menu.click();
    }

    public static String dateGeneration() {
        // Creating a random catalog name
        Date dNow = new Date();
        SimpleDateFormat nameFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
        return nameFormat.format(dNow);

    }

    public static WebElement fluientWaitforElement(WebElement element, int timoutSec, int pollingSec) {
        FluentWait<WebDriver> fWait = new FluentWait<WebDriver>(driver).withTimeout(timoutSec, TimeUnit.SECONDS)
                .pollingEvery(pollingSec, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class, TimeoutException.class)
                .ignoring(StaleElementReferenceException.class);
        for (int i = 0; i < 2; i++) {
            try {

                fWait.until(ExpectedConditions.visibilityOf(element));
                fWait.until(ExpectedConditions.elementToBeClickable(element));
            } catch (Exception e) {
                System.out.println("Element Not found trying again - " + element.toString().substring(70));
                e.printStackTrace();
            }
        }
        return element;
    }

    public static void dragDrop(WebElement dragElement, WebElement dropLocation) {
        Actions action = new Actions(driver);
        action.dragAndDrop(dragElement, dropLocation).perform();

    }

    public static void moveToElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();

    }

    public static String waitForTextToBePresentInElementAndReturnText(WebElement element, String text)
            throws InterruptedException {
        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(element, text));
        return text;
    }

    public static void waitForTextToBePresentInElement(WebElement element, String text) throws InterruptedException {
        new WebDriverWait(driver, 20)

                .until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public static void waitForInvisibilityOfElementWithText(String locator, String text) throws InterruptedException {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(locator), text));
    }

    public static boolean waitForInvisibilityOfElementWithTextAndReturn(String locator, String text)
            throws InterruptedException {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementWithText(By.cssSelector(locator), text));
    }

    public static void selectFromDropDown(WebElement dropdown, String optionsCssPath, String textToSelect)
            throws InterruptedException, AWTException {
        dropdown.click();
        List<WebElement> options = driver.findElements(By.cssSelector(optionsCssPath));
        wait1Seconds();
        for (WebElement option : options) {
            if (option.getText().equals(textToSelect)) {
                option.click();
                break;
            }
        }
    }

    public static String getDateWithFormatMMMddyyyy() {
        return new SimpleDateFormat("MMM dd, yyyy").format(new Date());
    }

    public static void waitForEnable1Sec(WebElement element) throws InterruptedException {
        do {
            wait1Seconds();
            // System.out.println("j");
        } while (element.isEnabled() == false);
    }

    public static void waitForEnable2Sec(WebElement element) throws InterruptedException {
        do {
            wait2Seconds();
        } while (element.isEnabled() == false);
    }

    public static void waitForListEnable2Sec(List<?> list) throws InterruptedException {

        do {
            wait2Seconds();

        } while (list.size() < 0);
    }

    public static boolean verifyElementNotPresent(WebElement element) throws Exception {
        try {
            element.isDisplayed();
            System.out.println("Element Present");
            return false;

        } catch (NoSuchElementException e) {
            System.out.println("Element absent");
            return true;
        }
    }

    public static void invisibilityOfElement(String element) {
        new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(element)));
    }

    public static void waitForElementPresentInSec(WebElement element, int waitForSeconds) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= waitForSeconds)
                fail("timeout");
            try {
                if (element.isDisplayed())
                    break;
            } catch (NoSuchElementException e) {
            }
            wait1Seconds();
        }
    }

    public static boolean ifElementPresentInSec(WebElement element, int waitForSeconds) throws InterruptedException {
        boolean found = false;
        for (int second = 0; ; second++) {
            if (second >= waitForSeconds)
                fail("timeout");
            try {
                if (element.isDisplayed()) {
                    found = true;
                }
                break;
            } catch (NoSuchElementException e) {
                found = false;
            }
            wait1Seconds();
        }
        return found;
    }

    public static boolean ifElementPresentInSeconds(By by, int waitForSeconds) throws InterruptedException {
        boolean found = false;
        for (int second = 0; ; second++) {
            if (second >= waitForSeconds)
                fail("timeout");

            if (driver.findElement(by).isDisplayed()) {
                found = true;
                break;
            }

            wait1Seconds();
        }
        return found;
    }

    public static boolean ifElementPresent(WebElement element) {
        boolean found;
        try {
            element.isDisplayed();
            found = true;

        } catch (NoSuchElementException e) {
            found = false;
        }
        return found;
    }

    public static boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void scrollToElementAndClick(WebElement element) throws Exception {
        waitForPageLoadAndPageReady();
        UtilityMethods.wait1Seconds();
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        element.click();
    }

    public static void scrollToElementtillfound(WebElement element) throws Exception {
        waitForPageLoadAndPageReady();
        UtilityMethods.wait2Seconds();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //js.executeScript("window.scrollBy(0,0)", "");

        //js.executeScript("arguments[0].scrollIntoView();",element);
        element = driver.findElement(By.tagName("header"));

        js.executeScript("arguments[0].scrollIntoView();", element);

    }

    public static void scrollToElementtillfounddown(WebElement element) throws Exception {
        waitForPageLoadAndPageReady();
        UtilityMethods.wait1Seconds();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,900)", "");
        //js.executeScript("arguments[0].scrollIntoView();",element);

    }
    public static void scrollToElement(WebElement element) throws Exception {
        waitForPageLoadAndPageReady();
        UtilityMethods.wait1Seconds();
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
        UtilityMethods.wait2Seconds();
    }
    public static void waitForPageLoadAndPageReady() {
        //wait2Seconds();
        waitForPageLoad();
        checkPageIsReady();
    }

    public static void clickOkatAlertIfPresent() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("alert was present");
            clickOkAtPopUpAlert();
        } catch (TimeoutException eTO) {
            System.out.println("alert was not present");
        }
    }

    public static String getTodaysDateMMddyyyy() {
        return new SimpleDateFormat("MM/dd/yyyy").format(new Date(System.currentTimeMillis()));
    }

    public static String getTodaysDateMMMddyyyy() {
        return new SimpleDateFormat("MMM dd, yyyy").format(new Date(System.currentTimeMillis()));
    }

    public static void waitForTextPresentInElement(String textToVerify, WebElement element, int waitForSeconds)
            throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= waitForSeconds)
                fail("timeout");
            try {
                if (textToVerify.equals(element.getText()))
                    break;
            } catch (Exception e) {
            }
            wait1Seconds();
        }
    }

    public static void waitForElementEnabled(WebElement element, int waitForSeconds) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= waitForSeconds)
                fail("timeout");
            try {
                if (element.isEnabled())
                    break;
            } catch (Exception e) {
            }
            wait1Seconds();
        }
    }

    public static void waitForElementDisplayed(WebElement element, int waitForSeconds) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= waitForSeconds)
                fail("timeout");
            try {
                if (element.isDisplayed())
                    break;
            } catch (Exception e) {
            }
            wait1Seconds();
        }
    }

    public static String convertDateFormatToString(String date, String input_pattern, String output_pattern)
            throws ParseException {
        return new SimpleDateFormat(output_pattern).format(new SimpleDateFormat(input_pattern).parse(date));
    }

    public static String getTextJavaScript(WebElement element) {
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return (String) js.executeScript("return $(arguments[0]).text();", element);
    }

    public static String getTextJavaScriptInnerHTML(WebElement element) {
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return (String) js.executeScript("return $(arguments[0]).innerHTML;", element);
    }

    public static String getInnerText(WebElement element) {
        return element.getAttribute("innerText");
    }

    public static String getTextContent(WebElement element) {
        return element.getAttribute("textContent");
    }

    public static void waitTillSomeTextAppearInElementUsingAttribute(final WebElement element, int timeOutInSeconds) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return element.getAttribute("value").length() != 0;
            }
        });
    }

    public static void waitTillSomeTextAppearInElementUsingText(final WebElement element, int timeOutInSeconds) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return element.getText().length() != 0;
            }
        });
    }

    public static void waitTillTextAppearInElementUsingPlaceholderAttribute(final WebElement element,
                                                                            int timeOutInSeconds, String text) {
        final String textToAppearInValue = text;
        (new WebDriverWait(driver, timeOutInSeconds)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                // System.out.println("text: "+textToAppearInValue);
                // System.out.println("element placeholder:
                // "+element.getAttribute("placeholder").toString());
                return element.getAttribute("placeholder").equals(textToAppearInValue);
            }
        });
    }

    public static void waitTillTextAppearInElementUsingAttribute(final WebElement element, int timeOutInSeconds,
                                                                 String text) {
        final String textToAppearInValue = text;
        (new WebDriverWait(driver, timeOutInSeconds)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                System.out.println("text: " + textToAppearInValue);
                return element.getAttribute("value").equals(textToAppearInValue);
            }
        });
    }

    public static String getDateWithFormatMMMdyyyy() {
        return new SimpleDateFormat("MMM d, yyyy").format(new Date());
    }

    public static boolean patternMatches_10_Digits(String s) {
        return s.matches("\\d{10}");
    }

    public static void waitForElementToDisappear(By by, int timeinSec) throws InterruptedException {

        for (int i = 0; i <= timeinSec; ++i) {
            if (driver.findElements(by).isEmpty()) {
                break;
            } else {
                UtilityMethods.wait1Seconds();
            }
            if (i == timeinSec)
                fail("element not disappeared within " + timeinSec + " seconds");
        }
    }

    public static void waitForNotifyPopupToDisappear_IFExists() throws InterruptedException {
        if (isElementPresent(By.cssSelector("span.noty_text"))) {
            waitForElementToDisappear(By.cssSelector("span.noty_text"), 10);
        }
    }

    public static void waitForInvisibilityOfElement(String locator) throws InterruptedException {
        new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locator)));
    }

    public static void waitForVisibilityOfNotifyPopup() throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 10)
                fail("timeout");
            try {
                if (isElementPresent(By.cssSelector("span.noty_text")))
                    break;
            } catch (Exception e) {
            }
            wait1Seconds();
        }
    }

    public static void waitForInvisibilityOfNotifyPopup() throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= 10)
                fail("timeout");
            try {
                if (!isElementPresent(By.cssSelector("span.noty_text")))
                    break;
            } catch (Exception e) {
            }
            wait1Seconds();
        }
    }

    public static void waitForVisibilityThenInvisibilityOfNotifyPopup() throws InterruptedException {
        waitForVisibilityOfNotifyPopup();
        waitForInvisibilityOfNotifyPopup();
    }

    public static String getTimeStampYYYYddHHmmss() {
        return new SimpleDateFormat("YYYYddHHmmss").format(new Date());
    }

    public static String getDateWithFormat_M_d_yy() {
        return new SimpleDateFormat("M/d/yy").format(new Date());
    }

    public static void waitForElementPresenceByCss(String element) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(element)));
    }

    public static void waitForElementPresenceByXpath(String element) {
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
    }

    public static boolean waitForElementNotPresentByXpath(String element) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(element)));
    }

    public static String wait30SecTextToBePresentInElementAndReturnText(WebElement element, String text)
            throws InterruptedException {
        new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElement(element, text));
        return text;
    }

    public static void wait3MinForElementInvisibility(String element) {
        new WebDriverWait(driver, 180).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(element)));
    }

    public static void scrollToSideBarLinkAndClickElement(WebElement sideBarLink) throws Exception {
        waitForPageLoadAndPageReady();
        scrollToWebElement(sideBarLink);
        clickLinkSidebar(sideBarLink);
        waitForPageLoadAndPageReady();
    }

    public static void moveToElementWithWaits(WebElement element) throws Exception {
        wait2Seconds();
        waitForPageLoadAndPageReady();
        waitForVisibility(element);
        waitForClickable(element);
        UtilityMethods.ifElementPresentInSec(element, 10);
        UtilityMethods.moveToElement(element);

    }

    public static void moveToElementWithWaitsVisibility(WebElement element) throws Exception {
        wait2Seconds();
        waitForPageLoadAndPageReady();
        waitForVisibility(element);
        UtilityMethods.ifElementPresentInSec(element, 10);
        UtilityMethods.moveToElement(element);
    }

    public static void moveToDisabledElementWithWaits(WebElement element) throws Exception {
        wait1Seconds();
        waitForPageLoadAndPageReady();
        waitForVisibility(element);
        UtilityMethods.ifElementPresentInSec(element, 10);
        UtilityMethods.moveToElement(element);
    }

    public static String isButtonDisabled(WebElement button) throws Exception {
        UtilityMethods.waitForPageLoadAndPageReady();
        UtilityMethods.moveToDisabledElementWithWaits(button);
        return button.getAttribute("disabled");
    }

    public static void switchToNewTab() throws InterruptedException {

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        UtilityMethods.waitForPageLoadAndPageReady();
    }

    public static void switchToFirstTab() throws InterruptedException {

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        UtilityMethods.waitForPageLoadAndPageReady();
    }

	/*
     *
	 * public static void pageRefreshUntilElementVisible(By by) throws
	 * InterruptedException { int count = 0;
	 * while(!UtilityMethods.isElementPresent(by)) {
	 * UtilityMethods.wait2Seconds(); if (UtilityMethods.isElementPresent(by) ||
	 * count > 10) { break; } UtilityMethods.refreshPage();
	 * UtilityMethods.wait2Seconds();
	 * System.out.println(" gmail page refresh count : " + count); count = count
	 * + 1; } }
	 */

    public static void pageRefreshUntilElementVisible(By by) throws InterruptedException {
        int count = 0;
        while (!UtilityMethods.isElementPresent(by)) {
            UtilityMethods.wait2Seconds();
            if (UtilityMethods.isElementPresent(by) || count > 15) {
                break;
            }

            UtilityMethods.refreshPage();
            System.out.println(" gmail page refresh count : " + count);
            count = count + 1;
        }
    }
	/*
	 * 
	 * public static void pageRefreshUntilElementVisible(By by) throws
	 * InterruptedException { for (int second = 0; ; second++) { if (second >=
	 * 20) fail("timeout"); try { UtilityMethods.wait2Seconds(); if
	 * (isElementPresent(by)) break; } catch (Exception e) { }
	 * UtilityMethods.refreshPage(); UtilityMethods.wait2Seconds(); } }
	 */


	public static boolean ifEmailPresentBySubject(String user,String password, String subject) {
		boolean isEmailPresent = false;
		Properties properties = new Properties();
		try {

			// server setting
	        properties.put("mail.imap.host", "imap.gmail.com");
	        properties.put("mail.imap.port", "993");
	  
	        // SSL setting
	        properties.setProperty("mail.imap.socketFactory.class",
	                "javax.net.ssl.SSLSocketFactory");
	        properties.setProperty("mail.imap.socketFactory.fallback", "false");
	        properties.setProperty("mail.imap.socketFactory.port",
	                String.valueOf("993"));
	  
	        Session session = Session.getInstance(properties);
			// create the POP3 store object and connect with the pop server
	        Store store = session.getStore("imap");
            store.connect(user, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length " + messages.length);
			for (int i = 0, n = messages.length; i < n; i++) {
				Message message = messages[i];
				System.out.println("---------------------------------");
				System.out.println("Email Number " + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println("Body: " + message.getContent().toString());
				isEmailPresent = subject.equalsIgnoreCase(message.getSubject()) ? true : false;
			}

			// close the store and folder objects
			emailFolder.close(false);
			store.close();
			
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isEmailPresent;
	}
	
	public static Boolean getShadesOFSelectedCOlor(WebElement element, String color) throws InterruptedException {
		Boolean result = false;
		System.out.println("color "+ element.getAttribute("text").toLowerCase());
		if (color.equalsIgnoreCase(" blue")) {
			if (element.getAttribute("text").toLowerCase().contains("blue")
					|| element.getAttribute("text").toLowerCase().contains("deep")
					|| element.getAttribute("text").toLowerCase().contains("ocean")
					|| element.getAttribute("text").toLowerCase().contains("moonshine")
					|| element.getAttribute("text").toLowerCase().contains("navy")
					|| element.getAttribute("text").toLowerCase().contains("spinnaker")
					|| element.getAttribute("text").toLowerCase().contains("hydrangea")
					|| element.getAttribute("text").toLowerCase().contains("marlin")
					|| element.getAttribute("text").toLowerCase().contains("wipeout")
					|| element.getAttribute("text").toLowerCase().contains("cornflower")
					|| element.getAttribute("text").toLowerCase().contains("pool")
					|| element.getAttribute("text").toLowerCase().contains("harbor")) {
				result= true;
			}
		}else if(color.equalsIgnoreCase(" black")){
			if (element.getAttribute("text").toLowerCase().contains("black")) {
				result= true;
			}
		}
		return result;
	}
	
	
    public static void pageRefreshUntilElementVisible(By by, int seconds) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= seconds)
                fail("timeout");
            try {
                UtilityMethods.wait2Seconds();
                if (isElementPresent(by))
                    break;
            } catch (Exception e) {
            }
            UtilityMethods.refreshPage();
            UtilityMethods.wait2Seconds();
        }
    }

    public static void switchToNewPopUpWindow() throws InterruptedException {

        // Store your parent window
        String parentWindowHandler = driver.getWindowHandle();
        String subWindowHandler = null;

        // get all window handles
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();

        while (iterator.hasNext()) {
            subWindowHandler = iterator.next();
        }

        // switch to popup window
        driver.switchTo().window(subWindowHandler);
        UtilityMethods.waitForPageLoadAndPageReady();

        // perform operations on popup window
        System.out.println("on popup window .. driver.getTitle() = " + driver.getTitle());

        // switch back to parent window
        driver.switchTo().window(parentWindowHandler);
        System.out.println("back to parent window .. driver.getTitle() = " + driver.getTitle());
    }
    public static void switchToalert() throws InterruptedException {
	    //String username
//        WebDriverWait wait = new WebDriverWait(driver, 20);
//        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//        alert.authenticateUsing(new UserAndPassword("storefront", "face"));


    }
    public static void enterDataInTextBox(WebElement element, String data_to_enter) throws InterruptedException {
        element.clear();
        element.sendKeys(data_to_enter);
        waitForPageLoadAndPageReady();
    }

    public static void clickByLink(String link) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(link)));
        waitForElementPresentInSec(driver.findElement(By.linkText(link)), 10);
        moveToElementWithWaits(driver.findElement(By.linkText(link)));
        driver.findElement(By.linkText(link)).click();
        waitForPageLoadAndPageReady();
    }

    public static WebElement getElementByLink(String link) throws Exception {
        moveToElementWithWaits(driver.findElement(By.linkText(link)));
        waitForElementPresentInSec(driver.findElement(By.linkText(link)), 10);
        return driver.findElement(By.linkText(link));
    }

    public static void clickElement(WebElement element) throws InterruptedException {
        UtilityMethods.waitForElementPresentInSec(element, 10);
        element.click();
        UtilityMethods.waitForPageLoadAndPageReady();
    }

    public static String getTextNotifyPopup() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement popupElement = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.noty_text")));
        return popupElement.getText();
    }

    public static boolean getElementNotVisibleByLink(String link) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 8);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(link)));
    }

    public static void pageRefreshUntilElementIsVisible(By by, int seconds) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= seconds)
                fail("timeout");
            try {
                UtilityMethods.wait2Seconds();
                if (driver.findElement(by).isDisplayed() == true) {
                    UtilityMethods.wait2Seconds();
                    System.out.println("yes");
                    break;
                }
            } catch (Exception e) {
            }
            UtilityMethods.refreshPage();
            UtilityMethods.wait2Seconds();

        }
    }

    public static void moveToElementWithJSExecutor(WebElement element) throws InterruptedException {
        wait2Seconds();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait2Seconds();
    }

    public static String appendStringWithDashes(String value) {
        return value.substring(0, 4) + "-" + value.substring(4, 8) + "-" + value.substring(value.length() - 2);
    }

    public static void verifyElementDisabled(WebElement element) {
        Assert.assertEquals("true", element.getAttribute("disabled"));
    }

    public static void verifyLinkDisplayed(String link) throws Exception {
        ifElementPresentInSec(getElementByLink(link), 100);
        Assert.assertTrue(getElementByLink(link).isDisplayed());
    }

    public static void verifyElementDisplayed(WebElement element) throws Exception {
        Assert.assertTrue(ifElementPresentInSec(element, 100));
    }
    
   

    public static String getTextFileContents(String fileName) throws IOException {
        String attachmentFileText;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            attachmentFileText = sb.toString();
        }
        return attachmentFileText;
    }

    public static void waitForFileExists(String fileName) {
        Boolean waitForFile = true;
        while (waitForFile) {
            File f = new File(fileName);
            if (f.exists() && !f.isDirectory()) {
                waitForFile = false;
            }
        }
    }

    public static void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public static void verifyNotifyPopUp(String textToVerify) throws InterruptedException {
        Assert.assertEquals(textToVerify, getTextNotifyPopup());
        waitForPageLoadAndPageReady();
    }

    // verify label on page
    public static void verifyPageLabel(WebElement element, String text) throws Throwable {

        String label = waitForTextToBePresentInElementAndReturnText(element, text);
        Assert.assertEquals(text, label);
    }

    // verify field on page
    public static void verifyPageElement(String elementPath, WebElement element) throws Throwable {

        waitForElementPresenceByCss(elementPath);
        waitForEnable1Sec(element);
        waitForVisibility(element);
    }

    public static boolean isElementDisplayed(By by, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by).isDisplayed();
    }

    public static WebElement fluentWait(final By locator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });

        return foo;
    };

    public static WebElement waitForElementVisible(WebElement element) {
        return new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForElementClickable(WebElement element) {
        return new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForElementVisibleAndClickable(WebElement element) {
        waitForElementVisible(element);
        return waitForElementClickable(element);
    }

//    public static Boolean elementNotPresent(WebElement element) {
//        return false;
//        //return new WebDriverWait(driver, 5).until(ExpectedConditions.invisibilityOf(element));
//    }

    public static void waitForTextPresent(WebElement webElement, String quantity) {
        wait.until(ExpectedConditions.textToBePresentInElement(webElement, quantity));
    }

    public static void deleteCookies() {
        Set<Cookie> allCookies = driver.manage().getCookies();
        for (Cookie cookie : allCookies) {
            driver.manage().deleteCookieNamed(cookie.getName());
        }
        driver.manage().deleteAllCookies();
    }

    public static String removeWhiteSpace(String string) {
        return string.replaceAll("\\s+", "");
    }

    public static void clickElementOnlyIfDisplayed(By selector) {
        Boolean isElementPresent = driver.findElements(selector).size() > 0;
        if (isElementPresent) {
            driver.findElement(selector).click();
        }
    }

    public static boolean is8DigitNumeric(String s) {
        return s.matches("^[0-9]{8}$");
    }

    public static boolean isCurrencyPattern(String s) {
        return s.matches("^\\$?[0-9][0-9\\,]*(\\.\\d{1,2})?$|^\\$?[\\.]([\\d][\\d]?)$");
    }

    public static String getLastWord(String s) {
        String[] wordList = s.trim().split("\\s+");
        String lastWord = wordList[wordList.length - 1];
        return lastWord;
    }

    public static String getNthWord(String fullString, int nth) {
        String[] temp = fullString.split(" ");
        if (nth - 1 < temp.length)
            return temp[nth - 1];
        return null;
    }

    public static String getFirstLineOfText(String string) {
        return string.substring(0, string.indexOf("\n"));
    }

    public static String getLastFourCharacters(String myString) {
        return myString.substring(myString.length() - 4);
    }

    public static String multiplyByNum_StringToFloat00(String floatNumber, int numMultiply) {
        int result = ((int) Double.parseDouble(floatNumber)) * numMultiply;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(Double.parseDouble(String.valueOf(result)));
    }

    public static void verifyElementNotVisible(By locator) {
        assertTrue(new WebDriverWait(driver, 4).until(ExpectedConditions.invisibilityOfElementLocated(locator)));
    }

    public static void printListDouble(List<Double> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list.get(i) = " + list.get(i));
        }
    }

    public static <S, T extends Iterable<S>> void printList(T list) {
        for (Object element : list) {
            System.out.println(element);
        }
    }

    public static <T extends Comparable<? super T>> boolean isSortedAscending(Iterable<T> iterable) {
        Iterator<T> iter = iterable.iterator();
        if (!iter.hasNext()) {
            return true;
        }
        T t = iter.next();
        while (iter.hasNext()) {
            T t2 = iter.next();
            if (t.compareTo(t2) > 0) {
                return false;
            }
            t = t2;
        }
        return true;
    }

    public static boolean isSortedAscending(List<Double> list) {
        boolean sorted = true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) > list.get(i)) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public static boolean isSortedDescending(List<Double> list) {
        boolean sorted = true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) < list.get(i)) {
                sorted = false;
                break;
            }
        }
        return sorted;
    }

    public static boolean isStringListSorted(List<String> list) {
        String previous = "";
        for (String current : list) {
            if (current.compareTo(previous) < 0)
                return false;
            previous = current;
        }
        return true;
    }


    public static WebElement findDynamicElement(By by, int timeOut) {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return element;
    }

    public static void waitTillUrlChanges(String url, String urlEndsWithText, int seconds) throws InterruptedException {
        for (int second = 0; ; second++) {
            if (second >= seconds)
                fail("timeout");
            try {
                UtilityMethods.wait1Seconds();
                if (url.contains(urlEndsWithText))
                    break;
            } catch (Exception e) {
            }
            UtilityMethods.wait1Seconds();
        }
    }

    public static boolean verifyPDFContents(String strURL, String reqTextInPDF) {

        boolean flag = false;

        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        String parsedText = null;

        try {
            URL url = new URL(strURL);
            BufferedInputStream file = new BufferedInputStream(url.openStream());
            PDFParser parser = new PDFParser(file);

            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(1);

            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
        } catch (MalformedURLException e2) {
            System.err.println("URL string could not be parsed " + e2.getMessage());
        } catch (IOException e) {
            System.err.println("Unable to open PDF Parser. " + e.getMessage());
            try {
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
            } catch (Exception e1) {
                e.printStackTrace();
            }
        }

        System.out.println("+++++++++++++++++");
        System.out.println("parsedText = " + parsedText);
        System.out.println("+++++++++++++++++");

        if (parsedText.contains(reqTextInPDF)) {
            flag = true;
        }

        return flag;
    }

    public static String getBalance(String myString) {
        String splitStringIntoSpaces[] = myString.split(" ");
        return myString.substring(1, splitStringIntoSpaces[0].length());
    }

    public static void readGMails(String user, String password, String emailSubject) {
        try {

            String MAIL_POP_HOST = "pop.gmail.com";
            String MAIL_STORE_TYPE = "pop3";

            String POP_PORT = "995";

            // create properties field
            Properties properties = new Properties();
            properties.put("mail.pop3.host", MAIL_POP_HOST);
            properties.put("mail.pop3.port", POP_PORT);
            properties.put("mail.pop3.starttls.enable", "true");
            properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session emailSession = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });
            // create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore(MAIL_STORE_TYPE);

            store.connect(MAIL_POP_HOST, user, password);

            // create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length " + messages.length);

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];

                if (message.getSubject().equals(emailSubject)) {
                    System.out.println("---------------------------------");
                    System.out.println("Email Number " + (i + 1));
                    System.out.println("Subject: " + message.getSubject());
                    System.out.println("From: " + message.getFrom()[0]);
                    System.out.println("Body: " + message.getContent().toString());
                }
            }

            // close the store and folder objects
            emailFolder.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void delete(String user, String password) {
        try {
            String MAIL_POP_HOST = "pop.gmail.com";
            String MAIL_STORE_TYPE = "pop3";

            String POP_PORT = "995";

            // create properties field
            Properties properties = new Properties();
            properties.put("mail.pop3.host", MAIL_POP_HOST);
            properties.put("mail.pop3.port", POP_PORT);
            properties.put("mail.pop3.starttls.enable", "true");
            properties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session emailSession = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });
            // create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore(MAIL_STORE_TYPE);

            store.connect(MAIL_POP_HOST, user, password);

            // create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();

            System.out.println("messages.length---" + messages.length);
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                 message.setFlag(Flags.Flag.DELETED, true);

            }
            // expunges the folder to remove messages which are marked deleted
            //emailFolder.close(true);
            emailFolder.expunge();
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void deletePreviousGMails(String userName, String password) {
        Properties properties = new Properties();
  
        // server setting
        properties.put("mail.imap.host", "imap.gmail.com");
        properties.put("mail.imap.port", "993");
  
        // SSL setting
        properties.setProperty("mail.imap.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.imap.socketFactory.fallback", "false");
        properties.setProperty("mail.imap.socketFactory.port",
                String.valueOf("993"));
  
        Session session = Session.getInstance(properties);
  
        try {
            // connects to the message store
            Store store = session.getStore("imap");
            store.connect(userName, password);
  
            // opens the inbox folder
            Folder folderInbox = store.getFolder("INBOX");
            folderInbox.open(Folder.READ_WRITE);
  
            // fetches new messages from server
            Message[] arrayMessages = folderInbox.getMessages();
  
            for (int i = 0; i < arrayMessages.length; i++) {
                Message message = arrayMessages[i];
                message.setFlag(Flags.Flag.DELETED, true);
                System.out.println("Marked DELETE for message: " + message.getSubject());
  
            }
 
            boolean expunge = true;
            folderInbox.close(expunge);
 
            // disconnect
            store.close();
        } catch (NoSuchProviderException ex) {
            System.out.println("No provider.");
            ex.printStackTrace();
        } catch (MessagingException ex) {
            System.out.println("Could not connect to the message store.");
            ex.printStackTrace();
        }
    }

    public static void getSelectedOptionByText(WebElement element, String text) throws InterruptedException {
        new Select(element).selectByVisibleText(text);
    }

    public static void hoverElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element);
    }

    public static void writeElemetJavaSciptExecutor(WebElement webElement,String inputText) throws InterruptedException {
        UtilityMethods.waitForElementEnabled(webElement, 100);
        String js = "arguments[0].setAttribute('value','"+inputText+"')";
        ((JavascriptExecutor) driver).executeScript(js, webElement);
    }
    public static void clickElemetJavaSciptExecutor(WebElement webElement) throws InterruptedException {
        UtilityMethods.waitForElementEnabled(webElement, 100);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public static void SendValuebyJavaSciptExecutor(WebElement webElement, String Text) throws InterruptedException {
        UtilityMethods.waitForElementEnabled(webElement, 100);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].value='"+Text+"';", webElement);
    }

    public static void SelectElemetJavaSciptExecutor(WebElement webElement, String text) throws InterruptedException {
        UtilityMethods.waitForElementEnabled(webElement, 100);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("var select = arguments[0]; for(var i = 0; i < select.options.length; i++){ if(select.options[i].text == arguments[1]){ select.options[i].selected = true; } }", webElement,text);
    }

    public static void setClipboardData(String string) {
        //StringSelection is a class that can be used for copy and paste operations.
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

    public static void uploadFile(String fileLocation) {
        try {
            //Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public static boolean Calneder(WebElement element, WebElement element2, WebElement element3, String MatchYear) throws InterruptedException{
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click()", element);
//        element.click();
        boolean found = false;
        int i = 0;
        while (!found && i < 6){
            UtilityMethods.wait2Seconds();
            executor.executeScript("arguments[0].click()", element2);
//            element2.click();
            found = element3.getText().equals(MatchYear);
            i++;
        }
        return found;
    }

    public static void evenctClick(WebElement element) throws Throwable{
        UtilityMethods.waitForPageLoadAndPageReady();
        element.click();
        UtilityMethods.wait1Seconds();
    }

    public static void evenctvalues(WebElement element, String string) throws Throwable{
        UtilityMethods.waitForPageLoadAndPageReady();
        if (element.getAttribute("text").isEmpty()) {
            UtilityMethods.wait1Seconds();
            element.sendKeys(string);
        }else{
            element.clear();
            element.sendKeys(string);
            UtilityMethods.wait1Seconds();
        }
    }
    public static String getScreenshotBefore(String screenshotName) throws Exception {

        //below line is just to append the date format with the screenshot name to avoid duplicate names
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/BeforeSteps/"+screenshotName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        //Returns the captured file path
        return destination;
    }

    public static String getScreenshotAfter(String screenshotName) throws Exception {

        //below line is just to append the date format with the screenshot name to avoid duplicate names
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = System.getProperty("user.dir") + "/AfterSteps/"+screenshotName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        //Returns the captured file path
        return destination;
    }
}