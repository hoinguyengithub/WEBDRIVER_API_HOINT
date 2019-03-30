package Slack_Github_Integration;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.junit.Assert;

public class Topic09_Iframe_Popup {
	WebDriver driver;
	private RemoteWebDriver javascript;
	

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
			}

	@Test 
	public void TC_01_()  {
		driver.get("http://www.hdfcbank.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		List <WebElement> NotificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		
		int NotificationIframeSize = NotificationIframe.size();
		
		if (NotificationIframeSize > 0) {
			driver.switchTo().frame(NotificationIframe.get(0));
			
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
			javascript.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='div-close']")));
			
			
			WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
			driver.switchTo().frame(lookingForIframe);
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Assert.assertTrue(driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']")).isDisplayed());
			
			driver.switchTo().defaultContent();
			WebElement SlidingBannerIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
			driver.switchTo().frame(SlidingBannerIframe);
			
		// Check if sliding banner have 6 images
		List <WebElement> SlidingBanner = driver.findElements(By.xpath("//div[@class='bannerimage-container']//img"));
		System.out.println("Sliding banner = " + SlidingBanner.size());
		Assert.assertEquals(SlidingBanner.size(), 6);
		
		for (WebElement imageElement : SlidingBanner) {
			Assert.assertTrue(isImageLoadedSuccess(imageElement));
			
		//Check if flip banner have 8 images
			driver.switchTo().defaultContent();
		
		List <WebElement> FlipBanner = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		System.out.println("Sliding banner = " + FlipBanner.size());
		Assert.assertEquals(FlipBanner.size(), 8);
				
		for (WebElement FlipImage : FlipBanner) {
		Assert.assertTrue(isImageLoadedSuccess(imageElement));
		Assert.assertTrue(FlipImage.isDisplayed());
		
		}
		}
		}
		
		
		//NotiPopup: //iframe[@id='vizury-notification-template']
		//closeIcon: //div[@id='div-close']
		// flipbanner lookingfor: //div[@class='flipBannerWrap']//iframe
		//sliding banner: //div[@class='bannerimage-container']//img
		//Verify slidingbanner have 6 images:
		
		
	}
	
	public boolean isImageLoadedSuccess(WebElement imageElement) {
		return (boolean) javascript.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageElement);
	
	}

	public void TC_02_() {
		;
		
	}
	
	
		
	
	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}

}