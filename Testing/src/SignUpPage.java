
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class SignUpPage {

	private static WebDriver driver;
	WebElement element;
	
	@BeforeClass
	public static void openBrowser() {
		//open a chrome window and go to signup page
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		driver.get("http://127.0.0.1:8000/accounts/signup/");
		System.out.println("\nTesting Sign Up page");
	}
	
	@Test
	public void testEmail() {
		try {
			//get the email box
	        element = driver.findElement(By.cssSelector("#id_email"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testUsername() {
		try {
			//get the username box
			element = driver.findElement(By.cssSelector("#id_username"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testPassword1() {
		try {
			//get password box 1
			element = driver.findElement(By.cssSelector("#id_password1"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testPassword2() {
		try {
			//get password box 2
			element = driver.findElement(By.cssSelector("#id_password2"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testButton() {
		try {
			//get sign up button
			element = driver.findElement(By.cssSelector("#signup_form > button"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testSignUpValidandInValid() {
		
		//generate random integers
		Random rn = new Random();
		String letters = "abcdefghijklmnopqrstuvwxyz";
	
		//generate random email
		String email = "sample";
		for(int i =0 ; i<rn.nextInt(3)+1;++i)
		{
			email+=letters.charAt(rn.nextInt(26));
		}
		email = email + "@gmail.com";
		
		//generate random username
		String username = "sample";
		for(int i =0 ; i<rn.nextInt(3)+1;++i)
		{
			username+=letters.charAt(rn.nextInt(26));
		}
		
		//get the email box, make sure it is there and fill it with the email to it
		element = driver.findElement(By.cssSelector("#id_email"));
		Assert.assertNotNull(element);
		element.sendKeys(email);
		
		//get the username box, make sure it is there and fill it with the username to it
		element = driver.findElement(By.cssSelector("#id_username"));
		Assert.assertNotNull(element);
		element.sendKeys(username);
		
		//get the password box1, make sure it is there and fill it with "password123"
		element = driver.findElement(By.cssSelector("#id_password1"));
		Assert.assertNotNull(element);
		element.sendKeys("password123");
		
		//get the password box1, make sure it is there and fill it with "password123"
		element = driver.findElement(By.cssSelector("#id_password2"));
		Assert.assertNotNull(element);
		element.sendKeys("password123");

		//get the sign up button, make sure it is there and click it
		element = driver.findElement(By.cssSelector("#signup_form > button"));
		Assert.assertNotNull(element);
		element.click();
		
		//make sure we are redirected to a new page
		Assert.assertEquals("http://127.0.0.1:8000/accounts/confirm-email/", driver.getCurrentUrl());
		
		//get the sign up lin page on header, make sure it is there and click it
		element = driver.findElement(By.xpath("//*[@id=\"sign-up-link\"]"));
		Assert.assertNotNull(element);
		element.click();
		
		//get the email box, make sure it is there and fill it with the email to it
		element = driver.findElement(By.cssSelector("#id_email"));
		Assert.assertNotNull(element);
		element.sendKeys(email);
		
		//get the username box, make sure it is there and fill it with the username to it
		element = driver.findElement(By.cssSelector("#id_username"));
		Assert.assertNotNull(element);
		element.sendKeys(username);
		
		//get the password box1, make sure it is there and fill it with "pass"
		element = driver.findElement(By.cssSelector("#id_password1"));
		Assert.assertNotNull(element);
		element.sendKeys("pass");
		
		//get the password box1, make sure it is there and fill it with the username to it
		element = driver.findElement(By.cssSelector("#id_password2"));
		Assert.assertNotNull(element);
		element.sendKeys("pass");

		//get the sign up button, make sure it is there and click it
		element = driver.findElement(By.cssSelector("#signup_form > button"));
		Assert.assertNotNull(element);
		element.click();
		
		
		try {
			//get the error message for the email
	        element = driver.findElement(By.cssSelector("#error_1_id_email"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
		
		try {
			//get the error message for the username
			element = driver.findElement(By.cssSelector("#error_1_id_username"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);
		
		try {
			//get the error message for password
			element =  driver.findElement(By.cssSelector("#error_1_id_password1"));
		} catch (Exception e) {
		}
		//make sure that it is there
		Assert.assertNotNull(element);

		
	}
	
	@AfterClass
	public static void closeBrower() {
		//close the window
		driver.quit();
		System.out.println("Ending Sign Up Page Testing");
	}
}
