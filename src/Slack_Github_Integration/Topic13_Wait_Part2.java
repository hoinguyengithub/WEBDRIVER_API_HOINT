package Slack_Github_Integration;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebElement;
import com.google.common.base.Function;




public class Topic13_Wait_Part2 {
	WebDriver driver;
    WebDriverWait waitExplicit;
    
   
	@BeforeTest
	public void beforeTest() {
		//System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
		//driver = new FirefoxDriver();
				
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		waitExplicit = new WebDriverWait(driver, 30);
				
		//System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
		
		
	}

	@Test
	public void TC_01_01_AjaxLoading_WithoutExplicitWait() {
				
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='contentWrapper']")).isDisplayed());
		
		String BeforeSelectingText = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		
		System.out.println("DateTime not selected = " + BeforeSelectingText);
		
		driver.findElement(By.xpath("//a[text()='17']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div//span[text()='Wednesday, April 17, 2019']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='17']/parent::td[@class='rcSelected']")).isDisplayed());

		String AfterSelectingText = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim();
		System.out.println("DateTime is selected = " + AfterSelectingText);
		
		Assert.assertEquals(AfterSelectingText, "Wednesday, April 17, 2019");
	}
	@Test
	public void TC_01_02_AjaxLoading_WithExplicitWait() {
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='contentWrapper']")));
		
		String BeforeSelectingText = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText();
		
		System.out.println("DateTime not selected = " + BeforeSelectingText);
		
		driver.findElement(By.xpath("//a[text()='17']")).click();
		
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		// waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='raDiv']")));
		
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='17']/parent::td[@class='rcSelected']")));

		String AfterSelectingText = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText().trim();
		System.out.println("DateTime is selected = " + AfterSelectingText);
	}

	@Test
	public void TC_02_FluentWait() {
		driver.get("https://daominhdam.github.io/fluent-wait/");

		WebElement countdown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		waitExplicit.until(ExpectedConditions.visibilityOf(countdown));
	
	
	// Khởi tạo Fluent wait
	new FluentWait<WebElement>(countdown)
	           // Tổng time wait là 15s
	           .withTimeout(15, TimeUnit.SECONDS)
	            // Tần số mỗi 1s check 1 lần
	            .pollingEvery(500, TimeUnit.MILLISECONDS)
	           // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
	            .ignoring(NoSuchElementException.class)
	            // Kiểm tra điều kiện
	            .until(new Function<WebElement, Boolean>() {
	                public Boolean apply(WebElement element) {
	                           // Kiểm tra điều kiện countdount = 00
	                           boolean flag =  element.getText().endsWith("00");
	                           System.out.println("Time = " +  element.getText());
	                           // return giá trị cho function apply
	                           return flag;
	                }
	            });
	}
	            
	        

	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}
		
}

