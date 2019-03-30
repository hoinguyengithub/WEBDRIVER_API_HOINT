package Slack_Github_Integration;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;

import org.junit.Assert;

public class Topic08_UserInteraction {
	WebDriver driver;
	Actions action;
    

	@BeforeTest
	public void beforeTest() {
		//System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		//driver = new ChromeDriver();
		
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
			}

	@Test
	public void TC_01_MouseHoverOverElement()  {
		driver.get("http://www.myntra.com/");
		WebElement profileIcon = driver.findElement(By.xpath("//span[text()='Profile']"));
		action.moveToElement(profileIcon).perform();
		WebElement LoginButton = driver.findElement(By.xpath("//a[text()='log in']"));
		//click Login button by Webelement
		//LoginButton.click();
		//Click by userinteraction
		action.click(LoginButton).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='login-title']")).isDisplayed());
		
	}
	@Test
	public void TC_02_ClickAndHold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List <WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.clickAndHold(numberItems.get(0)).moveToElement(numberItems.get(3)).release().perform();
		
		List <WebElement> numberItemSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(numberItemSelected.size(), 4);
	}
	
	@Test
	public void TC_03_ClickAndHoldRandomly() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List <WebElement> numberItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		action.keyDown(Keys.CONTROL).perform();
		action.click(numberItems.get(0));
		action.click(numberItems.get(2));
		action.click(numberItems.get(4));
		action.click(numberItems.get(6));
		action.click(numberItems.get(8));
		action.keyUp(Keys.CONTROL).perform();
		

		List <WebElement> numberItemSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(numberItemSelected.size(), 5);
		
		
		
}
	@Test
	public void TC_04_DoubleClick() {
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		action.doubleClick(doubleClickButton).perform();
		Alert acceptAlert = driver.switchTo().alert();
		Assert.assertEquals(acceptAlert.getText(), "The Button was double-clicked.");
		acceptAlert.accept();
		
	}
		

	//run on chrome
	public void TC_05_RightClick() throws Exception {
				
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement RightClick = driver.findElement(By.xpath("//span[text()='right click me']"));
		action.contextClick(RightClick).perform();
		WebElement QuitButton = driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']//span[contains(text(),'Quit')]"));
		action.click(QuitButton).perform();
		Alert acceptAlert = driver.switchTo().alert();
		Assert.assertEquals(acceptAlert.getText(), "clicked: quit");
		acceptAlert.accept();
		
		Thread.sleep(3000);
		Assert.assertFalse(QuitButton.isDisplayed());
		
		
	}
	
	@Test
	public void TC_06_DragAndDrop() {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(smallCircle, bigCircle).perform();;
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='droptarget' and text()='You did great!']")).isDisplayed());
		
		
		}
	
	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}

}