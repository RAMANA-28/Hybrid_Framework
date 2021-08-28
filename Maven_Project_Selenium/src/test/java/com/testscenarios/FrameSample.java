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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class FrameSample extends StaticVariables{
	Locators loc = new Locators();
	CommonFunctions cfn = new CommonFunctions();
	
	 @Parameters("browserName")
		@BeforeClass
		public void beforeClass(@Optional("Edge") String browserName) {

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
  public void frame() throws Exception{

    Properties p = new Properties();
	FileInputStream fi = new FileInputStream("./src/test/resources/testdata/td.properties");
	p.load(fi);
	
	/***************** Develop the Code here ***************/
	cfn.openTheURL(p.getProperty("FramesURL"));
	
	//To Move to iframe/frame
	driver.switchTo().defaultContent();
	JavascriptExecutor exe = (JavascriptExecutor) driver;
	int numberOfFrames = 0;
	numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
	System.out.println("Number of Frames are : " + numberOfFrames);
	//driver.switchTo().frame(1);
	//driver.switchTo().frame("MENU");
	
	//Click on web element
	cfn.clickByAnyLocator(loc.FRAME_ZONAL_LINK);
  }
  
  @AfterMethod
  public void afterMethod() throws Exception {
	  
	  cfn.takeScreenshot("FrameSample");
  }

 

  //@AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }

}
