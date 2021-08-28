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

public class DragAndDrop extends StaticVariables{
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

	cfn.openTheURL("https://jqueryui.com/droppable/");
	cfn.loopAllFramesForElement(loc.FRAME_JQEARY_DRAG_LINK);

	Actions abc = new Actions(driver);
	WebElement dragElement = driver.findElement(loc.FRAME_JQEARY_DRAG_LINK);
	WebElement dropElement = driver.findElement(loc.FRAME_JQEARY_DROP_LINK);
	abc.dragAndDrop(dragElement, dropElement).build().perform();

  }
  @AfterMethod
  public void afterMethod() throws Exception {
	  
	  cfn.takeScreenshot("DragAndDrop");
  }

 

 // @AfterClass
  public void afterClass() {
	  
	  driver.quit();
  }

}
