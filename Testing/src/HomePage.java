

import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

	private static WebDriver driver;
	WebElement element;
	
	@BeforeClass
	public static void openBrowser() {
		driver = new FirefoxDriver();
		driver.get("http://127.0.0.1:8000");
		System.out.println("\nTesting Home page");
	}

	@Test
	public void testHeader() {
		try {
	        element = driver.findElement(By.className("navbar"));
	    } catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
	}
	@Test
	public void testTable() {
		try {
	        element = driver.findElement(By.cssSelector("body > div.container > div > div > table > tbody"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testPopulated() {

		WebElement tablee = driver.findElement(By.cssSelector("body > div.container > div > div > table > tbody"));
		Assert.assertNotNull(tablee);
		
		List<WebElement> each = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(tablee, By.tagName("tr")));
		
		Assert.assertEquals(21, each.size());
	}
	
	@AfterClass
	public static void closeBrower() {
		driver.quit();
		System.out.println("Ending Home Page Testing");
	}
}
