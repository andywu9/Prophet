
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
	
	//opens up chrome and goes to login page
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
			//get signup link 
	        element = driver.findElement(By.cssSelector("body > div.container > div > div > p > a"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
	}

	
	@Test
	public void testUserName() {
		try {
			//get username box 
	        element = driver.findElement(By.cssSelector("#id_login"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testPassword() {
		try {
			//get password box 
			element = driver.findElement(By.cssSelector("#id_password"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testCheckBox() {
		try {
			//get remember me box 
			element = driver.findElement(By.cssSelector("#div_id_remember"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
	}

	@Test
	public void testForgotPassword() {
		try {
			//get forgot password link
			element = driver.findElement(By.cssSelector("body > div.container > div > div > form > a"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testButton() {
		try {
			//get sign in button
			element = driver.findElement(By.cssSelector("body > div.container > div > div > form > button"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testInvalidLogin() {
	
		String username = "sample";
		String password = "samplePass";
		try {
			//get username box 
	        element = driver.findElement(By.cssSelector("#id_login"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
		//fill it in with "sample"
		element.sendKeys(username);
		
		try {
			//get password box
	        element = driver.findElement(By.cssSelector("#id_password"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
		//fill it with samplePass
		element.sendKeys(password);
		
		try {
			//get sign in button
			element = driver.findElement(By.cssSelector("body > div.container > div > div > form > button"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
		//click sign in button
		element.click();
		
		try {
			//get the error message
	        element = driver.findElement(By.cssSelector("body > div.container > div > div > form > div.alert.alert-block.alert-danger"));
		} catch (Exception e) {
		}
		//make sure that the error message is there
		Assert.assertNotNull(element);
		
	}
	
	@Test
	public void testValidLoginAndSignOut() {
	
		String username = "Testuser";
		String password = "test1234";
		try {
			//get the login box
	        element = driver.findElement(By.cssSelector("#id_login"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
		//clear the data in it and fill it with "Testuser"
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		element.sendKeys(username);
		
		try {
			//get the password box
	        element = driver.findElement(By.cssSelector("#id_password"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
		//clear the data in it and fill it with "test1234"
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		element.sendKeys(password);
		
		
		try {
			//get the sign in button
			element = driver.findElement(By.cssSelector("body > div.container > div > div > form > button"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
		//click it
		element.click();
		
		//make sure we are redirected to a sign in page
		Assert.assertEquals("http://localhost:8000/users/Testuser/", driver.getCurrentUrl());
		
		
		try {
			//get the sign out box on header
			element = driver.findElement(By.cssSelector("#navbarSupportedContent > ul > li:nth-child(4) > a"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
		//click it
		element.click();
		
		try {
			//get the sign out button
			element = driver.findElement(By.cssSelector("body > div.container > div > div > form > button"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
		//click it
		element.click();
		
		//get the sign in link from header and click it
		element = driver.findElement(By.cssSelector("#log-in-link"));
		Assert.assertNotNull(element);
		element.click();
		
	}
	
	
	@AfterClass
	public static void closeBrower() {
		//close the chrome window
		driver.quit();
		System.out.println("Ending Sign Up Page Testing");
	}
}
