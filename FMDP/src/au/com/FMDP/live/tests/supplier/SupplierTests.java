package au.com.shortcuts.live.tests.supplier;

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
import au.com.FMDP.live.functionLibrary.supplier.SupplierAddLib;
import au.com.FMDP.live.functionLibrary.supplier.SupplierSearchLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class SupplierTests 
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
	private SupplierAddLib supplieradd;
	private SupplierSearchLib suppliersearch;
	private SeleniumHelpers selenium;
	
	//Test Data
	private String timeStamp;
	private String propertyFile="test-input/supplier.properties";
	private String name;
	private String name_create = JavaHelpers.getPropertyValue(propertyFile,"name_create");
	private String ref_create = JavaHelpers.getPropertyValue(propertyFile,"ref_create");
	private String status_create = JavaHelpers.getPropertyValue(propertyFile,"status_create");
	private String businessnumber_create = JavaHelpers.getPropertyValue(propertyFile,"businessnumber_create");
	private String accountnumber_create = JavaHelpers.getPropertyValue(propertyFile,"accountnumber_create");
	private String website_create = JavaHelpers.getPropertyValue(propertyFile,"website_create");
	private String leadtime_create = JavaHelpers.getPropertyValue(propertyFile,"leadtime_create");
	private String firstname_create = JavaHelpers.getPropertyValue(propertyFile,"firstname_create");
	private String lastname_create = JavaHelpers.getPropertyValue(propertyFile,"lastname_create");
	private String businessphone_create = JavaHelpers.getPropertyValue(propertyFile,"businessphone_create");
	private String mobile_create = JavaHelpers.getPropertyValue(propertyFile,"mobile_create");
	private String fax_create = JavaHelpers.getPropertyValue(propertyFile,"fax_create");
	private String email;
	private String email_create = JavaHelpers.getPropertyValue(propertyFile,"email_create");
	private String street1_create = JavaHelpers.getPropertyValue(propertyFile,"street1_create");
	private String street2_create = JavaHelpers.getPropertyValue(propertyFile,"steeet2_create");
	private String suburb_create = JavaHelpers.getPropertyValue(propertyFile,"suburb_create");
	private String state_create = JavaHelpers.getPropertyValue(propertyFile,"state_create");
	private String pcode_create = JavaHelpers.getPropertyValue(propertyFile,"pcode_create");
	
	
	private String businessnumber_edit = JavaHelpers.getPropertyValue(propertyFile,"businessnumber_edit");
	private String accountnumber_edit = JavaHelpers.getPropertyValue(propertyFile,"accountnumber_edit");
	private String website_edit = JavaHelpers.getPropertyValue(propertyFile,"website_edit");
	private String leadtime_edit = JavaHelpers.getPropertyValue(propertyFile,"leadtime_edit");
	private String businessphone_edit = JavaHelpers.getPropertyValue(propertyFile,"businessphone_edit");
	private String fax_edit = JavaHelpers.getPropertyValue(propertyFile,"fax_edit");
	private String mobile_edit = JavaHelpers.getPropertyValue(propertyFile,"mobile_edit");
	

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
		supplieradd = new SupplierAddLib(driver);
		suppliersearch = new SupplierSearchLib(driver);
		selenium = new SeleniumHelpers(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		timeStamp = javahelp.timeStamp();
		ex = new ExceptionHandler(driver);
	}
	
	/*Test 1 : Verify that user can add supplier successfully*/
	@Test (priority = 1)
	public void supplier_Create() throws IOException
	{
		try 
		{	
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to Item Setup > Supplier page
			Reporter.log("Step 2 = Opening Menu & Navigating to Item Setup > Supplier page");
			navigationlib.clickOnMenuItem("Item setup");
			navigationlib.menu_ClickOnSuppliersTile();
			
			//Step 3- On supplier setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 3- On supplier setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			name = name_create + timeStamp;
			email = email_create + timeStamp + "@mailinator.com";
			supplieradd.supplier_Add(name, ref_create, status_create, businessnumber_create, accountnumber_create, website_create, leadtime_create, firstname_create, lastname_create, email, businessphone_create, mobile_create, fax_create, street1_create, street2_create, suburb_create, state_create, pcode_create);
			
			//Step 4 = Searching for added supplier and verifying that it has correct details
			Reporter.log("Step 4 = Searching for added supplier and verifying that it has correct details");
			suppliersearch.search_SearchSupplierAndClick(name);
			
			//Details section
			
			expected = "supplier name " + name;
			actual = suppliersearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "supplier reference " + ref_create;
			actual = suppliersearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "status " + status_create;
			actual = suppliersearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			expected = "business number " + businessnumber_create;
			actual = suppliersearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "account number " + accountnumber_create;
			actual = suppliersearch.getDetailsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			expected = "website url " + website_create;
			actual = suppliersearch.getDetailsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			expected = "lead time days " + leadtime_create;
			actual = suppliersearch.getDetailsSectionData(7);
			sfassert.assertEquals(actual, expected);
			
			

			//Address
			expected = "address\nphysical address " + street1_create + 
						"\n" + street2_create + 
						"\n" + suburb_create + 
						"\n" + state_create + 
						"\n"+ pcode_create + 
						"\n" + "postal address " + street1_create + 
						"\n" + street2_create + 
						"\n" + suburb_create + 
						"\n" + state_create + 
						"\n"+ pcode_create ;
			
			actual = suppliersearch.getAddressSectionData();
			sfassert.assertEquals(actual, expected);
			
			
			//Contact
			expected = "name " + firstname_create + " " + lastname_create;
			actual = suppliersearch.getContactSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "email " + email;
			actual = suppliersearch.getContactSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "business phone " + businessphone_create;
			actual = suppliersearch.getContactSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			expected = "mobile phone " + mobile_create;
			actual = suppliersearch.getContactSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "fax " + fax_create;
			actual = suppliersearch.getContactSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 2 : Verify that user can edit supplier successfully*/
	@Test (priority = 2, dependsOnMethods = { "supplier_Create" })
	public void supplier_Update() throws IOException
	{
		try 
		{	
			selenium.refreshPage();
			
			//Step 1- On supplier setup page, editing added item
			Reporter.log("Step 1- On supplier setup page, editing added item");
			suppliersearch.search_SearchSupplierAndClick(name);
			suppliersearch.clickOnEditIcon();
			name = name_create + timeStamp;
			firstname_create = firstname_create + "edit";
			email = email_create + timeStamp + "@mailinator.com";
			ref_create = ref_create + "e";
			lastname_create = lastname_create + "edit";
			street1_create = street1_create + "edit";
			street2_create = street2_create + "edit";
			suburb_create = suburb_create + "edit";
			state_create = state_create + "edit";
			pcode_create = "11111";
					
			supplieradd.supplier_Add(name, ref_create, status_create, businessnumber_edit, 
									accountnumber_edit, website_edit, leadtime_edit, firstname_create, lastname_create, 
									email, businessphone_edit, mobile_edit, fax_edit, street1_create, 
									street2_create, suburb_create, state_create, pcode_create);
			
			//Step 2 = Searching for updated supplier and verifying that it has correct details
			Reporter.log("Step 2 = Searching for updated supplier and verifying that it has correct details");
			suppliersearch.search_SearchSupplierAndClick(name);
			
			//Details section
			
			expected = "supplier name " + name;
			actual = suppliersearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "supplier reference " + ref_create;
			actual = suppliersearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "status " + status_create;
			actual = suppliersearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			expected = "business number " + businessnumber_edit;
			actual = suppliersearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "account number " + accountnumber_edit;
			actual = suppliersearch.getDetailsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			expected = "website url " + website_edit;
			actual = suppliersearch.getDetailsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			expected = "lead time days " + leadtime_edit;
			actual = suppliersearch.getDetailsSectionData(7);
			sfassert.assertEquals(actual, expected);
			
			

			//Address
			expected = "address\nphysical address " + street1_create + 
						"\n" + street2_create + 
						"\n" + suburb_create + 
						"\n" + state_create + 
						"\n"+ pcode_create + 
						"\n" + "postal address " + street1_create + 
						"\n" + street2_create + 
						"\n" + suburb_create + 
						"\n" + state_create + 
						"\n"+ pcode_create ;
			
			actual = suppliersearch.getAddressSectionData();
			sfassert.assertEquals(actual, expected);
			
			
			//Contact
			expected = "name " + firstname_create + " " + lastname_create;
			actual = suppliersearch.getContactSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "email " + email;
			actual = suppliersearch.getContactSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "business phone " + businessphone_edit;
			actual = suppliersearch.getContactSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			expected = "mobile phone " + mobile_edit;
			actual = suppliersearch.getContactSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "fax " + fax_edit;
			actual = suppliersearch.getContactSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 3 : Verify that user can inactive supplier successfully*/
	@Test (priority=3, dependsOnMethods={"supplier_Create"})
	public  void supplier_Delete() throws IOException
	{
		try 
		{	
			selenium.refreshPage();
			
			//Step 1 = Searching for added supplier and inactivating supplier
			Reporter.log("Step 1 = Searching for added supplier and inactivating supplier");
			suppliersearch.search_SearchSupplierAndClick(name);
			
			suppliersearch.clickOnEditIcon();
			String status = "Inactive";
			supplieradd.supplier_changeStatus(status);
			
			//Step 2 = Verify that supplier is inactive
			Reporter.log("Step 2 = Verify that employee is supplier");
			
			String partialname= name.substring((name.length()/2),name.length());
			suppliersearch.search_SearchSupplier(partialname);
			
			//verifying that 'There are no records to display' text displayed
			expected = "There are no records to display";
			actual = suppliersearch.search_GetNoRecordMsgText();
			sfassert.assertEquals(actual, expected);		
			
			//Step 3 = On Search filter, select Inactive and performing search again
			Reporter.log("Step 3 = On Search filter, select Inactive");
			suppliersearch.search_SearchSupplier("");
			suppliersearch.search_Filter_ClickOnInactiveCheckbox();
			
			//Step 4 = Searching for inactive supplier and verifying that it has correct details
			Reporter.log("Step 4 = Searching for inactive supplier and verifying that it has correct details");
			suppliersearch.search_SearchSupplierAndClick(partialname);
			
			//Details section
			
			expected = "supplier name " + name;
			actual = suppliersearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "supplier reference " + ref_create;
			actual = suppliersearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "status " + status;
			actual = suppliersearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			expected = "business number " + businessnumber_edit;
			actual = suppliersearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "account number " + accountnumber_edit;
			actual = suppliersearch.getDetailsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			expected = "website url " + website_edit;
			actual = suppliersearch.getDetailsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			expected = "lead time days " + leadtime_edit;
			actual = suppliersearch.getDetailsSectionData(7);
			sfassert.assertEquals(actual, expected);
			
			

			//Address
			expected = "address\nphysical address " + street1_create + 
						"\n" + street2_create + 
						"\n" + suburb_create + 
						"\n" + state_create + 
						"\n"+ pcode_create + 
						"\n" + "postal address " + street1_create + 
						"\n" + street2_create + 
						"\n" + suburb_create + 
						"\n" + state_create + 
						"\n"+ pcode_create ;
			
			actual = suppliersearch.getAddressSectionData();
			sfassert.assertEquals(actual, expected);
			
			
			//Contact
			expected = "name " + firstname_create + " " + lastname_create;
			actual = suppliersearch.getContactSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "email " + email;
			actual = suppliersearch.getContactSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "business phone " + businessphone_edit;
			actual = suppliersearch.getContactSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			expected = "mobile phone " + mobile_edit;
			actual = suppliersearch.getContactSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "fax " + fax_edit;
			actual = suppliersearch.getContactSectionData(5);
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
