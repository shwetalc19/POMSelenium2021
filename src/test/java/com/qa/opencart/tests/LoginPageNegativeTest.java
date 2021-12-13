package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest{
	
	@DataProvider
	public Object[][] NegativeLoginData()
	{
		return new Object[][] {
			{"Shweta", "Shweta"},
			{" ", " "},
			{"test@123", "test@123"},
			{"@#@$@", "#@#@$"}
		};
	}
	
	@Test(dataProvider = "NegativeLoginData")
	public void loginNegativeTest(String un, String pwd) {
		Assert.assertFalse(loginpage.doLoginwithWrongCredentials(un, pwd));
	}
}
