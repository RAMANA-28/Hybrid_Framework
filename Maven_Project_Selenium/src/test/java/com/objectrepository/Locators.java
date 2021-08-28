package com.objectrepository;

import org.openqa.selenium.By;

public class Locators {
	
	//here we will store the application locators!
	public final By FBLOGIN_USERNAME_EDITBOX = By.id("email");
	public final By FBLOGIN_PASSWORD_EDITBOX = By.name("pass");
	public final By FBLOGIN_LOGIN_BUTTON = By.name("login");
	//nyiso
	public final By FRAME_ZONAL_LINK = By.name("P-24Alist");
	public final By FRAME_LASTUPDAE_TEXT = By.xpath("//body[1]/table[1]/tbody[1]/tr[8]/td[2]/span[1]");
	//Drag and Drop
	public final By FRAME_JQEARY_DRAG_LINK = By.xpath("//p[contains(text(),'Drag me to my target')]");
	public final By FRAME_JQEARY_DROP_LINK = By.xpath("//div[@id='droppable']");
    //AppleVaction
	public final By LEAVING_FROM= By.xpath("//input[@id='orgAC_value']");
	public final By DESTINATION_TO= By.xpath("//input[@id='desAC_value']");
	
	//Webtable
	public final By FIRSTNAME_EDIT_BOX = By.xpath("//tbody/tr[@id='d1']/td[5]/button[1]/span[1]");
	public final By FIRSTNAME_CLEAR= By.xpath("//input[@id='fn']");
	public final By FIRSTNAME_MIDDLE= By.xpath("//input[@id='ln']");
	public final By FIRSTNAME_LAST= By.xpath("//input[@id='mn']");
	public final By UPDATE = By.xpath("//button[@id='up']");
	
	//Actions
	
	public final By PRODUCT_MENU = By.xpath("//div[contains(text(),'Products')]");
	
	
}
