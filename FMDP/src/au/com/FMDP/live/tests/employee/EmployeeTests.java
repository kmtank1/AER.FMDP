package au.com.shortcuts.live.tests.employee;

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
import au.com.FMDP.live.functionLibrary.employee.EmployeeAddLib;
import au.com.FMDP.live.functionLibrary.employee.EmployeeSearchLib;
import au.com.FMDP.live.functionLibrary.login.LoginLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class EmployeeTests 
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
	private EmployeeAddLib employeeadd;
	private EmployeeSearchLib employeesearch;
	private SeleniumHelpers selenium;
	
	//Test Data
	private String timeStamp;
	private String propertyFile="test-input/employee.properties";
	private String alias;
	private String alias_create = JavaHelpers.getPropertyValue(propertyFile,"alias_create");
	private String firstname_create = JavaHelpers.getPropertyValue(propertyFile,"firstname_create");
	private String lastname_create = JavaHelpers.getPropertyValue(propertyFile,"lastname_create");
	private String gender_create = JavaHelpers.getPropertyValue(propertyFile,"gender_create");
	private String startdate_create = JavaHelpers.getPropertyValue(propertyFile,"startdate_create");
	private String startdate_create_formated = JavaHelpers.getPropertyValue(propertyFile,"startdate_create_formated");
	private String biography_create = JavaHelpers.getPropertyValue(propertyFile,"biography_create");
	private String status_create = JavaHelpers.getPropertyValue(propertyFile,"status_create");
	private String walkins_create = JavaHelpers.getPropertyValue(propertyFile,"walkins_create");
	private String mobile_create = JavaHelpers.getPropertyValue(propertyFile,"mobile_create");
	private String home_create = JavaHelpers.getPropertyValue(propertyFile,"home_create");
	private String email;
	private String email_create = JavaHelpers.getPropertyValue(propertyFile,"email_create");
	private String street1_create = JavaHelpers.getPropertyValue(propertyFile,"street1_create");
	private String street2_create = JavaHelpers.getPropertyValue(propertyFile,"steeet2_create");
	private String suburb_create = JavaHelpers.getPropertyValue(propertyFile,"suburb_create");
	private String state_create = JavaHelpers.getPropertyValue(propertyFile,"state_create");
	private String pcode_create = JavaHelpers.getPropertyValue(propertyFile,"pcode_create");
	private String interval_create = JavaHelpers.getPropertyValue(propertyFile,"interval_create");
	private String sendconfirmation_create = JavaHelpers.getPropertyValue(propertyFile,"sendconfirmation_create");
	private String showinappointment_create = JavaHelpers.getPropertyValue(propertyFile,"showinappointment_create");
	
	private String gender_edit = JavaHelpers.getPropertyValue(propertyFile,"gender_edit");
	private String startdate_edit = JavaHelpers.getPropertyValue(propertyFile,"startdate_edit");
	private String startdate_edit_formated = JavaHelpers.getPropertyValue(propertyFile,"startdate_edit_formated");
	private String walkins_edit = JavaHelpers.getPropertyValue(propertyFile,"walkins_edit");
	private String mobile_edit = JavaHelpers.getPropertyValue(propertyFile,"mobile_edit");
	private String home_edit = JavaHelpers.getPropertyValue(propertyFile,"home_edit");
	private String pcode_edit = JavaHelpers.getPropertyValue(propertyFile,"pcode_edit");
	private String interval_edit = JavaHelpers.getPropertyValue(propertyFile,"interval_edit");
	private String sendconfirmation_edit = JavaHelpers.getPropertyValue(propertyFile,"sendconfirmation_edit");

	private int max_employee_limit = Integer.parseInt(JavaHelpers.getPropertyValue(propertyFile,"max_employee_limit"));
	private String max_employee_validation_text = JavaHelpers.getPropertyValue(propertyFile,"max_employee_validation_text");
	

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
		employeeadd = new EmployeeAddLib(driver);
		employeesearch = new EmployeeSearchLib(driver);
		selenium = new SeleniumHelpers(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		timeStamp = javahelp.timeStamp();
		ex = new ExceptionHandler(driver);
	}
	
	/*Test 1 : Verify that user can add employee successfully*/
	@Test (priority = 1)
	public void employee_Create() throws IOException
	{
		try 
		{	
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to Employee and rosters > Employee page
			Reporter.log("Step 2 = Opening Menu & Navigating to Employee and rosters > Employee page");
			navigationlib.clickOnMenuItem("Employees and rosters");
			navigationlib.menu_ClickOnEmployeesTile();
			
			//Step 3- On Employee setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 3- On Employee setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			alias = alias_create + timeStamp;
			email = email_create + timeStamp + "@mailinator.com";
			
			employeeadd.employee_Add(alias, firstname_create, lastname_create, gender_create, startdate_create, biography_create, status_create, mobile_create, home_create, email, street1_create, street2_create, suburb_create, state_create, pcode_create, interval_create, sendconfirmation_create, showinappointment_create, walkins_create);
			
			//Step 4 = Searching for added employee and verifying that it has correct details
			Reporter.log("Step 4 = Searching for added employee and verifying that it has correct details");
			employeesearch.search_SearchEmployeeAndClick(alias);
			
			//Details section
			
			//Verifying Alias
			expected = "alias " + alias;
			actual = employeesearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying first name
			expected = "first name " + firstname_create;
			actual = employeesearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying last name
			expected = "last name " + lastname_create;
			actual = employeesearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Gender
			expected = "gender " + gender_create;
			actual = employeesearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Start Date
			expected = "start date " + startdate_create_formated;
			actual = employeesearch.getDetailsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Biography
			expected = "biography " + biography_create;
			actual = employeesearch.getDetailsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Status
			expected = "status " + status_create;
			actual = employeesearch.getDetailsSectionData(8);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Available for walk-ins
			expected = "available for walk-ins " + walkins_create;
			actual = employeesearch.getDetailsSectionData(9);
			sfassert.assertEquals(actual, expected);
			
			
			//Mobile section 
			
			//Verifying Mobile
			expected = "mobile " + mobile_create;
			actual = employeesearch.getPhoneSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Home
			expected = "home " + home_create;
			actual = employeesearch.getPhoneSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Mobile
			expected = "email " + email;
			actual = employeesearch.getPhoneSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			
			//Address
			expected = "address\nhome address " + street1_create + " " + street2_create + 
						"\n" + suburb_create + 
						"\n" + state_create + " "+ pcode_create;
			actual = employeesearch.getAddressSectionData();
			sfassert.assertEquals(actual, expected);
			
			
			//Appointment Book
			
			//Verifying Interval
			expected = "interval" + interval_create + " Minutes";
			actual = employeesearch.getAppointmentBookSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Send Confirmation to clients
			expected = "send confirmation to clients" + sendconfirmation_create;
			actual = employeesearch.getAppointmentBookSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Show in appointment book
			expected = "show in appointment book" + showinappointment_create;
			actual = employeesearch.getAppointmentBookSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 2 : Verify that user can edit employee successfully*/
	@Test (priority = 2, dependsOnMethods = { "employee_Create" })
	public void employee_Update() throws IOException
	{
		try 
		{	
			selenium.refreshPage();
			
			//Step 1 = Searching for added employee and editing information
			Reporter.log("Step 1 = Searching for added employee and editing information");
			employeesearch.search_SearchEmployeeAndClick(alias);
			
			alias = alias_create + timeStamp;
			email = email_create + timeStamp + "@mailinator.com";
			firstname_create = firstname_create + "e";
			lastname_create = lastname_create + "e";
			biography_create  = biography_create + "e";
			street1_create = street1_create + "e";
			street2_create = street2_create + "e";
			suburb_create = suburb_create + "e";
			state_create = state_create + "e";
			
			employeesearch.clickOnEditIcon();
			employeeadd.employee_Add(alias, firstname_create, lastname_create, gender_edit, startdate_edit, biography_create, status_create, mobile_edit, home_edit, email, street1_create, street2_create, suburb_create, state_create, pcode_edit, interval_edit, sendconfirmation_edit, showinappointment_create, walkins_edit);
			
			//Step 2 = Searching for edited employee and verifying that it has correct details
			Reporter.log("Step 2 = Searching for edited employee and verifying that it has correct details");
			employeesearch.search_SearchEmployeeAndClick(alias);
			
			//Details section
			
			//Verifying Alias
			expected = "alias " + alias;
			actual = employeesearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying first name
			expected = "first name " + firstname_create;
			actual = employeesearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying last name
			expected = "last name " + lastname_create;
			actual = employeesearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Gender
			expected = "gender " + gender_edit;
			actual = employeesearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Start Date
			expected = "start date " + startdate_edit_formated;
			actual = employeesearch.getDetailsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Biography
			expected = "biography " + biography_create;
			actual = employeesearch.getDetailsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Status
			expected = "status " + status_create;
			actual = employeesearch.getDetailsSectionData(8);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Available for walk-ins
			expected = "available for walk-ins " + walkins_edit;
			actual = employeesearch.getDetailsSectionData(9);
			sfassert.assertEquals(actual, expected);
			
			
			//Mobile section 
			
			//Verifying Mobile
			expected = "mobile " + mobile_edit;
			actual = employeesearch.getPhoneSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Home
			expected = "home " + home_edit;
			actual = employeesearch.getPhoneSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Mobile
			expected = "email " + email;
			actual = employeesearch.getPhoneSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			
			//Address
			expected = "address\nhome address " + street1_create + " " + street2_create + 
						"\n" + suburb_create + 
						"\n" + state_create + " "+ pcode_edit;
			actual = employeesearch.getAddressSectionData();
			sfassert.assertEquals(actual, expected);
			
			
			//Appointment Book
			
			//Verifying Interval
			expected = "interval" + interval_edit + " Minutes";
			actual = employeesearch.getAppointmentBookSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Send Confirmation to clients
			expected = "send confirmation to clients" + sendconfirmation_edit;
			actual = employeesearch.getAppointmentBookSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Show in appointment book
			expected = "show in appointment book" + showinappointment_create;
			actual = employeesearch.getAppointmentBookSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 3 : Verify that user can inactive employee successfully*/
	@Test (priority=3, dependsOnMethods={"employee_Create"})
	public  void employee_Delete() throws IOException
	{
		try 
		{	
			selenium.refreshPage();
			
			//Step 1 = Searching for added employee and inactivating employee
			Reporter.log("Step 1 = Searching for added employee and inactivating employee");
			employeesearch.search_SearchEmployeeAndClick(alias);
			
			employeesearch.clickOnEditIcon();
			String status = "Inactive";
			employeeadd.employee_ChangeStatus(status);
			
			//Step 2 = Verify that employee is inactive
			Reporter.log("Step 2 = Verify that employee is inactive");
			
			String partialalias= alias.substring((alias.length()/2),alias.length());
			employeesearch.search_SearchEmployee(partialalias);
			
			//verifying that 'There are no records to display' text displayed
			expected = "There are no records to display";
			actual = employeesearch.search_GetNoRecordMsgText();
			sfassert.assertEquals(actual, expected);		
			
			//Step 3 = On Search filter, select Inactive and performing search again
			Reporter.log("Step 3 = On Search filter, select Inactive");
			employeesearch.search_SearchEmployee("");
			employeesearch.search_Filter_ClickOnInactiveCheckbox();
			
			//Step 4 = Searching for inactive employee and verifying that it has correct details
			Reporter.log("Step 4 = Searching for inactive activity and verifying that it has correct details");
			employeesearch.search_SearchEmployeeAndClick(partialalias);
			
			//Details section
			
			//Verifying Alias
			expected = "alias " + alias;
			actual = employeesearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying first name
			expected = "first name " + firstname_create;
			actual = employeesearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying last name
			expected = "last name " + lastname_create;
			actual = employeesearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Gender
			expected = "gender " + gender_edit;
			actual = employeesearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Start Date
			expected = "start date " + startdate_edit_formated;
			actual = employeesearch.getDetailsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Biography
			expected = "biography " + biography_create;
			actual = employeesearch.getDetailsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Status
			expected = "status " + status;
			actual = employeesearch.getDetailsSectionData(8);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Available for walk-ins
			expected = "available for walk-ins " + walkins_edit;
			actual = employeesearch.getDetailsSectionData(9);
			sfassert.assertEquals(actual, expected);
			
			
			//Mobile section 
			
			//Verifying Mobile
			expected = "mobile " + mobile_edit;
			actual = employeesearch.getPhoneSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Home
			expected = "home " + home_edit;
			actual = employeesearch.getPhoneSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Mobile
			expected = "email " + email;
			actual = employeesearch.getPhoneSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			
			//Address
			expected = "address\nhome address " + street1_create + " " + street2_create + 
						"\n" + suburb_create + 
						"\n" + state_create + " "+ pcode_edit;
			actual = employeesearch.getAddressSectionData();
			sfassert.assertEquals(actual, expected);
			
			
			//Appointment Book
			
			//Verifying Interval
			expected = "interval" + interval_edit + " Minutes";
			actual = employeesearch.getAppointmentBookSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Send Confirmation to clients
			expected = "send confirmation to clients" + sendconfirmation_edit;
			actual = employeesearch.getAppointmentBookSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Show in appointment book
			expected = "show in appointment book" + showinappointment_create;
			actual = employeesearch.getAppointmentBookSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
	}
	
	/*Test 4 :  Verify that more than allowed user can't be allocated for appointment book.*/
	@Test (priority=4)
	public  void employee_VerifyMaxEmployeeAllowedOnAppointmentBook() throws IOException
	{
		try 
		{	
			selenium.refreshPage();
			
			//Step 1 = Adding more than allowed employee and verifying validation
			Reporter.log("Step 1 = Adding more than allowed employee and verifying validation");
			employeeadd.employee_AddNthEmployee(max_employee_limit);
			
			//Verifying validation message
			expected = max_employee_validation_text;
			actual = common.getValidationTextFromBottomRightPopUp();
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
