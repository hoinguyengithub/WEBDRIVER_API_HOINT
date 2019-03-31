package Slack_Github_Integration;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

public class Topic09_Iframe_Popup {
	WebDriver driver;
	JavascriptExecutor javascript;
	

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		javascript = ((JavascriptExecutor) driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
			}

	@Test 
	public void TC_01_() throws Exception  {
		driver.get("http://www.hdfcbank.com/");
		
		List <WebElement> notificationIframe = driver.findElements(By.xpath("//div[@id='parentdiv']//img[@class='popupbanner']")); 
		int notificationIframeSize = notificationIframe.size();
		
		if (notificationIframeSize > 0){
			javascript.executeScript("arguments[0].click()", driver.findElement(By.xpath("//img[@class='popupCloseButton']")));
		}
		
		WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		
		driver.switchTo().frame(lookingForIframe);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']")).isDisplayed());
		
		driver.switchTo().defaultContent();
		
		WebElement slidingBannerIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		
		driver.switchTo().frame(slidingBannerIframe);
		
		List <WebElement> bannerImage = driver.findElements(By.xpath("//img[@class='bannerimage']"));
		Assert.assertEquals(bannerImage.size(), 6);
		
		for (WebElement image:bannerImage){
			Assert.assertTrue(isImageLoadedSuccess(image));
			}
		
		driver.switchTo().defaultContent();
		List <WebElement> flipBanner = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		Assert.assertEquals(flipBanner.size(), 8);
		
		for (WebElement image:flipBanner){
			Assert.assertTrue(isImageLoadedSuccess(image));
			Assert.assertTrue(image.isDisplayed());
			
			}
	}
	
	
		public boolean isImageLoadedSuccess (WebElement imageElement){
		return (boolean) javascript.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageElement);
		}

		
	
	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}

}