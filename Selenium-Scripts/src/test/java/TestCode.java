import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestCode {
  
	public static void main(String[] args) {

		FirefoxDriver driver = new FirefoxDriver();
	    String baseUrl = "https://www.google.com/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    
		driver.get(baseUrl + "/");
	    driver.findElement(By.id("lst-ib")).clear();
	    driver.findElement(By.id("lst-ib")).sendKeys("omer aqeel");
	    driver.findElement(By.id("lst-ib")).sendKeys(Keys.ENTER);
	    
	}
	
	
	public static boolean isStringListSorted(List<String> list){
	    String previous = "";
	    for (String current: list) {
	        if (current.compareTo(previous) < 0)
	            return false;
	        previous = current;
	    }
	    return true;
	}
}