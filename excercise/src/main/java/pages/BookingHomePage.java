package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BookingHomePage extends AbstractPage{
	
	@FindBy(how= How.XPATH, using=".//*[@id='outbound']/div/div[3]/div/flights-table/div[2]/div/div/div[2]/div[1]/div/span[1]/label/span")
	private WebElement regularRadio;
	
	@FindBy(how= How.CSS, using=".core-btn-primary.core-btn-block.core-btn-medium")
	private WebElement continueButton;
	
	public BookingHomePage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method wait's for options to appear, and select's normal type of seats
	 * Then is sleeps, because the page was throwing timeout and rejecting transaction
	 * Then it wait's for button to go to the next step, and clicks it
	 * 
	 * @return page object of page appearing after button click
	 * @throws InterruptedException
	 */
	public BookingExtrasPage regularFlightClass() throws InterruptedException{
		waitForVisibility(regularRadio, 10).click();
		regularRadio.click();
		
		// Thread sleep is added to overcome transaction timeout from the page
		Thread.sleep(5000);
		
		waitForVisibility(continueButton, 10).click();
		return new BookingExtrasPage(driver);
	}
	
}
