package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	
	private  WebDriver  driver;
	private ElementUtil eleUtil;
	
	private By firstname = By.id("input-firstname");
	private By lastname = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscriber_Yes = By.xpath("(//input[@type='radio'])[2]");
	private By subscriber_No = By.xpath("(//input[@type='radio'])[3]");

	private By agreeChkbox = By.name("agree");
	private By continue_Btn = By.xpath("//input[@type='submit']");
	private By success_Msg = By.xpath("//div[@id='content']/h1");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	
	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean accountRegistration(String firstname, String lastname, String email, String telephone, String password, String subscribe)
	{
			eleUtil.waitForElementToBeVisible(this.firstname, 10, 2000).sendKeys(firstname);
			eleUtil.doSendKeys(this.lastname, lastname);
			eleUtil.doSendKeys(this.email, email);
			eleUtil.doSendKeys(this.telephone, telephone);
			eleUtil.doSendKeys(this.password, password);
			eleUtil.doSendKeys(confirmPassword, password);
			if (subscribe.equals("yes")) 
				eleUtil.doClick(subscriber_Yes);
			else
				eleUtil.doClick(subscriber_No);
			eleUtil.doClick(agreeChkbox);
			eleUtil.doClick(continue_Btn);
			String mesg= eleUtil.waitForElementToBeVisible(success_Msg, 10, 1000).getText();
			if(mesg.contains(Constants.REGISTRATION_SUCCESS_MSG)) {
				eleUtil.doClick(logoutLink);
				eleUtil.doClick(registerLink);
				return true;
			}
			else
				return false;
	}

}
