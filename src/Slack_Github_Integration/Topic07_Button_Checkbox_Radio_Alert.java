package Slack_Github_Integration;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic07_Button_Checkbox_Radio_Alert {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
    

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		jsExecutor = ((JavascriptExecutor) driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
			}

	@Test
	public void TC_01_Button_Click()  {
		driver.get("http://live.guru99.com/");
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		myAccountLink.click();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		
		WebElement CreateAnAccount = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		CreateAnAccount.click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_02_Button_JSClick()  {
		driver.get("http://live.guru99.com/");
		WebElement myAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		jsExecutor.executeScript("arguments[0].click();", myAccountLink);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");

		WebElement CreateAnAccount = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		jsExecutor.executeScript("arguments[0].click();", CreateAnAccount);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_03_DefaultCheckbox() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		WebElement DevelopmentCheckbox = driver.findElement(By.xpath("//label[text()='Development']/preceding-sibling::input"));
		checkToCheckbox(DevelopmentCheckbox);
		Assert.assertTrue(DevelopmentCheckbox.isSelected());
		UncheckToCheckbox(DevelopmentCheckbox);
		Assert.assertFalse(DevelopmentCheckbox.isSelected());
		
	}
	
	@Test
	public void TC_04_CustomCheckbox() {
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
	
		WebElement DuoZoneCheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		checkToCheckbox(DuoZoneCheckbox);
		Assert.assertTrue(DuoZoneCheckbox.isSelected());
	
		UncheckToCheckbox(DuoZoneCheckbox);
		Assert.assertFalse(DuoZoneCheckbox.isSelected());
	
	}
	
	@Test
	public void TC_05_RadioButton() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		WebElement radioButton = driver.findElement(By.xpath("//label[contains(text(),'2.0 Petrol, 147kW')]/preceding-sibling::input"));
		checkToCheckbox(radioButton);
		Assert.assertTrue(radioButton.isSelected());
	}
	
	@Test
	public void TC_06_Accept_Confirm_Prompt_Alert() throws Exception {
		driver.get(" https://daominhdam.github.io/basic-form/index.html");
	
		//JS Alert Accept
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Alert acceptAlert = driver.switchTo().alert();
		Assert.assertEquals(acceptAlert.getText(), "I am a JS Alert");
		acceptAlert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You clicked an alert successfully ']")).isDisplayed());
		Thread.sleep(2000);
		
		// JS Alert Confirm
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		Alert ConfirmAlert = driver.switchTo().alert();
		Assert.assertEquals(ConfirmAlert.getText(), "I am a JS Confirm");
		ConfirmAlert.dismiss();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You clicked: Cancel']")).isDisplayed());
		Thread.sleep(2000);
		
		// JS Alert prompt
		driver.navigate().refresh();
		String textInput ="Hoi Nguyen test";
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		Alert PromptAlert = driver.switchTo().alert();
		Assert.assertEquals(PromptAlert.getText(), "I am a JS prompt");
		PromptAlert.sendKeys(textInput);
		PromptAlert.accept();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@id='result' and text()='You entered: " + textInput + "']")).isDisplayed());
		Thread.sleep(2000);
		
	}
	@Test
	public void TC_07_AuthenticationAlert () {
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}

	
	public void checkToCheckbox (WebElement element) {
		if (!element.isSelected()) {
			if (element.isDisplayed()) {
				element.click();
				}	else {
			jsExecutor.executeScript("arguments[0].click();", element);
				}
			}
	}

	public void UncheckToCheckbox (WebElement element) {
		if(element.isSelected()) {
			if(element.isDisplayed()) {
				element.click();
			} else {
				jsExecutor.executeScript("arguments[0].click();", element);
			}
		}
	}
	
	
	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}

}