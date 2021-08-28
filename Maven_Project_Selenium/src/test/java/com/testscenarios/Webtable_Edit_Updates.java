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
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class Webtable_Edit_Updates extends StaticVariables{
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
	
	cfn.openTheURL("https://stqatools.com/demo/WebTable.php");
	cfn.implicitWait(8000);
	cfn.clickByAnyLocator(loc.FIRSTNAME_EDIT_BOX);
	cfn.implicitWait(5000);
	driver.findElement(loc.FIRSTNAME_CLEAR).clear();
	cfn.sendkeyByAnyLocator(loc.FIRSTNAME_CLEAR, p.getProperty("FT_Web_Table_FN"));
	cfn.implicitWait(5000);
	driver.findElement(loc.FIRSTNAME_MIDDLE).clear();
	cfn.sendkeyByAnyLocator(loc.FIRSTNAME_MIDDLE, p.getProperty("FT_Web_Table_SN"));
	cfn.implicitWait(5000);
	driver.findElement(loc.FIRSTNAME_LAST).clear();
	cfn.sendkeyByAnyLocator(loc.FIRSTNAME_LAST, p.getProperty("FT_Web_Table_MN"));
	cfn.implicitWait(5000);
	cfn.clickByAnyLocator(loc.UPDATE);
  }
  @AfterMethod
  public void afterMethod(ITestResult res) throws Exception {
	  
		 // cfn.takeScreenshot("SBI_POPUP");
		  cfn.screenshotswithstatus(res);
  }

 

 // @AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }
  

}
