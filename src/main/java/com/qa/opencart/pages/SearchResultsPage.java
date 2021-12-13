package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productResults = By.xpath("//div[@class = 'caption']//a");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getProductListCount() {
		int srchcount  = eleUtil.waitForElementsToBeVisible(productResults, 10, 2000).size();
		System.out.println("Search Product count : " + srchcount);
		return srchcount;
	}
	
	
	public ProductInfoPage selectProduct(String mainProductName)
	{
		System.err.println("main product name is : "+ mainProductName);
		List<WebElement> srchList = eleUtil.waitForElementsToBeVisible(productResults, 10, 2000);
		for (WebElement e : srchList) {
			String text = e.getText();
			if(text.contains(mainProductName))
			{
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}
	
	

}
