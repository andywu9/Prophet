

import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

	private static WebDriver driver;
	WebElement element;
	
	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		driver.get("localhost:8000");
		System.out.println("\nTesting Home page");
	}
	

	@Test
	public void testHeader() {
		//check navbar
		try {
	        element = driver.findElement(By.className("navbar"));
	    } catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		
		//check home button
		try {
	        element = driver.findElement(By.cssSelector("#navbarSupportedContent > ul > li.nav-item.active > a"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		
		//check about button
		try {
	        element = driver.findElement(By.cssSelector("#navbarSupportedContent > ul > li:nth-child(2) > a"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		
		//check sign up
		try {
	        element = driver.findElement(By.cssSelector("#sign-up-link"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		
		//check sign in
		try {
	        element = driver.findElement(By.cssSelector("#log-in-link"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testSearchBarExists() {
		//test its there
		try {
	        element = driver.findElement(By.cssSelector("#table-search-bar"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		
	}
	
	@Test
	public void testAllCoinsVisible() {
		List<WebElement> ele = driver.findElements(By.xpath("//*[@id=\"coins\"]/tbody//tr"));
		for(WebElement el1:ele)
		{
			Assert.assertEquals("", el1.getAttribute("style"));
		}
	}
	
	@Test
	public void testAllCoinsBit() {
		List<WebElement> ele = driver.findElements(By.xpath("//*[@id=\"coins\"]/tbody//tr"));
		WebElement searchbar = driver.findElement(By.cssSelector("#table-search-bar"));
		searchbar.sendKeys(Keys.CONTROL + "a");
		searchbar.sendKeys(Keys.DELETE);
		searchbar.sendKeys("bit");
		for(WebElement el1:ele)
		{
			if(el1.getAttribute("id").contains("bit")||el1.getAttribute("id").contains("Bit"))
			{
				Assert.assertEquals("", el1.getAttribute("style"));
			}else {

				Assert.assertEquals("display: none;", el1.getAttribute("style"));
			}
		}
	}
	
	@Test
	public void testAllCoinsBitCoin() {
		List<WebElement> ele = driver.findElements(By.xpath("//*[@id=\"coins\"]/tbody//tr"));
		WebElement searchbar = driver.findElement(By.cssSelector("#table-search-bar"));
		searchbar.sendKeys(Keys.CONTROL + "a");
		searchbar.sendKeys(Keys.DELETE);
		searchbar.sendKeys("bitcoin");
		for(WebElement el1:ele)
		{
			if(el1.getAttribute("id").contains("bitcoin")||el1.getAttribute("id").contains("Bitcoin"))
			{
				Assert.assertEquals("", el1.getAttribute("style"));
			}else {

				Assert.assertEquals("display: none;", el1.getAttribute("style"));
			}
		}
	}
	
	@Test
	public void testAllCoinsBitCoinCoin() {
		List<WebElement> ele = driver.findElements(By.xpath("//*[@id=\"coins\"]/tbody//tr"));
		WebElement searchbar = driver.findElement(By.cssSelector("#table-search-bar"));
		searchbar.sendKeys(Keys.CONTROL + "a");
		searchbar.sendKeys(Keys.DELETE);
		searchbar.sendKeys("bitcoincoin");
		for(WebElement el1:ele)
		{
			if(el1.getAttribute("id").contains("bitcoincoin")||el1.getAttribute("id").contains("Bitcoincoin"))
			{
				Assert.assertEquals("", el1.getAttribute("style"));
			}else {
				Assert.assertEquals("display: none;", el1.getAttribute("style"));
			}
		}
	}
	
	@Test
	public void testAllCoinsBitCoinTake2() {
		List<WebElement> ele = driver.findElements(By.xpath("//*[@id=\"coins\"]/tbody//tr"));
		WebElement searchbar = driver.findElement(By.cssSelector("#table-search-bar"));
		searchbar.sendKeys(Keys.CONTROL + "a");
		searchbar.sendKeys(Keys.DELETE);
		searchbar.sendKeys("bitcoincoin");
		for(WebElement el1:ele)
		{
			if(el1.getAttribute("id").contains("bitcoincoin")||el1.getAttribute("id").contains("Bitcoincoin"))
			{
				Assert.assertEquals("", el1.getAttribute("style"));
			}else {
				Assert.assertEquals("display: none;", el1.getAttribute("style"));
			}
		}
		
		searchbar.sendKeys(Keys.BACK_SPACE);
		searchbar.sendKeys(Keys.BACK_SPACE);
		searchbar.sendKeys(Keys.BACK_SPACE);
		searchbar.sendKeys(Keys.BACK_SPACE);
		for(WebElement el1:ele)
		{
			if(el1.getAttribute("id").contains("bitcoin")||el1.getAttribute("id").contains("Bitcoin"))
			{
				Assert.assertEquals("", el1.getAttribute("style"));
			}else {

				Assert.assertEquals("display: none;", el1.getAttribute("style"));
			}
		}	
	}	
	
	@Test
	public void testModalExists() {
		try {
	        element = driver.findElement(By.cssSelector("#myModal"));
	    } catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testModalVisible() {
		try {
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();

		try {
	        element = driver.findElement(By.cssSelector("#myModal"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		Assert.assertEquals("display: block;", element.getAttribute("style"));
		
		try {
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();
	}

	@Test
	public void testModalData() {
		try {
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();

		try {
	        element = driver.findElement(By.cssSelector("#data-tab-button"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		Assert.assertEquals("active", element.getAttribute("class"));
		
		try {
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();
	}
	
	@Test
	public void testModalInfo() {
		try {
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();

		try {
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(1) > ul > li:nth-child(2)"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		Assert.assertEquals("", element.getAttribute("class"));
		
		try {
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();
	}
	
	@Test
	public void testModalNamesMatch() {
		try {
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();

		try {
	        element = driver.findElement(By.cssSelector("#modal-title-text"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		Assert.assertEquals("Bitcoin", element.getText());
		
		try {
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();
	}
	
	@Test
	public void testModal1mActive() {
		try {
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();
		
		try {
	        element = driver.findElement(By.cssSelector("#data-tab-button > a"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();
		
		try {
	        element = driver.findElement(By.cssSelector("#price-graph-tabs"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		
		
		List<WebElement> ele = driver.findElements(By.xpath("//*[@id=\"price-graph-tabs\"]//button"));
		for(WebElement el1:ele)
		{
			if(el1.getText().equals("1m"))
			{
				Assert.assertEquals("tablinks active", el1.getAttribute("class"));
			}else {
				Assert.assertEquals("tablinks", el1.getAttribute("class"));
			}
		}
		
		try {
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();
	}
		
	@Test
	public void testModalInfoPopulated() {
		try {
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();

		try {
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(1) > ul > li.active"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();
		
		try {
	        element = driver.findElement(By.cssSelector("#info"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		
		try {
	        element = driver.findElement(By.cssSelector("#coin-desc"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		
		try {
	        element = driver.findElement(By.cssSelector("#coin-desc-text"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		
		try {
	        element = driver.findElement(By.cssSelector("#algo-desc"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		
		try {
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();
	}
	
	@Test
	public void testModalDataAlwaysActive() {
		try {
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();

		try {
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(1) > ul > li:nth-child(2) > a"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();
		
		try {
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();
		
		try {
	        element = driver.findElement(By.cssSelector("#Ethereum"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		element.click();
		
		try {
	        element = driver.findElement(By.cssSelector("#info"));
		} catch (Exception e) {	
	    }
		Assert.assertNotNull(element);
		Assert.assertEquals("tab-panel", element.getAttribute("class"));
	}
	

	
	@AfterClass
	public static void closeBrower() {
		driver.quit();
		System.out.println("Ending Home Page Testing");
	}
}
