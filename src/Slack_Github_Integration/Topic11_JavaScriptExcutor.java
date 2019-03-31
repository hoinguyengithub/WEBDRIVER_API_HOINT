package Slack_Github_Integration;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.junit.Assert;

public class Topic11_JavaScriptExcutor {
	WebDriver driver;
	
	
    

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		//driver = new ChromeDriver();
			
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
			}

	
	public void TC_01_()  {
		driver.get("http://live.guru99.com/");
		String domain =  (String) executeForBrowser ("return document.domain");
		Assert.assertEquals(domain, "live.guru99.com");
		String URL = (String) executeForBrowser ("return document.URL");
		Assert.assertEquals(URL, "http://live.guru99.com/");
		
		WebElement MobileLink = driver.findElement(By.xpath("//a[text()='Mobile']"));
		highlightElement (MobileLink);
		clickToElementByJS(MobileLink);
		
		WebElement SamsungAddtoCart = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button[@title='Add to Cart']"));
		highlightElement (SamsungAddtoCart);
		clickToElementByJS (SamsungAddtoCart);
		
		String innerText = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
		
		//xpath of Add to Cart: //a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button[@title='Add to Cart']
		
		WebElement PPlink = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		highlightElement (PPlink);
		clickToElementByJS(PPlink);
		
		//url of PP link: http://live.guru99.com/index.php/privacy-policy-cookie-restriction-mode/
		
		String TitleOfPrivacyPolicy = (String) executeForBrowser("return document.title");
		Assert.assertEquals(TitleOfPrivacyPolicy,"Privacy Policy");
		
		scrollToBottomPage();
		
		WebElement Wishlist = driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()='The number of items in your Wishlist.']"));
		Assert.assertTrue(Wishlist.isDisplayed());
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		
		String DomainGuru = (String) executeForBrowser ("return document.domain");
		Assert.assertEquals(DomainGuru, "demo.guru99.com");
		
	}

	//Run on Chrome
	public void TC_02_() {
		driver.get("http://demo.guru99.com/v4");
		String CustomerName,Gender,DateofBirth,Address,City,State,PIN,phone,email,password;
		By CustomerNameTextbox = By.xpath("//input[@name='name']");
	    By genderRadiobox = By.xpath("//input[@value='m']");
	    By dateofBirthTextboxBy = By.xpath("//input[@name='dob']");
	    By AddressTextArea = By.xpath("//textarea[@name='addr']");
	    By cityTextbox = By.xpath("//input[@name='city']");
	    By StateTextbox = By.xpath("//input[@name='state']");
	    By Pintextbox = By.xpath("//input[@name='pinno']");
	    By Phonetextbox = By.xpath("//input[@name='telephoneno']");
	    By emailtextbox = By.xpath("//input[@name='emailid']");
	    By passwordtextbox = By.xpath("//input[@name='password']");
	    By Submitbutton = By.xpath("//input[@name='sub']");
	    driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr181358" );
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("berydUp");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
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
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Add New Customer']")).isDisplayed());
		
		driver.findElement(CustomerNameTextbox).sendKeys(CustomerName);
		driver.findElement(genderRadiobox).click();
		WebElement dateofBirthTextbox = driver.findElement(dateofBirthTextboxBy);
		removeAttributeInDOM (dateofBirthTextbox, "type");
		driver.findElement(dateofBirthTextboxBy).sendKeys(DateofBirth);
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
		
		
		
	}
	
	@Test
	public void TC_03_() throws Exception {
		driver.get("http://live.guru99.com/");
		WebElement MyAccountLink = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
		clickToElementByJS(MyAccountLink);
		
		WebElement CreateAnAccount = driver.findElement(By.xpath("//span[text()='Create an Account']"));
		clickToElementByJS(CreateAnAccount);
		String firstName, MiddleName, LastName, EmailText, Password;
		firstName = "HN";
		MiddleName ="Test";
		LastName = "Auto";
		EmailText = "hoi" + randomNumber() + "@gmail.com";
		Password = "123456";
		WebElement FirstnameTextbox = driver.findElement(By.xpath("//input[@id='firstname']"));
		WebElement MiddleNameTextBox = driver.findElement(By.xpath("//input[@id='middlename']"));
		WebElement LastNameTextbox = driver.findElement(By.xpath("//input[@id='lastname']"));
		WebElement EmailTextBox = driver.findElement(By.xpath("//input[@id='email_address']"));
		WebElement PassTextbox = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement ConfirmPassWord = driver.findElement(By.xpath("//input[@id='confirmation']"));
		
		sendkeyToElementByJS(FirstnameTextbox, firstName);
		sendkeyToElementByJS(MiddleNameTextBox, MiddleName);
		sendkeyToElementByJS(LastNameTextbox, LastName);
		sendkeyToElementByJS(EmailTextBox, EmailText);
		sendkeyToElementByJS(PassTextbox, Password);
		sendkeyToElementByJS(ConfirmPassWord, Password);
		
		WebElement Registerbutton = driver.findElement(By.xpath("//button[@title='Register']"));
		clickToElementByJS(Registerbutton);
			
		String SuccessMsg = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(SuccessMsg.contains("Thank you for registering with Main Website Store."));
		
		//click Logout
		WebElement AccountLink = driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']"));
		clickToElementByJS(AccountLink);
		WebElement LogoutButton = driver.findElement(By.xpath("//a[@title='Log Out']"));
		clickToElementByJS(LogoutButton);
		//Thread.sleep(7000);	
		navigateToUrlByJS("http://live.guru99.com/index.php/");
		
				
	// xpath of successMsg: //span[text()='Thank you for registering with Main Website Store.']
	}
	
	
	public int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		System.out.println("Random Number="+number);
		return number;
	}
	
	public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='6px groove red'", element);
    }

    public Object executeForBrowser(String javaSript) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript(javaSript);
    }

    public Object clickToElementByJS(WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].click();", element);
    }

    public Object sendkeyToElementByJS(WebElement element, String value) {
           JavascriptExecutor js = (JavascriptExecutor) driver;
           return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public Object removeAttributeInDOM(WebElement element, String attribute) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
    }

    public Object scrollToBottomPage() {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public Object navigateToUrlByJS(String url) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.location = '" + url + "'");
    }
		
	
	@AfterTest
	public void afterTest() {
		String path = System.getenv("PATH");
		System.out.println(path); // Should contain C:\Windows\system32
		driver.quit();
	}

}