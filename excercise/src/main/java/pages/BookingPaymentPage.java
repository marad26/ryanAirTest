package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BookingPaymentPage extends AbstractPage{
	
	/* Passenger details selectors */
	
	@FindBy(how= How.CSS, using="[id^=title]")
	private List<WebElement> titles;
	
	@FindBy(how= How.CSS, using="[id^=firstName]")
	private List<WebElement> firstNames;
	
	@FindBy(how= How.CSS, using="[id^=lastName]")
	private List<WebElement> lastNames;
	
	/* Infant birth date selectors */
	
	@FindBy(how= How.CSS, using=".desktop-date>.form-field>.core-select>select[name='day']")
	private List<WebElement> infantDay;
	
	@FindBy(how= How.CSS, using=".desktop-date>.form-field>.core-select>select[name='month']")
	private List<WebElement> infantMonth;
	
	@FindBy(how= How.CSS, using=".desktop-date>.form-field>.core-select>select[name='year']")
	private List<WebElement> infantYear;
	
	/* Contact details selectors */
	
	@FindBy(how= How.CSS, using="[id^=emailAddress]")
	private WebElement emailAddress;
	
	@FindBy(how= How.CSS, using="[id^=confirmEmail]")
	private WebElement confirmEmailAddress;
	
	@FindBy(how= How.XPATH, using=".//*[@id='payment-contact-form']/div[2]/contact-details-form/div/div[1]/div[3]/div/div[1]/div/select")
	private WebElement phoneCountryCode;
	
	@FindBy(how= How.XPATH, using=".//*[@id='payment-contact-form']/div[2]/contact-details-form/div/div[1]/div[3]/div/div[2]/input")
	private WebElement phoneNumber;
	
	/* Payment method selectors */
	
	@FindBy(how= How.CSS, using="[id^=cardNumber]")
	private WebElement cardNumber;
	
	@FindBy(how= How.CSS, using="[id^=cardType]")
	private WebElement cardType;
	
	@FindBy(how= How.CSS, using="[id^=expiryMonth]")
	private WebElement expiryMonth;
	
	@FindBy(how= How.CSS, using=".expiry-year-select")
	private WebElement expiryYear;
	
	@FindBy(how= How.CSS, using=".card-security-code>input")
	private WebElement securityCode;
	
	@FindBy(how= How.CSS, using=".core-input.cardholder")
	private WebElement cardholdersName;
	
	/* Billing address selectors */
	
	@FindBy(how= How.ID, using="billingAddressAddressLine1")
	private WebElement billingAddress1;
	
	@FindBy(how= How.ID, using="billingAddressAddressLine2")
	private WebElement billingAddress2;
	
	@FindBy(how= How.ID, using="billingAddressCity")
	private WebElement city;
	
	@FindBy(how= How.ID, using="billingAddressCountry")
	private WebElement country;
	
	@FindBy(how= How.ID, using="billingAddressPostcode")
	private WebElement postcode;
	
	/* Other form selectors */
	
	@FindBy(how= How.CSS, using="[id^=acceptTerms]")
	private WebElement acceptTerms;
	
	@FindBy(how= How.CSS, using=".core-btn-primary.core-btn-medium")
	private WebElement payButton;
	
	@FindBy(how= How.CSS, using=".error")
	private WebElement errorBox;
	
	@FindBy(how= How.CSS, using=".error>.info-text")
	private WebElement errorInfo;
	
	@FindBy(how= How.CSS, using=".tpl-homepage.homepage")
	private WebElement loadedPageBody;
	
	
	public BookingPaymentPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
		waitForPageToLoad();
	}
	
	/**
	 * This method calls other helper methods in order to fill form
	 * 
	 * @param cardNumber
	 * @param expiryDate
	 * @param ccv
	 */
	public void makePayment(String cardNumber, String expiryDate, String ccv){
		fillPassengerDetailsWithFakeData();
		fillContactDetails("test@test.pl", "Poland", "111111111");
		fillPaymentMethod(cardNumber, expiryDate, ccv, "MasterCard", "Test test");
		fillBillingAddress("21 Test test", "Test test", "Test", "12-213", "Poland");
		acceptTermsAndClickContinueButton();
	}
	
	/**
	 * This method waits for body element to change class value to specified
	 */
	private void waitForPageToLoad(){
		waitForVisibility(loadedPageBody, 10);
	}
	
	/**
	 * This method fills passenger details with fake generated data
	 * There is always Mr title selected and TestFirst|LastNameA/B/C etc.
	 * 
	 */
	public void fillPassengerDetailsWithFakeData(){		
		waitForVisibility(titles.get(0), 10);
		
		for(WebElement x : titles){
			new Select(x).selectByIndex(1);
		}
		
		for(int i=0; i<firstNames.size(); i++){
			firstNames.get(i).clear();
			firstNames.get(i).sendKeys("TestFirstName" + String.valueOf(Character.toChars(65+i)));
			lastNames.get(i).clear();
			lastNames.get(i).sendKeys("TestLastName" + String.valueOf(Character.toChars(65+i)));
		}
		
		for(int i=0; i<infantDay.size(); i++){
			new Select(infantDay.get(i)).selectByIndex(1);
			new Select(infantMonth.get(i)).selectByIndex(1);
			new Select(infantYear.get(i)).selectByIndex(1);
		}
	}
	
	/**
	 * This method fills contact details with data
	 * 
	 */
	public void fillContactDetails(String email, String country, String phoneNr){		
		waitForVisibility(emailAddress, 10);
		emailAddress.clear();
		emailAddress.sendKeys(email);
		confirmEmailAddress.clear();
		confirmEmailAddress.sendKeys(email);
		new Select(phoneCountryCode).selectByVisibleText(country);
		phoneNumber.clear();
		phoneNumber.sendKeys(phoneNr);
	}
	
	/**
	 * This method fills payment method fields with passed parameters.
	 * Also cardNumer is rewritten without spaces, because method send keys is not sending full amount of numbers.
	 * Also expire date is separated to get smaller strings with month and year separately.
	 * There is always year starting from 20, e.g. 2016
	 * 
	 * @param cardNumberVal
	 * @param expiry
	 * @param ccv
	 */
	public void fillPaymentMethod(String cardNumberVal, String expiry, String ccv, String cardProvider, String cardHolder){	
		String cardNumberValue = "";
		
		String [] tab = cardNumberVal.split(" ");
			for(String s : tab){
				cardNumberValue+=s;
			}
			
		cardNumber.clear();	
		cardNumber.sendKeys(cardNumberValue);
		
		String [] expiryDate = expiry.split("/");
		new Select(cardType).selectByVisibleText(cardProvider);
		new Select(expiryMonth).selectByVisibleText(expiryDate[0]);
		new Select(expiryYear).selectByVisibleText("20"+expiryDate[1]);
		
		securityCode.clear();
		securityCode.sendKeys(ccv);
		cardholdersName.clear();
		cardholdersName.sendKeys(cardHolder);
	}
	
	/**
	 * This method fills billing address.
	 * payButton is just clicked to lose focus of select
	 */
	public void fillBillingAddress(String address1, String address2, String cityName, String postCode, String countryName){
		billingAddress1.sendKeys(address1);
		billingAddress2.sendKeys(address2);
		city.sendKeys(cityName);
		postcode.sendKeys(postCode);
		new Select(country).selectByVisibleText(countryName);
		payButton.click();
	
	}
	
	public void acceptTermsAndClickContinueButton(){
		acceptTerms.click();
		payButton.click();
	}
	
	/*
	 * This method waits for error box to appear, and returns info message from it.
	 */
	public String getErrorText(){	
		waitForVisibility(errorBox, 10);
		return errorInfo.getText();
	}
}
