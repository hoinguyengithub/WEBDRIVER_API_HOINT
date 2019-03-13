package Slack_Github_Integration;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic05_Textbox_TextAreas {
	WebDriver driver;
	String CustomerID, CustomerName,Gender,DateofBirth,Address,City,State,PIN,phone,email,password;
   
	String editAddress,editCity, editState, editPin, editPhone, editEmail;
	
	//Locate elements
    By CustomerNameTextbox = By.xpath("//input[@name='name']");
    By genderRadiobox = By.xpath("//input[@value='m']");
    By dateofBirthTextbox = By.xpath("//input[@name='dob']");
    By AddressTextArea = By.xpath("//textarea[@name='addr']");
    By cityTextbox = By.xpath("//input[@name='city']");
    By StateTextbox = By.xpath("//input[@name='state']");
    By Pintextbox = By.xpath("//input[@name='pinno']");
    By Phonetextbox = By.xpath("//input[@name='telephoneno']");
    By emailtextbox = By.xpath("//input[@name='emailid']");
    By passwordtextbox = By.xpath("//input[@name='password']");
    By Submitbutton = By.xpath("//input[@name='sub']");

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr183631" );
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("UvaqadE");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		//Initiate test data for new customers
		CustomerName ="Automation Testing";
		Gender ="male";
		DateofBirth ="2000-01-01";
		Address ="20 Cach Mang Thang 8";
		City ="Danang";
		State ="Danang";
		PIN ="555000";
		phone ="0988661222";
		email ="hoi" + randomNumber() + "@gmail.com";
		password ="123456";
		
		// Initiate test data for edit account
		
		editAddress = "234 2/9";
		editCity = "Hanoi";
		editState = "Tay Ho";
		editPin = "660000";
		editPhone ="0988222333";
		editEmail = "editemail" +  randomNumber() + "@gmail.com";
	}

	@Test 
	public void TC_01_CreateCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Add New Customer']")).isDisplayed());
		driver.findElement(CustomerNameTextbox).sendKeys(CustomerName);
		driver.findElement(genderRadiobox).click();
		driver.findElement(dateofBirthTextbox).sendKeys(DateofBirth);
		driver.findElement(AddressTextArea).sendKeys(Address);
		driver.findElement(cityTextbox).sendKeys(City);
		driver.findElement(StateTextbox).sendKeys(State);
		driver.findElement(Pintextbox).sendKeys(PIN);
		driver.findElement(Phonetextbox).sendKeys(phone);
		driver.findElement(emailtextbox).sendKeys(email);
		driver.findElement(passwordtextbox).sendKeys(password);
		driver.findElement(Submitbutton).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), CustomerName);		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), Gender);		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), DateofBirth);		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), Address);		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), City);		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), State);	
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), PIN);	
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), phone);	
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);	
		
		CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID at TC01 = " + CustomerID);
		
	
	}
	
	@Test 
	public void TC_02_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(CustomerID);
		System.out.println("CustomerID at TC02 = " + CustomerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Edit Customer']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), Address);
		
		driver.findElement(AddressTextArea).clear();
		driver.findElement(AddressTextArea).sendKeys(editAddress);
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(editCity);
		driver.findElement(StateTextbox).clear();
		driver.findElement(StateTextbox).sendKeys(editState);
		driver.findElement(Pintextbox).clear();
		driver.findElement(Pintextbox).sendKeys(editPin);
		driver.findElement(Phonetextbox).clear();
		driver.findElement(Phonetextbox).sendKeys(editPhone);
		driver.findElement(emailtextbox).clear();
		driver.findElement(emailtextbox).sendKeys(editEmail);
		driver.findElement(Submitbutton).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);	
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);	
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);	
		
	}
	
	
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		System.out.println("Random Number="+number);
		return number;
	}
		
	
	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}

}