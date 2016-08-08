package pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage{
	Actions action;
	WebDriver driver;
	
	/* Flights Form Elements */
	
	@FindBy(how = How.CLASS_NAME, using = "core-input")
	private List<WebElement> departureDestinationFields;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='search-container']/div[1]/div/form/div[1]/div[2]/div/div[1]/div[3]/div/div/div[2]/popup-content/core-linked-list/div[2]/div[1]/div[3]")
	private WebElement departureFirstAirport;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='search-container']/div[1]/div/form/div[1]/div[2]/div/div[2]/div[3]/div/div/div[2]/popup-content/core-linked-list/div[2]/div[1]/div[3]")
	private WebElement destinationFirstAirport;
	
	@FindBy(how = How.ID, using = "flight-search-type-option-one-way")
	private WebElement flightsFormOneWayRadio;
	
	@FindBy(how = How.CLASS_NAME, using = "dd")
	private WebElement flyOutDD;
	
	@FindBy(how = How.CLASS_NAME, using = "mm")
	private WebElement flyOutMM;
	
	@FindBy(how = How.CLASS_NAME, using = "yyyy")
	private WebElement flyOutYYYY;
	
	@FindBy(how = How.CLASS_NAME, using = "dropdown-handle")
	private WebElement passengersDropdown;
	
	@FindBy(how = How.CSS, using = ".core-btn.dec")
	private List<WebElement> substractPassengersButtons;
	
	@FindBy(how = How.CSS, using = ".core-btn.inc")
	private List<WebElement> addPassengersButtons;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='row-dates-pax']/div[2]/div[3]/div/div/div[2]/popup-content/div[6]/div/div[3]/core-inc-dec/button[2]")
	private WebElement randomButtonOnPassengersButtons;
	
	@FindBy(how = How.XPATH, using = ".//*[@id='search-container']/div[1]/div/form/div[3]/button[2]")
	private WebElement flightsFormSubmitButton;
	
	@FindBy(how = How.CSS, using = ".tpl-homepage.homepage")
	private WebElement loadedPageBody;
	
	/**
	 * call AbstractPage constr, Init driver variable,
	 * and call page factory to setup proxies
	 * 
	 * @param driver
	 */
	public HomePage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		action = new Actions(driver);
		waitForPageToLoad();
	}
	
	/**
	 * This method calls other helper methods in order to fill flights form
	 * 
	 * @param departure		place from which the flight is taking place
	 * @param destination	place to which the flight is taking place
	 * @param date			departure date table in format tab[0]==DD etc.
	 * @param adult			number of adult's
	 * @param child			number of children
	 * @return				page object of page appearing after button click
	 */
	public BookingHomePage bookOneWayFlight(String departure, String destination, Map<String, Integer> date, int adult, int child){
		setOneWayTicket();
		setRoute(departure, destination);
		setDepartureDate(date.get("day"), date.get("month"), date.get("year"));
		addPassengers(adult, 0, child);
		clickContinueButton();
		return new BookingHomePage(driver);
	}
	
	/**
	 * This method waits for body element to change class value to specified
	 */
	private void waitForPageToLoad(){
		waitForVisibility(loadedPageBody, 10);
	}
	
	private void setOneWayTicket(){	
		flightsFormOneWayRadio.click();
	}	
	
	/**
	 * This method enter text to field's and select first airport appearing on the list
	 * 
	 * @param departure		place from which the flight is taking place	
	 * @param destination	place to which the flight is taking place
	 */
	private void setRoute(String departure, String destination){
		departureDestinationFields.get(0).clear();
		departureDestinationFields.get(0).sendKeys(departure);
		waitForVisibility(departureFirstAirport, 10).click();
		departureDestinationFields.get(1).clear();
		departureDestinationFields.get(1).sendKeys(destination);
		waitForVisibility(destinationFirstAirport, 10).click();
	}
	
	/**
	 * This method enter date 
	 * 
	 * @param day
	 * @param month
	 * @param year
	 */
	private void setDepartureDate(int day, int month, int year){
		waitForVisibility(flyOutDD, 10);
		flyOutDD.clear();
		flyOutDD.sendKeys(Integer.toString(day));
		flyOutMM.clear();
		flyOutMM.sendKeys(Integer.toString(month));
		flyOutYYYY.clear();
		flyOutYYYY.sendKeys(Integer.toString(year));
	}
	
	/**
	 * This method click's on buttons adding passengers in top-down order.
	 * Passenger clicked depending on given parameters position.
	 * 
	 * @param passenger		number of adults, teens, children and infants
	 */
	private void addPassengers(Integer... passenger){
		passengersDropdown.click();
		waitForVisibility(randomButtonOnPassengersButtons, 10);
		if(passenger.length>0){
			if(passenger[0]>1){
				for(int i = passenger[0]-1; i>0; i--){
					addPassengersButtons.get(0).click();
				}
			}
			for(int i=1; i<passenger.length; i++){
				for(int j=passenger[i]; j>0; j--){
					addPassengersButtons.get(i).click();
				}
			}
		}
		passengersDropdown.click();
	}
	
	/*
	 * Used double click, because sometime's only overlay was appearing after click
	 * and button was focused but not clicked
	 */
	public void clickContinueButton(){
		action.doubleClick(flightsFormSubmitButton).perform();
	}
}