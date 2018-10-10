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

import au.com.FMDP.live.functionLibrary.common.CommonLib;
import au.com.FMDP.live.functionLibrary.common.TopNavigationLib;
import au.com.FMDP.live.functionLibrary.login.LoginLib;
import au.com.FMDP.live.functionLibrary.rosters.RosterActivitySearchLib;
import au.com.FMDP.live.functionLibrary.rosters.RosterActivitySetupLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class RostersActivityTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	private CommonLib common;
	private SeleniumHelpers selenium;
	private JavaHelpers javahelp;
	private RosterActivitySetupLib rosteractivityadd ;
	private RosterActivitySearchLib  rosteractivitysearch ;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	
	//Test Data
	private String timeStamp;
	private String propertyFile="test-input/roster.properties";
	private String name_create = JavaHelpers.getPropertyValue(propertyFile,"name_create");
	private String name;
	private String status_create = JavaHelpers.getPropertyValue(propertyFile,"status_create");
	private String colorposition_create = JavaHelpers.getPropertyValue(propertyFile,"colorposition_create");
	private String colorstyle_create = JavaHelpers.getPropertyValue(propertyFile,"colorstyle_create");
	private String notes_create = JavaHelpers.getPropertyValue(propertyFile,"notes_create");
	private String bookable_create = JavaHelpers.getPropertyValue(propertyFile,"bookable_create");
	private String payable_create = JavaHelpers.getPropertyValue(propertyFile,"payable_create");
	private String walkins_create = JavaHelpers.getPropertyValue(propertyFile,"walkins_create");
	private String colorposition_update = JavaHelpers.getPropertyValue(propertyFile,"colorposition_update");
	private String colorstyle_update = JavaHelpers.getPropertyValue(propertyFile,"colorstyle_update");
	private String bookable_update = JavaHelpers.getPropertyValue(propertyFile,"bookable_update");
	private String payable_update = JavaHelpers.getPropertyValue(propertyFile,"payable_update");
	private String walkins_update = JavaHelpers.getPropertyValue(propertyFile,"walkins_update");
	

	@BeforeClass
	@Parameters({"node", "browser"})
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception
	{
		drivermanager = new WebDriverManager();
		driver = drivermanager.setUp(node,browser); 
		loginlib = new LoginLib(driver);
		navigationlib = new TopNavigationLib(driver);
		common = new CommonLib(driver);
		selenium = new SeleniumHelpers(driver);
		javahelp = new JavaHelpers();
		rosteractivityadd = new RosterActivitySetupLib(driver);
		rosteractivitysearch = new RosterActivitySearchLib(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		timeStamp = javahelp.timeStamp();
		ex = new ExceptionHandler(driver);
	}
	
	/*Test 1 : Verify that user can add roster activity successfully*/
	@Test (priority = 1)
	public void rosterActivity_Create() throws IOException
	{
		try 
		{	
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to Employee and rosters > Roster activities page
			Reporter.log("Step 2 = Opening Menu & Navigating to Employee and rosters > Roster activities page");
			navigationlib.clickOnMenuItem("Employees and rosters");
			navigationlib.menu_ClickOnRosterActivitiesTile();
			
			//Step 3- On Roster activity setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 3 = On Roster activity setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			name = name_create + timeStamp;
			rosteractivityadd.rosterActivity_Add(name, status_create, colorposition_create, notes_create,bookable_create,payable_create,walkins_create);
			
			//Step 4 = Searching for added activity and verifying that it has correct details
			Reporter.log("Step 4 = Searching for added activity and verifying that it has correct details");
			rosteractivitysearch.search_SearchActivityAndClick(name);
			
			//Verifying name
			expected = "name " + name;
			actual = rosteractivitysearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = rosteractivitysearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying notes
			expected = "notes " + notes_create;
			actual = rosteractivitysearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			//Verifying bookable
			expected = "bookable " + bookable_create;
			actual = rosteractivitysearch.getDetailsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			//Verifying payable
			expected = "payable " + payable_create;
			actual = rosteractivitysearch.getDetailsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			//Verifying available for walk-ins
			expected = "available for walk-ins " + walkins_create;
			actual = rosteractivitysearch.getDetailsSectionData(7);
			sfassert.assertEquals(actual, expected);
			
			//Verifying color
			expected = colorstyle_create;
			actual = rosteractivitysearch.getDetailsSectionData_ColorStyle();
			sfassert.assertEquals(actual, expected);
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	
	/*Test 2 : Verify that user can edit roster activity successfully*/
	@Test (priority = 2, dependsOnMethods = { "rosterActivity_Create" })
	public void rosterActivity_Update() throws IOException
	{
		try 
		{
			//Step 1 = Editing added roster activity
			Reporter.log("Step 1 = Editing added roster activity");
			
			selenium.refreshPage();
			common.waitTillPageLoaded();
			rosteractivitysearch.search_SearchActivityAndClick(name);		
			rosteractivitysearch.clickOnEditIcon();
			name = name_create + timeStamp;
			notes_create = notes_create + " edited";
			rosteractivityadd.rosterActivity_Add(name, status_create, colorposition_update, notes_create,bookable_update,payable_update,walkins_update);
			
			//Step 2 = Searching for edited activity and verifying that it has correct details
			Reporter.log("Step 2 = Searching for edited activity and verifying that it has correct details");
			rosteractivitysearch.search_SearchActivityAndClick(name);
			
			//Verifying name
			expected = "name " + name;
			actual = rosteractivitysearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = rosteractivitysearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying notes
			expected = "notes " + notes_create;
			actual = rosteractivitysearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			//Verifying bookable
			expected = "bookable " + bookable_update;
			actual = rosteractivitysearch.getDetailsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			//Verifying payable
			expected = "payable " + payable_update;
			actual = rosteractivitysearch.getDetailsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			//Verifying available for walk-ins
			expected = "available for walk-ins " + walkins_update;
			actual = rosteractivitysearch.getDetailsSectionData(7);
			sfassert.assertEquals(actual, expected);
			
			//Verifying color
			expected = colorstyle_update;
			actual = rosteractivitysearch.getDetailsSectionData_ColorStyle();
			sfassert.assertEquals(actual, expected);
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 3 : Verify that user can inactive roster activity successfully*/
	@Test (priority=3, dependsOnMethods={"rosterActivity_Create"})
	public  void rosterActivity_Delete() throws IOException
	{
		try 
		{	
			//Step 1 = Marking added roster activity as Inactive
			Reporter.log("Step 1 = Marking added roster activity as Inactive");
			selenium.refreshPage();
			common.waitTillPageLoaded();
			rosteractivitysearch.search_SearchActivityAndClick(name);
			rosteractivitysearch.clickOnEditIcon();
			status_create = "Inactive";
			rosteractivityadd.rosterActivity_ChangeStatus(status_create);
			common.footer_ClickOnDone();
			selenium.refreshPage();
			
			//Step 2 = Verify that activity is inactive
			Reporter.log("Step 2 = Verify that activity is inactive");
			
			String partialname= name.substring((name.length()/2),name.length());
			rosteractivitysearch.search_SearchActivity(partialname);
			
			//verifying that 'There are no records to display' text displayed
			expected = "There are no records to display";
			actual = rosteractivitysearch.search_GetNoRecordMsgText();
			sfassert.assertEquals(actual, expected);		
			
			//Step 3 = On Search filter, select Inactive and performing search again
			Reporter.log("Step 3 = On Search filter, select Inactive");
			rosteractivitysearch.search_SearchActivity("");
			rosteractivitysearch.search_Filter_ClickOnInactiveCheckbox();
			
			//Step 4 = Searching for inactive activity and verifying that it has correct details
			Reporter.log("Step 4 = Searching for inactive activity and verifying that it has correct details");
			rosteractivitysearch.search_SearchActivityAndClick(partialname);
			
			//Verifying name
			expected = "name " + name;
			actual = rosteractivitysearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = rosteractivitysearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying notes
			expected = "notes " + notes_create;
			actual = rosteractivitysearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			//Verifying bookable
			expected = "bookable " + bookable_update;
			actual = rosteractivitysearch.getDetailsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			//Verifying payable
			expected = "payable " + payable_update;
			actual = rosteractivitysearch.getDetailsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			//Verifying available for walk-ins
			expected = "available for walk-ins " + walkins_update;
			actual = rosteractivitysearch.getDetailsSectionData(7);
			sfassert.assertEquals(actual, expected);
			
			//Verifying color
			expected = colorstyle_update;
			actual = rosteractivitysearch.getDetailsSectionData_ColorStyle();
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
