package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage  {

	private ElementUtil eleUtil;
	private  WebDriver driver;
	private Map<String, String> productInfoMap;
	
	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By productImages = By.xpath("//ul[@class='thumbnails']/li");
	private By productMetaData = By.xpath("(//div[@class='col-sm-4']/ul[@class='list-unstyled'])[1]/li");
	private By productPriceData= By.xpath("(//div[@class='col-sm-4']/ul[@class='list-unstyled'])[2]/li");
//  private By priceExTaxPrice = By.xpath("(//div[@class='col-sm-4']/ul[@class='list-unstyled'])[2]/li[contains(text(),'Ex Tax')]");
//	private By productPrice = By.xpath("(//div[@class='col-sm-4']/ul[@class='list-unstyled'])[2]/li/h2");
	private By qty = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public String getProductHeader()
	{
		String productHeaderText = eleUtil.doGetText(productHeader);
		System.out.println("Product header is "+ productHeaderText);
		return productHeaderText;
	}

	public int getproductImagesCount()
	{
		return eleUtil.waitForElementsToBeVisible(productImages, 10).size();
	}
	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("name", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
	}
	
	private void getProductMetaData()
	{
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		List<String> prodMetaList = new ArrayList<>();
		for(WebElement e: metaDataList)
		{
			String data = e.getText();
			String[] meta = data.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();	
			productInfoMap.put(metaKey,metaValue);
		}
 	}
	
	//does not work for Apple Cinema 30"
	private void getProductPriceData()
	{
		List<WebElement> prodPriceData = eleUtil.getElements(productPriceData);
		String price = prodPriceData.get(0).getText().trim();
		productInfoMap.put("Price", price );
		String taxPrice = null;
		for(WebElement e : prodPriceData )
		{
			if(e.getText().contains("Ex Tax")) {
				taxPrice = e.getText().trim();
				break;
			}
		}
		if(taxPrice!=null) {
			String []taxPriceSplit = taxPrice.split(":");
			productInfoMap.put("ExTaxPrice", taxPriceSplit[1]);
		}
 	}
	
}
