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
import org.testng.annotations.AfterClass;

public class DropdownSamples extends StaticVariables{
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
	
	cfn.openTheURL("https://tirupatibalaji.ap.gov.in/#/registration");
	cfn.implicitWait(10);
	//cfn.selectByVisibleText(By.name("countryS"), "India");
	//cfn.printAllDropdownValues(By.name("countryS"));
	cfn.selectCustomiseOptionFromTheDropdownValues(By.name("countryS"), "India");
	//Select the proof dropdown vlaue based on index
	//cfn.selectByIndex(By.name("proofS"), 4);
	
	//conver String in to Integer
	int rannum = cfn.randomNumber(7);
	System.out.println(rannum);
	cfn.implicitWait(10);
	cfn.selectByIndex(By.name("proofS"), rannum);
	
  }
  @AfterMethod
  public void afterMethod() throws Exception {
	  
	  cfn.takeScreenshot("DropdownSamples");
  }

 

  //@AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }

}
