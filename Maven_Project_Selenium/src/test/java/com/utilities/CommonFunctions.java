package com.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonFunctions extends StaticVariables{
	//Constructor
	public CommonFunctions() {
	//Get the project Location 
		projectDir = System.getProperty("user.dir");
		System.out.println(projectDir);
	//Check whether the folder is there in project location?
	File screenshotPath = new File("./screenshots");
	if (screenshotPath.exists()) {
		System.out.println("screenshot floder is already available in the project location");
		
	} else {
		System.out.println("screenshot floder is NOT available in the project location");
		//Create folder using MKDIR
		screenshotPath.mkdir();
		System.out.println("*************screenshot folder Created*************");
	}
		
	
}
	
  /******************* Browser Driver Methods ***********/
	public void chromeBrowserLunch() {
		 WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
	} 
    public void firefoxBrowserLunch() {
    	WebDriverManager.firefoxdriver().setup();
		  driver = new FirefoxDriver();
    }
    	public void edgeBrowserLunch() {	
  		  WebDriverManager.edgedriver().setup();
  		  driver = new EdgeDriver();
    	}
   /******************* URL Open ************************/
    	public void openTheURL(String URL) {
    		driver.navigate().to(URL);
    		driver.manage().window().maximize();
    		implicitWait(20);
    	}	
    /******************* Implicit Wait ******************/
    	public void implicitWait(int seconds) {
    	  driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    	}
    /************** Sendkeys using any locators *********/
    	public void sendkeyByAnyLocator(By locator,String inputdata) {
    		WebElement ele = driver.findElement(locator);
    		if (ele.isDisplayed() && ele.isEnabled()) {
				ele.clear();
				ele.sendKeys(inputdata);
			} else {
               System.out.println("Webelement is not dispalyed on DOM or disable state");
			}
    	}
    /************** click using any locators *************/
    	public void clickByAnyLocator(By locator) {
    		WebElement ele = driver.findElement(locator);
    		if (ele.isDisplayed() && ele.isEnabled()) {
				ele.click();
			} else {
               System.out.println("Webelement is not dispalyed on DOM or disable state");
			}
    	}
    	
    /********************* Time Stamp ********************/
    	public String timestamp() {
    		Date d = new Date();
    		DateFormat df = new SimpleDateFormat("ddMMMyyy_HHmmss");
    		String timeTamp = df.format(d);
    		return timeTamp;
	
    	}
    	
    	/*****
    	 * takescreenshot
    	 * 
    	 * @throws Exception
    	 ************/
    	public void takeScreenshot(String filename) throws Exception {
    		File fi = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    		FileHandler.copy(fi, new File("./Screenshots/fb"+ filename + timestamp() +".png"));
    	}
    	
    	/***** take screenshots with status 
    	 * @throws IOException ******************/
    	
    	public void screenshotswithstatus(ITestResult res) throws IOException {
    		//Get the current running Class name
    		className = res.getTestClass().getName().trim();
    		//Get the running class testcase name(Method Name)
    		methodName = res.getName().trim();
    		//Check current Testcase is pass or fail
    		
    		if (res.getStatus() == ITestResult.SUCCESS) {
    			File fi = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        		FileHandler.copy(fi, new File("./screenshots/" + "PASS_" + className + "_" + methodName + "_" + timestamp() +".JPEG"));
			} else {
				File fi = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        		FileHandler.copy(fi, new File("./screenshots/" + "FAIL_" + className + "_" + methodName + "_" + timestamp() +".JPEG"));
			}
    		
    	}
    	
    	/******************** Frames Handling *******************/

    	public int iframeCount() {
    		driver.switchTo().defaultContent();
    		JavascriptExecutor exe = (JavascriptExecutor) driver;
    		int numberOfFrames = 0;
    		numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
    		System.out.println("Number of iframes on the page are: " + numberOfFrames);
    		return numberOfFrames;
    	}

    	public void switchToFrameByInt(int i) {
    		driver.switchTo().defaultContent();
    		driver.switchTo().frame(i);
    	}

    	public int loopAllFramesForElement(By locator) {

    		int elementpresenceCount = 0;
    		int loop = 0;
    		int maxFramaecount = iframeCount();// 6
    		// if given locater has present on webpage, then the element size would be '1'
    		// else '0'
    		elementpresenceCount = driver.findElements(locator).size();// 0
    		while (elementpresenceCount == 0 && loop < maxFramaecount) {
    			try {
    				switchToFrameByInt(loop);
    				elementpresenceCount = driver.findElements(locator).size();// 1
    				System.out.println("Try Loop frames and the Results of webelement : " + loop + ": "+ elementpresenceCount);
    				if (elementpresenceCount > 0 || loop > maxFramaecount) {
    					break;
    				}
    			} catch (Exception ex) {
    				System.out.println("Catch LoopAllframesAndReturnWebEL Old: " + loop + "; " + ex);
    			}
    			loop++;//1
    		}
    		return elementpresenceCount;
    	}	

    	/************** Alert Handle *************************/
    	public void alertHandleByAccept() {
    		Alert alert = driver.switchTo().alert();
    		String alertText = alert.getText();
    		System.out.println("Alert text is: " + alertText);
    		alert.accept();	
    	}

    	public void alertHandleByDismiss() {
    		Alert alert = driver.switchTo().alert();
    		String alertText = alert.getText();
    		System.out.println("Alert text is: " + alertText);
    		alert.dismiss();
    	}
	
    	/************************* Actions ************/

    	public void moveToOnElement(By locator) {
    		try {
    			WebElement element = driver.findElement(locator);
    			Actions actions = new Actions(driver);
    			actions.moveToElement(element).build().perform();
    		} catch (Exception e) {
    			System.out.println("Error in description: " + e.getStackTrace());
    		}
    	}

    	public void mouseHoverClickandHold(By locator) {
    		try {
    			WebElement element = driver.findElement(locator);
    			Actions actions = new Actions(driver);
    			actions.clickAndHold(element).build().perform();
    		} catch (Exception e) {
    			System.out.println("Error in description: " + e.getStackTrace());
    		}

    	}

    	public void mouseHoverContextClick(By locator) {
    		try {
    			WebElement element = driver.findElement(locator);
    			Actions actions = new Actions(driver);
    			actions.contextClick(element).build().perform();

    		} catch (Exception e) {
    			System.out.println("Error in description: " + e.getStackTrace());
    		}

    	}

    	public void doubleClick(By locator) {
    		try {
    			WebElement element = driver.findElement(locator);
    			Actions actions = new Actions(driver);
    			actions.doubleClick(element).build().perform();

    		} catch (Exception e) {
    			System.out.println("Error in description: " + e.getStackTrace());
    		}

    	}

    	public void dragandDrop(By sourceelementLocator, By destinationelementLocator) {
    		try {
    			WebElement sourceElement = driver.findElement(sourceelementLocator);
    			WebElement destinationElement = driver.findElement(destinationelementLocator);

    			if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
    				Actions action = new Actions(driver);
    				action.dragAndDrop(sourceElement, destinationElement).build().perform();
    			} else {
    				System.out.println("Element(s) was not displayed to drag / drop");
    			}
    		} catch (StaleElementReferenceException e) {
    			System.out.println("Element with " + sourceelementLocator + "or" + destinationelementLocator
    					+ "is not attached to the page document " + e.getStackTrace());
    		} catch (NoSuchElementException e) {
    			System.out.println("Element " + sourceelementLocator + "or" + destinationelementLocator
    					+ " was not found in DOM " + e.getStackTrace());
    		} catch (Exception e) {
    			System.out.println("Error occurred while performing drag and drop operation " + e.getStackTrace());
    		}
    	}
    	
    	/************
    	 * popupHandle
    	 * 
    	 * @throws InterruptedException
    	 *********************************/
    	public void popupHandleToCloseTheChildWindow() throws InterruptedException {
    		// get the main windown name
    		String mainWindowName = driver.getWindowHandle();
    		System.out.println("mainWindowName:" + mainWindowName);

    		Set<String> allWindowNames = driver.getWindowHandles();
    		System.out.println("allWindowNames:" + allWindowNames);

    		// Close the child window (popups)
    		for (String abc : allWindowNames) {//2
    			// validate the window name is parent window /Child window?
    			if (!mainWindowName.equals(abc)) {
    				// switch to child window
    				driver.switchTo().window(abc);
    				Thread.sleep(3000);
    				// Close my child window
    				driver.close();
    			}
    		}
    		// move cursor point to back to mainwindow
    		driver.switchTo().window(mainWindowName);
    	}

    	public void navigateToPopupWindow() throws InterruptedException {
    		// get the main windown name
    		String mainWindowName = driver.getWindowHandle();
    		System.out.println("mainWindowName:" + mainWindowName);

    		Set<String> allWindowNames = driver.getWindowHandles();// 4
    		System.out.println("allWindowNames:" + allWindowNames);
    		
    		for (String string : allWindowNames) {
    			// validate the window name is parent window /Child window?
    			if (!mainWindowName.equals(string)) {
    				// switch to child window
    				driver.switchTo().window(string);
    				Thread.sleep(3000);
    			}
    		}

    	}
    	
    	/*********** SwithchToWindow using Tab ***************************/
    	public void switchToNewTab(int tabIndex) {
    		// Get the current window handle
    		String windowHandle = driver.getWindowHandle();//abc

    		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
    		driver.switchTo().window(allTabs.get(tabIndex));

    		// Switch back to original window
    		// driver.switchTo().window(windowHandle);
    	}

    	/***********
    	 * SwithchToWindow using Tab then close the ChildTab againg comeback to ParentWindow(Main TAB)
    	 ***************************/
    	public void switchAndCloseNewTab(int tabIndex) {
    		// Get the current window handle
    		String mainTab = driver.getWindowHandle();
    		ArrayList<String> allTabs = new ArrayList<String>(driver.getWindowHandles());
    		driver.switchTo().window(allTabs.get(tabIndex));
    		// Close the newly Opened Window
    		driver.close();
    		// Switch back to original window
    		driver.switchTo().window(mainTab);
    	}	
    	
    	
    	/****************** Dropdown selection **************************************/

    	public void selectByVisibleText(By locater, String visibleText) {

    		WebElement element = driver.findElement(locater);
    		if (element.isDisplayed()) {
    			if (element.isEnabled()) {
    				Select dropdown = new Select(element);
    				dropdown.selectByVisibleText(visibleText);
    			} else {
    				System.out.println("The webelement is NOT Enabled, please check**************");
    			}
    		} else {
    			System.out.println("The webelement is NOT displayed, please check**************");
    		}

    	}

    	public void selectByIndex(By locater, int index) {
    		WebElement element = driver.findElement(locater);
    		if (element.isDisplayed()) {
    			// isEnabled()
    			if (element.isEnabled()) {
    				Select dropdown = new Select(element);
    				dropdown.selectByIndex(index);
    			} else {
    				System.out.println("The webelement is NOT Enabled, please check**************");
    			}
    		} else {
    			System.out.println("The webelement is NOT displayed, please check**************");
    		}

    	}

    	public void selectByValue(By locater, String value) {
    		WebElement element = driver.findElement(locater);
    		if (element.isDisplayed()) {
    			// isEnabled()
    			if (element.isEnabled()) {
    				Select dropdown = new Select(element);
    				dropdown.selectByValue(value);
    			} else {
    				System.out.println("The webelement is NOT Enabled, please check**************");
    			}
    		} else {
    			System.out.println("The webelement is NOT displayed, please check**************");
    		}

    	}

    	public void printAllDropdownValues(By locater) {
    		WebElement element = driver.findElement(locater);
    		if (element.isDisplayed() && element.isEnabled()) {
    			Select dropdown = new Select(element);
    			List<WebElement> dropdownValues = dropdown.getOptions();
    			// Print the size of dropdown values
    			System.out.println(dropdownValues.size());
    			// Print the dropdown values
    			for (WebElement allvalue : dropdownValues) {
    				System.out.println(allvalue.getText());
    			}
    		}

    	}

    	public void selectCustomiseOptionFromTheDropdownValues(By locater, String visibleText) {
    		WebElement element = driver.findElement(locater);
    		if (element.isDisplayed() && element.isEnabled()) {
    			Select dropdown = new Select(element);
    			List<WebElement> dropdownValues = dropdown.getOptions();
    			// Print the size of dropdown values
    			System.out.println(dropdownValues.size());
    			// Print the dropdown values
    			for (int i = 0; i < dropdownValues.size(); i++) {
    				System.out.println(dropdownValues.get(i).getText());

    				// Select India option from the dropdown
    				if (dropdownValues.get(i).getText().equals(visibleText)) {
    					dropdown.selectByIndex(i);
    					break;
    				}
    			}
    		}

    	}
	
    	
    	/*********** Random number **************/
    	public int randomNumber(int maxRange) {
    		Random r = new Random();
    		int rNum = r.nextInt(maxRange);
    		return rNum;

    	}
    	
    	
    	
    	
    
    	
}