package Slack_Github_Integration;


import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.Date;


public class Topic13_Wait_Part1 {
	WebDriver driver;
    WebDriverWait waitExplicit;
    Date date;
    
    

	By StartButton = By.xpath("//button[text()='Start']");
	By LoadingIndicator = By.xpath("//div[@id='loading']/img");
	By HelloText = By.xpath("//h4[text()='Hello World!']");

	@BeforeTest
	public void beforeTest() {
		//System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
		//driver = new FirefoxDriver();
				
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
				
		//System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}
	

	@Test
	public void TC_01_WaitImplicit_2SFailed()  {
		//HelloWord does not display within 2s -> Failed
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(StartButton).click();
		Assert.assertTrue(driver.findElement(HelloText).isDisplayed());
		
	}
	
	@Test
	public void TC_02_WaitImplicit_5S()  {
		//After 5s, Hello Wordl text displays -> passed
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(StartButton).click();
		Assert.assertTrue(driver.findElement(HelloText).isDisplayed());
	}
	
	@Test
	public void TC_03_WaitExplicit_LoadingIndicatorInvisible_Passed() {
		//It takes 5s to hidden loading indicator and show HelloWord Text -> implicit 3s + explicit wait 2s -> failed
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 2);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingIndicator));
		Assert.assertTrue(driver.findElement(HelloText).isDisplayed());
		
		
	}
	@Test
	public void TC_04_WaitExplicit_HelloWorldVisible_Passed() {
		//It takes 5s to hidden loading indicator and show HelloWord Text -> explicit wait 2s -> failed
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 2);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(StartButton).click();
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(HelloText));
		Assert.assertTrue(driver.findElement(HelloText).isDisplayed());
	}
	
	@Test
	public void TC_05_TimesToElementsVisibleOrInvisible() {
		//It takes 5s to hidden loading indicator and show HelloWord Text -> Implicit 3s + explicit wait 2s -> failed
		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);
	
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		
		//Check Helloworld invisible + ko có trong DOM
		
		System.out.println("Start time: " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(HelloText));
		System.out.println("End time: " + getDateTimeSecond());
		
		//Check LoadingIndicator invisible + ko có trong DOM
		System.out.println("Start time: " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingIndicator));
		System.out.println("End time: " + getDateTimeSecond());
		
		System.out.println("Start time: " + getDateTimeSecond());
		driver.findElement(StartButton).click();
		System.out.println("End time: " + getDateTimeSecond());
		
		System.out.println("Start time: " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(LoadingIndicator));
		System.out.println("End time: " + getDateTimeSecond());
		
		System.out.println("Start time: " + getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(StartButton));
		System.out.println("End time: " + getDateTimeSecond());
		
	}

	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}
	
	public Date getDateTimeSecond() {
        Date date = new Date();
        date = new Timestamp(date.getTime());
        return date;
}
	
	
}

