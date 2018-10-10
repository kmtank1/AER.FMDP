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

import au.com.FMDP.live.functionLibrary.common.TopNavigationLib;
import au.com.FMDP.live.functionLibrary.login.LoginLib;
import au.com.FMDP.live.functionLibrary.settings.BusinessSetupLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class BusinessSetupTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	private BusinessSetupLib business;
	private String propertyFile="test-input/settings.properties";
	
	@BeforeClass
	@Parameters({"node", "browser"})
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception
	{
		drivermanager = new WebDriverManager();
		driver = drivermanager.setUp(node,browser); 
		loginlib = new LoginLib(driver);
		navigationlib = new TopNavigationLib(driver);
		business = new BusinessSetupLib(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		ex = new ExceptionHandler(driver);
	}
	
	
	/*Test 1 : Verify that user can edit business setup information successfully*/
	@Test (priority = 1)
	public void businessSetup_Update() throws IOException
	{
		try 
		{	
		
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to General Settings > Business Setup page
			Reporter.log("Step 2 - Opening Menu & Navigating to General Settings > Business Setup page");
			navigationlib.clickOnMenuItem("General settings");
			navigationlib.menu_ClickOnBusinessSetupTile();
			
			//Step 3- On business setup page, editing information
			Reporter.log("Step 3- On business setup page, editing information");
			business.clickOnEditIcon();
			
			String namevalue = "FitnessFreak(FF) AT";
			String abnvalue="BBB";
			String refvalue="Patel";
			String sitecodevalue="XXXX";
			String startofweekvalue="Tuesday";
			String street1value="168 Knapp Street";
			String street2value="Stree 2";
			String suburbvalue="Silicon Valley";
			String statevalue="ACT";
			String pcodevalue="2003";
			String[] sunday = {"no","",""};
			String[] monday = {"yes","9:00 AM","9:00 PM"};
			String[] tuesday = {"yes","10:00 AM","10:00 PM"};
			String[] wednessday= {"yes","9:00 AM","9:00 PM"};
			String[] thursday = {"yes","9:00 AM","11:00 PM"};
			String[] friday = {"yes","9:00 AM","5:00 PM"};
			String[] saturaday = {"yes","8:00 AM","8:00 PM"};
			String phonevalue ="963215412";
			String faxvalue = "963258742";
			String emailvalue = "atemail@mailinator.com";
			String websitevalue = "http://www.random.com/";
			String shortdescriptionvalue= "This is for automation short description";
			String longdescriptionvalue = "This is for automation long description, This is for automation long description";
			String pricingbandvalue ="Budget";
			
			business.businesssetup_Edit(namevalue, abnvalue, refvalue, sitecodevalue, startofweekvalue, 
										street1value, street2value, suburbvalue, statevalue, pcodevalue, 
										sunday, monday, tuesday, wednessday, thursday, friday, saturaday, 
										phonevalue, faxvalue, emailvalue, websitevalue, 
										shortdescriptionvalue, longdescriptionvalue, pricingbandvalue);
			
			
			//Details section
			expected = "name " + namevalue;
			actual = business.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "ABN " + abnvalue;
			actual = business.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "reference " + refvalue;
			actual = business.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "site code " + sitecodevalue;
			actual = business.getDetailsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			expected = "start of week " + startofweekvalue;
			actual = business.getDetailsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			
			//Opening hours section		
			expected = "sunday " + "Closed";
			actual = business.getOpeningHoursSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "monday " + monday[1] + " – " + monday[2];
			actual = business.getOpeningHoursSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "tuesday " + tuesday[1] + " – " + tuesday[2];
			actual = business.getOpeningHoursSectionData(3);
			sfassert.assertEquals(actual, expected);

			expected = "wednesday " + wednessday[1] + " – " + wednessday[2];
			actual = business.getOpeningHoursSectionData(4);
			sfassert.assertEquals(actual, expected);

			expected = "thursday " + thursday[1] + " – " + thursday[2];
			actual = business.getOpeningHoursSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			expected = "friday " + friday[1] + " – " + friday[2];
			actual = business.getOpeningHoursSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			expected = "saturday " + saturaday[1] + " – " + saturaday[2];
			actual = business.getOpeningHoursSectionData(7);
			sfassert.assertEquals(actual, expected);
			
			
			//Address section
			expected = "address\n" + street1value+ " " + street2value + 
						"\n" + suburbvalue+ " " + statevalue + " "+ pcodevalue;
			actual = business.getAddressSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			
			//Contact section
			expected = "phone " + phonevalue + " (Business) " + faxvalue + " (Fax)";
			actual = business.getContactSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "email " + emailvalue;
			actual = business.getContactSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "website " + websitevalue;
			actual = business.getContactSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			
			//mylocalsalon section
			expected = "short description " + shortdescriptionvalue;
			actual = business.getMylocalSalonSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "long description " + longdescriptionvalue;
			actual = business.getMylocalSalonSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "pricing band " + pricingbandvalue;
			actual = business.getMylocalSalonSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			
			//Step 4- editing information again
			Reporter.log("Step 4- editing information again");
			business.clickOnEditIcon();
			
			namevalue = JavaHelpers.getPropertyValue(propertyFile,"name");
			abnvalue=JavaHelpers.getPropertyValue(propertyFile,"abn");
			refvalue=JavaHelpers.getPropertyValue(propertyFile,"reference");
			sitecodevalue=JavaHelpers.getPropertyValue(propertyFile,"sitecode");
			startofweekvalue=JavaHelpers.getPropertyValue(propertyFile,"startofweek");
			street1value=JavaHelpers.getPropertyValue(propertyFile,"address1");
			street2value=JavaHelpers.getPropertyValue(propertyFile,"address2");
			suburbvalue=JavaHelpers.getPropertyValue(propertyFile,"suburb");
			statevalue=JavaHelpers.getPropertyValue(propertyFile,"state");
			pcodevalue=JavaHelpers.getPropertyValue(propertyFile,"pcode");
			sunday = JavaHelpers.getPropertyValue(propertyFile,"sunday").split(",");
			monday = JavaHelpers.getPropertyValue(propertyFile,"monday_to_saturaday").split(",");
			tuesday = JavaHelpers.getPropertyValue(propertyFile,"monday_to_saturaday").split(",");
			wednessday= JavaHelpers.getPropertyValue(propertyFile,"monday_to_saturaday").split(",");
			thursday = JavaHelpers.getPropertyValue(propertyFile,"monday_to_saturaday").split(",");
			friday = JavaHelpers.getPropertyValue(propertyFile,"monday_to_saturaday").split(",");
			saturaday = JavaHelpers.getPropertyValue(propertyFile,"monday_to_saturaday").split(",");
			phonevalue =JavaHelpers.getPropertyValue(propertyFile,"phone");
			faxvalue = JavaHelpers.getPropertyValue(propertyFile,"fax");
			emailvalue = JavaHelpers.getPropertyValue(propertyFile,"email");
			websitevalue = JavaHelpers.getPropertyValue(propertyFile,"website");
			shortdescriptionvalue= JavaHelpers.getPropertyValue(propertyFile,"shortdescription");
			longdescriptionvalue = JavaHelpers.getPropertyValue(propertyFile,"logdescription");
			pricingbandvalue =JavaHelpers.getPropertyValue(propertyFile,"pricingband");
			
			business.businesssetup_Edit(namevalue, abnvalue, refvalue, sitecodevalue, startofweekvalue, 
										street1value, street2value, suburbvalue, statevalue, pcodevalue, 
										sunday, monday, tuesday, wednessday, thursday, friday, saturaday, 
										phonevalue, faxvalue, emailvalue, websitevalue, 
										shortdescriptionvalue, longdescriptionvalue, pricingbandvalue);
			
			
			//Details section
			expected = "name " + namevalue;
			actual = business.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "ABN " + abnvalue;
			actual = business.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "reference " + refvalue;
			actual = business.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "site code " + sitecodevalue;
			actual = business.getDetailsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			expected = "start of week " + startofweekvalue;
			actual = business.getDetailsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			
			//Opening hours section		
			expected = "sunday " + "Closed";
			actual = business.getOpeningHoursSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "monday " + monday[1] + " – " + monday[2];
			actual = business.getOpeningHoursSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "tuesday " + tuesday[1] + " – " + tuesday[2];
			actual = business.getOpeningHoursSectionData(3);
			sfassert.assertEquals(actual, expected);

			expected = "wednesday " + wednessday[1] + " – " + wednessday[2];
			actual = business.getOpeningHoursSectionData(4);
			sfassert.assertEquals(actual, expected);

			expected = "thursday " + thursday[1] + " – " + thursday[2];
			actual = business.getOpeningHoursSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			expected = "friday " + friday[1] + " – " + friday[2];
			actual = business.getOpeningHoursSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			expected = "saturday " + saturaday[1] + " – " + saturaday[2];
			actual = business.getOpeningHoursSectionData(7);
			sfassert.assertEquals(actual, expected);
			
			
			//Address section
			expected = "address\n" + street1value+ " " + street2value + 
						"\n" + suburbvalue+ " " + statevalue + " "+ pcodevalue;
			actual = business.getAddressSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			
			//Contact section
			expected = "phone " + phonevalue + " (Business) " + faxvalue + " (Fax)";
			actual = business.getContactSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "email " + emailvalue;
			actual = business.getContactSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "website " + websitevalue;
			actual = business.getContactSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			
			//mylocalsalon section
			expected = "short description " + shortdescriptionvalue;
			actual = business.getMylocalSalonSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "long description " + longdescriptionvalue;
			actual = business.getMylocalSalonSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "pricing band " + pricingbandvalue;
			actual = business.getMylocalSalonSectionData(3);
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
