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

import au.com.FMDP.live.functionLibrary.appointment.AppointmentBookLib;
import au.com.FMDP.live.functionLibrary.appointment.AppointmentSetupLib;
import au.com.FMDP.live.functionLibrary.common.TopNavigationLib;
import au.com.FMDP.live.functionLibrary.login.LoginLib;
import au.com.FMDP.live.functionLibrary.rosters.RosterLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class AppointmentBookTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	private JavaHelpers javahelp;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	private SeleniumHelpers selenium;
	private AppointmentSetupLib aptsetup;
	private AppointmentBookLib aptbook;
	private RosterLib roster;
	private String interval;
	private String firsttimeonbookingslot;
	private String expectednexttimeslot;
	private String expectednexttimeslot_min;
	private String actualtimeslot;
	private String is_appointment_creation_with_no_roster_enabled;
	

	@BeforeClass
	@Parameters({"node", "browser"})
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception
	{
		
		drivermanager = new WebDriverManager();
		driver = drivermanager.setUp(node,browser); 
		loginlib = new LoginLib(driver);
		navigationlib = new TopNavigationLib(driver);
		javahelp = new JavaHelpers();		
		aptsetup = new AppointmentSetupLib(driver);
		aptbook = new AppointmentBookLib(driver);
		roster = new RosterLib(driver);
		selenium = new SeleniumHelpers(driver);
		
		//Pre-requisite : Navigating and Logging in to Live Application
		Reporter.log("Pre-requisite : Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
		loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		ex = new ExceptionHandler(driver);
	}
	
	/*Test 1 : Appointment Book > Verify that timeslots are displayed as per 40 mins booking interval on appointment setup page*/
	@Test (priority = 1)
	public void appointmentbook_Verify40MinsBookingInterval() throws IOException
	{
		try 
		{	
			
			//Step 1- Opening Menu & Navigating to Appointment book Setup > appointment setup page
			Reporter.log("Step 1- Opening Menu & Navigating to Appointment book Setup > appointment setup page");
			navigationlib.clickOnMenuItem("Appointment book setup");
			navigationlib.menu_ClickOnAppointmentSetupTile();
			
			//Step 2- On appointment setup page, setting booking interval to 40 mins
			Reporter.log("Step 2- On appointment setup page, setting booking interval to 40 mins");
			aptsetup.clickOnEditIcon();
			interval = "40";
			aptsetup.appointmentsetup_ChangeBookingIntervalAndClickOnDone(interval);
			
			expected = "booking interval " + interval + " Minutes";
			actual = aptsetup.getAppointmentsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			//Step 3- Opening Menu & Navigating to Operations > appointment book page
			Reporter.log("Step 3- Opening Menu & Navigating to Operations > appointment book page");
			navigationlib.clickOnMenuItem("Operations");
			navigationlib.menu_ClickOnAppointmentBookTile();
			
			//Step 4- Verifying that on appointment book > timeslots are correctly displayed i.e. 40 mins apart
			Reporter.log("Step 4- Verifying that on appointment book > timeslots are correctly displayed i.e. 40 mins apart");
			
			firsttimeonbookingslot=aptbook.getNthTimeslot(1).split(" ")[0];
			
			expectednexttimeslot = javahelp.addMinutesToTime(firsttimeonbookingslot,Integer.parseInt(interval));
			actualtimeslot=aptbook.getNthTimeslot(2).split(" ")[0];
			
			sfassert.assertEquals(actualtimeslot, expectednexttimeslot);
			
			expectednexttimeslot = javahelp.addMinutesToTime(actualtimeslot,Integer.parseInt(interval));
			actualtimeslot=aptbook.getNthTimeslot(3).split(" ")[0];
			
			sfassert.assertEquals(actualtimeslot, expectednexttimeslot);
			
			//soft assert
			sfassert.assertAll();

		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	
	/*Test 2 : Appointment Book > Verify that timeslots are displayed as per 30 mins booking interval on appointment setup page*/
	@Test (priority = 2)
	public void appointmentbook_Verify30MinsBookingInterval() throws IOException
	{
		try 
		{	
		
			//Step 1- On appointment setup page, setting booking interval to 30 mins
			Reporter.log("Step 1- On appointment setup page, setting booking interval to 30 mins");
			navigationlib.clickOnMenuItem("Appointment book setup");
			navigationlib.menu_ClickOnAppointmentSetupTile();
			aptsetup.clickOnEditIcon();
			interval = "30";
			aptsetup.appointmentsetup_ChangeBookingIntervalAndClickOnDone(interval);
			
			//Step 2- Opening Menu & Navigating to Operations > appointment book page
			Reporter.log("Step 2- Opening Menu & Navigating to Operations > appointment book page");
			navigationlib.clickOnMenuItem("Operations");
			navigationlib.menu_ClickOnAppointmentBookTile();
			
			//Step 3- Verifying that on appointment book > timeslots are correctly displayed i.e. 30 mins apart
			Reporter.log("Step 3- Verifying that on appointment book > timeslots are correctly displayed i.e. 30 mins apart");
			
			firsttimeonbookingslot=aptbook.getNthTimeslot(1).split(" ")[0];
			expectednexttimeslot = javahelp.addMinutesToTime(firsttimeonbookingslot,Integer.parseInt(interval));
			expectednexttimeslot_min = expectednexttimeslot.split(":")[1];
			
			actualtimeslot=aptbook.getNthTimeslot(2).split(" ")[0];
			sfassert.assertEquals(actualtimeslot, expectednexttimeslot_min);
			
			expectednexttimeslot = javahelp.addMinutesToTime(expectednexttimeslot,Integer.parseInt(interval));
			actualtimeslot=aptbook.getNthTimeslot(3).split(" ")[0];
			
			sfassert.assertEquals(actualtimeslot, expectednexttimeslot);
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	
	/*Test 3 : Appointment book setup > Verify 'Allow appointments to be created on a day with no roster entries' option functionality when set to No & roster isn't setup */
	@Test (priority = 3)
	public void appointmentbooksetup_VerifyWhenAllowAppointmentsToBeCreatedOnDayWithNoRosterSetToNoAndRosterNotSetup() throws IOException
	{
		try 
		{	
		
			//Step 2 - Opening Menu & Navigating to Employees and rosters > roster page
			Reporter.log("Step 2 - Opening Menu & Navigating to Employees and rosters > roster page");
			navigationlib.clickOnMenuItem("Employees and rosters");
			navigationlib.menu_ClickOnRosterTile();
			
			//Step 3 - Deleting all added rosters
			Reporter.log("Step 3 - Deleting all added rosters");
			roster.DeleteAllRosters();
			
			//Step 4- Opening Menu & Navigating to Appointment book Setup > appointment setup page
			Reporter.log("Step 4- Opening Menu & Navigating to Appointment book Setup > appointment setup page");
			navigationlib.clickOnMenuItem("Appointment book setup");
			navigationlib.menu_ClickOnAppointmentSetupTile();
			
			//Step 5- On appointment setup page, set Allow appointments to be created on a day with no roster entries = No
			Reporter.log("Step 5- On appointment setup page, set Allow appointments to be created on a day with no roster entries = No");
			aptsetup.clickOnEditIcon();
			is_appointment_creation_with_no_roster_enabled= "No";
			aptsetup.appointmentsetup_setIsAppointmentCreationWithNoRosterEnabledAndClickOnDone(is_appointment_creation_with_no_roster_enabled);
			expected = "allow appointments to be created on a day with no roster entries " + is_appointment_creation_with_no_roster_enabled ;
			actual = aptsetup.getAppointmentsSectionData(9);
			sfassert.assertEquals(actual, expected);
			
			//Step 6- Opening Menu & Navigating to Operations > appointment book page
			Reporter.log("Step 6- Opening Menu & Navigating to Operations > appointment book page");
			navigationlib.clickOnMenuItem("Operations");
			navigationlib.menu_ClickOnAppointmentBookTile();
			
			//Step 7- Verifying that 'No roster has been entered' message displayed on appointment book
			Reporter.log("Step 7- Verifying that 'No roster has been entered' message displayed on appointment book");
			
			expected = "No roster has been entered";
			actual = aptbook.getAppointmentBookMessage();
			sfassert.assertEquals(actual, expected);
			
			//Step 8- Verifying that hours aren't displayed on appointment book
			Reporter.log("Step 4- Verifying that hours aren't displayed on appointment book");
			sfassert.assertEquals(aptbook.isHourDisplayedOnGrid(), false);
			

			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	

	/*Test 4 : Appointment book setup > Verify 'Allow appointments to be created on a day with no roster entries' option functionality when set to yes & roster isn't setup */
	@Test (priority = 4,dependsOnMethods = { "appointmentbooksetup_VerifyWhenAllowAppointmentsToBeCreatedOnDayWithNoRosterSetToNoAndRosterNotSetup"})
	public void appointmentbooksetup_VerifyWhenAllowAppointmentsToBeCreatedOnDayWithNoRosterSetToYesAndRosterNotSetup() throws IOException
	{
		try 
		{	
		
			//Step 1- On appointment setup page, set Allow appointments to be created on a day with no roster entries = Yes
			Reporter.log("Step 1- On appointment setup page, set Allow appointments to be created on a day with no roster entries = Yes");
			navigationlib.clickOnMenuItem("Appointment book setup");
			navigationlib.menu_ClickOnAppointmentSetupTile();
			aptsetup.clickOnEditIcon();
			is_appointment_creation_with_no_roster_enabled = "Yes";
			aptsetup.appointmentsetup_setIsAppointmentCreationWithNoRosterEnabledAndClickOnDone(is_appointment_creation_with_no_roster_enabled);
			selenium.refreshPage();
			expected = "allow appointments to be created on a day with no roster entries " + is_appointment_creation_with_no_roster_enabled ;
			actual = aptsetup.getAppointmentsSectionData(9);
			sfassert.assertEquals(actual, expected);
			
			//Step 2- Opening Menu & Navigating to Operations > appointment book page
			Reporter.log("Step 2- Opening Menu & Navigating to Operations > appointment book page");
			navigationlib.clickOnMenuItem("Operations");
			navigationlib.menu_ClickOnAppointmentBookTile();
			
			//Step 3- Verifying that 'No roster has been entered' message isn't displayed on appointment book
			Reporter.log("Step 3- Verifying that 'No roster has been entered' message isn't displayed on appointment book");
			expected = "";
			try 
			{
				actual = aptbook.getAppointmentBookMessage();
			} 
			catch (Exception e) 
			{
				actual="";
			}
			sfassert.assertEquals(actual, expected);
			
			//Step 4- Verifying that hours are displayed on appointment book
			Reporter.log("Step 4- Verifying that hours are displayed on appointment book");
			sfassert.assertEquals(aptbook.isHourDisplayedOnGrid(), true);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	
	/*Test 5 : Appointment book setup > Verify 'Allow appointments to be created on a day with no roster entries' option functionality when set to yes & roster is setup */
	@Test (priority = 5,dependsOnMethods = { "appointmentbooksetup_VerifyWhenAllowAppointmentsToBeCreatedOnDayWithNoRosterSetToYesAndRosterNotSetup"})
	public void appointmentbooksetup_VerifyWhenAllowAppointmentsToBeCreatedOnDayWithNoRosterSetToYesAndRosterSetup() throws IOException
	{
		try 
		{	
		
			//Step 1- On Roster page, adding roster for today 
			Reporter.log("Step 1- On Roster page, adding roster for today ");
			navigationlib.clickOnMenuItem("Employees and rosters");
			navigationlib.menu_ClickOnRosterTile();
			roster.addRosterForToday();
			
			//Step 2- Opening Menu & Navigating to Operations > appointment book page
			Reporter.log("Step 2- Opening Menu & Navigating to Operations > appointment book page");
			navigationlib.clickOnMenuItem("Operations");
			navigationlib.menu_ClickOnAppointmentBookTile();
			
			//Step 3- Verifying that 'No roster has been entered' message isn't displayed on appointment book
			Reporter.log("Step 3- Verifying that 'No roster has been entered' message isn't displayed on appointment book");
			expected = "";
			try 
			{
				actual = aptbook.getAppointmentBookMessage();
			} 
			catch (Exception e) 
			{
				actual="";
			}
			sfassert.assertEquals(actual, expected);
			
			//Step 4- Verifying that hours are displayed on appointment book
			Reporter.log("Step 4- Verifying that hours are displayed on appointment book");
			sfassert.assertEquals(aptbook.isHourDisplayedOnGrid(), true);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	
	/*Test 6 : Appointment book setup > Verify 'Allow appointments to be created on a day with no roster entries' option functionality when set to no & roster is setup */
	@Test (priority = 6,dependsOnMethods = { "appointmentbooksetup_VerifyWhenAllowAppointmentsToBeCreatedOnDayWithNoRosterSetToYesAndRosterSetup"})
	public void appointmentbooksetup_VerifyWhenAllowAppointmentsToBeCreatedOnDayWithNoRosterSetToNoAndRosterSetup() throws IOException
	{
		try 
		{	
			//Step 1- Opening Menu & Navigating to Appointment book Setup > appointment setup page
			Reporter.log("Step 1- Opening Menu & Navigating to Appointment book Setup > appointment setup page");
			navigationlib.clickOnMenuItem("Appointment book setup");
			navigationlib.menu_ClickOnAppointmentSetupTile();
			
			//Step 2- On appointment setup page, set Allow appointments to be created on a day with no roster entries = No
			Reporter.log("Step 2- On appointment setup page, set Allow appointments to be created on a day with no roster entries = No");
			aptsetup.clickOnEditIcon();
			is_appointment_creation_with_no_roster_enabled= "No";
			aptsetup.appointmentsetup_setIsAppointmentCreationWithNoRosterEnabledAndClickOnDone(is_appointment_creation_with_no_roster_enabled);
			selenium.refreshPage();
			expected = "allow appointments to be created on a day with no roster entries " + is_appointment_creation_with_no_roster_enabled ;
			actual = aptsetup.getAppointmentsSectionData(9);
			sfassert.assertEquals(actual, expected);
			
			//Step 3- Opening Menu & Navigating to Operations > appointment book page
			Reporter.log("Step 3- Opening Menu & Navigating to Operations > appointment book page");
			navigationlib.clickOnMenuItem("Operations");
			navigationlib.menu_ClickOnAppointmentBookTile();
			
			//Step 4- Verifying that 'No roster has been entered' message isn't displayed on appointment book
			Reporter.log("Step 4- Verifying that 'No roster has been entered' message isn't displayed on appointment book");
			expected = "";
			try 
			{
				actual = aptbook.getAppointmentBookMessage();
			} 
			catch (Exception e) 
			{
				actual="";
			}
			sfassert.assertEquals(actual, expected);
			
			//Step 5- Verifying that hours are displayed on appointment book
			Reporter.log("Step 5- Verifying that hours are displayed on appointment book");
			sfassert.assertEquals(aptbook.isHourDisplayedOnGrid(), true);
			
			//resetting value for 'Allow appointments to be created on a day with no roster entries = Yes 
			navigationlib.clickOnMenuItem("Appointment book setup");
			navigationlib.menu_ClickOnAppointmentSetupTile();
			aptsetup.clickOnEditIcon();
			is_appointment_creation_with_no_roster_enabled= "Yes";
			aptsetup.appointmentsetup_setIsAppointmentCreationWithNoRosterEnabledAndClickOnDone(is_appointment_creation_with_no_roster_enabled);
		
			
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
