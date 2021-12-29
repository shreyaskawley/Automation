package stepDefinition;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public static void initializeDriver() throws Exception {
		
		//Initialize the property file
		
		prop = new Properties();
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/stepDefinition/config.properties");
		
		prop.load(fs);
		String browserName = prop.getProperty("Browser");
		
		if (browserName.equals("chrome")) {
			//Set the property & path for chrome driver 
			System.setProperty("webdriver.chrome.driver", "chrome");
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		
		else if (browserName == "firefox") {
			
			//Write code to invoke firefox browser
			
		}
		else {
			
			System.out.println("The browser name is incorrect: "+browserName);
		}
	}
	
}
