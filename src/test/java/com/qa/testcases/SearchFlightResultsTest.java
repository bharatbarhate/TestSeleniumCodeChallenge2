package com.qa.testcases;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.core.IsNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.base.Function;

import com.qa.uiMap.Flights;
import com.qa.uiMap.HomePage;

import com.qa.uiMap.SearchIternary;
import com.qa.util.Utilities;

import ExcelReader.ExcelReader;

public class SearchFlightResultsTest {

	WebDriver driver;
	String baseUrl;
	String mon;
	String dd;
	String yyyy;


	@BeforeTest
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://www.makemytrip.com/";
		//		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get(baseUrl);
		driver.manage().deleteAllCookies();
		//		ExcelReader.testDataExcelFileName = "TestExcel.xlsx";
		//		ExcelReader.setExcelFileSheet("PropertySearchTest");
	}

	// @BeforeMethod

	//	@BeforeMethod
	//	@Test(priority = 1)
	public void closePromoImages() throws NoSuchFrameException {

		try {
			driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(HomePage.imgPromotionClose(driver)));
			if (HomePage.imgPromotionClose(driver).isDisplayed()) {
				HomePage.imgPromotionClose(driver).click();
			}
			driver.switchTo().defaultContent();
			// Thread.sleep(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void selectTripOption() throws InterruptedException {

		//		WebDriverWait wait = new WebDriverWait(driver, 60);
		//		wait.until(ExpectedConditions.visibilityOf(HomePage.icnSelectOption(driver, "Flights")));

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofMillis(600))
				.ignoring(WebDriverException.class);

		WebElement we = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement optionFlights = HomePage.icnSelectOption(driver, "Flights");
				if (optionFlights.isDisplayed()) {
					optionFlights.click();
				} else {
					System.out.println("Missing Flight Option");
				} 
				return optionFlights;
			}
		});

		//		we.click();
		Assert.assertEquals(HomePage.icnSelectOption(driver, "Flights").isDisplayed(), true,
				"Flight option is selectd sucessfully.");
	}
	@Test(priority = 3)
	public void selectTripType() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(Flights.rdbSelectTripOption(driver, "Round Trip")));
		if (Flights.rdbSelectTripOption(driver, "Round Trip").isDisplayed()) {
			Flights.rdbSelectTripOption(driver, "Round Trip").click();
			Assert.assertEquals(Flights.rdbSelectTripOption(driver, "Round Trip").isDisplayed(), true,
					"Round Type flight option is selected sucessfully.");
		} else { //
			System.out.println("Round Type flight option is not Available :: fail");
			Assert.assertEquals(Flights.rdbSelectTripOption(driver, "Round Trip").isDisplayed(), false,
					"Round Type flight option is missing.");
		}

	}

	@Test(priority = 4)
	public void selectFromCity() throws InterruptedException {

		if (Flights.txtFromCity(driver).isDisplayed()) {
			Flights.txtFromCity(driver).sendKeys("Delhi");
			Flights.txtFromCity(driver).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			System.out.println("From City Delhi Selected");
		} else { //
			System.out.println("Round Type flight option is not Available :: fail"); //
			Assert.assertEquals(Flights.rdbSelectTripOption(driver, "Round Trip").isDisplayed(), false, //
					"Round Type flight option is missing.");
		}

	}

	@Test(priority = 5)
	public void selectToCity() throws InterruptedException {

		if (Flights.txtToCity(driver).isDisplayed()) {
			Flights.txtToCity(driver).sendKeys("Banglore");
			Flights.txtToCity(driver).sendKeys(Keys.ENTER);
			Thread.sleep(1000);
			System.out.println("To City Banglore Selected");
		} else {
		}

	}
	public int addNumberofDaystoCurrentDate(int NoofDaysAdd) {
		int futDate = 0;
		if (NoofDaysAdd >=0) {
			futDate = LocalDate.now().plusDays(NoofDaysAdd).getDayOfMonth(); 
		}else
		{
			System.out.println("Please enter correct number of Days!!!!!");
		}
		return futDate;
	}
	@Test(priority = 6)
	public void selectDepartureDate() throws InterruptedException {

		mon = new SimpleDateFormat("MMM").format(Calendar.getInstance().getTime());
		dd = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
		yyyy = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());

		System.out.println("mon is :: "+mon);
		System.out.println("dd is :: "+dd);
		System.out.println("year is :: "+yyyy);

		int depDate =  addNumberofDaystoCurrentDate(1);
		//   int d1 = date.getDayOfMonth();


		if (Flights.txtDepartureDate(driver).isDisplayed()) {
			Flights.txtDepartureDate(driver).click();
			Thread.sleep(1000);
			System.out.println("Departure Date is Clicked");

			System.out.println(
					driver.findElement(By.xpath("//div[@class='DayPicker-Month']/div/div[contains(text(),'"+mon+"')]"))
					.getText());
			System.out.println(driver.findElement(By.xpath(
					"//div[contains(text(),'"+mon+"')]/../../div/div/div[@aria-disabled='false']/div/p[1][contains(text(),"+depDate+")]"))
					.getText());
			driver.findElement(By.xpath(
					"//div[contains(text(),'"+mon+"')]/../../div/div/div[@aria-disabled='false']/div/p[1][contains(text(),"+depDate+")]"))
			.click();
		} else {
		}
	}

	@Test(priority = 6)
	public void selectReturnDate() throws InterruptedException {

		mon = new SimpleDateFormat("MMM").format(Calendar.getInstance().getTime());
		//        dd = Integer.parseInt(new SimpleDateFormat("dd").format(Calendar.getInstance().getTime()))+12;
		yyyy = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());

		System.out.println("mon is :: "+mon);
		System.out.println("dd is :: "+dd);
		System.out.println("year is :: "+yyyy);

		LocalDate date =  LocalDate.now().plusDays(12);
		int retDate = addNumberofDaystoCurrentDate(12);

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofMillis(600)).ignoring(WebDriverException.class);

		WebElement we = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement returnDate = Flights.txtReturnDate(driver);
				if (returnDate.isDisplayed()) { //
					returnDate.click();
					System.out.println(driver
							.findElement(By.xpath("//div[@class='DayPicker-Month']/div/div[contains(text(),'Jun')]"))
							.getText());
					driver.findElement(By.xpath(
							"//div[contains(text(),'Jun')]/../../div/div/div[@aria-disabled='false']/div/p[1][contains(text(),"+retDate+")]"))
					.click();

				} else {
					System.out.println("Return Dates are not selected");
				}
				return returnDate;
			}
		});
	}

	@Test(priority = 7)
	public void clickSearch() throws InterruptedException {

		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 30);
		 * wait.until(ExpectedConditions.elementToBeClickable(Flights.btnSearch(driver,
		 * "Search")));
		 * 
		 * if (Flights.btnSearch(driver, "Search").isDisplayed()) {
		 * Flights.btnSearch(driver, "Search").click();
		 * System.out.println("Search Button is Clicked"); } else { }
		 */

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(100))
				.pollingEvery(Duration.ofMillis(600)).ignoring(WebDriverException.class);

		WebElement we = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement btnSearch = Flights.btnSearch(driver, "Search");
				if (btnSearch.isDisplayed()) {
					btnSearch.click();
					Assert.assertEquals(driver.getTitle(), "Makemytrip", "Flights Options are displayed");
				} else {
					System.out.println("Missing Search Button ");
				}
				return btnSearch;
			}
		});

		Thread.sleep(5000);
	}

	@Test(priority = 8)
	public void findNumberOfFlightsCompleScroll() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfAllElements(SearchIternary.DepartureFlights(driver)));
		wait.until(ExpectedConditions.visibilityOfAllElements(SearchIternary.ReturnFlights(driver)));
		//		Utilities.ScrollDownComplete();
		ScrollDownComplete();
	}

	public void ScrollDownComplete() {

		//		Reporter.log("Scrolling down to load complete Page content..");
		JavascriptExecutor js = (JavascriptExecutor) driver;
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

	@Test(priority = 9)
	public void findNumberOfFlights() throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfAllElements(SearchIternary.DepartureFlights(driver)));
		wait.until(ExpectedConditions.visibilityOfAllElements(SearchIternary.ReturnFlights(driver)));

		Thread.sleep(5000);

		if (SearchIternary.DepartureFlights(driver).size() != 0 && SearchIternary.ReturnFlights(driver).size() != 0) {
			System.out.println("Number of Departure Flights :: " + SearchIternary.DepartureFlights(driver).size());
			System.out.println("Number of Arrival Flights :: " + SearchIternary.ReturnFlights(driver).size());
			/*
			 * for (int i = 1; i < SearchIternary.DepartureFlights(driver).size(); i++) {
			 * System.out.println(i + " :: Departure Flight Details" + "\n" +
			 * SearchIternary.DepartureFlightIndex(driver, i).getText()); } for (int i = 1;
			 * i < SearchIternary.ReturnFlights(driver).size(); i++) { System.out.println( i
			 * + " :: Return Flight Details" + "\n" +
			 * SearchIternary.ReturnFlightIndex(driver, i).getText()); }
			 * 
			 */

		} else {
			System.out.println("Flights are not visible for selection criteria");
		}
	}

	@Test(priority = 10)
	public void checkStopOptionsfor1Stop() throws InterruptedException {
		if (SearchIternary.chkNoOfStops(driver, "1 Stop").isSelected()) {
			findNumberOfFlights();
		} else {
			SearchIternary.chkNoOfStops(driver, "1 Stop").click();
			findNumberOfFlights();
		}
		SearchIternary.chkNoOfStops(driver, "1 Stop").click();
	}

	@Test(priority = 11)
	public void checkStopOptionsforNonStop() throws InterruptedException {
		if (SearchIternary.chkNoOfStops(driver, "Non Stop").isSelected()) {
			findNumberOfFlights();
		} else {
			SearchIternary.chkNoOfStops(driver, "Non Stop").click();
			findNumberOfFlights();
		}
		SearchIternary.chkNoOfStops(driver, "Non Stop").click();
	}

	@DataProvider
	public static Object[][] getdata() {

		Object[][] object = { { 10, 1 }, { 3, 4 }, { 5, 6 }, { 8, 3 }, { 1, 9 }, { 5, 3 }, { 2, 3 }, { 2, 1 }, { 4, 1 },
				{ 4, 5 }, { 300, 4 } };

		return object;
	}

	public int SelectDepartureFlight(int Nthflight) throws InterruptedException {
		/*
		 * Click is not working Hence use Javascript to select Flight Option
		 */

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", SearchIternary.rdbdepartureFlight(driver, Nthflight));
		String deptflightCost = SearchIternary.departureFlightCost(driver, Nthflight).getText();
		/*
		 * String str = SearchIternary.rdbdepartureFlight(driver, Nthflight).toString();
		 * System.out.println("1st String :: "+str); String str1 =
		 * SearchIternary.rdbdepartureFlight(driver,
		 * Nthflight)+"/../../../div[2]/div[3]/p/span[contains(text(),'')]";
		 * System.out.println("2nd String :: "+str1);
		 */
		return ConvertToIntPrice(deptflightCost);
	}

	public int selectArrivalFlight(int Nthflight) throws InterruptedException {
		/*
		 * Click is not working Hence use Javascript to select Flight Option
		 */
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", SearchIternary.rdbreturnFlight(driver, Nthflight));
		String returnflightCost = SearchIternary.returnFlightCost(driver, Nthflight).getText();
		// System.out.println("Return flight Cost for "+Nthflight+"th flight is ::
		// "+returnflightCost);
		return ConvertToIntPrice(returnflightCost);
	}

	public int getTotalFarewithDiscountTemp() {

		int totalFooterFare = 0;
		// WebDriverWait wait = new WebDriverWait(driver, 60);
		// wait.until(ExpectedConditions.visibilityOf(SearchIternary.lblSlashedPriceFare(driver)));
		System.out.println("Gtetext :: ===> " + SearchIternary.lblSlashedPriceFare(driver).getText());
		try {
			if (SearchIternary.lblSlashedPriceFare(driver).getText().toString() != null) {
				totalFooterFare = ConvertToIntPrice(SearchIternary.lblSlashedPriceFare(driver).getText());
				System.out.println("if ===> " + totalFooterFare);
			} else {
				totalFooterFare = ConvertToIntPrice(SearchIternary.lbltotalFooterFare(driver).getText());
				System.out.println("Else ====>" + totalFooterFare);
			}
			return totalFooterFare;
		} catch (NoSuchElementException e) {
		}
		return totalFooterFare;
	}

	public int getTotalFarewithDiscount() {

		int totalFooterFare = 0;
		// WebDriverWait wait = new WebDriverWait(driver, 60);
		// wait.until(ExpectedConditions.visibilityOf(SearchIternary.lblSlashedPriceFare(driver)));
		// System.out.println("Gtetext :: ===>
		// "+SearchIternary.lblSlashedPriceFare(driver).getText());
		try {
			if (SearchIternary.lblSlashedPriceFare(driver).getText() != null) {
				totalFooterFare = ConvertToIntPrice(SearchIternary.lblSlashedPriceFare(driver).getText());
			}
		} catch (NoSuchElementException e) {
		}
		return totalFooterFare;
	}

	public static int ConvertToIntPrice(String s) {

		if (s.contains("Rs")) {
			s = s.substring(3, s.length());
		}

		s = s.replaceAll(",", ""); // remove commas
		return (int) Math.round(Double.parseDouble(s)); // return rounded double cast to int
	}

	@Test(priority = 12, dataProvider = "getdata")
	public void SelectFlightAndValidateTotal(int depaInx, int ArrivalIdx)
			throws InterruptedException, NoSuchElementException {
		int deptflightCost = SelectDepartureFlight(depaInx);
		int returnflightCost = selectArrivalFlight(ArrivalIdx);
		// System.out.println(depaInx+" ::
		// "+deptflightCost+"|"+SearchIternary.departureFlightCostFooter(driver).getText()+"
		// ===== "+ArrivalIdx+" ::
		// "+returnflightCost+"|"+SearchIternary.returnFlightCostFooter(driver).getText()
		// );

		Thread.sleep(2000);
		int deptflightCostFooter = ConvertToIntPrice(SearchIternary.departureFlightCostFooter(driver).getText());
		int returnflightCostFooter = ConvertToIntPrice(SearchIternary.returnFlightCostFooter(driver).getText());
		int totalFooterFare;
		if (getTotalFarewithDiscount() > 0) {
			totalFooterFare = getTotalFarewithDiscount();
		} else {
			totalFooterFare = ConvertToIntPrice(SearchIternary.lbltotalFooterFare(driver).getText());
		}
		/*
		 * int totalFooterFare = getTotalFarewithDiscount();
		 */
		/*
		 * //= ConvertToIntPrice(SearchIternary.lbltotalFooterFare(driver).getText());
		 * WebDriverWait wait = new WebDriverWait(driver, 60);
		 * wait.until(ExpectedConditions.visibilityOf(SearchIternary.lblSlashedPriceFare
		 * (driver)));
		 * 
		 * try { if (SearchIternary.lblSlashedPriceFare(driver).isDisplayed()) {
		 * totalFooterFare =
		 * ConvertToIntPrice(SearchIternary.lblSlashedPriceFare(driver).getText());
		 * System.out.println("If "+totalFooterFare); } else { totalFooterFare =
		 * ConvertToIntPrice(SearchIternary.lbltotalFooterFare(driver).getText());
		 * System.out.println("Else "+totalFooterFare); }
		 * System.out.println(depaInx+" :: "+deptflightCost+"|"+SearchIternary.
		 * departureFlightCostFooter(driver).getText()+" ===== "+ArrivalIdx+" :: "
		 * +returnflightCost+"|"+SearchIternary.returnFlightCostFooter(driver).getText()
		 * +"Total Fare :: "+totalFooterFare);
		 * Assert.assertEquals(deptflightCost+returnflightCost,
		 * totalFooterFare,"Total Flight cost is not Matching"); } catch
		 * (NoSuchElementException e) { // TODO Auto-generated catch block //
		 * e.addSuppressed(e); // e.printStackTrace(); } //
		 * Assert.assertEquals(deptflightCost+returnflightCost, deptflightCostFooter+
		 * returnflightCostFooter,"Total Flight cost is not Matching");
		 * 
		 */
		System.out.println(
				depaInx + " :: " + deptflightCost + "|" + SearchIternary.departureFlightCostFooter(driver).getText()
				+ " ===== " + ArrivalIdx + " :: " + returnflightCost + "|"
				+ SearchIternary.returnFlightCostFooter(driver).getText() + "Total Fare :: " + totalFooterFare);
		Assert.assertEquals(deptflightCost + returnflightCost, totalFooterFare, "Total Flight cost is not Matching");

	}

	@Test(priority = 5)
	public void findFlightCost() throws InterruptedException {
		if (SearchIternary.chkNoOfStops(driver, "Non Stop").isSelected()) {
			findNumberOfFlights();
		} else {
			SearchIternary.chkNoOfStops(driver, "Non Stop").click();
			findNumberOfFlights();
		}
		SearchIternary.chkNoOfStops(driver, "Non Stop").click(); // Uncheck Non Stop
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
