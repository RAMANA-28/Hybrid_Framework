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
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class SBI_POPUP extends StaticVariables{
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
	cfn.openTheURL("https://retail.onlinesbi.com/retail/login.htm");
	cfn.clickByAnyLocator(By.linkText("CONTINUE TO LOGIN"));
	cfn.implicitWait(3000);
	cfn.clickByAnyLocator(By.partialLinkText("New User"));
	//Click on Alert
	cfn.alertHandleByAccept();
	//Switch to popup page and Do click on Next Button
	cfn.navigateToPopupWindow();
	//click on Next Button on popup window (Child)
	cfn.clickByAnyLocator(By.name("nextStep"));
	
  }
  @AfterMethod
  public void afterMethod(ITestResult res) throws Exception {
	  
	 // cfn.takeScreenshot("SBI_POPUP");
	  cfn.screenshotswithstatus(res);
  }

  //@AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }

}
