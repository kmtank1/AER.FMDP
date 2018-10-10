package au.com.shortcuts.live.tests.appointment;

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

import au.com.FMDP.live.functionLibrary.appointment.ContactSetupLib;
import au.com.FMDP.live.functionLibrary.common.TopNavigationLib;
import au.com.FMDP.live.functionLibrary.login.LoginLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class ContactSetupTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	private ContactSetupLib contact;
	private JavaHelpers javahelp;
	private String timeStamp;
	private SeleniumHelpers selenium;
	
	
	@BeforeClass
	@Parameters({"node", "browser"})
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception
	{
		drivermanager = new WebDriverManager();
		driver = drivermanager.setUp(node,browser); 
		loginlib = new LoginLib(driver);
		navigationlib = new TopNavigationLib(driver);
		contact = new ContactSetupLib(driver);
		javahelp = new JavaHelpers();
		selenium = new SeleniumHelpers(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		ex = new ExceptionHandler(driver);
	}
	
	
	/*Test 1 : Verify that user can edit contact setup information successfully*/
	@Test (priority = 1)
	public void contactsetup_Update() throws IOException
	{
		try 
		{	
		
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to Appointment book setup > Contact setup page
			Reporter.log("Step 2 = Opening Menu & Navigating to Appointment book setup > Receipt page");
			navigationlib.clickOnMenuItem("Appointment book setup");
			navigationlib.menu_ClickOnContactSetupTile();
			
			//Step 3- On Contact setup page, editing information
			Reporter.log("On Contact setup page, editing information");
			contact.clickOnEditIcon();
			
			timeStamp = javahelp.timeStamp();
			String remindermessage = "This is a custom message by AT " + timeStamp;
			String insertfield= "Customer First Name";
			String confirmtext="yes" + timeStamp.substring(timeStamp.length()-5);
			String canceltext= "no" + timeStamp.substring(timeStamp.length()-5);
			
			
			contact.contactsetup_Edit(remindermessage, insertfield, confirmtext, canceltext);
			selenium.refreshPage();
			
			//Details section
			
			expected = "reminder method SMS";
			actual = contact.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "status Active";
			actual = contact.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			
			
			//Option section
			
			expected = "reminder message " + remindermessage + "{{" + insertfield + "}}";
			actual = contact.getTemplateSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			int expectedcharlength = (remindermessage + insertfield).toString().length() + 1;
			expected = "characters count " + expectedcharlength;
			actual = contact.getTemplateSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "messages count 1";
			actual = contact.getTemplateSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			

			//Reply phrases section
			
			expected = "confirm " + confirmtext;
			actual = contact.getReplyPhrasesSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "cancel " + canceltext;
			actual = contact.getReplyPhrasesSectionData(2);
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
