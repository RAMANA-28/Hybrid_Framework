package com.testscenarios;

import org.testng.annotations.Test;

import com.objectrepository.Locators;
import com.utilities.CommonFunctions;
import com.utilities.StaticVariables;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class FacebookLogin extends StaticVariables{
	//CLASSNAME referencename = new CLASSNAME
	Locators loc = new Locators();
	CommonFunctions cfn = new CommonFunctions();
	
	  @BeforeClass
	  public void beforeClass() {
		cfn.chromeBrowserLunch();
		driver.manage().window().maximize(); 
	  }

  @Test
  public void fbLogin() throws Exception{

	//Read the test data from property file
	  //CLASSNAME referencename = new CLASSNAME
		Properties p = new Properties();
		FileInputStream fi = new FileInputStream("./src/test/resources/testdata/td.properties");
		p.load(fi);
	   
	// Type the website URL
	driver.get(p.getProperty("FB_URL"));
	cfn.sendkeyByAnyLocator(loc.FBLOGIN_USERNAME_EDITBOX, p.getProperty("FB_USERNAME"));
	cfn.sendkeyByAnyLocator(loc.FBLOGIN_PASSWORD_EDITBOX, p.getProperty("FB_PASSWORD"));
	cfn.clickByAnyLocator(loc.FBLOGIN_LOGIN_BUTTON);
	
  }

}
