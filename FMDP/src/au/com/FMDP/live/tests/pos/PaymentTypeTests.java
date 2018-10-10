package au.com.shortcuts.live.tests.pos;

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
import au.com.FMDP.live.functionLibrary.pos.PaymentTypeAddLib;
import au.com.FMDP.live.functionLibrary.pos.PaymentTypeSearchLib;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class PaymentTypeTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	private SeleniumHelpers selenium;
	private PaymentTypeAddLib paymenttypeadd;
	private PaymentTypeSearchLib paymenttypesearch;
	private String selectedpaymenttype;
	
	
	@BeforeClass
	@Parameters({"node", "browser"})
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception
	{
		drivermanager = new WebDriverManager();
		driver = drivermanager.setUp(node,browser); 
		loginlib = new LoginLib(driver);
		navigationlib = new TopNavigationLib(driver);
		selenium = new SeleniumHelpers(driver);
		paymenttypeadd = new PaymentTypeAddLib(driver);
		paymenttypesearch = new PaymentTypeSearchLib(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		ex = new ExceptionHandler(driver);
	}
	
	
	/*Test 1 : Verify that user can edit payment type successfully*/
	@Test (priority = 1)
	public void paymenttype_Update() throws IOException
	{
		try 
		{	
		
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to POS setup > Payment types page
			Reporter.log("Step 2 = Opening Menu & Navigating to POS setup > Payment types page");
			navigationlib.clickOnMenuItem("POS setup");
			navigationlib.menu_ClickOnPaymentTypesTile();
			
			//Step 3- On payment type setup page, editing information for existing first available payment type 
			Reporter.log("Step 3- On payment type setup page, editing information for existing first available payment type");
			paymenttypesearch.search_SearchPaymentTypeAndClick("");
			selectedpaymenttype = paymenttypesearch.getDetailsSectionData(1).substring(5);
			paymenttypesearch.clickOnEditIcon();
			
			String status = "Active";
			String creditsbankaccountvalue = "Yes";
			paymenttypeadd.paymenttype_Add(status, creditsbankaccountvalue);
			
			//Details section
			
			//Verifying Alias
			expected = "name " + selectedpaymenttype;
			actual = paymenttypesearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			
			//Verifying Status
			expected = "status " + status;
			actual = paymenttypesearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying credits bank account
			expected = "credits bank account " + creditsbankaccountvalue;
			actual = paymenttypesearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			
			paymenttypesearch.clickOnEditIcon();
			
			status = "Active";
			creditsbankaccountvalue = "No";
			paymenttypeadd.paymenttype_Add(status, creditsbankaccountvalue);
			
			//Details section
			
			//Verifying Alias
			expected = "name " + selectedpaymenttype;
			actual = paymenttypesearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			
			//Verifying Status
			expected = "status " + status;
			actual = paymenttypesearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying credits bank account
			expected = "credits bank account " + creditsbankaccountvalue;
			actual = paymenttypesearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 2 : Verify that user can inactive payment type successfully*/
	//@Test (priority=2)
	public  void paymenttype_Delete() throws IOException
	{
		try 
		{	
			selenium.refreshPage();
			
			//Step 1- On payment type setup page, in activating information for existing available payment type 
			Reporter.log("Step 1- On payment type setup page, in activating information for existing available payment type ");
			paymenttypesearch.search_SearchPaymentTypeAndClick(selectedpaymenttype);
			paymenttypesearch.clickOnEditIcon();
			
			String status = "Inactive";
			paymenttypeadd.paymenttype_ChangeStatus(status);
			
			//Step 2 = Verify that payment is inactive
			Reporter.log("Step 2 = Verify that payment is inactive");
			paymenttypesearch.search_SearchPaymentTypeAndClick(selectedpaymenttype);
			
			//Step 3 = On Search filter, select Inactive and performing search again
			Reporter.log("Step 3 = On Search filter, select Inactive");
			paymenttypesearch.search_SearchPaymentType("");
			paymenttypesearch.search_Filter_ClickOnInactiveCheckbox();
			
			//Step 4 = Searching for inactive payment type and verifying that it has correct details
			Reporter.log("Step 4 = Searching for inactive payment type and verifying that it has correct details");
			paymenttypesearch.search_SearchPaymentTypeAndClick(selectedpaymenttype);
			
			//Details section	
			//Verifying Alias
			expected = "name " + selectedpaymenttype;
			actual = paymenttypesearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
		
			//Verifying Status
			expected = "status " + status;
			actual = paymenttypesearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			
			//activating payment type for future use
			paymenttypesearch.clickOnEditIcon();
			
			status = "Active";
			paymenttypeadd.paymenttype_ChangeStatus(status);
			
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
