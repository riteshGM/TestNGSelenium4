package com.ui.tests;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.ui.utilities.DriverManager;
import com.ui.utilities.LambdatestUtility;

public class TestBase {
	
	private String browser;
	
	@Parameters({"browser", "isLambdaTest" , "isHeadLess"})
	
	@BeforeMethod(description="load the home page of the website", alwaysRun=true)
	public void setUp(@Optional("chrome")String browser, 
			          @Optional("false")boolean isLambdaTest,
			          @Optional("false")boolean isHeadLess,
			          ITestResult result) {
			this.browser=browser;
			DriverManager.getInstance(browser).getDriver();
	}
		
	
	@AfterMethod(description="close the browser session")
	 public void tearDown() {
		DriverManager.getInstance(browser).quitDriver();
		
	}	
}
