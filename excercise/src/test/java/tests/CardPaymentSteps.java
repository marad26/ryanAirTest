package tests;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.BookingExtrasPage;
import pages.BookingHomePage;
import pages.BookingPaymentPage;
import pages.HomePage;
import utilities.EventListener;
import utilities.Log;

public class CardPaymentSteps {
	
	WebDriver driver;
	BookingPaymentPage bookingPaymentPage;
	
	//strings to verify error communicate
	String infoTextCase1 = "As your payment was not authorised we could not complete your reservation."
								+ " Please ensure that the information was correct or use a new payment to try again";
	String startOfInfoTextCase2 = "Our systems have detected an identical reservation.";
	
	@Before
	public void setUp(){
		Log.startTestCase("Declined card payment");
        Log.info("Browser: FIREFOX");
        Log.info("System: " + System.getProperty("os.name"));
        
        //creating new FireFox instance
        driver = new FirefoxDriver();
        
        //setting up listener, for log purposes
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        EventListener listener = new EventListener();
        eventDriver.register(listener);
        driver = eventDriver;
        
        //setting up timeout for page loading
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        
        //navigating to main page
        driver.get("https://www.ryanair.com/ie/en/");
	}
	
	@Given("^I make a booking from \"([^\"]*)\" to \"([^\"]*)\" on (\\d+)/(\\d+)/(\\d+) for (\\d+) adults and (\\d+) child$")
	public void selectFlightOnSpecifiedData(String departure, String destination, int day , int month, int year, int adult, int child) throws InterruptedException{
		Map<String, Integer> date = new LinkedHashMap<String, Integer>();
		date.put("day", day);
		date.put("month", month);
		date.put("year", year);
		
		HomePage homePage = new HomePage(driver);
        BookingHomePage bookingHomePage = homePage.bookOneWayFlight(departure, destination, date, adult, child);
        BookingExtrasPage bookingExtrasPage = bookingHomePage.regularFlightClass();
        bookingPaymentPage = bookingExtrasPage.checkOut();
	}
	
	@When("^I pay for booking with card details \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
	public void payForFlightWithGivenData(String cardNumber, String expiryDate, String ccv) throws InterruptedException{
        bookingPaymentPage.makePayment(cardNumber, expiryDate, ccv);
	}
	
	@Then("^I should get payment declined message")
	public void verifyMessage() throws InterruptedException{
		Assert.assertTrue((bookingPaymentPage.getErrorText().equals(infoTextCase1)) || (bookingPaymentPage.getErrorText().startsWith(startOfInfoTextCase2)));
	}
	
	@After
	public void cleanUp(){
		Log.endTestCase("Declined card payment");
		driver.quit();
	}
}