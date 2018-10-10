package au.com.FMDP.utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class ExceptionHandler 
{
	WebDriver driver;
	JavaHelpers javahelp;
	SeleniumHelpers selenium;
	
	public ExceptionHandler(WebDriver driver)
	{
		this.driver = driver;
		javahelp = new JavaHelpers();
		selenium = new SeleniumHelpers(driver);
	}
	
	
	public void TakeScreenshotAndLogException(String exception) throws IOException
	{
		selenium.takeScreenshot(javahelp.getMethodName());
		Reporter.log(exception);
		Assert.fail();
	}
}
