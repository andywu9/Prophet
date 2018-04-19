
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class AboutPage {

	private static WebDriver driver;
	WebElement element;
	
	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		driver.get("http://127.0.0.1:8000/about");
		System.out.println("\nTesting About page");
	}
	
	@Test
	public void testProphet() {
		try {
	        element = driver.findElement(By.cssSelector("body > div.container > div > div > div:nth-child(1) > div:nth-child(1)"));
	    } catch (Exception e) {
	    }
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testWhyOpenSource() {
		try {
			element = driver.findElement(By.cssSelector("body > div.container > div > div > div:nth-child(1) > div:nth-child(2)"));
		} catch (Exception e) {
	    }
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testAlgorithm() {
		try {
			element = driver.findElement(By.cssSelector("body > div.container > div > div > div:nth-child(1) > div:nth-child(3)"));
		} catch (Exception e) {
	    }
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testTeamMembers() {
		try {
	        element = driver.findElement(By.cssSelector("body > div.container > div > div > div:nth-child(2) > div"));
		} catch (Exception e) {
	    }
		Assert.assertNotNull(element);
	}

	
	@AfterClass
	public static void closeBrower() {
		driver.quit();
		System.out.println("\nEnding About Page Test");
	}
}
