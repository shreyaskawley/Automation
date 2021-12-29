package stepDefinition;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePageObjects;
import utilities.ReusableFunctions;

public class StepDefinition extends BaseClass {

	HomePageObjects homeScreen;
	ReusableFunctions reuseUtil;

	@Given("^User is on Homepage$")
	public void user_is_on_homepage() {

		homeScreen = new HomePageObjects(driver);

		//verify the page title on homepage
		String actualTitle = "e.ggtimer - a simple countdown timer";	
		Assert.assertEquals(homeScreen.getHomePageTitle(), actualTitle);
	}

	@When("^User enters (.*) in seconds$")
	public void user_enters_25_seconds(String time) {
		//Enter countdown time 
		homeScreen.enterTimeTextField().sendKeys(time);

	}

	@And("^Click on Go button to start the counter$")
	public void click_on_go_button_to_start_the_counter() {
		//Click on Start button
		homeScreen.clickOnStartButton();
	}

	@Then("^Wait for the counter to start the counting down (.+)$")
	public void wait_for_the_counter_to_start_the_counting_down(String time) throws Throwable {
		//Wait for the countdown to start
		int countdown = Integer.parseInt(time);
		reuseUtil = new ReusableFunctions(driver);

		reuseUtil.expectedWaitWithPollingTimeInSec(countdown, "//span[normalize-space()='"+countdown+" seconds']");

	}

	@Then("Verify that the countdown is happening every sec & the remaining (.+) decreases in one-second$")
	public void verify_the_countdown_is_happening_every_sec(String time) throws Exception {
		//Convert time into Integer
		int countdown = Integer.parseInt(time);

		//Condition to check wheather the countdown time is greater than 0
		while(countdown>=0) {
			//Condition to verify that the countdown is decreasing in every second
			if (countdown>1) {
				reuseUtil.expectedWaitWithPollingTimeInSec(countdown, "//span[normalize-space()='"+countdown+" seconds']");
				Assert.assertTrue(driver.findElement(By.xpath( "//span[normalize-space()='"+countdown+" seconds']")).isDisplayed());

			} else {
				reuseUtil.expectedWaitWithPollingTimeInSec(countdown, "//span[normalize-space()='"+countdown+" second']");
				Assert.assertTrue(driver.findElement(By.xpath( "//span[normalize-space()='"+countdown+" second']")).isDisplayed());
			}
			countdown --;
		}
	}

	@Then("Verify that Time Expired alert is displayed")
	public void verify_that_time_expired_alert_is_displayed() {
		//Wait for the alert and capture the text present on alert
		reuseUtil.expectedWaitForAlert(10);
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		System.out.println("Text on alert is: "+alertText);
	}
}
