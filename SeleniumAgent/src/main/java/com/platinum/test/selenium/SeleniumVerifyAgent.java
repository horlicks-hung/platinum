package com.platinum.test.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumVerifyAgent {
	private WebDriver driver;
	
	public SeleniumVerifyAgent(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isElementEnabled(By 怎麼找到element) {
		ExpectedCondition<WebElement> 選項看得到 = ExpectedConditions.presenceOfElementLocated(怎麼找到element);
		WebElement element = new WebDriverWait(driver, 1).until(選項看得到);
		if (element.isEnabled()) {
			return true;
		}
		return false;
	}

	public boolean isElementDisabled(By 怎麼找到element) {
		ExpectedCondition<WebElement> 選項看得到 = ExpectedConditions.presenceOfElementLocated(怎麼找到element);
		WebElement element = new WebDriverWait(driver, 1).until(選項看得到);
		if (element.isEnabled()) {
			return false;
		}
		return true;
	}

}
