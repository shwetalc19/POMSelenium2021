package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private  WebDriver  driver;
	private ElementUtil eleUtil;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}


	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginErrorMsg = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

	@Step("get Login page title..")
	public String getLoginPageTitle() {
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	@Step("get Login page url..")
	public boolean getLoginPageUrl() {
		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}

	@Step("checking if forgot password link is displayed..")
	public boolean isForgotPwdLinkExist()
	{
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}

	@Step("click on Register link..")
	public RegistrationPage goToRegisterPage()
	{
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
	
	@Step("checking if Registration link is displayed..")
	public boolean isRegisterPageLinkExist()
	{
		return eleUtil.doIsDisplayed(registerLink);
	}

	@Step("login with username: {0} and password : {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Login with : "+un+": " + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	@Step("login with wrong username: {0} and password : {1}")
	public boolean doLoginwithWrongCredentials(String un, String pwd) {
		System.out.println("Login with : "+un+": " + pwd);
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String err_msg = eleUtil.doGetText(loginErrorMsg);
		System.out.println(err_msg);
		if(err_msg.contains(Constants.LOGIN_ERROR_MSG)) {
			System.err.println("login not successfull...");
			return false;
		}
		return true;
	}
		
}
