package com.testscenarios;

import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.CommonFunctions;
import com.utilities.StaticVariables;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class ExplicityWaitSample extends StaticVariables{
	Locators loc = new Locators();
	CommonFunctions cfn = new CommonFunctions();
	
	 @Parameters("browserName")
		@BeforeClass
		public void beforeClass(@Optional("Chrome") String browserName) {

			if (browserName.equalsIgnoreCase("Chrome")) {
				cfn.chromeBrowserLunch();
			} else if (browserName.equalsIgnoreCase("Firefox")) {
				cfn.firefoxBrowserLunch();
			} else if (browserName.equalsIgnoreCase("Edge")) {
				cfn.edgeBrowserLunch();
			} else {
				System.out.println("Please give valid browserName");
			}
	 }
  @Test
  public void f() throws Exception{

    Properties p = new Properties();
	FileInputStream fi = new FileInputStream("./src/test/resources/testdata/td.properties");
	p.load(fi);
	
	/***************** Develop the Code here ***************/
	
	cfn.openTheURL("https://demoqa.com/alerts");
	cfn.clickByAnyLocator(By.xpath("//button[@id='timerAlertButton']"));
	//Expliciy wait
	WebDriverWait ww = new WebDriverWait(driver, 50);
	ww.until(ExpectedConditions.alertIsPresent());
	cfn.alertHandleByAccept();
	//Navigate to other URL
	cfn.openTheURL("https://www.seleniumeasy.com/test/generate-file-to-download-demo.html");
	//Type something on Edit box
	cfn.sendkeyByAnyLocator(By.id("textbox"), "RAMANA");
	ww.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("create"))));
	System.out.println("******************************");
	
  }
  @AfterMethod
  public void afterMethod(ITestResult res) throws Exception {
	  
		  cfn.screenshotswithstatus(res);
  }

 

  @AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }

}
