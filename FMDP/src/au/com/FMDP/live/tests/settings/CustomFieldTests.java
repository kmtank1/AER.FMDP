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

import au.com.FMDP.live.functionLibrary.common.CommonLib;
import au.com.FMDP.live.functionLibrary.common.TopNavigationLib;
import au.com.FMDP.live.functionLibrary.login.LoginLib;
import au.com.FMDP.live.functionLibrary.settings.customfields.CustomFieldEntityTypeLib;
import au.com.FMDP.live.functionLibrary.settings.customfields.CustomFieldSearchLib;
import au.com.FMDP.live.functionLibrary.settings.customfields.CustomFieldSetupLib;
import au.com.FMDP.live.pageObjects.customer.CustomerAddPO;
import au.com.FMDP.live.pageObjects.employee.EmployeeAddPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class CustomFieldTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib;
	private CommonLib common;
	private SeleniumHelpers selenium;
	private JavaHelpers javahelp;
	private CustomFieldEntityTypeLib entity;
	private CustomFieldSetupLib customfieldadd;
	private CustomFieldSearchLib customfieldsearch;
	private CustomerAddPO customeradd;
	private EmployeeAddPO employeeadd;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	
	//Test Data
	private String timeStamp;
	private String propertyFile="test-input/customfield.properties";
	private String name_customer = JavaHelpers.getPropertyValue(propertyFile,"name_customer");
	private String name;
	private String status_create = JavaHelpers.getPropertyValue(propertyFile,"status_create");
	private String datatype_customer_create = JavaHelpers.getPropertyValue(propertyFile, "datatype_customer_create");
	private String fieldlength_customer_create = JavaHelpers.getPropertyValue(propertyFile, "fieldlength_customer_create");
	private String mandatory_create = JavaHelpers.getPropertyValue(propertyFile, "mandatory_create");
	private String defaultvalue_customer_create = JavaHelpers.getPropertyValue(propertyFile, "defaultvalue_customer_create");

	private String datatype_customer_update = JavaHelpers.getPropertyValue(propertyFile, "datatype_customer_update");
	private String defaultvalue_customer_update = JavaHelpers.getPropertyValue(propertyFile, "defaultvalue_customer_update");

	private String name_employee = JavaHelpers.getPropertyValue(propertyFile,"name_employee");
	private String datatype_employee_create = JavaHelpers.getPropertyValue(propertyFile, "datatype_employee_create");
	private String fieldlength_employee_create = JavaHelpers.getPropertyValue(propertyFile, "fieldlength_employee_create");
	private String defaultvalue_employee_create = JavaHelpers.getPropertyValue(propertyFile, "defaultvalue_employee_create");

	private String datatype_employee_update = JavaHelpers.getPropertyValue(propertyFile, "datatype_employee_update");
	private String defaultvalue_employee_update = JavaHelpers.getPropertyValue(propertyFile, "defaultvalue_employee_update");

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
		entity = new CustomFieldEntityTypeLib(driver);
		customfieldadd = new CustomFieldSetupLib(driver);
		customfieldsearch = new CustomFieldSearchLib(driver);
		customeradd = new CustomerAddPO(driver);
		employeeadd = new EmployeeAddPO(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		timeStamp = javahelp.timeStamp();
		ex = new ExceptionHandler(driver);
	}

	/*Test 1 : Verify that user can add customer's custom field successfully*/
	@Test (priority = 1)
	public void customercustomfield_Create() throws IOException
	{
		try 
		{	
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to General settings > Custom fields > Customer
			Reporter.log("Step 2 = Opening Menu & Navigating to General settings > Custom fields > Customer");
			navigationlib.clickOnMenuItem("General settings");
			navigationlib.menu_ClickOnCustomFieldsTile();
			entity.clickOnEntityItem("Customer");
			
			//Step 3- On Custom field (customer) page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 3 = On Custom field (customer) page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			name = name_customer + timeStamp;
			customfieldadd.customerfield_Add(name, status_create, datatype_customer_create, fieldlength_customer_create, mandatory_create, defaultvalue_customer_create);

			//Step 4 = Searching for added Custom field (customer) and verifying that it has correct details
			Reporter.log("Step 4 = Searching for added Custom field (customer) and verifying that it has correct details");
			customfieldsearch.search_SearchCustomFieldAndClick(name);
		
		//Details section :
			
			//Verifying name
			expected = "name " + name;
			actual = customfieldsearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = customfieldsearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
		
		//Definition section :
			
			//Verifying data type
			expected = "data type " + datatype_customer_create;
			actual = customfieldsearch.getDefinitionSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Number of characters
			expected = "number of characters " + fieldlength_customer_create;
			actual = customfieldsearch.getDefinitionSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Mandatory
			expected = "mandatory " + mandatory_create;
			actual = customfieldsearch.getDefinitionSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Default value
			expected = "default value " + defaultvalue_customer_create;
			actual = customfieldsearch.getDefinitionSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			//Step 4- Opening Menu & Navigating to Operations > Customers, Click on +NEW button, verifying that it has correct custom field
			Reporter.log("Step 4- Opening Menu & Navigating to Operations > Customers, Click on +NEW button, verifying that it has correct custom field");
			navigationlib.clickOnMenuItem("Operations");
			navigationlib.menu_ClickOnCustomerTile();
			common.footer_ClickOnNewButton();
			
			//Verifying Customer's custom field
			sfassert.assertEquals(true, customeradd.IsCustomFieldPresent(name));
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
	}
	
	
	/*Test 2 : Verify that user can edit Custom field (customer) successfully*/
	@Test (priority = 2, dependsOnMethods = { "customercustomfield_Create" })
	public void customercustomfield_Update() throws IOException
	{
		try 
		{
			//Step 1 = Editing added Custom field (customer)
			Reporter.log("Step 1 = Editing Custom field (customer)");
			
			navigationlib.clickOnMenuItem("General settings");
			navigationlib.menu_ClickOnCustomFieldsTile();
			entity.clickOnEntityItem("Customer");
			common.waitTillPageLoaded();
			customfieldsearch.search_SearchCustomFieldAndClick(name);
			customfieldsearch.clickOnEditIcon();
			name = name_customer + timeStamp;
			customfieldadd.customerfield_Add(name, status_create, datatype_customer_update, "", mandatory_create, defaultvalue_customer_update);
			
			//Step 2 = Searching for edited Custom field (customer) and verifying that it has correct details
			Reporter.log("Step 2 = Searching for edited Custom field (customer) and verifying that it has correct details");
			customfieldsearch.search_SearchCustomFieldAndClick(name);
		
		//Details section :

			//Verifying name
			expected = "name " + name;
			actual = customfieldsearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = customfieldsearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);

		//Definition section :
			
			//Verifying data type
			expected = "data type " + datatype_customer_update;
			actual = customfieldsearch.getDefinitionSectionData(1);
			sfassert.assertEquals(actual, expected);
	
			//Verifying Mandatory
			expected = "mandatory " + mandatory_create;
			actual = customfieldsearch.getDefinitionSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Default value
			expected = "default value " + defaultvalue_customer_update;
			actual = customfieldsearch.getDefinitionSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
	}


	/*Test 3 : Verify that user can inactive Custom field (customer) successfully*/
	@Test (priority=3, dependsOnMethods={"customercustomfield_Create"})
	public  void customercustomfield_Delete() throws IOException
	{
		try 
		{	
			//Step 1 = Marking added Custom field (customer) as Inactive
			Reporter.log("Step 1 = Marking added Custom field (customer) as Inactive");
			selenium.refreshPage();
			common.waitTillPageLoaded();
			entity.clickOnEntityItem("Customer");
			common.waitTillPageLoaded();
			customfieldsearch.search_SearchCustomFieldAndClick(name);
			customfieldsearch.clickOnEditIcon();
			status_create = "Inactive";
			customfieldadd.customfield_ChangeStatus(status_create);
			common.footer_ClickOnDone();
			selenium.refreshPage();
			entity.clickOnEntityItem("Customer");
			common.waitTillPageLoaded();
			
			//Step 2 = Verify that Custom field (customer) is inactive
			Reporter.log("Step 2 = Verify that Custom field (customer) is inactive");
			String partialname= name.substring((name.length()/2),name.length());
			customfieldsearch.search_SearchCustomField(partialname);
			
			//verifying that 'There are no records to display' text displayed
			expected = "There are no records to display";
			actual = customfieldsearch.search_GetNoRecordMsgText();
			sfassert.assertEquals(actual, expected);		
			
			//Step 3 = On Search filter, select Inactive and performing search again
			Reporter.log("Step 3 = On Search filter, select Inactive");
			customfieldsearch.search_SearchCustomField("");
			customfieldsearch.search_Filter_ClickOnInactiveCheckbox();

			//Step 4 = Searching for inactive Custom field (customer) and verifying that it has correct details
			Reporter.log("Step 4 = Searching for inactive Custom field (customer) and verifying that it has correct details");
			customfieldsearch.search_SearchCustomFieldAndClick(partialname);
		
		//Details section :	
		
			//Verifying name
			expected = "name " + name;
			actual = customfieldsearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = customfieldsearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
		//Definition section :
			
			//Verifying data type
			expected = "data type " + datatype_customer_update;
			actual = customfieldsearch.getDefinitionSectionData(1);
			sfassert.assertEquals(actual, expected);
	
			//Verifying Mandatory
			expected = "mandatory " + mandatory_create;
			actual = customfieldsearch.getDefinitionSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Default value
			expected = "default value " + defaultvalue_customer_update;
			actual = customfieldsearch.getDefinitionSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			//soft assert
			sfassert.assertAll();
		} 
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
	}


	/*Test 4 : Verify that user can add employee's custom field successfully*/
	@Test (priority = 4)
	public void employeecustomfield_Create() throws IOException
	{
		try 
		{	
			//Step 1- Opening Menu & Navigating to General settings > Custom fields > Employee
			Reporter.log("Step 1 = Opening Menu & Navigating to General settings > Custom fields > Employee");
			selenium.refreshPage();
			navigationlib.clickOnMenuItem("General settings");
			navigationlib.menu_ClickOnCustomFieldsTile();
			entity.clickOnEntityItem("Employee");
			
			//Step 2- On Custom field (employee) page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 2 = On Custom field (employee) page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			name = name_employee + timeStamp;
			status_create = "Active";
			customfieldadd.customerfield_Add(name, status_create, datatype_employee_create, fieldlength_employee_create, mandatory_create, defaultvalue_employee_create);
			
			//Step 3 = Searching for added Custom field (employee) and verifying that it has correct details
			Reporter.log("Step 3 = Searching for added Custom field (employee) and verifying that it has correct details");
			customfieldsearch.search_SearchCustomFieldAndClick(name);
		
		//Details section :
			
			//Verifying name
			expected = "name " + name;
			actual = customfieldsearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = customfieldsearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
		
		//Definition section :
			
			//Verifying data type
			expected = "data type " + datatype_employee_create;
			actual = customfieldsearch.getDefinitionSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Number of characters
			expected = "number of characters " + fieldlength_employee_create;
			actual = customfieldsearch.getDefinitionSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Mandatory
			expected = "mandatory " + mandatory_create;
			actual = customfieldsearch.getDefinitionSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Default value
			expected = "default value " + defaultvalue_employee_create;
			actual = customfieldsearch.getDefinitionSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			//Step 4- Opening Menu & Navigating to Employees and rosters > Employees, Click on +NEW button, verifying that it has correct custom field
			Reporter.log("Step 4- Opening Menu & Navigating to Employees and rosters > Employees, Click on +NEW button, verifying that it has correct custom field");
			navigationlib.clickOnMenuItem("Employees and rosters");
			navigationlib.menu_ClickOnEmployeesTile();
			common.footer_ClickOnNewButton();
			
			//Verifying Employee's custom field
			sfassert.assertEquals(true, employeeadd.IsCustomFieldPresent(name));
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
	}
	

	/*Test 5 : Verify that user can edit Custom field (employee) successfully*/
	@Test (priority = 5, dependsOnMethods = { "employeecustomfield_Create" })
	public void employeecustomfield_Update() throws IOException
	{
		try 
		{
			//Step 1 = Editing added Custom field (employee)
			Reporter.log("Step 1 = Editing Custom field (employee)");
			
			navigationlib.clickOnMenuItem("General settings");
			navigationlib.menu_ClickOnCustomFieldsTile();
			entity.clickOnEntityItem("Employee");
			common.waitTillPageLoaded();
			customfieldsearch.search_SearchCustomFieldAndClick(name);
			customfieldsearch.clickOnEditIcon();
			name = name_employee + timeStamp;
			customfieldadd.customerfield_Add(name, status_create, datatype_employee_update, "", mandatory_create, defaultvalue_employee_update);
			
			//Step 2 = Searching for edited Custom field (employee) and verifying that it has correct details
			Reporter.log("Step 2 = Searching for edited Custom field (employee) and verifying that it has correct details");
			customfieldsearch.search_SearchCustomFieldAndClick(name);
		
		//Details section :

			//Verifying name
			expected = "name " + name;
			actual = customfieldsearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = customfieldsearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);

		//Definition section :
			
			//Verifying data type
			expected = "data type " + datatype_employee_update;
			actual = customfieldsearch.getDefinitionSectionData(1);
			sfassert.assertEquals(actual, expected);
	
			//Verifying Mandatory
			expected = "mandatory " + mandatory_create;
			actual = customfieldsearch.getDefinitionSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Default value
			expected = "default value " + defaultvalue_employee_update;
			actual = customfieldsearch.getDefinitionSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
	}

	
	/*Test 6 : Verify that user can inactive Custom field (employee) successfully*/
	@Test (priority=6, dependsOnMethods={"employeecustomfield_Create"})
	public  void employeecustomfield_Delete() throws IOException
	{
		try 
		{	
			//Step 1 = Marking added Custom field (employee) as Inactive
			Reporter.log("Step 1 = Marking added Custom field (employee) as Inactive");
			selenium.refreshPage();
			common.waitTillPageLoaded();
			entity.clickOnEntityItem("Employee");
			common.waitTillPageLoaded();
			customfieldsearch.search_SearchCustomFieldAndClick(name);
			customfieldsearch.clickOnEditIcon();
			status_create = "Inactive";
			customfieldadd.customfield_ChangeStatus(status_create);
			common.footer_ClickOnDone();
			selenium.refreshPage();
			entity.clickOnEntityItem("Employee");
			common.waitTillPageLoaded();
			
			//Step 2 = Verify that Custom field (employee) is inactive
			Reporter.log("Step 2 = Verify that Custom field (employee) is inactive");
			String partialname= name.substring((name.length()/2),name.length());
			customfieldsearch.search_SearchCustomField(partialname);
			
			//verifying that 'There are no records to display' text displayed
			expected = "There are no records to display";
			actual = customfieldsearch.search_GetNoRecordMsgText();
			sfassert.assertEquals(actual, expected);		
			
			//Step 3 = On Search filter, select Inactive and performing search again
			Reporter.log("Step 3 = On Search filter, select Inactive");
			customfieldsearch.search_SearchCustomField("");
			customfieldsearch.search_Filter_ClickOnInactiveCheckbox();

			//Step 4 = Searching for inactive Custom field (employee) and verifying that it has correct details
			Reporter.log("Step 4 = Searching for inactive Custom field (employee) and verifying that it has correct details");
			customfieldsearch.search_SearchCustomFieldAndClick(partialname);
		
		//Details section :	
		
			//Verifying name
			expected = "name " + name;
			actual = customfieldsearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = customfieldsearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
		//Definition section :
			
			//Verifying data type
			expected = "data type " + datatype_employee_update;
			actual = customfieldsearch.getDefinitionSectionData(1);
			sfassert.assertEquals(actual, expected);
	
			//Verifying Mandatory
			expected = "mandatory " + mandatory_create;
			actual = customfieldsearch.getDefinitionSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying Default value
			expected = "default value " + defaultvalue_employee_update;
			actual = customfieldsearch.getDefinitionSectionData(4);
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