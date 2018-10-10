package au.com.FMDP.live.functionLibrary.login;
import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.functionLibrary.common.CommonLib;
import au.com.FMDP.live.pageObjects.login.LoginPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class LoginLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	LoginPO loginpo;
	CommonLib common;
	
	public LoginLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        loginpo = new LoginPO(driver);
        common = new CommonLib(driver);
	}
	
	
	/**
	 * Login to application
	 * @param url login page url
	 * @param username username
	 * @param password password
	 */
	public void loginToApplication(String url, String username, String password)
	{
		selenium.navigateToPage(url);
		loginpo.enterUsername(username);
		loginpo.enterPassword(password);
		loginpo.clickOnLogin();
		common.waitAfterLogin();
	}
	
	/**
	 * Login to application without waiting to finish page load after login
	 * @param url login page url
	 * @param username username
	 * @param password password
	 */
	public void loginToApplication(String username, String password)
	{
		loginpo.enterUsername(username);
		loginpo.enterPassword(password);
		loginpo.clickOnLogin();
	}
	
	/**
	 * Get validation message on login page
	 * @return validation text
	 */
	public String getErrorMessage()
	{
		return loginpo.getErrorMessage();
	}
	
	/**
	 * To check whether error message displayed or not
	 * @return true or false
	 */
	public boolean isErrorMessageDispalyed()
	{
		boolean b = true;
		
		try
		{
			getErrorMessage();
		}
		catch(Exception e)
		{
			b=false;
		}
		return b;
	}
	
	/**
	 * Get all validation messages 
	 * @return validation text
	 */
	public String getAllFieldValidationMessages()
		{
			return loginpo.getAllFieldValidationMessages();
		}
	
}
