package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test(priority =1)
	public void productHeaderTest()
	{
		srchResultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = srchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(), "MacBook Pro");
	}
	
	@Test(priority=2)
	public void productImagesCountTest()
	{
		srchResultsPage = accountsPage.doSearch("iMac");
		productInfoPage = srchResultsPage.selectProduct("iMac");

		int actualImgCount = productInfoPage.getproductImagesCount();
		Assert.assertEquals(actualImgCount, Constants.IMAC_IMAGE_COUNT);
	}
	@DataProvider
	public Object[][] ProductInfoData() {
		return new Object[][] {
	//		{"MacBook","MacBook Pro" },
	//	 	{"Samsung", "Samsung Galaxy Tab 10.1"},
	//	 	{"iMac", "iMac"},
	//	 	{"HP", "HP LP3065"},
			{"Apple", "Apple Cinema 30\""},
		};
	}
	
	@Test(priority =3, dataProvider="ProductInfoData")
	public void productInfoTest(String productName, String mainProductName){
		srchResultsPage = accountsPage.doSearch(productName);
		productInfoPage = srchResultsPage.selectProduct(mainProductName);
		Map<String,String> actProductInfoMap = productInfoPage.getProductInfo();
		actProductInfoMap.forEach((k,v) -> System.out.println(k + ":" + v));
	//	softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
	//	softAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
	//	softAssert.assertEquals(actProductInfoMap.get("Price"), "$2,000.00");
	//	softAssert.assertAll();
	}
	

}
