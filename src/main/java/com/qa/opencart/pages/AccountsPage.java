package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By header = By.xpath("//div[@id='logo']//a");
	private By accountSections = By.xpath("//div[@id='content']//h2");
	private By searchField = By.name("search");
	private By searchButton = By.xpath("//div[@id='search']//button[@type='button']");
	private By logoutLink = By.linkText("Logout");

	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountPageTitle()
	{
		return eleUtil.doGetTitle(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public String getAccountsPageHeader()
	{
		return eleUtil.doGetText(header);
	}

	public boolean isLogOutLinkExist()
	{
		return eleUtil.doIsDisplayed(accountSections);
	}
	public void logout() {
		if (isLogOutLinkExist())
			eleUtil.doClick(logoutLink);
	}
	public  List<String> getAccountSectionsList()
	{
		List<WebElement> accsecList = eleUtil.waitForElementsToBeVisible(accountSections, 10);
		List<String> accTextList = new ArrayList<>();
		for(WebElement e : accsecList )
		{
			String text = e.getText();
			accTextList.add(text);
		}
		return accTextList;
	}
	public boolean isSearchExist(){
		return eleUtil.doIsDisplayed(searchField);
	}

	public SearchResultsPage doSearch(String productName)
	{
		System.out.println("searching the product : " + productName);
		eleUtil.doSendKeys(searchField, productName);
		eleUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}
}
