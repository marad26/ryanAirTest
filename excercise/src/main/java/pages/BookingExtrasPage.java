package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BookingExtrasPage extends AbstractPage{
	
	@FindBy(how= How.CSS, using=".core-btn-primary.core-btn-block.core-btn-medium.btn-text")
	private WebElement continueButton;
	
	@FindBy(how= How.CSS, using=".core-btn-ghost.seat-prompt-popup-footer-btn")
	private WebElement popupButton;
	
	
	public BookingExtrasPage(WebDriver driver){
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method waits for continue button to appear and click's it.
	 * Then waits for popup to appear and click button for not picking seats
	 * 
	 * @return page object of page appearing after button click
	 */
	public BookingPaymentPage checkOut(){
		waitForVisibility(continueButton, 10).click();
		waitForVisibility(popupButton, 10).click();
		return new BookingPaymentPage(driver);
	}
	
}
