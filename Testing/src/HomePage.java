

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
		//open a window and go to home page
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		driver.get("localhost:8000");
		System.out.println("\nTesting Home page");
	}
	

	@Test
	public void testHeader() {
		
		try {
			//get the nav bar
	        element = driver.findElement(By.className("navbar"));
	    } catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
		
		try {
			//check home button
	        element = driver.findElement(By.cssSelector("#navbarSupportedContent > ul > li.nav-item.active > a"));
		} catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
		
		try {
			//check about button
	        element = driver.findElement(By.cssSelector("#navbarSupportedContent > ul > li:nth-child(2) > a"));
		} catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
		
		try {
			//check sign up
	        element = driver.findElement(By.cssSelector("#sign-up-link"));
		} catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
		
		try {
			//check sign in
	        element = driver.findElement(By.cssSelector("#log-in-link"));
		} catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testSearchBarExists() {
		
		try {
			//get the search bar
	        element = driver.findElement(By.cssSelector("#table-search-bar"));
		} catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
		
	}
	
	@Test
	public void testAllCoinsVisible() {

		//get all the coins
		List<WebElement> ele = driver.findElements(By.xpath("//*[@id=\"coins\"]/tbody//tr"));
		for(WebElement el1:ele)
		{
			//make sure they are visible
			Assert.assertEquals("", el1.getAttribute("style"));
		}
	}
	
	@Test
	public void testAllCoinsBit() {
		//get all the coins
		List<WebElement> ele = driver.findElements(By.xpath("//*[@id=\"coins\"]/tbody//tr"));
		
		//get the search bar, clear it, and fill it with "bit"
		WebElement searchbar = driver.findElement(By.cssSelector("#table-search-bar"));
		searchbar.sendKeys(Keys.CONTROL + "a");
		searchbar.sendKeys(Keys.DELETE);
		searchbar.sendKeys("bit");
		
		//for all the coins, make sure it is visible if it has the word "bit"
		//and those without the "bit" are not visible
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
		//get all the coins
		List<WebElement> ele = driver.findElements(By.xpath("//*[@id=\"coins\"]/tbody//tr"));

		//get the search bar, clear it, and fill it with "bitcoin"
		WebElement searchbar = driver.findElement(By.cssSelector("#table-search-bar"));
		searchbar.sendKeys(Keys.CONTROL + "a");
		searchbar.sendKeys(Keys.DELETE);
		searchbar.sendKeys("bitcoin");
		
		//for all the coins, make sure it is visible if it has the word "bitcoin"
		//and those without the "bitcoin" are not visible
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
		//get all the coins
		List<WebElement> ele = driver.findElements(By.xpath("//*[@id=\"coins\"]/tbody//tr"));
		
		//get the search bar, clear it, and fill it with "bitcoin"
		WebElement searchbar = driver.findElement(By.cssSelector("#table-search-bar"));
		searchbar.sendKeys(Keys.CONTROL + "a");
		searchbar.sendKeys(Keys.DELETE);
		searchbar.sendKeys("bitcoincoin");

		//for all the coins, make sure it is visible if it has the word "bitcoincoin"
		//and those without the "bitcoincoin" are not visible
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
		//get all the coins
		List<WebElement> ele = driver.findElements(By.xpath("//*[@id=\"coins\"]/tbody//tr"));
		
		//get the search bar, clear it, and fill it with "bitcoin"
		WebElement searchbar = driver.findElement(By.cssSelector("#table-search-bar"));
		searchbar.sendKeys(Keys.CONTROL + "a");
		searchbar.sendKeys(Keys.DELETE);
		searchbar.sendKeys("bitcoincoin");

		//for all the coins, make sure it is visible if it has the word "bitcoincoin"
		//and those without the "bitcoincoin" are not visible
		for(WebElement el1:ele)
		{
			if(el1.getAttribute("id").contains("bitcoincoin")||el1.getAttribute("id").contains("Bitcoincoin"))
			{
				Assert.assertEquals("", el1.getAttribute("style"));
			}else {
				Assert.assertEquals("display: none;", el1.getAttribute("style"));
			}
		}
		
		//clear the search bar back to "bitcoin"
		searchbar.sendKeys(Keys.BACK_SPACE);
		searchbar.sendKeys(Keys.BACK_SPACE);
		searchbar.sendKeys(Keys.BACK_SPACE);
		searchbar.sendKeys(Keys.BACK_SPACE);
		//for all the coins, make sure it is visible if it has the word "bitcoin"
		//and those without the "bitcoin" are not visible
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
			//get the modal
	        element = driver.findElement(By.cssSelector("#myModal"));
	    } catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
	}
	
	@Test
	public void testModalVisible() {
		try {
			//get bitcoin row
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();

		try {
			//get the modal
	        element = driver.findElement(By.cssSelector("#myModal"));
		} catch (Exception e) {	
	    }
		//make sure that it is there adn visible
		Assert.assertNotNull(element);
		Assert.assertEquals("display: block;", element.getAttribute("style"));
		
		try {
			//get the x on the modal
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();
	}

	@Test
	public void testModalData() {
		try {
			//get the bitcoin row
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();

		try {
			//get the data tab
	        element = driver.findElement(By.cssSelector("#data-tab-button"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and that it is active
		Assert.assertNotNull(element);
		Assert.assertEquals("active", element.getAttribute("class"));
		
		try {
			//get the x on the modal
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();
	}
	
	@Test
	public void testModalInfo() {
		try {
			//get the bitcoin row
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();

		try {
			//get the info tab
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(1) > ul > li:nth-child(2)"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and not click
		Assert.assertNotNull(element);
		Assert.assertEquals("", element.getAttribute("class"));
		
		try {
			//get the x on the modal
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();
	}
	
	@Test
	public void testModalNamesMatch() {
		try {
			//get the bitcoin row
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();

		try {
			//get the coin name on modal
	        element = driver.findElement(By.cssSelector("#modal-title-text"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and says "Bitcoin"
		Assert.assertNotNull(element);
		Assert.assertEquals("Bitcoin", element.getText());
		
		try {
			//get the x on the modal
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();
	}
	
	@Test
	public void testModal1mActive() {
		try {
			//get the bitcoin row
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();
		
		try {
			//get the data tab
	        element = driver.findElement(By.cssSelector("#data-tab-button > a"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();
		
		try {
			//get the tabs on the graph
	        element = driver.findElement(By.cssSelector("#price-graph-tabs"));
		} catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
		
		//go through each tab and make sure it is inactive unless it is the default "1m"
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
			//get the x on the modal
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
		element.click();
	}
		
	@Test
	public void testModalInfoPopulated() {
		try {
			//get the bitcoin modal
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();

		try {
			//get the info tab
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(1) > ul > li.active"));
		} catch (Exception e) {	
	    }
		//make sure that it is there click it
		Assert.assertNotNull(element);
		element.click();
		
		try {
			//get the information 
	        element = driver.findElement(By.cssSelector("#info"));
		} catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
		
		try {
			//get the coin description
	        element = driver.findElement(By.cssSelector("#coin-desc"));
		} catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
		
		try {
			//get the coin discription text
	        element = driver.findElement(By.cssSelector("#coin-desc-text"));
		} catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
		
		try {
			//get the coin algo
	        element = driver.findElement(By.cssSelector("#algo-desc"));
		} catch (Exception e) {	
	    }
		//make sure that it is there
		Assert.assertNotNull(element);
		
		try {
			//get the coin algo text
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();
	}
	
	@Test
	public void testModalDataAlwaysActive() {
		try {
			//get the bitcoin row
	        element = driver.findElement(By.cssSelector("#Bitcoin"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();

		try {
			// get the data tab
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(1) > ul > li:nth-child(2) > a"));
		} catch (Exception e) {	
	    }
		//make sure that it is ther eand click it
		Assert.assertNotNull(element);
		element.click();
		
		try {
			//get the x on the modal
	        element = driver.findElement(By.cssSelector("#myModal > div > table > tbody > tr > td:nth-child(2) > div > span"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();
		
		try {
			//get the ethereum row
	        element = driver.findElement(By.cssSelector("#Ethereum"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and click it
		Assert.assertNotNull(element);
		element.click();
		
		try {
			//get the info tab
	        element = driver.findElement(By.cssSelector("#info"));
		} catch (Exception e) {	
	    }
		//make sure that it is there and not active
		Assert.assertNotNull(element);
		Assert.assertEquals("tab-panel", element.getAttribute("class"));
	}
	

	
	@AfterClass
	public static void closeBrower() {
		//close the window
		driver.quit();
		System.out.println("Ending Home Page Testing");
	}
}
