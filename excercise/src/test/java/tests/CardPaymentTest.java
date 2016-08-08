package tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		   features = "src/main/java/feature",
		   plugin = {"pretty", "html:target/Cucumber-report"}) 

public class CardPaymentTest {

}
