package com.qa.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Utilities {
	
	static WebDriver driver;
	static JavascriptExecutor js = (JavascriptExecutor) driver;
	
	public static void ScrollDownComplete() {
		
		Reporter.log("Scrolling down to load complete Page content..");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Scroll down completly to load all elements...");

		for (int i = 0; i < 10; i++) {

			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

		}
	}
	
	public static void ScrollUPComplete() {
		Reporter.log("Scrolling up to view element from start..");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Scroll Up...");

		for (int i = 0; i < 10; i++) {

			js.executeScript("window.scrollTo(document.body.scrollHeight,0);");

		}
	}
}
