package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("ID – 54321 Design Registration Page")
@Story("US 876 : Registration Page for multiple users")
public class RegistrationPageTest extends BaseTest {
	@BeforeClass
	public void accPageSetup() {
		registrationPage = loginpage.goToRegisterPage();
	}
	
	public String getRandomEmail()
	{
		Random randomGenerator =  new Random();
		String email = "Septautomation" + randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	
	@DataProvider
	public Object[][] getRegisterTestData()
	{
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	
	@Test(dataProvider = "getRegisterTestData")
	public void userRegistrationTest(String firstname, String lastname, String telephone, String password, String subscribe)
	{
		Assert.assertTrue(registrationPage.accountRegistration(firstname, lastname, getRandomEmail(), telephone, password, subscribe));
	}		
}
