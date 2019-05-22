package com.qa.uiMap;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchIternary {

	public static WebElement chkStop(WebDriver driver, String stopOption) {
		return driver.findElement(By.xpath("(//span[contains(text(),'"+stopOption+"')])[1]/../span/span[@class='check']"));

	}
	public static List<WebElement> DepartureFlights(WebDriver driver)
	{
		return driver.findElements(By.xpath("//div[@class='splitVw-sctn pull-left']/div[2]/div[contains(@class,'fli-list splitVw-listing')]"));
	}

	public static List<WebElement> ReturnFlights(WebDriver driver)
	{
		return driver.findElements(By.xpath("//div[@class='splitVw-sctn pull-right']/div[2]/div[contains(@class,'fli-list splitVw-listing')]"));
	}
	public static WebElement DepartureFlightIndex(WebDriver driver, int flightindex)
	{
		return driver.findElement(By.xpath("(//div[@class='splitVw-sctn pull-left']/div[2]/div[contains(@class,'fli-list splitVw-listing')])["+flightindex+"]"));
	}
	public static WebElement ReturnFlightIndex(WebDriver driver, int flightindex)
	{
		return driver.findElement(By.xpath("(//div[@class='splitVw-sctn pull-right']/div[2]/div[contains(@class,'fli-list splitVw-listing')])["+flightindex+"]"));
	}	
	public static WebElement chkNoOfStops(WebDriver driver, String stopOption)//1 Stop , Non Stop
	{
		return driver.findElement(By.xpath("(//span[contains(text(),'"+stopOption+"')])[1]/../span/span[@class='check']"));
	}

	public static WebElement rdbdepartureFlight(WebDriver driver, int index)
	{
		return driver.findElement(By.xpath("(//div[@class='splitVw-sctn pull-left']/div[2]/div[contains(@class,'fli-list splitVw-listing')])["+index+"]/label/div/span/span[@class='splitVw-inner']"));
	}
	public static WebElement rdbreturnFlight(WebDriver driver, int index)
	{
		return driver.findElement(By.xpath("(//div[@class='splitVw-sctn pull-right']/div[2]/div[contains(@class,'fli-list splitVw-listing')])["+index+"]/label/div/span/span[@class='splitVw-inner']"));
	}

	public static WebElement departureFlightCost(WebDriver driver, int index)
	{
		return driver.findElement(By.xpath("(//div[@class='splitVw-sctn pull-left']/div[2]/div[contains(@class,'fli-list splitVw-listing')])["+index+"]/label/div/span/span[@class='splitVw-inner']/../../../div[2]/div[3]/p/span[contains(text(),'')]"));
	}
	public static WebElement returnFlightCost(WebDriver driver, int index)
	{
		return driver.findElement(By.xpath("(//div[@class='splitVw-sctn pull-right']/div[2]/div[contains(@class,'fli-list splitVw-listing')])["+index+"]/label/div/span/span[@class='splitVw-inner']/../../../div[2]/div[3]/p/span[contains(text(),'')]"));
	}
	public static WebElement departureFlightCostFooter(WebDriver driver)
	{
		return driver.findElement(By.xpath("//div[contains(@class,'splitVw-footer-left')]/div/div[2]/div[4]/p/span[contains(text(),'')]"));
	}
	public static WebElement returnFlightCostFooter(WebDriver driver)
	{
		return driver.findElement(By.xpath("//div[contains(@class,'splitVw-footer-right ')]/div/div[2]/div[4]/p/span[contains(text(),'')]"));
	}
	
	public static WebElement lbltotalFooterFare(WebDriver driver)
	{
		return driver.findElement(By.xpath("//span[@class='splitVw-total-fare']/span"));
	}
	public static WebElement lblSlashedPriceFare(WebDriver driver)
	{
		return driver.findElement(By.xpath("//span[@class='slashed-price']/span"));
	}
}
