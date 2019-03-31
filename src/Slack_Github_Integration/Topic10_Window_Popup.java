package Slack_Github_Integration;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic10_Window_Popup {
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
	public void TC_01_SwithByID() throws Exception  {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		String ParentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='Click Here']")).click();
		switchToChildWindowByID(ParentID);
		Thread.sleep(2000);
		
		String GoogleTitle = driver.getTitle();
		Assert.assertEquals(GoogleTitle, "Google");
		Assert.assertTrue(closeAllWithoutParentWindows(ParentID));
		Assert.assertEquals(driver.getTitle(),"SELENIUM WEBDRIVER FORM DEMO");
		
	}
	@Test
	public void TC_02_SwitchByTitle() throws Exception {
		driver.get("http://www.hdfcbank.com/");
		String parentID = driver.getWindowHandle();
		
		List <WebElement> NotiPopup = driver.findElements(By.xpath("//div[@id='parentdiv']/img[@class='popupbanner']"));
		
		int notificationIframeSize = NotiPopup.size();
		
		if (notificationIframeSize > 0){
			javascript.executeScript("arguments[0].click()", driver.findElement(By.xpath("//img[@class='popupCloseButton']")));
			
		}
		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		//Title of Agri page: HDFC Bank Kisan Dhan Vikas e-Kendra
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		Thread.sleep(2000);
		
		//xpath of Account Details: //p[text()='Account Details']
		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		Thread.sleep(2000);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='footer']")));
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		//Title of PP: HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='CSR']"));
		Assert.assertTrue(closeAllWithoutParentWindows(parentID));
	
	}
	
	@Test
	public void TC_03_AddtoCompare() throws Exception {
		driver.get("http://live.guru99.com/index.php/");
		String ParentID = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//ul//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//ul//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		driver.findElement(By.xpath("//button[@title='Compare']//span//span[contains(text(),'Compare')]")).click();
		//title of Compare page: Products Comparison List - Magento Commerce
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		Assert.assertTrue(closeAllWithoutParentWindows(ParentID));
	}
		
	
	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}


	public void switchToChildWindowByID(String parentID) {
		// Get ID of all active tabs
    Set<String> allWindows = driver.getWindowHandles();
    for (String runWindow : allWindows) {
                if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
    break;
                }
    }
	}
	public void switchToWindowByTitle(String ExpectedTitle) {
    Set<String> allWindows = driver.getWindowHandles();
    for (String runWindows : allWindows) {
                driver.switchTo().window(runWindows);
                String currentWin = driver.getTitle();
                if (currentWin.equals(ExpectedTitle)) {
    break;
                }
    }
	}
	
	public boolean closeAllWithoutParentWindows(String parentWindow) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
                    if (!runWindows.equals(parentWindow)) {
                                driver.switchTo().window(runWindows);
                                driver.close();
                    }
        }
        driver.switchTo().window(parentWindow);
        if (driver.getWindowHandles().size() == 1)
                   return true;
        else
                   return false;
	}
	}
