package com.qa.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.pages.AddEmpPages;
import com.qa.pages.LoginPages;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	// Invoke the browser // equipped for cross browser testing
			// launch the URL //parameterized for the url from xml file
			// Initialize pages
			// Maximize the window
			// delete cookies
			// pageLoadTime
			//Implicit wait
			// Initialize and instantiate the property files etc.
	WebDriver driver;
	LoginPages LoginOR;
	AddEmpPages AddEmpPagesOR;
	SoftAssert sAssert;
	FileInputStream fileLoc;
	Properties prop;
	JavascriptExecutor Js;

	@Parameters({"Browser","Url"})
	@BeforeClass
	public void setUp(String Browser,String Url) throws IOException {
		fileLoc = new FileInputStream("D:\\Selenium Training\\HyDigit - Batch 2 - Fy'23\\IBM.EMSDEMO.TestAutomation\\src\\test\\java\\com\\qa\\testdata\\credentials.properties");
		prop = new Properties();
		prop.load(fileLoc);
		
		if(Browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(Browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if(Browser.equalsIgnoreCase("Ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}else if(Browser.equalsIgnoreCase("Firefo")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		sAssert = new SoftAssert();
		Js = (JavascriptExecutor)driver;
		LoginOR = new LoginPages(driver);
		AddEmpPagesOR =new AddEmpPages(driver);
		driver.get(Url);
		driver.manage().window().maximize();
		
		
	}

	@AfterClass
	public void tearDown() throws IOException {
		driver.quit();
		fileLoc.close();
	}


	public void captureScreenshot(WebDriver driver, String tName) throws IOException {

		TakesScreenshot ts = (TakesScreenshot)driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File Target = new File(System.getProperty("user.dir")+"/Screenshots/"+tName+".png");
		FileUtils.copyFile(Source, Target);
	}

}
