package Slack_Github_Integration;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.junit.Assert;

public class Topic03_xpath_css {
    WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_01_LoginEmptyEmailAndPass() {
		driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		String passwordRequired = driver.findElement(By.xpath("//*[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(passwordRequired, "This is a required field.");
		String emailRequired = driver.findElement(By.xpath("//*[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(emailRequired, "This is a required field.");
		
	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("invalid@123.123");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		String emailInvalid = driver.findElement(By.xpath("//*[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailInvalid, "Please enter a valid email address. For example johndoe@domain.com.");
		
	}

	
	@Test
	public void TC_03_LoginWithPassLessThen6Chars() {
		driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("hoi@mailinator.com");
		driver.findElement(By.id("pass")).sendKeys("1234");
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		String passwordLessthan6 = driver.findElement(By.xpath("//*[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passwordLessthan6, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_LoginWithIncorrectPassword() {
		driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456789");
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		String passwordIncorrect = driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div/div/ul/li/ul/li/span")).getText();
		Assert.assertEquals(passwordIncorrect, "Invalid login or password.");
	}
	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}

}