package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	public WebDriver init_driver(Properties prop)
	{
		String browserName = prop.getProperty("browser").trim();
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		System.out.println("Browser is : " + browserName);
		if(browserName.equals("chrome")){
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tldriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		else if (browserName.equals("safari")) {
			//driver = new SafariDriver();
			tldriver.set(new SafariDriver());
		}
		else
			System.out.println("Please pass the right browser..." + browserName);
		
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		getDriver().manage().window().maximize(); 
		return getDriver();
	}
	
	public synchronized WebDriver getDriver()
	{
		return tldriver.get();
	}

	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		String env = System.getProperty("env");
	//	String env = null;
		if (env == null)
		{
			System.out.println("Running on prod env.....");
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
			System.out.println("Running on " + env + " env.....");
			switch(env.toLowerCase())	
			{
			case "qa" : 
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
				
			case "dev" : 
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
				
			case "stage" : 
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
				
			case "uat" : 
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;
		
			default:	
				System.out.print("Please pass the right env...");
				break;
			}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public String getScreenshot()
	{
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") +"/Screenshots/" + System.currentTimeMillis() + ".png";
		File dest = new File(path);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	return path;
	}
}
