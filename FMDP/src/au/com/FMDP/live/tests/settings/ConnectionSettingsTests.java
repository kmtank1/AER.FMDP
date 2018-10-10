package au.com.shortcuts.live.tests.settings;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import au.com.FMDP.live.functionLibrary.common.TopNavigationLib;
import au.com.FMDP.live.functionLibrary.login.LoginLib;
import au.com.FMDP.live.functionLibrary.settings.ConnectionSettingsLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class ConnectionSettingsTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	private ConnectionSettingsLib connection;
	
	private String propertyFile="test-input/settings.properties";
	private String partnerid = JavaHelpers.getPropertyValue(propertyFile,"partnerid");
	private String username = JavaHelpers.getPropertyValue(propertyFile,"username");
	private String password= JavaHelpers.getPropertyValue(propertyFile,"password");
	
	
	@BeforeClass
	@Parameters({"node", "browser"})
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception
	{
		drivermanager = new WebDriverManager();
		driver = drivermanager.setUp(node,browser); 
		loginlib = new LoginLib(driver);
		navigationlib = new TopNavigationLib(driver);
		connection = new ConnectionSettingsLib(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		ex = new ExceptionHandler(driver);
	}
	
	
	/*Test 1 : Verify that user can edit connection settings for Marketing successfully*/
	@Test (priority = 1)
	public void connectionsettings_MarketingUpdate() throws IOException
	{
		try 
		{	
		
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to General Settings > Connection Settings page
			Reporter.log("Step 2 - Opening Menu & Navigating to General Settings > Connection Settings page");
			navigationlib.clickOnMenuItem("General settings");
			navigationlib.menu_ClickOnConnectionSettingsTile();
			
			//Step 3- On connection settings page, editing information for Marketing section
			Reporter.log("On connection settings page, editing information for Marketing section");
			
			String id = "48559bf6ca184ae1b73e4606d1552571";
			String uname="sherellietest11";
			String pwd="surebiz1";
			connection.marketing_Edit(id, uname, pwd);
			
			//Details section
			expected = "partner id " + id;
			actual = connection.getConnectionDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "username " + uname;
			actual = connection.getConnectionDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			expected = "password " + pwd;
			actual = connection.getConnectionDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			
			//Step 4- editing information again
			Reporter.log("Step 4- editing information again");
			
			connection.marketing_Edit(partnerid, username, password);
			
			//Details section
			expected = "partner id " + partnerid;
			actual = connection.getConnectionDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "username " + username;
			actual = connection.getConnectionDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			expected = "password " + password;
			actual = connection.getConnectionDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	
	/*Test 2 : Verify that user can edit connection settings for SMS successfully*/
	@Test (priority = 2)
	public void connectionsettings_SMSUpdate() throws IOException
	{
		try 
		{	
		
			//Step 1- On connection settings page, editing information for SMS section
			Reporter.log("Step 1 - On connection settings page, editing information for SMS section");

			String uname="sherellietest11";
			String pwd="surebiz1";
			connection.sms_Edit(uname, pwd);
			
			//Details section
			
			expected = "username: " + uname;
			actual = connection.getConnectionDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "password: " + pwd;
			actual = connection.getConnectionDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			
			//Step 2- editing information again
			Reporter.log("Step 2- editing information again");
			
			uname="sherellietest22";
			pwd="surebiz22";
			connection.sms_Edit(uname, pwd);
			
			//Details section
			
			expected = "username: " + uname;
			actual = connection.getConnectionDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "password: " + pwd;
			actual = connection.getConnectionDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	
	@AfterClass
	public void tearDown() throws Exception
	{
		drivermanager.tearDown();
	}  
	
}
