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
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		String passwordRequired = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(passwordRequired, "This is a required field.");
		String emailRequired = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(emailRequired, "This is a required field.");
		
	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("invalid@123.123");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		String emailInvalid = driver.findElement(By.id("advice-validate-email-email")).getText();
		Assert.assertEquals(emailInvalid, "Please enter a valid email address. For example johndoe@domain.com.");
		
	}

	
	@Test
	public void TC_03_LoginWithPassLessThen6Chars() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("hoi@mailinator.com");
		driver.findElement(By.id("pass")).sendKeys("1234");
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		String passwordLessthan6 = driver.findElement(By.id("advice-validate-password-pass")).getText();
		Assert.assertEquals(passwordLessthan6, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_LoginWithIncorrectPassword() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456789");
		driver.findElement(By.xpath("//*[@id='send2']")).click();
		
		String passwordIncorrect = driver.findElement(By.className("error-msg")).getText();
		Assert.assertEquals(passwordIncorrect, "Invalid login or password.");
	}
	@Test
	public void TC_05_CreateAnAccount() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys("Hoi15");
		driver.findElement(By.id("middlename")).sendKeys("");
		driver.findElement(By.id("lastname")).sendKeys("HoiNT15");
		driver.findElement(By.id("email_address")).sendKeys("hoint15@mailinator.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		String successMessage = driver.findElement(By.className("success-msg")).getText();
		Assert.assertEquals(successMessage, "Thank you for registering with Main Website Store.");
		
		driver.findElement(By.xpath("//span[@class='label'][contains(text(),'Account')]")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		String LogoutSuccess = driver.findElement(By.className("page-title")).getText();
		Assert.assertEquals(LogoutSuccess, "YOU ARE NOW LOGGED OUT");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/index.php/");
		
		
	}
	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}

}