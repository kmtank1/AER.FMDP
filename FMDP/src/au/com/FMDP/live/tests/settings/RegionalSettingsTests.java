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
import au.com.FMDP.live.functionLibrary.settings.RegionalSettingsLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class RegionalSettingsTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	private RegionalSettingsLib regional;
	private JavaHelpers javahelp;
	private String timeStamp;
	
	
	@BeforeClass
	@Parameters({"node", "browser"})
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception
	{
		drivermanager = new WebDriverManager();
		driver = drivermanager.setUp(node,browser); 
		loginlib = new LoginLib(driver);
		navigationlib = new TopNavigationLib(driver);
		regional = new RegionalSettingsLib(driver);
		javahelp = new JavaHelpers();
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		ex = new ExceptionHandler(driver);
	}
	
	
	/*Test 1 : Verify that user can edit regional settings information successfully*/
	@Test (priority = 1)
	public void regionalsettings_Update() throws IOException
	{
		try 
		{	
		
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to General Settings > Regional Settings page
			Reporter.log("Step 2 - Opening Menu & Navigating to General Settings > Regional Settings page");
			navigationlib.clickOnMenuItem("General settings");
			navigationlib.menu_ClickOnRegionalSettingsTile();
			
			//Step 3- On regional settings page, editing information
			Reporter.log("Step 3- On regional settings page, editing information");
			regional.clickOnEditIcon();
			
			timeStamp = javahelp.timeStamp();
			
			String shorttaxvalue = "GST" + timeStamp;
			String longtaxvalue= "LT" + timeStamp;
			String taxvalue = "ABN" + timeStamp;
			String statevalue = "Reg" + timeStamp ;
			String postcodevalue = "postcode" + timeStamp;
			String suburbvalue = "sub" + timeStamp;
			String roundingvalue = "0.1";
			String directionvalue = "Up";
			
			regional.receiptsetup_Edit(shorttaxvalue, longtaxvalue, taxvalue, statevalue, postcodevalue, suburbvalue, roundingvalue, directionvalue);
			
			//Details section
			expected = "short tax " + shorttaxvalue;
			actual = regional.getLabelsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "long tax " + longtaxvalue;
			actual = regional.getLabelsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "tax number " + taxvalue;
			actual = regional.getLabelsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			expected = "state " + statevalue;
			actual = regional.getLabelsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "postcode " + postcodevalue;
			actual = regional.getLabelsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			expected = "suburb " + suburbvalue;
			actual = regional.getLabelsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			//Currency section
			expected = "rounding " + roundingvalue;
			actual = regional.getCurrencySectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "direction " + directionvalue;
			actual = regional.getCurrencySectionData(2);
			sfassert.assertEquals(actual, expected);
			
			
			
			//Step 4- editing information again
			Reporter.log("Step 4- editing information again");
			regional.clickOnEditIcon();
			
			timeStamp = javahelp.timeStamp();
			
			shorttaxvalue = "GST";
			longtaxvalue= "LT";
			taxvalue = "ABN";
			statevalue = "Reg";
			postcodevalue = "postcode" ;
			suburbvalue = "sub";
			roundingvalue = "0.05";
			directionvalue = "Nearest";
			
			regional.receiptsetup_Edit(shorttaxvalue, longtaxvalue, taxvalue, statevalue, postcodevalue, suburbvalue, roundingvalue, directionvalue);
			
			//Details section
			expected = "short tax " + shorttaxvalue;
			actual = regional.getLabelsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "long tax " + longtaxvalue;
			actual = regional.getLabelsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "tax number " + taxvalue;
			actual = regional.getLabelsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			expected = "state " + statevalue;
			actual = regional.getLabelsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "postcode " + postcodevalue;
			actual = regional.getLabelsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			expected = "suburb " + suburbvalue;
			actual = regional.getLabelsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			//Currency section
			expected = "rounding " + roundingvalue;
			actual = regional.getCurrencySectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "direction " + directionvalue;
			actual = regional.getCurrencySectionData(2);
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
