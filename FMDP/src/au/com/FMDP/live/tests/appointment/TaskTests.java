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

import au.com.FMDP.live.functionLibrary.appointment.TaskAddLib;
import au.com.FMDP.live.functionLibrary.appointment.TaskSearchLib;
import au.com.FMDP.live.functionLibrary.common.CommonLib;
import au.com.FMDP.live.functionLibrary.common.TopNavigationLib;
import au.com.FMDP.live.functionLibrary.login.LoginLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class TaskTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	private CommonLib common;
	private JavaHelpers javahelp;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	private TaskAddLib taskadd;
	private TaskSearchLib tasksearch;
	private SeleniumHelpers selenium;
	
	//Test Data
	private String timeStamp;
	private String name;
	private String status;
	private String description;
	private String defaultduration;
	

	@BeforeClass
	@Parameters({"node", "browser"})
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception
	{
		drivermanager = new WebDriverManager();
		driver = drivermanager.setUp(node,browser); 
		loginlib = new LoginLib(driver);
		navigationlib = new TopNavigationLib(driver);
		common = new CommonLib(driver);
		javahelp = new JavaHelpers();
		taskadd = new TaskAddLib(driver);
		tasksearch = new TaskSearchLib(driver);
		selenium = new SeleniumHelpers(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		timeStamp = javahelp.timeStamp();
		ex = new ExceptionHandler(driver);
	}
	
	/*Test 1 : Verify that user can add task successfully*/
	@Test (priority = 1)
	public void task_Create() throws IOException
	{
		try 
		{	
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to Appointment book Setup > tasks page
			Reporter.log("Step 2- Opening Menu & Navigating to Appointment book Setup > tasks page");
			navigationlib.clickOnMenuItem("Appointment book setup");
			navigationlib.menu_ClickOnTasksTile();
			
			//Step 3- On task setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 3- On task setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			name = "AT" + timeStamp;
			status = "Active";
			description = "Test description " + timeStamp;
			defaultduration = "30";
			taskadd.task_Add(name, status, description, defaultduration);
			
			//Step 4 = Searching for added task and verifying that it has correct details
			Reporter.log("Step 4 = Searching for added task and verifying that it has correct details");
			tasksearch.search_SearchTaskAndClick(name);
			
			//Name section
			expected = "name " + name;
			actual = tasksearch.getNameSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "status " + status;
			actual = tasksearch.getNameSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			
			//Details section
			expected = "description " + description;
			actual = tasksearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "default duration " + defaultduration + " Minutes";
			actual = tasksearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 2 : Verify that user can edit task successfully*/
	@Test (priority = 2, dependsOnMethods = { "task_Create" })
	public void task_Update() throws IOException
	{
		try 
		{	
			selenium.refreshPage();
			
			//Step 1- On task setup page, editing added item
			Reporter.log("Step 1- On task setup page, editing added item");
			tasksearch.search_SearchTaskAndClick(name);
			tasksearch.clickOnEditIcon();
			name = "AT" + timeStamp;
			status = "Active";
			description = "Test description " + timeStamp;
			defaultduration = "60";
			taskadd.task_Add(name, status, description, defaultduration);
			
			//Step 2 = Searching for updated task and verifying that it has correct details
			Reporter.log("Step 2 = Searching for updated task and verifying that it has correct details");
			tasksearch.search_SearchTaskAndClick(name);
			
			//Name section
			expected = "name " + name;
			actual = tasksearch.getNameSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "status " + status;
			actual = tasksearch.getNameSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			
			//Details section
			expected = "description " + description;
			actual = tasksearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "default duration " + defaultduration + " Minutes";
			actual = tasksearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 3 : Verify that user can inactive task successfully*/
	@Test (priority=3, dependsOnMethods={"task_Create"})
	public  void task_Delete() throws IOException
	{
		try 
		{	
			selenium.refreshPage();
			
			//Step 1 = Searching for added task and inactivating task
			Reporter.log("Step 1 = Searching for added task and inactivating task");
			tasksearch.search_SearchTaskAndClick(name);
			
			tasksearch.clickOnEditIcon();
			status = "Inactive";
			taskadd.task_changeStatus(status);
			
			//Step 2 = Verify that task is inactive
			Reporter.log("Step 2 = Verify that task is inactive");
			
			String partialname= name.substring((name.length()/2),name.length());
			tasksearch.search_SearchTask(partialname);
			
			//verifying that 'There are no records to display' text displayed
			expected = "There are no records to display";
			actual = tasksearch.search_GetNoRecordMsgText();
			sfassert.assertEquals(actual, expected);		
			
			//Step 3 = On Search filter, select Inactive and performing search again
			Reporter.log("Step 3 = On Search filter, select Inactive");
			tasksearch.search_SearchTask("");
			tasksearch.search_Filter_ClickOnInactiveCheckbox();
			
			//Step 4 = Searching for inactive task and verifying that it has correct details
			Reporter.log("Step 4 = Searching for inactive task and verifying that it has correct details");
			tasksearch.search_SearchTaskAndClick(partialname);
			
			//Name section
			expected = "name " + name;
			actual = tasksearch.getNameSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "status " + status;
			actual = tasksearch.getNameSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			
			//Details section
			expected = "description " + description;
			actual = tasksearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "default duration " + defaultduration + " Minutes";
			actual = tasksearch.getDetailsSectionData(2);
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
