package au.com.FMDP.utilities;

import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebDriverManager 
{
	private WebDriver driver; 
	private String chromedriver_path= "lib/drivers/chromedriver.exe";
	private String iedriver_path = "lib/drivers/IEDriverServer.exe";
	private String geckodriver_path= "lib/drivers/geckodriver.exe";
	
	public WebDriver setUp(String node, String browserName) throws Exception 
	{
		browserName = browserName.toLowerCase();
		
		//Local machine run
		if (node.equalsIgnoreCase("local"))
		{
			switch(browserName)
			{
				case "firefox" :
					System.setProperty("webdriver.gecko.driver", geckodriver_path);
					FirefoxOptions options = new FirefoxOptions();
					options.setAcceptInsecureCerts(true);
					driver = new FirefoxDriver(options);
					break;
					
				case "chrome":
					System.setProperty("webdriver.chrome.driver",chromedriver_path);

					//Set a Chrome preferences
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", Constants.currentDir + Constants.downloadFilePath);
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.setExperimentalOption("prefs", chromePrefs);
					
					//Disable browser extension popup
					chromeOptions.addArguments("--disable-extensions");
					
					//Setting up general capabilities
					chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromePrefs);
					chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					chromeOptions.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

					//Start the chrome session
					driver = new ChromeDriver(chromeOptions);
					break;
					
				case "ie":
					System.setProperty("webdriver.ie.driver",iedriver_path);
					driver = new InternetExplorerDriver();
					break;
				
				default:
					System.setProperty("webdriver.chrome.driver",chromedriver_path);
					driver = new ChromeDriver();
					break;
			}
			
		}
		
		//Grid run
		else
		{
			DesiredCapabilities cap;
			
			switch (browserName)
			{
				case "firefox" :
					cap = DesiredCapabilities.firefox(); 
					cap.setAcceptInsecureCerts(true);
				 	cap.setBrowserName("firefox");
			  	 	break;
					
				case "chrome":
					cap = DesiredCapabilities.chrome(); 
				 	cap.setBrowserName("chrome");
				 	break;
					
				case "ie":
					cap = DesiredCapabilities.internetExplorer(); 
				 	cap.setBrowserName("Internet Explorer");
				 	break;
				
				default:
					cap = DesiredCapabilities.chrome(); 
				 	cap.setBrowserName("chrome");
			  	 	break;
			
			}
			
			cap.setPlatform(Platform.WINDOWS);	  	
			driver = new RemoteWebDriver(new URL(node), cap);
			
		}
		
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60, TimeUnit.SECONDS);
        driver.manage().window().maximize(); 
        
        return driver;   
	 }
	
	public void tearDown() throws Exception 
	{
		//driver.close();
		driver.quit();
	}
}
