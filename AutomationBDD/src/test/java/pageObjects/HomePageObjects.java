package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import stepDefinition.BaseClass;

public class HomePageObjects {
	
	private WebDriver driver;
	
	//1. Find By Locators 
	private By enterTimeTextField = By.cssSelector("#EggTimer-start-time-input-text");
	private By startButton = By.xpath("//button[normalize-space()='Start']");
	
	//2. Constructor of the page class
	public HomePageObjects(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	//3. Page Actions
	public WebElement enterTimeTextField() {
		return driver.findElement(enterTimeTextField);
	}
	
	public void clickOnStartButton() {
		driver.findElement(startButton).click();
	}
	
	public String getHomePageTitle() {
		return driver.getTitle();
	}

}
