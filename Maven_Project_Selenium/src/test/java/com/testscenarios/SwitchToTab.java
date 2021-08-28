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
import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;

public class SwitchToTab extends StaticVariables{
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
	
	cfn.openTheURL("https://www.toyota.co.uk/");
	cfn.implicitWait(10);
	cfn.clickByAnyLocator(By.id("tgbgdpr-overlay-agree"));
	cfn.implicitWait(10);
	
	cfn.loopAllFramesForElement(By.xpath("//body/div[@id='pagecontent']/div[@id='ctaBar']/a[4]/span[1]"));
	cfn.clickByAnyLocator(By.xpath("//body/div[@id='pagecontent']/div[@id='ctaBar']/a[4]/span[1]"));
    cfn.implicitWait(10);

	ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
    System.out.println("allTabs.size() is :" +allTabs.size());
	
    Thread.sleep(5000);
    //Switch to New TAB
	driver.switchTo().window(allTabs.get(1));
	//Do Actions on child Window
	Select abc = new Select(driver.findElement(By.id("codeweaversModels")));
	abc.selectByVisibleText("Aygo");
	//To close the child Tab
	driver.close();
	//Switch to MainTab
	driver.switchTo().window(allTabs.get(0));
	cfn.clickByAnyLocator(By.xpath("//a[contains(text(),'Used cars')]"));

//    /**SwithchToWindow using Tab then close the ChildTab againg comeback to ParentWindow(Main TAB)**/
//    cfn.switchAndCloseNewTab(1);
//    cfn.implicitWait(10);
//    //Click on UsedCars link Parent Page
//    cfn.clickByAnyLocator(By.xpath("//a[contains(text(),'Used cars')]"));
  }
  @AfterMethod
  public void afterMethod() throws Exception {
	  
	  cfn.takeScreenshot("SwitchToTab");
  }

 

  //@AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }

}
