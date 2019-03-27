package Slack_Github_Integration;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic06_DropdownList {
    WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascriptExecutor;
	
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  waitExplicit = new WebDriverWait(driver, 30);
	  javascriptExecutor = (JavascriptExecutor) driver;
	  	  
	  }
  
  @Test
  public void TC_01_HTML_Dropdown() throws Exception {
	  driver.get("https://daominhdam.github.io/basic-form/index.html");

	  WebElement jobRole1 = driver.findElement(By.xpath("//select[@id='job1']"));
	  Select jobRoleSelect = new Select(jobRole1);
	  
	  Assert.assertFalse(jobRoleSelect.isMultiple());
	  
	  jobRoleSelect.selectByVisibleText("Automation Tester");
	  Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Automation Tester");
	  
	  jobRoleSelect.selectByValue("manual");
	  Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Manual Tester");
	  
	  jobRoleSelect.selectByIndex(3);
	  Assert.assertEquals(jobRoleSelect.getFirstSelectedOption().getText(), "Mobile Tester");
	  
	  Assert.assertEquals(jobRoleSelect.getOptions().size(), 5);
  }

  @Test
  public void TC_02_jquerry_Dropdown() throws Exception {
	  driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
	  
	  selectItemIncustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "19");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']"));
	  
	  selectItemIncustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "1");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='1']"));
	  
	  selectItemIncustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "13");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='13']"));
	  
	  selectItemIncustomDropdown("//span[@id='number-button']","//ul[@id='number-menu']//li[@class='ui-menu-item']/div", "2");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='2']"));
	  
  }  
  @Test
  public void TC_03_Angular_Custom_Dropdown () throws Exception {
	  driver.get("https://material.angular.io/components/select/examples");
	  
	  selectItemIncustomDropdown ("//mat-label[contains(text(),'State')]", "//mat-option/span", "Hawaii");
	  Assert.assertTrue(isElementDisplayed("//span[@class='ng-tns-c21-18 ng-star-inserted']"));
	  
	  selectItemIncustomDropdown ("//mat-label[contains(text(),'State')]", "//mat-option/span", "Alaska");
	  Assert.assertTrue(isElementDisplayed("//span[@class='ng-tns-c21-18 ng-star-inserted']"));
	  
	  selectItemIncustomDropdown ("//mat-label[contains(text(),'State')]", "//mat-option/span", "New York");
	  Assert.assertTrue(isElementDisplayed("//span[@class='ng-tns-c21-18 ng-star-inserted']"));
	  
  }
  
  @Test
  public void TC_04_Telerik_Custom_Dropdown () throws Exception {
	  driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
	    
	  selectItemIncustomDropdown("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']/child::li", "Orange");
	  Assert.assertTrue(isElementDisplayed("//input[@id='color']/preceding-sibling::span/span[text()='Orange']"));	  
	  
	  selectItemIncustomDropdown("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']/child::li", "Black");
	  Assert.assertTrue(isElementDisplayed("//span[@aria-owns='color_listbox']//span[text()='Black']"));	 
	  
	  selectItemIncustomDropdown("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']/child::li", "Grey");
	  Assert.assertTrue(isElementDisplayed("//input[@id='color']/preceding-sibling::span/span[text()='Grey']"));	

	  
  }
  
  @Test 
  public void TC_05_Vue_Custom_Dropdown () throws Exception {
	  driver.get("https://mikerodham.github.io/vue-dropdowns/");
	  
	  
	  selectItemIncustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Second Option");
	  Assert.assertTrue(isElementDisplayed("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]"));	  
	  
	  selectItemIncustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "First Option");
	  Assert.assertTrue(isElementDisplayed("//li[@class='dropdown-toggle' and contains(text(),'First Option')]"));	 
	  
	  selectItemIncustomDropdown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']/li/a", "Third Option");
	  Assert.assertTrue(isElementDisplayed("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]"));	  	

	  
  }
  
	  
  
  @Test 
  public void TC_06_Custom_Dropdown_Indrimuska_Editable() throws Exception {
	  driver.get("https://indrimuska.github.io/jquery-editable-select/");
	  selectItemIncustomDropdown ("//div[@id='default-place']/input", "//div[@id='default-place']//li", "Land Rover");
	  Thread.sleep(5000);
	  Assert.assertTrue(isElementDisplayed("//div[@id='default-place']//li[@class='es-visible' and contains(text(),'Land Rover')]/parent::ul/preceding-sibling::input"));	
	  
  }
	  
  //duyet qua het tat ca cac phan tu den khi thoa man dieu kien 
	/*  for(int i=0; i<allItems.size(); i++)
	  {
		  // allItem.get(0) = WebElement = click/ sendkeys/ getText/....
		  if(allItems.get(i).getText().equals(expectedValueItem))
		  {
			  System.out.println("Gettex moi lan chon" + allItems.get(i).getText());
			  //3 Scroll den item can chon (Neu nhin thay item can chon thi khong croll nua)
			  javacriptExecutor.executeScript("aguments[0].srollIntoView(true);", allItems.get(i));
			  Thread.sleep(2000);
			  //click vao itea can chon
			  allItems.get(i).click();
			  break;
		  }
	  }*/
	  
	 

	public void selectItemIncustomDropdown(String parentXpath, String allItemXpath, String expectedValueItem) throws InterruptedException {
		WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
		javascriptExecutor.executeScript("arguments[0].click()", parentDropdown);
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		
		for (WebElement childElement : allItems){
			if (childElement.getText().equals(expectedValueItem)) {
				
				javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
				Thread.sleep(2000);
				if (childElement.isDisplayed()) {
					  childElement.click();
				  } else {
					  javascriptExecutor.executeScript("arguments[0].click();", childElement);
				  }
				  Thread.sleep(1000);
				  break;
			}
		}
	
	  }
	public boolean isElementDisplayed(String xpathValue) {
		WebElement element = driver.findElement(By.xpath(xpathValue));
		if (element.isDisplayed()){
			return true;
		}else {
			return false;
		}
	}
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}