package Slack_Github_Integration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic02_GithubIntegration {
    WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_CheckUrl() {
		String homePageUrl = driver.getCurrentUrl();
		Assert.assertEquals(homePageUrl, "https://www.facebook.com/");
	}
	
	@Test
	public void TC_01_CheckTitle() {
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Facebook - Đăng nhập hoặc đăng ký");
	}

	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}

}