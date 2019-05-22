package com.qa.uiMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Flights {

	public static WebElement rdbSelectTripOption(WebDriver driver, String option) {
		return driver.findElement(By.xpath("//li[contains(text(),'" + option + "')]/span"));
	}

	public static WebElement txtFromCity(WebDriver driver) {
		return driver.findElement(By.xpath("//label/input[@id='fromCity']"));
	}

	public static WebElement txtToCity(WebDriver driver) {
		return driver.findElement(By.xpath("//label/input[@id='toCity']"));
	}

	public static WebElement txtDepartureDate(WebDriver driver) {
		return driver.findElement(By.xpath("(//p[@class='blackText font20 code'])[1]"));
	}

	public static WebElement txtReturnDate(WebDriver driver) {
//		return driver.findElement(By.xpath("(//p[@class='blackText font20 code'])[2]"));
		return driver.findElement(By.xpath("//span[contains(text(),'Select return Date')]"));
	}

	public static WebElement btnSearch(WebDriver driver, String btName) {
		return driver.findElement(By.xpath("//a[contains(text(),'" + btName + "')]"));
	}

}
