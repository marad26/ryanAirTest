package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

	WebDriver driver;
	
	public AbstractPage(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * Wrapped wait to shorten declaration
	 * 
	 * @param element
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForVisibility(WebElement element, int timeOut){
		return new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOf(element));
	}
}
