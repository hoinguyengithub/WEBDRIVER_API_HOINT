package Slack_Github_Integration;

import java.util.List;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Topic12_UploadFiles {
	WebDriver driver;
	JavascriptExecutor js;
	JavascriptExecutor javascriptExecutor;
    

	String rootFolder = System.getProperty("user.dir");
	String fileName01 ="Image01.png";
	String fileName02 ="Image02.png";
	String fileName03 ="Image03.png";
	
	
	String fileNamePath01 = rootFolder + "\\UploadFiles\\" + fileName01;
	String fileNamePath02 = rootFolder + "\\UploadFiles\\" + fileName02;
	String fileNamePath03 = rootFolder + "\\UploadFiles\\" + fileName03;
	
	String chromePath = rootFolder + "\\UploadFiles\\" + "chrome.exe";
	String firefoxPath = rootFolder + "\\UploadFiles\\" + "firefox.exe";
	String IEPath = rootFolder + "\\UploadFiles\\" + "ie.exe";
	
	String [] files = {fileNamePath01, fileNamePath02, fileNamePath03};
	String NewFolder, email, firstName; {
	NewFolder = "hoi" + randomNumber();
	email = "hoi" + randomNumber() + "@gmail.com"; 
	firstName = "HoiNT"; }

	@BeforeTest
	public void beforeTest() {
		//System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
		//driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
		//driver = new InternetExplorerDriver();
		
		js = ((JavascriptExecutor) driver);
		
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		javascriptExecutor = (JavascriptExecutor) driver;
	}
	
	
	
	public void TC_01_UploadFile_Sendkeys_Queue()  {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		for (String file : files) 	{
			WebElement UploadFileButton = driver.findElement(By.xpath("//input[@type='file']"));
			UploadFileButton.sendKeys(file);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		driver.findElement(By.xpath("//span[text()='Start upload']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName03 + "']")).isDisplayed());
		
	}
	
	
	
	public void TC_02_UploadFile_Sendkeys_MultipleAtOnce() throws Exception  {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement UploadFileButton = driver.findElement(By.xpath("//input[@type='file']"));
		UploadFileButton.sendKeys(fileNamePath01 + "\n" + fileNamePath02 + "\n" + fileNamePath03);
		Thread.sleep(3000);
		
		List <WebElement> Startbuttons = driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for (WebElement StartButton : Startbuttons ) {
			if (driver.toString().contains("chrome")|| driver.toString().contains("firefox")) {
			StartButton.click();}
			else {
				clickToElementByJS (StartButton);	
			}
		}
			
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName03 + "']")).isDisplayed());
		
	
	}
	
	
	public void TC_03_UploadFile_AutoIT() throws Exception  {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		if (driver.toString().contains("internet explorer")) {
			WebElement UploadFileButton = driver.findElement(By.xpath("//input[@type='file']"));
			clickToElementByJS(UploadFileButton);
			Thread.sleep(3000);
			Runtime.getRuntime().exec(new String[] { IEPath, fileNamePath01 }); }
		
		else if (driver.toString().contains("firefox")) {
			WebElement UploadFileButton = driver.findElement(By.cssSelector(".fileinput-button"));
			UploadFileButton.click();
			Thread.sleep(3000);
			Runtime.getRuntime().exec(new String[] { firefoxPath, fileNamePath01 });
			Thread.sleep(3000);
		}
		else {
			WebElement UploadFileButton = driver.findElement(By.cssSelector(".fileinput-button"));
			UploadFileButton.click();
			Thread.sleep(3000);
			Runtime.getRuntime().exec(new String[] {chromePath, fileNamePath01 });
			Thread.sleep(3000);
		
		driver.findElement(By.xpath("//span[text()='Start upload']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName01 + "']")).isDisplayed());
		
		}
	
	}
	
	
	public void TC_04_UploadFile_Robot() throws Exception  {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		// Specify the file location with extension
        StringSelection select = new  StringSelection(fileNamePath01);

        // Copy to clipboard
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

        if (driver.toString().contains("chrome")  || driver.toString().contains("firefox")) {
            WebElement uploadFile =  driver.findElement(By.cssSelector(".fileinput-button"));
            uploadFile.click();
            Thread.sleep(1000);
        } else {
            System.out.println("Go to IE");
            WebElement uploadFile =  driver.findElement(By.xpath("//input[@type='file']"));
            clickToElementByJS(uploadFile);
            Thread.sleep(1000);
        }

        Robot robot = new Robot();
        Thread.sleep(1000);

        // Nhan phim Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Nhan xuong Ctrl - V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        // Nha Ctrl - V
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(1000);

        // Nhan Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        
        Thread.sleep(2000);
		
        driver.findElement(By.xpath("//span[text()='Start upload']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + fileName01 + "']")).isDisplayed());
	}
	
	@Test
	public void TC_05_UploadFileToRandomFolder() throws Exception {
	
		driver.get("https://encodable.com/uploaddemo/");
		//Select file to upload
		WebElement ChooseFile = driver.findElement(By.xpath("//input[@id='uploadname1']"));
		ChooseFile.sendKeys(fileNamePath01);
		Thread.sleep(1000);
		
		//Select dropdown (Upload to: /uploaddemo/files/)
		
		WebElement parent = driver.findElement(By.xpath("//select[@name='subdir1']"));
	      parent.click();
	      List <WebElement> allItems = driver.findElements(By.xpath("//select[@name='subdir1']//option"));
	      for(WebElement Item: allItems) {
	    	  if(Item.getText().equals("/uploaddemo/files/")) {
	    		  js.executeScript("arguments[0].scrollIntoView(true);", Item);
	    		  Item.click();
	    	  }
	      }
		
		// fill data to corresponding fields
		WebElement NewSubFolder = driver.findElement(By.xpath("//input[@id='newsubdir1']"));
		NewSubFolder.sendKeys(NewFolder);
		WebElement emailTextbox= driver.findElement(By.xpath("//input[@id='formfield-email_address']"));
		emailTextbox.clear();
		emailTextbox.sendKeys(email);
		WebElement FirstName = driver.findElement(By.xpath("//input[@id='formfield-first_name']"));
		FirstName.clear();
		FirstName.sendKeys(firstName);
		Thread.sleep(2000);
		
		//Click Upload button to upload file
		
		driver.findElement(By.xpath("//input[@id='uploadbutton']")).click();
		Thread.sleep(7000);
		//Verify files has been uploaded + display name
		driver.findElement(By.xpath("//dt[text()='Your upload is complete:']")).isDisplayed();
		
		
		String innerText = (String) js.executeScript("return document.documentElement.innerText;");
	      Assert.assertTrue(innerText.contains("Email Address: "+email));
	      Assert.assertTrue(innerText.contains("First Name: "+ firstName));
	      Assert.assertTrue(innerText.contains(fileName01)); 
		
	    //click View Upload files
		driver.findElement(By.xpath("//a[text()='View Uploaded Files']")).click();
		Thread.sleep(5000);
		
		//Click to Random folder
		List<WebElement> allFolders = driver.findElements(By.xpath("//td/a"));
		for (WebElement subFolder : allFolders) {
			if (subFolder.getText().equals(NewFolder)) {
				
				javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", subFolder);
				Thread.sleep(2000);
				if (subFolder.isDisplayed()) {
					subFolder.click();
				  } else {
					  javascriptExecutor.executeScript("arguments[0].click();", subFolder);
				  }
				  Thread.sleep(1000);
				  break;
			
			}
			}
		String NameOfUploadedFile = (String) js.executeScript("return document.documentElement.innerText;");
	      Assert.assertTrue(NameOfUploadedFile.contains(fileName01)); 
	}
	
	
	
	
	 public Object clickToElementByJS(WebElement element) {
         JavascriptExecutor js = (JavascriptExecutor) driver;
         return js.executeScript("arguments[0].click();", element);
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