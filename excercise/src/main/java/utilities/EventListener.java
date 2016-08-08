package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class EventListener implements WebDriverEventListener {
	
	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1) {
		Log.info("Value had been changed");
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		Log.info("Element had been clicked");
	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		Log.info("Element had been found");
	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		Log.info("After navigating back to " + arg0.getCurrentUrl());
	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
		Log.info("After navigating forward to " + arg0.getCurrentUrl());
	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		Log.info("After navigating to " + arg0);
	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		Log.info("After script; The scritp is " + arg0);
	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1) {
		Log.info("Before changing value of " + arg0.toString());
	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		Log.info("Just before click on " + arg0.toString());
	}

	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		Log.info("Just before finding element " + arg0.toString());
	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		Log.info("Just before navigating back" + arg0.getCurrentUrl());
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		Log.info("Just before navigating forwards " + arg0.getCurrentUrl());
	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		Log.info("Just before navigating to " + arg0);
	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		Log.info("Just before script" + arg0);
	}

	@Override
	public void onException(Throwable arg0, WebDriver arg1) {
		Log.info("Exception at " + arg0.getMessage());
	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		
	}
}

