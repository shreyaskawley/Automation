package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableFunctions {
	WebDriverWait expectedWait;
	WebDriver driver;

	public ReusableFunctions(WebDriver driver) {
		this.driver = driver;
	}

	public void expectedWaitWithCondition(int waitTimeInSec, String locator) {
		//Explicit wait for the conditional basis
		expectedWait=new WebDriverWait(driver, waitTimeInSec);
		expectedWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}

	public void expectedWaitForAlert(int waitTimeInSec) {

		expectedWait=new WebDriverWait(driver, waitTimeInSec);
		expectedWait.until(ExpectedConditions.alertIsPresent());
	}

	public void expectedWaitWithPollingTimeInSec(int waitTimeInSec, String locator) {
		//Fluent wait - To verify element per second
		Wait<WebDriver> wait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(waitTimeInSec))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}

}
