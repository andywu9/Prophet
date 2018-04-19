
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class SignInPage {

	private static WebDriver driver;
	WebElement element;
	
	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		driver.get("http://localhost:8000/accounts/login/");
		System.out.println("\nTesting Sign In page");
	}
	
	@Test
	public void testSignUp() {
		try {
	        element = driver.findElement(By.cssSelector("body > div.container > div > div > p > a"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testUserName() {
		try {
	        element = driver.findElement(By.cssSelector("#id_login"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testPassword() {
		try {
			element = driver.findElement(By.cssSelector("#id_password"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testCheckBox() {
		try {
			element = driver.findElement(By.cssSelector("#div_id_remember"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testForgotPassword() {
		try {
			element = driver.findElement(By.cssSelector("body > div.container > div > div > form > a"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testButton() {
		try {
			element = driver.findElement(By.cssSelector("body > div.container > div > div > form > button"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testInvalidLogin() {
	
		//generate random email
		String username = "sample";
		String password = "samplePass";
		try {
	        element = driver.findElement(By.cssSelector("#id_login"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		element.sendKeys(username);
		
		try {
	        element = driver.findElement(By.cssSelector("#id_password"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		element.sendKeys(password);
		
		try {
			element = driver.findElement(By.cssSelector("body > div.container > div > div > form > button"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		element.click();
		
		try {
	        element = driver.findElement(By.cssSelector("body > div.container > div > div > form > div.alert.alert-block.alert-danger"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		
	}
	
	@Test
	public void testValidLoginAndSignOut() {
	
		//generate random email
		String username = "Testuser";
		String password = "test1234";
		try {
	        element = driver.findElement(By.cssSelector("#id_login"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		element.sendKeys(username);
		
		try {
	        element = driver.findElement(By.cssSelector("#id_password"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		element.sendKeys(password);
		
		
		try {
			element = driver.findElement(By.cssSelector("body > div.container > div > div > form > button"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		
		element.click();
		
		Assert.assertEquals("http://localhost:8000/users/Testuser/", driver.getCurrentUrl());
		
		
		try {
			element = driver.findElement(By.cssSelector("#navbarSupportedContent > ul > li:nth-child(4) > a"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		element.click();
		try {
			element = driver.findElement(By.cssSelector("body > div.container > div > div > form > button"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		element.click();
		

		element = driver.findElement(By.cssSelector("#log-in-link"));
		Assert.assertNotNull(element);
		element.click();
		
	}
	
	@AfterClass
	public static void closeBrower() {
		driver.quit();
		System.out.println("Ending Sign Up Page Testing");
	}
}
