package com.qa.opencart.tests;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class AccountsPageTest extends BaseTest{

	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test(priority =1)
	public void accPageTitleTest()
	{
		String acTitle = accountsPage.getAccountPageTitle();
		Assert.assertEquals(acTitle, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Test(priority =2)
	public void accPageHeaderTest()
	{
		String actHeader = accountsPage.getAccountsPageHeader();
		Assert.assertEquals(actHeader, Constants.ACCOUNT_PAGE_HEADER);
	}

	@Test(priority =3)
	public void isLogOutExistTest() {
		Assert.assertTrue(accountsPage.isLogOutLinkExist());
	}
	
	@Test(priority =4)
	public void accPageSectionsTest()
	{
		List<String> actSecList = accountsPage.getAccountSectionsList();
		Assert.assertEquals(actSecList, Constants.getExpectedAccSectionList());
	}
	
	@DataProvider
	public Object[][] productData()
	{
		return ExcelUtil.getTestData("searchdata");
	}
	@Test(priority =6, dataProvider = "productData")
	public void searchTest(String productName, String searchResultCount)
	{
		srchResultsPage = accountsPage.doSearch(productName);	
	//	Assert.assertTrue(srchResultsPage.getProductListCount()> 0);
		Assert.assertEquals(srchResultsPage.getProductListCount(), Float.valueOf(searchResultCount).intValue());
	}
	
	@DataProvider
	public Object[][] productSelectData()
	{
		return ExcelUtil.getTestData("productselectdata");
	}
	
	@Test(priority =6, dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		srchResultsPage = accountsPage.doSearch(productName);
		productInfoPage = srchResultsPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProductName);
 
	}

}
