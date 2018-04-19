
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
		//opens up chrome window and goes to about page
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		driver.get("http://127.0.0.1:8000/about");
		System.out.println("\nTesting About page");
	}
	
	@Test
	public void testProphet() {
		try {
			//get the prophet container
	        element = driver.findElement(By.cssSelector("body > div.container > div > div > div:nth-child(1) > div:nth-child(1)"));
	    } catch (Exception e) {
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testWhyOpenSource() {
		try {
			//get the Why Open Source container
			element = driver.findElement(By.cssSelector("body > div.container > div > div > div:nth-child(1) > div:nth-child(2)"));
		} catch (Exception e) {
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testAlgorithm() {
		try {
			//get the Algorithm container
			element = driver.findElement(By.cssSelector("body > div.container > div > div > div:nth-child(1) > div:nth-child(3)"));
		} catch (Exception e) {
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testTeamMembers() {
		try {
	        element = driver.findElement(By.cssSelector("body > div.container > div > div > div:nth-child(2) > div"));
		} catch (Exception e) {
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
	}

	
	@AfterClass
	public static void closeBrower() {
		//close the window
		driver.quit();
		System.out.println("\nEnding About Page Test");
	}
}
