
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
		System.setProperty("webdriver.chrome.driver", "/home/igor/Downloads/chromedriver_linux64 (1)/chromedriver");
		driver = new ChromeDriver();
		driver.get("http://127.0.0.1:8000/accounts/signup/");
		System.out.println("\nTesting Sign Up page");
	}
	
	@Test
	public void testEmail() {
		try {
	        element = driver.findElement(By.cssSelector("#id_email"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testUsername() {
		try {
			element = driver.findElement(By.cssSelector("#id_username"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testPassword1() {
		try {
			element = driver.findElement(By.cssSelector("#id_password1"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testPassword2() {
		try {
			element = driver.findElement(By.cssSelector("#id_password2"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testButton() {
		try {
			element = driver.findElement(By.cssSelector("#signup_form > button"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testSignUpValidandInValid() {
		
		//generate randome intergets
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
		
		//test a succesfull sign up
		driver.findElement(By.cssSelector("#id_email")).sendKeys(email);
		driver.findElement(By.cssSelector("#id_username")).sendKeys(username);
		driver.findElement(By.cssSelector("#id_password2")).sendKeys("password123");
		driver.findElement(By.cssSelector("#id_password1")).sendKeys("password123");
		driver.findElement(By.cssSelector("#signup_form > button")).click();
		
		Assert.assertEquals("http://127.0.0.1:8000/accounts/confirm-email/", driver.getCurrentUrl());
		
		element = driver.findElement(By.xpath("//*[@id=\"sign-up-link\"]"));
		Assert.assertNotNull(element);
		element.click();
		
		
		//test a sign up failed
		driver.findElement(By.cssSelector("#id_email")).sendKeys(email);
		driver.findElement(By.cssSelector("#id_username")).sendKeys(username);
		driver.findElement(By.cssSelector("#id_password2")).sendKeys("pass");
		driver.findElement(By.cssSelector("#id_password1")).sendKeys("pass");
		driver.findElement(By.cssSelector("#signup_form > button")).click();
		
		try {
	        element = driver.findElement(By.cssSelector("#error_1_id_email"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		
		try {
			element = driver.findElement(By.cssSelector("#error_1_id_username"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);
		
		try {
			element =  driver.findElement(By.cssSelector("#error_1_id_password1"));
		} catch (Exception e) {
		}
		Assert.assertNotNull(element);

		
	}
	
	@AfterClass
	public static void closeBrower() {
		driver.quit();
		System.out.println("Ending Sign Up Page Testing");
	}
}
