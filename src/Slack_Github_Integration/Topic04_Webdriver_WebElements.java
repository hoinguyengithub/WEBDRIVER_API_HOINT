package Slack_Github_Integration;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.junit.Assert;

public class Topic04_Webdriver_WebElements {
    WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_CheckDisplayingElements() throws InterruptedException {
		WebElement mail = (driver.findElement(By.id("mail")));
		if (mail.isDisplayed()) mail.sendKeys("Automation");
		WebElement under18 = (driver.findElement(By.id("under_18")));
		if (under18.isDisplayed()) under18.click();
		WebElement edu = (driver.findElement(By.id("edu")));
		if (edu.isDisplayed()) edu.sendKeys("Automation");
		
		Thread.sleep(5000);
		
	}
	
	@Test 
	public void TC_02_CheckEnaledDisabledElements() throws InterruptedException {
		IsElementEnabled(driver.findElement(By.id("mail")));
		IsElementEnabled (driver.findElement(By.id("under_18")));
		IsElementEnabled (driver.findElement(By.id("edu")));
		IsElementEnabled (driver.findElement(By.id("job1")));
		IsElementEnabled (driver.findElement(By.id("development")));
		IsElementEnabled(driver.findElement(By.id("slider-1")));
		IsElementEnabled(driver.findElement(By.id("button-enabled")));
		System.out.println("Element is enabled");
		
		WebElement RadioDisabled = (driver.findElement(By.id("radio-disabled")));
		Assert.assertFalse(RadioDisabled.isEnabled());
		WebElement password = (driver.findElement(By.id("password")));
		Assert.assertFalse(password.isEnabled());
		WebElement bio = (driver.findElement(By.id("bio")));
		Assert.assertFalse(bio.isEnabled());
		WebElement job2 = (driver.findElement(By.id("job2")));
		Assert.assertFalse(job2.isEnabled());
		WebElement checkboxDisabled = (driver.findElement(By.id("check-disbaled")));
		Assert.assertFalse(checkboxDisabled.isEnabled());
		WebElement slider02 = (driver.findElement(By.id("slider-2")));
		Assert.assertFalse(slider02.isEnabled());
		WebElement ButtonDisabled = (driver.findElement(By.id("button-disabled")));
		Assert.assertFalse(ButtonDisabled.isEnabled());
		System.out.println("Element is disabled");
		Thread.sleep(5000);
	}
	@Test
	public void TC_03_CheckSelectedElementsInPage() throws InterruptedException {
		WebElement under18 = (driver.findElement(By.id("under_18")));
		if (under18.isDisplayed()) under18.click();
		WebElement development = (driver.findElement(By.id("development")));
		if (development.isDisplayed()) development.click();
		if (!under18.isSelected()) under18.click();
		if (!development.isSelected()) development.click();
		Thread.sleep(5000);
		
	}
		
	
	private void IsElementEnabled(WebElement findElement) {
		// TODO Auto-generated method stub
		
	}
		
	
	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}

}