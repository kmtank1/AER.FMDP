package au.com.shortcuts.live.tests.settings;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
import au.com.FMDP.live.functionLibrary.settings.RoleSearchLib;
import au.com.FMDP.live.functionLibrary.settings.RoleSetupLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class RolesTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	private CommonLib common;
	private SeleniumHelpers selenium;
	private JavaHelpers javahelp;
	private RoleSetupLib roleadd ;
	private RoleSearchLib rolesearch ;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	
	//Test Data
	private String timeStamp;
	private String propertyFile="test-input/roles.properties";
	private String name_create = JavaHelpers.getPropertyValue(propertyFile,"name_create");
	private String name;
	private String status_create = JavaHelpers.getPropertyValue(propertyFile,"status_create");
	private String employeesearch_create = JavaHelpers.getPropertyValue(propertyFile,"employeesearch_create");
	private String employeestatus_create = JavaHelpers.getPropertyValue(propertyFile,"employeestatus_create");
	private String appointmentbook_section_create = JavaHelpers.getPropertyValue(propertyFile,"appointmentbook_section_create");
	private List<String> appointmentbook_datalist = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"appointmentbook_permission_create").split(","));
	private String configuration_section_create = JavaHelpers.getPropertyValue(propertyFile,"configuration_section_create");
	private List<String> configuration_datalist = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"configuration_permission_create").split(","));
	private String general_section_create = JavaHelpers.getPropertyValue(propertyFile,"general_section_create");
	private List<String>  general_datalist = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"general_permission_create").split(","));
	private String customer_section_create = JavaHelpers.getPropertyValue(propertyFile,"customer_section_create");
	private List<String> customer_datalist = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"customer_permission_create").split(","));
	private String employee_section_create = JavaHelpers.getPropertyValue(propertyFile,"employee_section_create");
	private List<String> employee_datalist = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"employee_permission_create").split(","));
	private String pointofsale_section_create = JavaHelpers.getPropertyValue(propertyFile,"pointofsale_section_create");
	private List<String> pointofsale_datalist = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"pointofsale_permission_create").split(","));
	private String reports_section_create = JavaHelpers.getPropertyValue(propertyFile,"reports_section_create");
	private List<String> reports_datalist = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"reports_permission_create").split(","));private String tools_section_create = JavaHelpers.getPropertyValue(propertyFile,"tools_section_create");
	private List<String> tools_datalist = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"tools_permission_create").split(","));
	private String walkinmanager_section_create = JavaHelpers.getPropertyValue(propertyFile,"walkinmanager_section_create");
	private List<String> walkinmanager_datalist = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"walkinmanager_permission_create").split(","));private String services_section_create = JavaHelpers.getPropertyValue(propertyFile,"services_section_create");
	private List<String> services_datalist = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"services_permission_create").split(","));
	private String employeesearch_update = JavaHelpers.getPropertyValue(propertyFile, "employeesearch_update");	
	private List<String> appointmentbook_datalist_edit = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"appointmentbook_permission_update").split(","));
	private List<String> configuration_datalist_edit = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"configuration_permission_update").split(","));
	private List<String> general_datalist_edit = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"general_permission_update").split(","));
	private List<String> customer_datalist_edit = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"customer_permission_update").split(","));
	private List<String> employee_datalist_edit = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"employee_permission_update").split(","));
	private List<String> pointofsale_datalist_edit = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"pointofsale_permission_update").split(","));
	private List<String> reports_datalist_edit = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"reports_permission_update").split(","));
	private List<String> tools_datalist_edit = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"tools_permission_update").split(","));
	private List<String> walkinmanager_datalist_edit = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"walkinmanager_permission_update").split(","));
	private List<String> services_datalist_edit = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile,"services_permission_update").split(","));
	

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
		roleadd = new RoleSetupLib(driver);
		rolesearch = new RoleSearchLib(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		timeStamp = javahelp.timeStamp();
		ex = new ExceptionHandler(driver);
	}
	
	/*Test 1 : Verify that user can setup role successfully*/
	@Test (priority = 1)
	public void role_Create() throws IOException
	{
		try 
		{	
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to General settings > Roles page
			Reporter.log("Step 2 = Opening Menu & Navigating to General settings > Roles page");
			navigationlib.clickOnMenuItem("General settings");
			navigationlib.menu_ClickOnRolesTile();
			
			//Step 3- On Tax setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 3 = On Tax setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			name = name_create + timeStamp;
			

			roleadd.roledetails_Add(name, status_create, employeesearch_create, employeestatus_create,
									appointmentbook_section_create, appointmentbook_datalist,
									configuration_section_create, configuration_datalist,
									general_section_create, general_datalist,
									customer_section_create, customer_datalist,
									employee_section_create, employee_datalist,
									pointofsale_section_create, pointofsale_datalist,
									reports_section_create, reports_datalist,
									tools_section_create, tools_datalist,
									walkinmanager_section_create, walkinmanager_datalist,
									services_section_create, services_datalist);

			//Step 4 = Searching for added role and verifying that it has correct details
			Reporter.log("Step 4 = Searching for added role and verifying that it has correct details");
			rolesearch.search_SearchRoleAndClick(name);


			//Details section :
				
				//Verifying name
				expected = "name " + name;
				actual = rolesearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying status
				expected = "status " + status_create;
				actual = rolesearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
			//Employees section :
				
				//Verifying employee
				expected = employeesearch_create;
				actual = rolesearch.getEmployeesSectionData(1);
				sfassert.assertEquals(actual, expected);
				
			//Appointment book Permissions section :
				
				//Verifying appointment book permission - 1
				expected = appointmentbook_datalist.get(0).toLowerCase();
				actual = rolesearch.getAppointmentBookSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
				//Verifying appointment book permission - 2
				expected = appointmentbook_datalist.get(1).toLowerCase();
				actual = rolesearch.getAppointmentBookSectionData(2).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
				//Verifying appointment book permission - 3
				expected = appointmentbook_datalist.get(2).toLowerCase();
				actual = rolesearch.getAppointmentBookSectionData(3).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Configuration Permissions section :
				
				//Verifying configuration permission - 1
				expected = configuration_datalist.get(0).toLowerCase();
				actual = rolesearch.getConfigurationSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
				//Verifying configuration permission - 2
				expected = configuration_datalist.get(1).toLowerCase();
				actual = rolesearch.getConfigurationSectionData(2).toLowerCase();
				sfassert.assertEquals(actual, expected);
	
				//Verifying configuration permission - 3
				expected = configuration_datalist.get(2).toLowerCase();
				actual = rolesearch.getConfigurationSectionData(3).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//General Permissions section :
				
				//Verifying general permission - 1
				expected = general_datalist.get(0).toLowerCase();
				actual = rolesearch.getGeneralSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
				//Verifying general permission - 2 (Default)
				expected = "i can access this site";
				actual = rolesearch.getGeneralSectionData(2).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
				//Verifying general permission - 3
				expected = general_datalist.get(1).toLowerCase();
				actual = rolesearch.getGeneralSectionData(3).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Customer Permissions section :
				
				//Verifying customer permission - 1
				expected = customer_datalist.get(0).toLowerCase();
				actual = rolesearch.getCustomerSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Employee Permissions section :
				
				//Verifying employee permission - 1
				expected = employee_datalist.get(0).toLowerCase();
				actual = rolesearch.getEmployeeSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
				//Verifying employee permission - 2
				expected = employee_datalist.get(1).toLowerCase();
				actual = rolesearch.getEmployeeSectionData(2).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Point of sale Permissions section :
				
				//Verifying point of sale permission - 1
				expected = "i can use point of sale";
				actual = rolesearch.getPointOfSaleSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
				//Verifying point of sale permission - 2
				expected = pointofsale_datalist.get(0).toLowerCase();
				actual = rolesearch.getPointOfSaleSectionData(2).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Reports Permissions section :
				
				//Verifying reports permission - 1
				expected = reports_datalist.get(0).toLowerCase();
				actual = rolesearch.getReportsSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Tools Permissions section :
				
				//Verifying tools permission - 1
				expected = tools_datalist.get(0).toLowerCase();
				actual = rolesearch.getToolsSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Walk-in manager Permissions section :
				
				//Verifying walk in manager permission - 1
				expected = walkinmanager_datalist.get(0).toLowerCase();
				actual = rolesearch.getWalkinManagerSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
	
			//Services Permissions section :
				
				//Verifying Services permission - 1
				expected = services_datalist.get(0).toLowerCase();
				actual = rolesearch.getServicesSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
	
				//Verifying Services permission - 1
				expected = services_datalist.get(1).toLowerCase();
				actual = rolesearch.getServicesSectionData(2).toLowerCase();
				sfassert.assertEquals(actual, expected);

			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 2 : Verify that user can edit role successfully*/
	@Test (priority = 2, dependsOnMethods = { "role_Create" })
	public void role_Update() throws IOException
	{
		try 
		{
			//Step 1 = Editing added role
			Reporter.log("Step 1 = Editing added role");
			
			selenium.refreshPage();
			common.waitTillPageLoaded();
			rolesearch.search_SearchRoleAndClick(name);
			rolesearch.clickOnDetailsText();
			name = name_create + timeStamp;
			roleadd.roledetails_Add(name, status_create, employeesearch_update, employeestatus_create,
					appointmentbook_section_create, appointmentbook_datalist_edit,
					configuration_section_create, configuration_datalist_edit,
					general_section_create, general_datalist_edit,
					customer_section_create, customer_datalist_edit,
					employee_section_create, employee_datalist_edit,
					pointofsale_section_create, pointofsale_datalist_edit,
					reports_section_create, reports_datalist_edit,
					tools_section_create, tools_datalist_edit,
					walkinmanager_section_create, walkinmanager_datalist_edit,
					services_section_create, services_datalist_edit);
			
			//Step 2 = Searching for edited role and verifying that it has correct details
			Reporter.log("Step 2 = Searching for edited role and verifying that it has correct details");
			rolesearch.search_SearchRoleAndClick(name);
		
			//Details section :
			
			//Verifying name
			expected = "name " + name;
			actual = rolesearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = rolesearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Employees section :
				
				//Verifying employee - 1 
				expected = employeesearch_create;
				actual = rolesearch.getEmployeesSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying employee - 2
				expected = employeesearch_update;
				actual = rolesearch.getEmployeesSectionData(1);
				sfassert.assertEquals(actual, expected);
				
			//Appointment book Permissions section :
				
				//Verifying appointment book permission
				expected = "i can view my appointments";
				actual = rolesearch.getAppointmentBookSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
				//Verifying appointment book permission
				expected = appointmentbook_datalist_edit.get(3).toLowerCase();
				actual = rolesearch.getAppointmentBookSectionData(2).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
				
			//Configuration Permissions section :
				
				//Verifying configuration permission
				expected = configuration_datalist_edit.get(3).toLowerCase();
				actual = rolesearch.getConfigurationSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//General Permissions section :
				
				//Verifying general permission
				expected = "i can access this site";
				actual = rolesearch.getGeneralSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Customer Permissions section :
				
				//Verifying customer permission
				expected = customer_datalist_edit.get(1).toLowerCase();
				actual = rolesearch.getCustomerSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Employee Permissions section :
				
				//Verifying employee permission
				expected = employee_datalist_edit.get(2).toLowerCase();
				actual = rolesearch.getEmployeeSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Point of sale Permissions section :
				
				//Verifying point of sale permission - 1
				expected = pointofsale_datalist_edit.get(0).toLowerCase();
				actual = rolesearch.getPointOfSaleSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
				//Verifying point of sale permission - 2
				expected = pointofsale_datalist_edit.get(1).toLowerCase();
				actual = rolesearch.getPointOfSaleSectionData(2).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Reports Permissions section :
				
				//Verifying reports permission
				expected = reports_datalist_edit.get(1).toLowerCase();
				actual = rolesearch.getReportsSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
	
			//Services Permissions section :
				
				//Verifying Services permission
				expected = services_datalist_edit.get(2).toLowerCase();
				actual = rolesearch.getServicesSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
	}

	/*Test 3 : Verify that user can inactive role successfully*/
	@Test (priority=3, dependsOnMethods={"role_Create"})
	public  void role_Delete() throws IOException
	{
		try 
		{	
			//Step 1 = Marking added role as Inactive
			Reporter.log("Step 1 = Marking added role as Inactive");
			selenium.refreshPage();
			common.waitTillPageLoaded();
			rolesearch.search_SearchRoleAndClick(name);
			rolesearch.clickOnDetailsText();
			status_create = "Inactive";
			roleadd.role_ChangeStatus(status_create);
			common.footer_ClickOnDone();
			selenium.refreshPage();
			
			//Step 2 = Verify that role is inactive
			Reporter.log("Step 2 = Verify that role is inactive");
			String partialname= name.substring((name.length()/2),name.length());
			rolesearch.search_SearchRole(partialname);
			
			//verifying that 'There are no records to display' text displayed
			expected = "There are no records to display";
			actual = rolesearch.search_GetNoRecordMsgText();
			sfassert.assertEquals(actual, expected);		
			
			//Step 3 = On Search filter, select Inactive and performing search again
			Reporter.log("Step 3 = On Search filter, select Inactive");
			rolesearch.search_SearchRole("");
			rolesearch.search_Filter_ClickOnInactiveCheckbox();

			//Step 4 = Searching for inactive role and verifying that it has correct details
			Reporter.log("Step 4 = Searching for inactive role and verifying that it has correct details");
			rolesearch.search_SearchRoleAndClick(partialname);
		
			//Details section :	
			
				//Verifying name
				expected = "name " + name;
				actual = rolesearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying status
				expected = "status " + status_create;
				actual = rolesearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				
				//Employees section :
				
				//Verifying employee - 1 
				expected = employeesearch_create;
				actual = rolesearch.getEmployeesSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying employee - 2
				expected = employeesearch_update;
				actual = rolesearch.getEmployeesSectionData(1);
				sfassert.assertEquals(actual, expected);
				
			//Appointment book Permissions section :
				
				//Verifying appointment book permission
				expected = "i can view my appointments";
				actual = rolesearch.getAppointmentBookSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
				//Verifying appointment book permission
				expected = appointmentbook_datalist_edit.get(3).toLowerCase();
				actual = rolesearch.getAppointmentBookSectionData(2).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Configuration Permissions section :
				
				//Verifying configuration permission
				expected = configuration_datalist_edit.get(3).toLowerCase();
				actual = rolesearch.getConfigurationSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//General Permissions section :
				
				//Verifying general permission
				expected = "i can access this site";
				actual = rolesearch.getGeneralSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Customer Permissions section :
				
				//Verifying customer permission
				expected = customer_datalist_edit.get(1).toLowerCase();
				actual = rolesearch.getCustomerSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Employee Permissions section :
				
				//Verifying employee permission
				expected = employee_datalist_edit.get(2).toLowerCase();
				actual = rolesearch.getEmployeeSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Point of sale Permissions section :
				
				//Verifying point of sale permission - 1
				expected = pointofsale_datalist_edit.get(0).toLowerCase();
				actual = rolesearch.getPointOfSaleSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
				//Verifying point of sale permission - 2
				expected = pointofsale_datalist_edit.get(1).toLowerCase();
				actual = rolesearch.getPointOfSaleSectionData(2).toLowerCase();
				sfassert.assertEquals(actual, expected);
				
			//Reports Permissions section :
				
				//Verifying reports permission
				expected = reports_datalist_edit.get(1).toLowerCase();
				actual = rolesearch.getReportsSectionData(1).toLowerCase();
				sfassert.assertEquals(actual, expected);
	
			//Services Permissions section :
				
				//Verifying Services permission
				expected = services_datalist_edit.get(2).toLowerCase();
				actual = rolesearch.getServicesSectionData(1).toLowerCase();
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