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
import au.com.FMDP.live.functionLibrary.settings.TaxSearchLib;
import au.com.FMDP.live.functionLibrary.settings.TaxSetupLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class TaxTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	private CommonLib common;
	private SeleniumHelpers selenium;
	private JavaHelpers javahelp;
	private TaxSetupLib taxadd ;
	private TaxSearchLib  taxsearch ;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	
	//Test Data
	private String timeStamp;
	private String propertyFile="test-input/tax.properties";
	private String name_create = JavaHelpers.getPropertyValue(propertyFile,"name_create");
	private String name;
	private String status_create = JavaHelpers.getPropertyValue(propertyFile,"status_create");
	private String purchase_create = JavaHelpers.getPropertyValue(propertyFile, "purchase_create");
	private String sale_create = JavaHelpers.getPropertyValue(propertyFile, "sale_create");
	private String service_create = JavaHelpers.getPropertyValue(propertyFile, "service_create");
	private String product_create = JavaHelpers.getPropertyValue(propertyFile, "product_create"); 
	private String sundry_create = JavaHelpers.getPropertyValue(propertyFile, "sundry_create");
	private String expense_create = JavaHelpers.getPropertyValue(propertyFile, "expense_create");
	private String ratename_update;
	private String ratename_create = JavaHelpers.getPropertyValue(propertyFile, "ratename_create");
	private String ratepercent_create = JavaHelpers.getPropertyValue(propertyFile, "ratepercent_create");
	private String effectivedate_create = JavaHelpers.getPropertyValue(propertyFile, "effectivedate_create");
	
	private String purchase_update = JavaHelpers.getPropertyValue(propertyFile, "purchase_update");
	private String sale_update = JavaHelpers.getPropertyValue(propertyFile, "sale_update"); 
	private String service_update = JavaHelpers.getPropertyValue(propertyFile, "service_update");
	private String product_update = JavaHelpers.getPropertyValue(propertyFile, "product_update");
	private String sundry_update = JavaHelpers.getPropertyValue(propertyFile, "sundry_update");
	private String expense_update = JavaHelpers.getPropertyValue(propertyFile, "expense_update");
	private String ratepercent_update = JavaHelpers.getPropertyValue(propertyFile, "ratepercent_update");
	private String effectivedate_verify = JavaHelpers.getPropertyValue(propertyFile, "effectivedate_verify");

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
		taxadd = new TaxSetupLib(driver);
		taxsearch = new TaxSearchLib(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		timeStamp = javahelp.timeStamp();
		ex = new ExceptionHandler(driver);
	}
	
	/*Test 1 : Verify that user can add tax category successfully*/
	@Test (priority = 1)
	public void tax_Create() throws IOException
	{
		try 
		{	
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to General settings > Tax page
			Reporter.log("Step 2 = Opening Menu & Navigating to General settings > Tax page");
			navigationlib.clickOnMenuItem("General settings");
			navigationlib.menu_ClickOnTaxTile();
			
			//Step 3- On Tax setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 3 = On Tax setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			name = name_create + timeStamp;
			taxadd.tax_Add(name, status_create, purchase_create, sale_create, service_create, product_create, sundry_create, expense_create,"",ratename_create, ratepercent_create);

			//Step 4 = Searching for added tax and verifying that it has correct details
			Reporter.log("Step 4 = Searching for added tax and verifying that it has correct details");
			taxsearch.search_SearchTaxAndClick(name);
		
		//Details section :
			
			//Verifying name
			expected = "name " + name;
			actual = taxsearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = taxsearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying purchase
			expected = "applies to purchases " + purchase_create;
			actual = taxsearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying sale
			expected = "applies to sales " + sale_create;
			actual = taxsearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
		
		//Applicable To section :
			
			//Verifying service
			expected = "services" + service_create;
			actual = taxsearch.getApplicableToSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying product
			expected = "products" + product_create;
			actual = taxsearch.getApplicableToSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying sundry
			expected = "sundries" + sundry_create;
			actual = taxsearch.getApplicableToSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying expenses
			expected = "expenses" + expense_create;
			actual = taxsearch.getApplicableToSectionData(4);
			sfassert.assertEquals(actual, expected);
			
		//Current Rates section :
			
			//Verifying rate name 
			expected = ratename_create;
			actual = taxsearch.getCurrentRateData(1, 1);
			sfassert.assertEquals(actual, expected);		
			
			//Verifying rate percent
			expected = ratepercent_create + ".00 %";
			actual = taxsearch.getCurrentRateData(1, 2);
			sfassert.assertEquals(actual, expected);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 2 : Verify that user can edit tax category successfully*/
	@Test (priority = 2, dependsOnMethods = { "tax_Create" })
	public void tax_Update() throws IOException
	{
		try 
		{
			//Step 1 = Editing added tax category
			Reporter.log("Step 1 = Editing tax category");
			
			selenium.refreshPage();
			common.waitTillPageLoaded();
			taxsearch.search_SearchTaxAndClick(name);
			taxsearch.clickOnEditIcon();
			name = name_create + timeStamp;
			ratename_update = ratename_create + "_edited";
			taxadd.tax_Add(name, status_create, purchase_update, sale_update, service_update, product_update, sundry_update, expense_update, effectivedate_create, ratename_update,ratepercent_update);
			
			//Step 2 = Searching for edited tax category and verifying that it has correct details
			Reporter.log("Step 2 = Searching for edited tax category and verifying that it has correct details");
			taxsearch.search_SearchTaxAndClick(name);
		
		//Details section :

			//Verifying name
			expected = "name " + name;
			actual = taxsearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = taxsearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying purchase
			expected = "applies to purchases " + purchase_update;
			actual = taxsearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying sale
			expected = "applies to sales " + sale_update;
			actual = taxsearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
		//Applicable To section :
			
			//Verifying service
			expected = "services" + service_update;
			actual = taxsearch.getApplicableToSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying product
			expected = "products" + product_update;
			actual = taxsearch.getApplicableToSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying sundry
			expected = "sundries" + sundry_update;
			actual = taxsearch.getApplicableToSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying expenses
			expected = "expenses" + expense_update;
			actual = taxsearch.getApplicableToSectionData(4);
			sfassert.assertEquals(actual, expected);
			
		//Current Rates section :
			
			//Verifying rate name 
			expected = ratename_create;
			actual = taxsearch.getCurrentRateData(1, 1);
			sfassert.assertEquals(actual, expected);		
			
			//Verifying rate percent
			expected = ratepercent_create + ".00 %";
			actual = taxsearch.getCurrentRateData(1, 2);
			sfassert.assertEquals(actual, expected);	
			
		//Future Rates section :
			
			//Verifying effective from date
			expected = "effective from " + effectivedate_verify;
			actual = taxsearch.getFutureRateSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying future rate name
			expected = ratename_update;
			actual = taxsearch.getFutureRateData(2, 1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying future rate percent
			expected = ratepercent_update + ".00 %";
			actual = taxsearch.getFutureRateData(2, 2);
			sfassert.assertEquals(actual, expected);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
	}


	/*Test 3 : Verify that user can inactive tax category successfully*/
	@Test (priority=3, dependsOnMethods={"tax_Create"})
	public  void tax_Delete() throws IOException
	{
		try 
		{	
			//Step 1 = Marking added tax category as Inactive
			Reporter.log("Step 1 = Marking added tax category as Inactive");
			selenium.refreshPage();
			common.waitTillPageLoaded();
			taxsearch.search_SearchTaxAndClick(name);
			taxsearch.clickOnEditIcon();
			status_create = "Inactive";
			ratename_update = ratename_create + "_edited";
			taxadd.tax_ChangeStatus(status_create);
			common.footer_ClickOnDone();
			selenium.refreshPage();
			
			//Step 2 = Verify that tax is inactive
			Reporter.log("Step 2 = Verify that tax is inactive");
			String partialname= name.substring((name.length()/2),name.length());
			taxsearch.search_SearchTax(partialname);
			
			//verifying that 'There are no records to display' text displayed
			expected = "There are no records to display";
			actual = taxsearch.search_GetNoRecordMsgText();
			sfassert.assertEquals(actual, expected);		
			
			//Step 3 = On Search filter, select Inactive and performing search again
			Reporter.log("Step 3 = On Search filter, select Inactive");
			taxsearch.search_SearchTax("");
			taxsearch.search_Filter_ClickOnInactiveCheckbox();

			//Step 4 = Searching for inactive tax and verifying that it has correct details
			Reporter.log("Step 4 = Searching for inactive tax and verifying that it has correct details");
			taxsearch.search_SearchTaxAndClick(partialname);
		
		//Details section :	
		
			//Verifying name
			expected = "name " + name;
			actual = taxsearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying status
			expected = "status " + status_create;
			actual = taxsearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying purchase
			expected = "applies to purchases " + purchase_update;
			actual = taxsearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying sale
			expected = "applies to sales " + sale_update;
			actual = taxsearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
		//Applicable To section :
			
			//Verifying service
			expected = "services" + service_update;
			actual = taxsearch.getApplicableToSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying product
			expected = "products" + product_update;
			actual = taxsearch.getApplicableToSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying sundry
			expected = "sundries" + sundry_update;
			actual = taxsearch.getApplicableToSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			//Verifying expenses
			expected = "expenses" + expense_update;
			actual = taxsearch.getApplicableToSectionData(4);
			sfassert.assertEquals(actual, expected);
					
		//Current Rates section :
			
			//Verifying rate name 
			expected = ratename_create;
			actual = taxsearch.getCurrentRateData(1, 1);
			sfassert.assertEquals(actual, expected);		
			
			//Verifying rate percent
			expected = ratepercent_create + ".00 %";
			actual = taxsearch.getCurrentRateData(1, 2);
			sfassert.assertEquals(actual, expected);
			
		//Future Rates section :
			
			//Verifying effective from date
			expected = "effective from " + effectivedate_verify;
			actual = taxsearch.getFutureRateSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying future rate name
			expected = ratename_update;
			actual = taxsearch.getFutureRateData(2, 1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying future rate percent
			expected = ratepercent_update + ".00 %";
			actual = taxsearch.getFutureRateData(2, 2);
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