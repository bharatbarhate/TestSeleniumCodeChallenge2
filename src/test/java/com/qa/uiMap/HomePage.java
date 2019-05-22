package com.qa.uiMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	public static WebElement icnSelectOption(WebDriver driver, String option)
	{
		return driver.findElement(By.xpath("//span[contains(.,'"+option+"')]"));
	}
	public static WebElement imgPromotion(WebDriver driver)
	{
		return driver.findElement(By.xpath("//img[@id='second-img' and @src[contains(.,'promos')]]"));
	}

	public static WebElement imgPromotionClose(WebDriver driver)
	{
		return driver.findElement(By.xpath("//i[@class='wewidgeticon we_close']"));
	}

}
