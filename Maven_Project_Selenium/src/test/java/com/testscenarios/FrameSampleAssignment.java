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

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class FrameSampleAssignment extends StaticVariables{
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
	cfn.openTheURL("http://mis.nyiso.com/public/");
	cfn.loopAllFramesForElement(loc.FRAME_ZONAL_LINK);
	cfn.clickByAnyLocator(loc.FRAME_ZONAL_LINK);
	//Get the data
	cfn.loopAllFramesForElement(loc.FRAME_LASTUPDAE_TEXT);
	System.out.println(driver.findElement(loc.FRAME_LASTUPDAE_TEXT).getText());
	
  }
  @AfterMethod
  public void afterMethod() throws Exception {
	  
	  cfn.takeScreenshot("FrameSampleAssignment");
  }

 

  @AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }

}
