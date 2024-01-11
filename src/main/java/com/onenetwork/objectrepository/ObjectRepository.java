package com.onenetwork.objectrepository;

import org.openqa.selenium.By;

public class ObjectRepository {
	
	public static By byAddressPage_userName = By.id("userName");
	public static By byAddressPage_output = By.xpath("//div[@id='output']");
	public static By byAddressPage_textbox = By.xpath("//span[text()='Text Box']");
	public static By byHomePage_signoutBtn = By.xpath("//a[text()='Sign out']");
	public static By byFramePageTopicname = By.xpath("//input[@type='text']");
	public static By byFramePageCheckBox = By.xpath("//input[@type='checkbox']");
	public static By byFramePageAnimals =By.id("animals");
	public static By byFrameTopicHeading = By.xpath("//label/span");
	public static By byFrame1 = By.id("frame1");
	public static By byFrame2 = By.id("frame2");
	public static By byFrame3 = By.id("frame3");
	public static By byFrameElement = By.tagName("iframe");
	public static By bytutorialMenu = By.xpath("//nav[@class='navbar']//span[text()='Tutorials']");
	public static By bywebAutoToolMenu = By.xpath("//nav[@class='mega-menu']//span[text()='Front-End Testing Automation']");
	public static By bycucumberTutMenu = By.xpath("//div[@class='second-generation']//a[text()='Cypress']");
	public static By bybootstratLoginUserName = By.xpath("//input[@type='text']");
	public static By bytooltipText = By.xpath("//span[@class='tooltiptext']");
	
	
}
