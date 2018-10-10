package au.com.shortcuts.live.tests.rosters;
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
import au.com.FMDP.live.functionLibrary.rosters.RosterLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class RostersTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	private SeleniumHelpers selenium;
	private RosterLib roster;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	
	private String propertyFile="test-input/roster.properties";
	private String starttime_create = JavaHelpers.getPropertyValue(propertyFile,"starttime_create");
	private String endtime_create = JavaHelpers.getPropertyValue(propertyFile,"endtime_create");
	private String activity_create = JavaHelpers.getPropertyValue(propertyFile,"activity_create");
	private String break_create = JavaHelpers.getPropertyValue(propertyFile,"break_create");
	private String starttime_edit = JavaHelpers.getPropertyValue(propertyFile,"starttime_edit");
	private String endtime_edit = JavaHelpers.getPropertyValue(propertyFile,"endtime_edit");
	private String activity_edit = JavaHelpers.getPropertyValue(propertyFile,"activity_edit");
	private String break_edit = JavaHelpers.getPropertyValue(propertyFile,"break_edit");

	@BeforeClass
	@Parameters({"node", "browser"})
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception
	{
		drivermanager = new WebDriverManager();
		driver = drivermanager.setUp(node,browser); 
		loginlib = new LoginLib(driver);
		navigationlib = new TopNavigationLib(driver);
		selenium = new SeleniumHelpers(driver);
		roster = new RosterLib(driver);
		
		//Pre-requisite : Navigating and Logging in to Live Application
		Reporter.log("Pre-requisite : Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
		loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME1, Constants.LIVE_PASSWORD1);
		
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		ex = new ExceptionHandler(driver);
	}
	
	/*Test 1 : Roster > Verify roster entry can be created for an employee*/
	@Test (priority = 1)
	public void roster_Create() throws IOException
	{
		try 
		{	
			//Step 1 - Opening Menu & Navigating to Employees and rosters > roster page
			Reporter.log("Step 1 - Opening Menu & Navigating to Employees and rosters > roster page");
			navigationlib.clickOnMenuItem("Employees and rosters");
			navigationlib.menu_ClickOnRosterTile();
			roster.DeleteAllRosters();
			selenium.refreshPage();
			
			//Step 2 - Adding roster for today for an employee
			Reporter.log("Step 2 - Adding roster for today for an employee");
			roster.addRosterForToday(starttime_create, endtime_create, activity_create, break_create);
			selenium.refreshPage();
			
			//Step 3 - Verify hours details for added roster 
			Reporter.log("Step 3 - Verify hours details for added roster ");
			
				//Hours info
				expected = starttime_create + " - " + endtime_create;
				actual = roster.GetRosterCellText();
				sfassert.assertEquals(true, actual.equalsIgnoreCase(expected),"Expected " + expected + " Actual = " + actual);
				
			//Step 4 - For added roster, opening roster sidebar & verifying Activity, Duration.
			Reporter.log("Step 4 - For added roster, opening roster sidebar & verifying Activity, Duration. ");
			
				roster.clickOnRosterItem();
				
				//Activity info
				expected = activity_create;
				actual = roster.getselectedActivity();
				sfassert.assertEquals(actual, expected);
				
				//Break duration info
				expected = break_create;
				actual = roster.getselectedBreakDuration();
				sfassert.assertEquals(actual, expected);
				
		
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
	}
		
	/*Test 2 : Roster > Verify roster entry can be edited for an employee*/
	@Test (priority = 2,  dependsOnMethods = { "roster_Create" })
	public void roster_Edit() throws IOException
	{
		try 
		{
			//Step 1 - Editing roster for today for an employee
			Reporter.log("Step 1 - Editing roster for today for an employee");
			roster.editRosterForToday(starttime_edit, endtime_edit, activity_edit, break_edit);
			selenium.refreshPage();
			
			//Step 2 - Verify hours details for added roster 
			Reporter.log("Step 2 - Verify hours details for added roster ");
			
				//Hours info
				expected = starttime_edit + " - " + endtime_edit;
				actual = roster.GetRosterCellText();
				sfassert.assertEquals(true, actual.equalsIgnoreCase(expected),"Expected " + expected + " Actual = " + actual);
				
			//Step 3 - For edited roster, opening roster sidebar & verifying Activity, Duration.
			Reporter.log("Step 3 - For edited roster, opening roster sidebar & verifying Activity, Duration.");
				roster.clickOnRosterItem();
				
				//Activity info
				expected = activity_edit;
				actual = roster.getselectedActivity();
				sfassert.assertEquals(actual, expected);
				
				//Break duration info
				expected = break_edit;
				actual = roster.getselectedBreakDuration();
				sfassert.assertEquals(actual, expected);
				
		
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
	}
		
	/*Test 3 : Roster > Verify roster entry can be deleted for an employee*/
	@Test (priority = 3,  dependsOnMethods = { "roster_Create" })
	public void roster_Delete() throws IOException
	{
		try 
		{
			//Step 1 - deleting roster for today for an employee
			Reporter.log("Step 1 - deleting roster for today for an employee");			
			roster.clickOnDeletebutton();
			
			//Step 2 - verify that no roster is available on roster page
			Reporter.log("Step 2 - verify that no roster is available on roster page");
			
			//Activity info
			int expected = 0;
			int actual = roster.getTotalRosterItemsCount();
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
