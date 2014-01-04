package com.platinum.test.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumAgent {
	
	private WebDriver driver;
	
	public WebDriver getDriver() {
		return this.driver;
	}
	
	public SeleniumAgent(WebDriver driver) {
		this.driver = driver;
	}
	
	public void 選下拉(By 怎麼找到選項, String 選項字串) {
		ExpectedCondition<Boolean> 選項已經載入 = ExpectedConditions.textToBePresentInElement(怎麼找到選項, 選項字串);
		new WebDriverWait(driver, 50).until(選項已經載入);
		Select 下拉 = new Select(driver.findElement(怎麼找到選項));
		下拉.selectByVisibleText(選項字串);
	}
	
	public void clearAndSendKeys(By 怎麼找到元件, String keys) {
		ExpectedCondition<WebElement> 選項可以按下去 = ExpectedConditions.elementToBeClickable(怎麼找到元件);
		WebElement element = new WebDriverWait(driver, 30).until(選項可以按下去);
		element.clear();
		element.sendKeys(keys);
	}
	
	public void 按(By 怎麼找到元件) {
		ExpectedCondition<WebElement> 選項可以按下去 = ExpectedConditions.elementToBeClickable(怎麼找到元件);
		WebElement element = new WebDriverWait(driver, 30).until(選項可以按下去);
		element.click();
	}
	
	public void 選下拉BY選項VALUE(By 怎麼找到選項, String value字串) {
		ExpectedCondition<Boolean> 選項已經載入 = ExpectedConditions.textToBePresentInElement(怎麼找到選項, value字串);
		new WebDriverWait(driver, 50).until(選項已經載入);
		Select 下拉 = new Select(driver.findElement(怎麼找到選項));
		下拉.selectByValue(value字串);
	}
	
	public void clear(By 怎麼找到元件) {
		ExpectedCondition<WebElement> 選項可以按下去 = ExpectedConditions.elementToBeClickable(怎麼找到元件);
		WebElement element = new WebDriverWait(driver, 30).until(選項可以按下去);
		element.clear();
	}
	
	public String htmlElement內容(By 怎麼找到element) {
		ExpectedCondition<WebElement> 選項看得到 = ExpectedConditions.visibilityOfElementLocated(怎麼找到element);
		WebElement element = new WebDriverWait(driver, 120).until(選項看得到);
		String result = element.getText();
		return result;
	}
	
	public void delay(By 怎麼找到element) {
		ExpectedCondition<WebElement> 選項看得到 = ExpectedConditions.visibilityOfElementLocated(怎麼找到element);
		new WebDriverWait(driver, 120).until(選項看得到);
		new WebDriverWait(driver, 120).until(選項看得到);
		new WebDriverWait(driver, 120).until(選項看得到);
		new WebDriverWait(driver, 120).until(選項看得到);
		new WebDriverWait(driver, 120).until(選項看得到);
		new WebDriverWait(driver, 120).until(選項看得到);
		new WebDriverWait(driver, 120).until(選項看得到);
		new WebDriverWait(driver, 120).until(選項看得到);
		new WebDriverWait(driver, 120).until(選項看得到);
		new WebDriverWait(driver, 120).until(選項看得到);
		new WebDriverWait(driver, 120).until(選項看得到);
		new WebDriverWait(driver, 120).until(選項看得到);
	}
	
	public void alert對話框按確定() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
}
