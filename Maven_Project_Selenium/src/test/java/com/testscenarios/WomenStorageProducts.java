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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class WomenStorageProducts extends StaticVariables{
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
	
	cfn.openTheURL("http://automationpractice.com/index.php?id_category=3&controller=category");
	cfn.implicitWait(10);
    int rannum = cfn.randomNumber(7);
    System.out.println("Random Number is : " + rannum);
    Actions act = new Actions(driver);
    WebElement ele = driver.findElement(By.xpath("//*[@id='center_column']/ul/li["+rannum+"]/div/div[1]/div/a[1]/img"));
	act.moveToElement(ele).build().perform();
	//Wait for 
	cfn.implicitWait(10);
	cfn.clickByAnyLocator(By.xpath("//*[@id='center_column']/ul/li["+rannum+"]/div/div[2]/div[2]/a[1]/span"));
  }
  @AfterMethod
  public void afterMethod() throws Exception {
	  
	  cfn.takeScreenshot("WomenStorageProducts");
  }

 

  //@AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }

}
