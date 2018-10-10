package au.com.shortcuts.live.tests.product;

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
import au.com.FMDP.live.functionLibrary.product.BrandAddLib;
import au.com.FMDP.live.functionLibrary.product.BrandSearchLib;
import au.com.FMDP.live.functionLibrary.product.LineAddLib;
import au.com.FMDP.live.functionLibrary.product.LineSearchLib;
import au.com.FMDP.live.functionLibrary.product.ProductAddLib;
import au.com.FMDP.live.functionLibrary.product.ProductSearchLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class ProductTests 
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
	private BrandAddLib brandadd;
	private BrandSearchLib brandsearch;
	private LineAddLib lineadd;
	private LineSearchLib linesearch;
	private ProductAddLib productadd;
	private ProductSearchLib productsearch;
	private SeleniumHelpers selenium;
	
	//Test Data
	private String timeStamp;
	private String propertyFile="test-input/product.properties";
	private String brandname;
	private String linename;
	private String productname;
	private String brandname_create = JavaHelpers.getPropertyValue(propertyFile,"brandname_create");
	private String status_create = JavaHelpers.getPropertyValue(propertyFile,"status_create");
	private String linename_create = JavaHelpers.getPropertyValue(propertyFile,"linename_create");
	private String defaultproductusage_create = JavaHelpers.getPropertyValue(propertyFile,"defaultproductusage_create");
	private String productname_create = JavaHelpers.getPropertyValue(propertyFile,"productname_create");
	private String description_create = JavaHelpers.getPropertyValue(propertyFile,"description_create");
	private String itemreference_create = JavaHelpers.getPropertyValue(propertyFile,"itemreference_create");
	private String storedunits_create = JavaHelpers.getPropertyValue(propertyFile,"storedunits_create");
	private String sellprice_create = JavaHelpers.getPropertyValue(propertyFile,"sellprice_create");
	private String futureprice_create = JavaHelpers.getPropertyValue(propertyFile,"futureprice_create");
	private String effectivefrom_create = JavaHelpers.getPropertyValue(propertyFile,"effectivefrom_create");
	private String effectivefrom_dayformat_create = JavaHelpers.getPropertyValue(propertyFile,"effectivefrom_dayformat_create");
	private String effectivefrom_dateformat_create = JavaHelpers.getPropertyValue(propertyFile,"effectivefrom_dateformat_create");
	private String category_create = JavaHelpers.getPropertyValue(propertyFile,"category_create");
	private String subcategory_create = JavaHelpers.getPropertyValue(propertyFile,"subcategory_create");
	private String supplier_create = JavaHelpers.getPropertyValue(propertyFile,"supplier_create");
	private String buyprice_create = JavaHelpers.getPropertyValue(propertyFile,"buyprice_create");
	private String futurebuyprice_create = JavaHelpers.getPropertyValue(propertyFile,"futurebuyprice_create");
	private String productcode_create = JavaHelpers.getPropertyValue(propertyFile,"productcode_create");
	private String orderunits_create = JavaHelpers.getPropertyValue(propertyFile,"orderunits_create");
	private String coversiontostoredunits_create = JavaHelpers.getPropertyValue(propertyFile,"coversiontostoredunits_create");
	
	private String defaultproductusage_edit = JavaHelpers.getPropertyValue(propertyFile,"defaultproductusage_edit");
	private String storedunits_edit = JavaHelpers.getPropertyValue(propertyFile,"storedunits_edit");
	private String sellprice_edit = JavaHelpers.getPropertyValue(propertyFile,"sellprice_edit");
	private String futureprice_edit = JavaHelpers.getPropertyValue(propertyFile,"futureprice_edit");
	private String effectivefrom_edit = JavaHelpers.getPropertyValue(propertyFile,"effectivefrom_edit");
	private String effectivefrom_dayformat_edit = JavaHelpers.getPropertyValue(propertyFile,"effectivefrom_dayformat_edit");
	private String effectivefrom_dateformat_edit = JavaHelpers.getPropertyValue(propertyFile,"effectivefrom_dateformat_edit");
	private String category_edit = JavaHelpers.getPropertyValue(propertyFile,"category_edit");
	private String subcategory_edit = JavaHelpers.getPropertyValue(propertyFile,"subcategory_edit");
	private String supplier_edit = JavaHelpers.getPropertyValue(propertyFile,"supplier_edit");
	private String buyprice_edit = JavaHelpers.getPropertyValue(propertyFile,"buyprice_edit");
	private String futurebuyprice_edit = JavaHelpers.getPropertyValue(propertyFile,"futurebuyprice_edit");
	private String productcode_edit = JavaHelpers.getPropertyValue(propertyFile,"productcode_edit");
	private String orderunits_edit = JavaHelpers.getPropertyValue(propertyFile,"orderunits_edit");
	private String coversiontostoredunits_edit = JavaHelpers.getPropertyValue(propertyFile,"coversiontostoredunits_edit");
	
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
		brandadd = new BrandAddLib(driver);
		brandsearch = new BrandSearchLib(driver);
		lineadd = new LineAddLib(driver);
		linesearch = new LineSearchLib(driver);
		productadd = new ProductAddLib(driver);
		productsearch = new ProductSearchLib(driver);
		selenium = new SeleniumHelpers(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		timeStamp = javahelp.timeStamp();
		ex = new ExceptionHandler(driver);
	}
	
	/*Test 1 : Verify that user can add product(brand/line/product) successfully*/
	@Test (priority = 1)
	public void product_BrandLineProductCreate() throws IOException
	{
		try 
		{	
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 - Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2 - Opening Menu & Navigating to Item Setup > Products page
			Reporter.log("Step 2 - Opening Menu & Navigating to Item Setup > Products page");
			navigationlib.clickOnMenuItem("Item setup");
			navigationlib.menu_ClickOnProductsTile();
			
			//Step 3 - On brand setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 3 - On brand setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			brandname = brandname_create + timeStamp;
			brandadd.brand_Add(brandname, status_create);			
			
			//Step 4 - Searching for added brand and verifying that it has correct details
			Reporter.log("Step 4 - Searching for added brand and verifying that it has correct details");
			brandsearch.search_SearchBrandAndClickEditButton(brandname);
			
			//Details section
			
				//Verifying name
				expected = "name " + brandname;
				actual = brandsearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
			
				//Verifying status
				expected = "status " + status_create;
				actual = brandsearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
			
			//Step 5 - Click on added Brand & navigating to Line setup page
			Reporter.log("Step 5 - Click on added Brand & navigating to Line setup page");
			brandsearch.search_SearchBrandAndClick(brandname);
			
			//Step 6 - On line setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 6 - On line setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			linename = linename_create + timeStamp;
			lineadd.line_Add(linename, status_create, defaultproductusage_create);
				
			//Step 7 - Searching for added line and verifying that it has correct details
			Reporter.log("Step 7 - Searching for added line and verifying that it has correct details");
			
				//Details section
				
				//Verifying name
				expected = "name " + linename;
				actual = linesearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Default product usage
				expected = "default product usage " + defaultproductusage_create;
				actual = linesearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
			
				//Verifying status
				expected = "status " + status_create;
				actual = brandsearch.getDetailsSectionData(3);
				sfassert.assertEquals(actual, expected);
				
				
			//Step 8 - Click on added Line & navigating to product setup page
			Reporter.log("Step 8 - Click on added Line & navigating to product setup page");
			linesearch.search_SearchLineAndClick(linename);

			//Step 9 - On product setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 9 - On product setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			productname = productname_create + timeStamp;
			List<String> selectedtaxdata = productadd.product_Add(	productname, 
																	description_create, 
																	itemreference_create, 
																	defaultproductusage_create, 
																	status_create, 
																	storedunits_create, 
																	sellprice_create, 
																	futureprice_create, 
																	effectivefrom_create, 
																	1, 
																	category_create, 
																	subcategory_create, 
																	supplier_create, 
																	buyprice_create, 
																	futurebuyprice_create, 
																	effectivefrom_create, 
																	2, 
																	productcode_create, 
																	orderunits_create, 
																	coversiontostoredunits_create);
			
			//Step 10 - Searching for added product and verifying that it has correct details
			Reporter.log("Step 10 - Searching for added product and verifying that it has correct details");
			
			//Details section
			
				expected = "name " + productname;
				actual = productsearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
				
				expected = "description " + description_create;
				actual = productsearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				expected = "item reference " + itemreference_create;
				actual = productsearch.getDetailsSectionData(3);
				sfassert.assertEquals(actual, expected);
				
				expected = "status " + status_create;
				actual = productsearch.getDetailsSectionData(4);
				sfassert.assertEquals(actual, expected);
				
				expected = "product usage " + defaultproductusage_create;
				actual = productsearch.getDetailsSectionData(5);
				sfassert.assertEquals(actual, expected);
				
				expected = "stored units " + storedunits_create;
				actual = productsearch.getDetailsSectionData(6);
				sfassert.assertEquals(actual, expected);
			
				
			//Sale price section
				
				expected = "sell price $" + sellprice_create + ".00";
				actual = productsearch.getSalePriceSectionData(1);
				String message = "Expected: " + expected + " , Actual:" + actual;
				sfassert.assertTrue((actual.contains(expected)),message);
				
				expected = "future price $" + futureprice_create + ".00";
				actual = productsearch.getSalePriceSectionData(2);
				message = "Expected: " + expected + " , Actual:" + actual;
				sfassert.assertTrue((actual.contains(expected)),message);
				
				expected = "effective from " + effectivefrom_dayformat_create;
				actual = productsearch.getSalePriceSectionData(3);
				sfassert.assertEquals(actual, expected);
				
				expected = "tax category " + selectedtaxdata.get(0);
				actual = productsearch.getSalePriceSectionData(4);
				sfassert.assertEquals(actual, expected);
				
				
			//Hierarchy section
				
				expected = "brand " + brandname;
				actual = productsearch.getHierarchySectionData(1);
				sfassert.assertEquals(actual, expected);	
				
				expected = "line " + linename;
				actual = productsearch.getHierarchySectionData(2);
				sfassert.assertEquals(actual, expected);	
				
				expected = "category " + category_create;
				actual = productsearch.getHierarchySectionData(3);
				sfassert.assertEquals(actual, expected);	
				
				expected = "sub category " + subcategory_create;
				actual = productsearch.getHierarchySectionData(4);
				sfassert.assertEquals(actual, expected);	
			
				
			//Supplier section
				
				expected = supplier_create;
				actual = productsearch.getSupplierSectionData(1, 1);
				sfassert.assertEquals(actual, expected);	
				
				expected = "$" + buyprice_create + ".00";
				actual = productsearch.getSupplierSectionData(1, 3);
				sfassert.assertEquals(actual, expected);
				
				expected = "$" + futurebuyprice_create + ".00";
				actual = productsearch.getSupplierSectionData(1, 4);
				sfassert.assertEquals(actual, expected);
				
				expected = effectivefrom_dateformat_create;
				actual = productsearch.getSupplierSectionData(1, 5);
				sfassert.assertEquals(actual, expected);
				
				expected = orderunits_create;
				actual = productsearch.getSupplierSectionData(1, 6);
				sfassert.assertEquals(actual, expected);
				
				expected = productcode_create;
				actual = productsearch.getSupplierSectionData(1, 7);
				sfassert.assertEquals(actual, expected);
				
			
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 2 : Verify that user can edit product(brand/line/product) successfully*/
	@Test (priority = 2, dependsOnMethods = { "product_BrandLineProductCreate" })
	public void product_BrandLineProductUpdate() throws IOException
	{
		try 
		{	
			selenium.refreshPage();
			
			//Step 1 - On brand setup page, searching for added brand & updating information.
			Reporter.log("Step 1 - On brand setup page, searching for added brand & updating information.");
			brandsearch.search_SearchBrandAndClickEditButton(brandname);
			brandsearch.clickOnEditIcon();
			brandname = brandname_create + timeStamp;
			brandadd.brand_Add(brandname, status_create);			
			
			//Step 2 - Searching for updated brand and verifying that it has correct details
			Reporter.log("Step 2 - Searching for updated brand and verifying that it has correct details");
			brandsearch.search_SearchBrandAndClickEditButton(brandname);
			
			//Details section
			
				//Verifying name
				expected = "name " + brandname;
				actual = brandsearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
			
				//Verifying status
				expected = "status " + status_create;
				actual = brandsearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
			
			//Step 3 - Click on updated Brand & navigating to Line setup page
			Reporter.log("Step 3 - Click on updated Brand & navigating to Line setup page");
			brandsearch.search_SearchBrandAndClick(brandname);
			
			//Step 4 - On line setup page, searching for added line & updating information
			Reporter.log("Step 4 - On line setup page, searching for added line & updating information");
			linesearch.clickOnEditIcon();
			linename = linename_create + timeStamp;
			lineadd.line_Add(linename, status_create, defaultproductusage_edit);
				
			//Step 5 - Searching for updated line and verifying that it has correct details
			Reporter.log("Step 5 - Searching for updated line and verifying that it has correct details");
			
				//Details section
				
				//Verifying name
				expected = "name " + linename;
				actual = linesearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying Default product usage
				expected = "default product usage " + defaultproductusage_edit;
				actual = linesearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
			
				//Verifying status
				expected = "status " + status_create;
				actual = brandsearch.getDetailsSectionData(3);
				sfassert.assertEquals(actual, expected);
				
				
			//Step 6 - Click on updated Line & navigating to product setup page
			Reporter.log("Step 6 - Click on updated Line & navigating to product setup page");
			linesearch.search_SearchLineAndClick(linename);

			//Step 7 - On product setup page, searching for added product & updating information
			Reporter.log("Step 7 - On product setup page, searching for added product & updating information");
			
			productsearch.clickOnEditIcon();
			productname = productname_create + timeStamp;
			
			description_create = description_create + " edited";
			itemreference_create = itemreference_create + " edited";
			List<String> selectedtaxdata = productadd.product_Add(	productname, 
																	description_create, 
																	itemreference_create, 
																	defaultproductusage_edit, 
																	status_create, storedunits_edit, 
																	sellprice_edit, 
																	futureprice_edit, 
																	effectivefrom_edit, 
																	2, 
																	category_edit, 
																	subcategory_edit, 
																	supplier_edit, 
																	buyprice_edit, 
																	futurebuyprice_edit, 
																	effectivefrom_edit, 
																	1, 
																	productcode_edit, 
																	orderunits_edit, 
																	coversiontostoredunits_edit);
			
			
			//Step 8 - Searching for updated product and verifying that it has correct details
			Reporter.log("Step 8 - Searching for updated product and verifying that it has correct details");
			
			//Details section
			
				expected = "name " + productname;
				actual = productsearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
				
				expected = "description " + description_create;
				actual = productsearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				expected = "item reference " + itemreference_create;
				actual = productsearch.getDetailsSectionData(3);
				sfassert.assertEquals(actual, expected);
				
				expected = "status " + status_create;
				actual = productsearch.getDetailsSectionData(4);
				sfassert.assertEquals(actual, expected);
				
				expected = "product usage " + defaultproductusage_edit;
				actual = productsearch.getDetailsSectionData(5);
				sfassert.assertEquals(actual, expected);
				
				expected = "stored units " + storedunits_edit;
				actual = productsearch.getDetailsSectionData(6);
				sfassert.assertEquals(actual, expected);
			
				
			//Sale price section
				
				expected = "sell price $" + sellprice_edit + ".00";
				actual = productsearch.getSalePriceSectionData(1);
				String message = "Expected: " + expected + " , Actual:" + actual;
				sfassert.assertTrue((actual.contains(expected)),message);
				
				expected = "future price $" + futureprice_edit + ".00";
				actual = productsearch.getSalePriceSectionData(2);
				message = "Expected: " + expected + " , Actual:" + actual;
				sfassert.assertTrue((actual.contains(expected)),message);
				
				expected = "effective from " + effectivefrom_dayformat_edit;
				actual = productsearch.getSalePriceSectionData(3);
				sfassert.assertEquals(actual, expected);
				
				expected = "tax category " + selectedtaxdata.get(0);
				actual = productsearch.getSalePriceSectionData(4);
				sfassert.assertEquals(actual, expected);
				
				
			//Hierarchy section
				
				expected = "brand " + brandname;
				actual = productsearch.getHierarchySectionData(1);
				sfassert.assertEquals(actual, expected);	
				
				expected = "line " + linename;
				actual = productsearch.getHierarchySectionData(2);
				sfassert.assertEquals(actual, expected);	
				
				expected = "category " + category_edit;
				actual = productsearch.getHierarchySectionData(3);
				sfassert.assertEquals(actual, expected);	
				
				expected = "sub category " + subcategory_edit;
				actual = productsearch.getHierarchySectionData(4);
				sfassert.assertEquals(actual, expected);	
			
				
			//Supplier section
				
				//first row
				expected = supplier_create;
				actual = productsearch.getSupplierSectionData(1, 1);
				sfassert.assertEquals(actual, expected);	
				
				expected = "$" + buyprice_create + ".00";
				actual = productsearch.getSupplierSectionData(1, 3);
				sfassert.assertEquals(actual, expected);
				
				expected = "$" + futurebuyprice_create + ".00";
				actual = productsearch.getSupplierSectionData(1, 4);
				sfassert.assertEquals(actual, expected);
				
				expected = effectivefrom_dateformat_create;
				actual = productsearch.getSupplierSectionData(1, 5);
				sfassert.assertEquals(actual, expected);
				
				expected = orderunits_create;
				actual = productsearch.getSupplierSectionData(1, 6);
				sfassert.assertEquals(actual, expected);
				
				expected = productcode_create;
				actual = productsearch.getSupplierSectionData(1, 7);
				sfassert.assertEquals(actual, expected);
				
				//second row
				expected = supplier_edit;
				actual = productsearch.getSupplierSectionData(2, 1);
				sfassert.assertEquals(actual, expected);	
				
				expected = "$" + buyprice_edit + ".00";
				actual = productsearch.getSupplierSectionData(2, 3);
				sfassert.assertEquals(actual, expected);
				
				expected = "$" + futurebuyprice_edit + ".00";
				actual = productsearch.getSupplierSectionData(2, 4);
				sfassert.assertEquals(actual, expected);
				
				expected = effectivefrom_dateformat_edit;
				actual = productsearch.getSupplierSectionData(2, 5);
				sfassert.assertEquals(actual, expected);
				
				expected = orderunits_edit;
				actual = productsearch.getSupplierSectionData(2, 6);
				sfassert.assertEquals(actual, expected);
				
				expected = productcode_edit;
				actual = productsearch.getSupplierSectionData(2, 7);
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
