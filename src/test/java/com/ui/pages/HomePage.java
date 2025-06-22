package com.ui.pages;

import static com.constants.Environment.QA;
import static com.ui.utilities.PropertiesUtil.readProperty;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.ui.utilities.BrowserUtility;
import com.ui.utilities.LoggerUtility;

public final class HomePage extends BrowserUtility{
	
	Logger logger = LoggerUtility.getLogger(this.getClass());
          
	private static final By SIGN_IN_LINK_LOCATOR=By.xpath("//a[@title='Log in to your customer account']");
  

	public void openApplication() {
		// TODO Auto-generated method stub
		goToWebsite(readProperty(QA, "URL"));
   	 logger.info("trying to perform click to go to sign in page");
   	 clickOn(SIGN_IN_LINK_LOCATOR);
	}


   
}
