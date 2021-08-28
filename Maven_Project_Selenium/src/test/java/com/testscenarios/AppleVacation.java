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
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

public class AppleVacation extends StaticVariables{
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
	cfn.openTheURL("https://www.applevacations.com/");
	cfn.implicitWait(5000);
	driver.findElement(By.id("orgAC_value")).sendKeys("lo");
	cfn.implicitWait(5000);
	driver.findElement(By.xpath("//*[@id=\"orgAC_dropdown\"]/div[4]/div")).click();
	driver.findElement(By.id("desAC_value")).sendKeys("lo");
	cfn.implicitWait(5000);
	driver.findElement(By.xpath("//*[@id=\"desAC_dropdown\"]/div[4]/div")).click();
	driver.findElement(By.id("depDT_value")).clear();
	driver.findElement(By.id("depDT_value")).sendKeys("08/29/2021");
	driver.findElement(By.id("retDT_value")).clear();
	driver.findElement(By.id("retDT_value")).sendKeys("09/29/2021");
	driver.findElement(By.id("scc_rt_submit")).click();
	driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
	cfn.randomNumber(20);
	//Click on hotel by using Random number
	JavascriptExecutor js = (JavascriptExecutor) driver;
	WebElement Hotel = driver.findElement(By.xpath("/html[1]/body[1]/form[1]/div[9]/div[4]/div[3]/div[4]/ul[1]/li["+ 20 +"]/div[2]/div[1]/h3[1]/span[1]/a[1]"));
	js.executeScript("arguments[0].click()", Hotel);
	cfn.implicitWait(5000);
	 //Switch to New TAB
	cfn.switchToNewTab(1);
	cfn.implicitWait(5000);
	driver.findElement(By.xpath("//button[contains(text(),'Select Room')]")).click();
	cfn.implicitWait(5000);
	cfn.randomNumber(10);
}


  @AfterMethod
  public void afterMethod(ITestResult res) throws Exception {
	  
	  //cfn.takeScreenshot("AppleVacation");
	  cfn.screenshotswithstatus(res);
  }



 // @AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }

}
