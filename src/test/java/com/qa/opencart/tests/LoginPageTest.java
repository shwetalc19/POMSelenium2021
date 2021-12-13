package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("ID – 1101 Design Open Cart App Login Page")
@Story("US 365 : Open Cart Login Page with multiple features")
@Listeners(TestAllureListener.class)                  //add so that it takes screenshot compulsorily
public class LoginPageTest extends BaseTest{

	@Description("Login Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority =1)
	public void loginPageTitleTest() {
		String actualTitle = loginpage.getLoginPageTitle();
		System.out.println("page title : " + actualTitle);
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Description("Login Page Url Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority =2)
	public void loginPageUrlTest() {
		Assert.assertTrue(loginpage.getLoginPageUrl());
	}

	@Description("Forgot Password Exists Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority =3)
	public void forgotPwdLinkExistTest() {

		Assert.assertTrue(loginpage.isForgotPwdLinkExist());
	}
	
	@Description("Register Link Exist Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority =4)
	public void registerLinkExistTest() {

		Assert.assertTrue(loginpage.isRegisterPageLinkExist());
	}

	@Description("Do Login Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority =5)
	public void doLoginTest() {
		accountsPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(accountsPage.getAccountPageTitle(), Constants.ACCOUNT_PAGE_TITLE);
		
	}

}
