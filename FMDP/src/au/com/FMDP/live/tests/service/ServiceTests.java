package au.com.shortcuts.live.tests.service;
import java.io.IOException;
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
import au.com.FMDP.live.functionLibrary.service.ServiceAddLib;
import au.com.FMDP.live.functionLibrary.service.ServiceCategoryAddLib;
import au.com.FMDP.live.functionLibrary.service.ServiceCategorySearchLib;
import au.com.FMDP.live.functionLibrary.service.ServiceSearchLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class ServiceTests 
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
	private ServiceCategoryAddLib categoryadd;
	private ServiceCategorySearchLib categorysearch;
	private ServiceAddLib serviceadd;
	private ServiceSearchLib servicesearch;
	private SeleniumHelpers selenium;
	
	//Test Data
	private String timeStamp;
	private String propertyFile="test-input/service.properties";
	private String categoryname;
	private String servicename;
	private String categoryname_create = JavaHelpers.getPropertyValue(propertyFile,"categoryname_create");
	private String status_create = JavaHelpers.getPropertyValue(propertyFile,"status_create");
	private String onlinebooking_create = JavaHelpers.getPropertyValue(propertyFile,"onlinebooking_create");
	private String canbebooked_create = JavaHelpers.getPropertyValue(propertyFile,"canbebooked_create");
	private String reference_create = JavaHelpers.getPropertyValue(propertyFile,"reference_create");
	private String servicename_create = JavaHelpers.getPropertyValue(propertyFile,"servicename_create");
	private String description_create = JavaHelpers.getPropertyValue(propertyFile,"description_create");
	private String walkins_create = JavaHelpers.getPropertyValue(propertyFile,"walkins_create");
	private String selfcheckin_create = JavaHelpers.getPropertyValue(propertyFile,"selfcheckin_create");
	private String visitnote_create = JavaHelpers.getPropertyValue(propertyFile,"visitnote_create");
	private String defaultduration_create = JavaHelpers.getPropertyValue(propertyFile,"defaultduration_create");
	private String breakduration_create = JavaHelpers.getPropertyValue(propertyFile,"breakduration_create");
	private String price_create = JavaHelpers.getPropertyValue(propertyFile,"price_create");
	private String cost_create = JavaHelpers.getPropertyValue(propertyFile,"cost_create");
	private String futureprice_create = JavaHelpers.getPropertyValue(propertyFile,"futureprice_create");
	private String futurecost_create = JavaHelpers.getPropertyValue(propertyFile,"futurecost_create");
	private String effectivefrom_create = JavaHelpers.getPropertyValue(propertyFile,"effectivefrom_create");
	private String effectivefrom_dayformat_create = JavaHelpers.getPropertyValue(propertyFile,"effectivefrom_dayformat_create");
	
	private String defaultduration_edit = JavaHelpers.getPropertyValue(propertyFile,"defaultduration_edit");
	private String breakduration_edit = JavaHelpers.getPropertyValue(propertyFile,"breakduration_edit");
	private String price_edit = JavaHelpers.getPropertyValue(propertyFile,"price_edit");
	private String cost_edit = JavaHelpers.getPropertyValue(propertyFile,"cost_edit");
	private String futureprice_edit = JavaHelpers.getPropertyValue(propertyFile,"futureprice_edit");
	private String futurecost_edit = JavaHelpers.getPropertyValue(propertyFile,"futurecost_edit");
	private String effectivefrom_edit = JavaHelpers.getPropertyValue(propertyFile,"effectivefrom_edit");
	private String effectivefrom_dayformat_edit = JavaHelpers.getPropertyValue(propertyFile,"effectivefrom_dayformat_edit");
	
	
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
		categoryadd = new ServiceCategoryAddLib(driver);
		categorysearch = new ServiceCategorySearchLib(driver);
		serviceadd = new ServiceAddLib(driver);
		servicesearch = new ServiceSearchLib(driver);
		selenium = new SeleniumHelpers(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		timeStamp = javahelp.timeStamp();
		ex = new ExceptionHandler(driver);
	}
	
	/*Test 1 : Verify that user can add service category & service successfully*/
	@Test (priority = 1)
	public void service_CategoryAndServiceCreate() throws IOException
	{
		try 
		{	
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 - Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2 - Opening Menu & Navigating to Item Setup > Services page
			Reporter.log("Step 2 - Opening Menu & Navigating to Item Setup > Services page");
			navigationlib.clickOnMenuItem("Item setup");
			navigationlib.menu_ClickOnServicesTile();
			
			//Step 3 - On service category setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 3 - On service category setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			categoryname = categoryname_create+ timeStamp;
			categoryadd.servicecategory_Add(categoryname, status_create, onlinebooking_create, reference_create);
			
			//Step 4 - Searching for added service category and verifying that it has correct details
			Reporter.log("Step 4 - Searching for added service category and verifying that it has correct details");
			categorysearch.search_SearchCategoryAndClickEditButton(categoryname);
			
			//Details section
			
				//Verifying name
				expected = "name " + categoryname;
				actual = categorysearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
			
				//Verifying status
				expected = "status " + status_create;
				actual = categorysearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Online booking
				expected = "online booking " + onlinebooking_create;
				actual = categorysearch.getDetailsSectionData(3);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Reference
				expected = "reference " + reference_create;
				actual = categorysearch.getDetailsSectionData(4);
				sfassert.assertEquals(actual, expected);
				
			
			//Step 5 - Click on added Service Category & navigating to Line setup page
			Reporter.log("Step 5 - Click on added Service Category & navigating to Line setup page");
			categorysearch.search_SearchCateogryAndClick(categoryname);
			
			//Step 6 - On service setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 6 - On service setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			servicename = servicename_create + timeStamp;
			List<String> selectedtaxdata = serviceadd.service_Add(servicename, description_create, status_create,  canbebooked_create, onlinebooking_create, walkins_create, selfcheckin_create, visitnote_create, defaultduration_create, breakduration_create, price_create, cost_create, 1, futureprice_create, futurecost_create, effectivefrom_create, 2);
				
			//Step 7 - Verifying details for added service
			Reporter.log("Step 7 - Verifying details for added service");
			
			//Details section
				
				//Verifying name
				expected = "name " + servicename;
				actual = servicesearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying status
				expected = "status " + status_create;
				actual = servicesearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying description
				expected = "description " + description_create;
				actual = servicesearch.getDetailsSectionData(3);
				sfassert.assertEquals(actual, expected);

				//Verifying service category
				expected = "service category " + categoryname;
				actual = servicesearch.getDetailsSectionData(5);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Can be booked
				expected = "can be booked " + canbebooked_create;
				actual = servicesearch.getDetailsSectionData(6);
				sfassert.assertEquals(actual, expected);
				
				//Verifying online booking
				expected = "online booking " + onlinebooking_create;
				actual = servicesearch.getDetailsSectionData(7);
				sfassert.assertEquals(actual, expected);
				
				//Verifying available to walkin
				expected = "available to walk-ins " + walkins_create;
				actual = servicesearch.getDetailsSectionData(8);
				sfassert.assertEquals(actual, expected);
				
				//Verifying available to self check-in customers
				expected = "available to self check-in customers " + selfcheckin_create;
				actual = servicesearch.getDetailsSectionData(9);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Visit note required
				expected = "visit note required " + visitnote_create;
				actual = servicesearch.getDetailsSectionData(10);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Default duration
				expected = defaultduration_create;
				actual = servicesearch.getDefaultDuration();
				sfassert.assertEquals(actual, expected);
				
				//Verifying Break duration
				expected = breakduration_create;
				actual = servicesearch.getBreakDuration();
				sfassert.assertEquals(actual, expected);
				
				
			//Pricing section
				
				//Verifying price
				expected = "price $" + price_create + ".00";
				actual = servicesearch.getPricingSectionData(1,1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying cost
				expected = "costs $" + cost_create + ".00";
				actual = servicesearch.getPricingSectionData(1,2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying tax
				expected = "tax " + selectedtaxdata.get(0);
				actual = servicesearch.getPricingSectionData(1,3);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Future price
				expected = "future price $" + futureprice_create + ".00";
				actual = servicesearch.getPricingSectionData(2,1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Future costs
				expected = "future costs $" + futurecost_create + ".00";
				actual = servicesearch.getPricingSectionData(2,2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Effective from
				expected = "effective from " + effectivefrom_dayformat_create;
				actual = servicesearch.getPricingSectionData(2,3);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Tax
				expected = "tax " + selectedtaxdata.get(1);
				actual = servicesearch.getPricingSectionData(2,4);
				sfassert.assertEquals(actual, expected);
				

			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 2 : Verify that user can add service category & service successfully*/
	@Test (priority = 2, dependsOnMethods = { "service_CategoryAndServiceCreate" })
	public void service_CategoryAndServiceUpdate() throws IOException
	{
		try 
		{	
			selenium.refreshPage();
			
			//Step 1 - Searching for added service category , editing information"
			Reporter.log("Step 1 - Searching for added service category , editing information");
			categorysearch.search_SearchCategoryAndClickEditButton(categoryname);
			
			categoryname = categoryname_create+ timeStamp;
			onlinebooking_create = "No";
			reference_create = reference_create + " edited";
			categoryadd.servicecategory_Add(categoryname, status_create, onlinebooking_create, reference_create);
			
			//Step 2 - Searching for updated service category , verifing updated information"
			Reporter.log("Step 2 - Searching for updated service category , verifing updated information");
			categorysearch.search_SearchCategoryAndClickEditButton(categoryname);
			
			//Details section
			
				//Verifying name
				expected = "name " + categoryname;
				actual = categorysearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
			
				//Verifying status
				expected = "status " + status_create;
				actual = categorysearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Online booking
				expected = "online booking " + onlinebooking_create;
				actual = categorysearch.getDetailsSectionData(3);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Reference
				expected = "reference " + reference_create;
				actual = categorysearch.getDetailsSectionData(4);
				sfassert.assertEquals(actual, expected);
				
			
			//Step 3 - Click on updated Service Category & navigating to Line setup page
			Reporter.log("Step 3 - Click on added Service Category & navigating to Line setup page");
			categorysearch.search_SearchCateogryAndClick(categoryname);
			
			//Step 4 - On service setup page, editing added Service
			Reporter.log("Step 4 - On service setup page, editing added Service");
			servicesearch.clickOnEditIcon();
			
			servicename = servicename_create + timeStamp;
			description_create = description_create + " edited";
			canbebooked_create = "No";
			onlinebooking_create = "No";
			walkins_create = "No";
			selfcheckin_create = "No";
			visitnote_create = "No";
			List<String> selectedtaxdata = serviceadd.service_Add(	servicename, 
																	description_create, 
																	status_create,  
																	canbebooked_create, 
																	onlinebooking_create, 
																	walkins_create, 
																	selfcheckin_create, 
																	visitnote_create, 
																	defaultduration_edit, 
																	breakduration_edit, 
																	price_edit, 
																	cost_edit, 
																	2, 
																	futureprice_edit, 
																	futurecost_edit, 
																	effectivefrom_edit, 
																	1);
				
			//Step 5 - Verifying details for edit service
			Reporter.log("Step 5 - Verifying details for edit service");
			
			//Details section
				
				//Verifying name
				expected = "name " + servicename;
				actual = servicesearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying status
				expected = "status " + status_create;
				actual = servicesearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying description
				expected = "description " + description_create;
				actual = servicesearch.getDetailsSectionData(3);
				sfassert.assertEquals(actual, expected);

				//Verifying service category
				expected = "service category " + categoryname;
				actual = servicesearch.getDetailsSectionData(5);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Can be booked
				expected = "can be booked " + canbebooked_create;
				actual = servicesearch.getDetailsSectionData(6);
				sfassert.assertEquals(actual, expected);
				
				//Verifying online booking
				expected = "online booking " + onlinebooking_create;
				actual = servicesearch.getDetailsSectionData(7);
				sfassert.assertEquals(actual, expected);
				
				//Verifying available to walkin
				expected = "available to walk-ins " + walkins_create;
				actual = servicesearch.getDetailsSectionData(8);
				sfassert.assertEquals(actual, expected);
				
				//Verifying available to self check-in customers
				expected = "available to self check-in customers " + selfcheckin_create;
				actual = servicesearch.getDetailsSectionData(9);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Visit note required
				expected = "visit note required " + visitnote_create;
				actual = servicesearch.getDetailsSectionData(10);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Default duration
				expected = defaultduration_edit;
				actual = servicesearch.getDefaultDuration();
				sfassert.assertEquals(actual, expected);
				
				//Verifying Break duration
				expected = breakduration_edit;
				actual = servicesearch.getBreakDuration();
				sfassert.assertEquals(actual, expected);
				
				
			//Pricing section
				
				//Verifying price
				expected = "price $" + price_edit + ".00";
				actual = servicesearch.getPricingSectionData(1,1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying cost
				expected = "costs $" + cost_edit + ".00";
				actual = servicesearch.getPricingSectionData(1,2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying tax
				expected = "tax " + selectedtaxdata.get(0);
				actual = servicesearch.getPricingSectionData(1,3);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Future price
				expected = "future price $" + futureprice_edit + ".00";
				actual = servicesearch.getPricingSectionData(2,1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Future costs
				expected = "future costs $" + futurecost_edit + ".00";
				actual = servicesearch.getPricingSectionData(2,2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Effective from
				expected = "effective from " + effectivefrom_dayformat_edit;
				actual = servicesearch.getPricingSectionData(2,3);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Tax
				expected = "tax " + selectedtaxdata.get(1);
				actual = servicesearch.getPricingSectionData(2,4);
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
