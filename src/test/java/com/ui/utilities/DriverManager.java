package com.ui.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {
	
	private static volatile DriverManager instance;
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	
	private DriverManager() {}	
	
	private static void createDriver(String browser) {
		switch(browser) {
		case "chrome":
					tlDriver.set(new ChromeDriver());
					break;
		case "edge":
					tlDriver.set(new EdgeDriver());
					break;
		default :
			throw new IllegalArgumentException("Browser Name provided in not implemented please check again "+browser);
		}
	}
	
	public  static DriverManager getInstance(String browser) {		
		if(instance == null) {
			synchronized (DriverManager.class) {
				if(instance == null) {
					instance = new DriverManager();
				}
			}
		}	
		if(tlDriver.get()==null) {
			createDriver(browser);
		}
		return instance;
	}
	
	public WebDriver getDriver() {
		return tlDriver.get();
	}

	public void quitDriver() {
		if(tlDriver.get()!=null) {
			tlDriver.get().quit();
			tlDriver.remove();
		}
		
	}
}
