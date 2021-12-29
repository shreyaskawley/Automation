package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks extends BaseClass {



	@Before
	public void beforeHook() throws Exception {
		//Initialize the driver
		BaseClass.initializeDriver();

		//Get the URL of app from config file
		driver.get(prop.getProperty("URL"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@After(order = 0)
	public void afterHook() {
		//Quit the driver session
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		//Take the screenshot based on failed scenarios
		if (scenario.isFailed()) {
			//Take the sceenshot
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);

		}
	}
}
