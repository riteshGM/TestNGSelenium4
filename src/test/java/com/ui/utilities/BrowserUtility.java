package com.ui.utilities;

import static com.constants.Environment.QA;
import static com.ui.utilities.PropertiesUtil.readProperty;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public  abstract class BrowserUtility {
	
	private Logger logger = LoggerUtility.getLogger(this.getClass());
	
	WebDriver driver = DriverManager.getInstance(readProperty(QA, "browser")).getDriver();
	
	public WebDriverWait getFluentWait() {
		return new WebDriverWait(driver,Duration.ofSeconds(30L));
	}
	public void goToWebsite(String url) {
		logger.info("visiting the website");
		driver.get(url);
	}
	
	public void maximizeWindow() {
		logger.info("maximizing the browser window");
		driver.manage().window().maximize();
	}
	
	public void clickOn(By locator) {
		logger.info("finding element with the locator"+locator);
		WebElement element=getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
		WebElement element1=getFluentWait().until(ExpectedConditions.elementToBeClickable(element));
		logger.info("element found and now performing click");
		element1.click();
	}
	
	public void clickOnCheckbox(By locator) {
		logger.info("finding element with the locator"+locator);
		WebElement element=getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("element found and now performing click");
		element.click();
	}
	
	public void clickOn(WebElement element) {
		logger.info("performing click");
		element.click();
	}
	
	public void enterText(By locator, String textToEnter) {
		logger.info("finding element with the locator"+locator);
		WebElement element=getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("element found and now enter text"+textToEnter);
		element.sendKeys(textToEnter);
	}
	public void enterSpecialKey(By locator, Keys keyToEnter) {
		logger.info("finding element with the locator"+locator);
		WebElement element=getFluentWait().until(ExpectedConditions.elementToBeClickable(locator));
		logger.info("element found and now enter key" +keyToEnter);
		element.sendKeys(keyToEnter);
	}
	
	public String getStringValue(By locator) {
		logger.info("finding element with the locator"+locator);
		WebElement element=getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("element found and now returning the visible text");
		return element.getText();	
	}
	
	public String getStringValue(WebElement element) {
		logger.info("returning the visible text");
		return element.getText();	
	}
	

	public List<String> getAllStringValues(By locator) {
		logger.info("finding element with the locator"+locator);
		List<WebElement> elementList=getFluentWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		logger.info("element found and now returning the visible text");
		List<String> visibleTextList= new ArrayList<String>();
		logger.info("returning the visible text List");
		for(WebElement element:elementList) {
			visibleTextList.add(getStringValue(element));
		}
		return visibleTextList;	
	}
	
	public List<WebElement> getAllElements(By locator) {
		logger.info("finding element with the locator"+locator);
		List<WebElement> elementList=getFluentWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		return elementList;	
	}
	
	public void selectFromDropdown(By dropDownLocator, String textToSelect) {
		logger.info("Finding element with the locator"+ dropDownLocator);
		WebElement element =driver.findElement(dropDownLocator);
		Select select=new Select(element);
		logger.info("Selecting the option "+ textToSelect);
		select.selectByVisibleText(textToSelect);
		
	}
	
	public void clearText(By locator) {
		logger.info("finding element with the locator"+locator);
		WebElement element=getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("element found and now clearing the text");
		element.clear();	
	}
		
	public void closeBrowser() {
        driver.quit();
    }
	
	public String takeScreenShot(String testCaseName) {
		TakesScreenshot screenshot=(TakesScreenshot) driver;
		File screenshotData=screenshot.getScreenshotAs(OutputType.FILE);
		Date date=new Date();
		SimpleDateFormat format= new SimpleDateFormat("HH-mm-ss");
		String timestamp=format.format(date);
		String path= System.getProperty("user.dir")+"//screenshots//"+testCaseName+"-"+timestamp+".png";
		File screenshotFile= new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return path;
	}

}
