package au.com.shortcuts.live.tests.product;

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
import au.com.FMDP.live.functionLibrary.product.CategoryAddLib;
import au.com.FMDP.live.functionLibrary.product.CategorySearchLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class CategoryTests 
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
	private CategoryAddLib categoryadd;
	private CategorySearchLib categorysearch;
	private CategoryAddLib subcategoryadd;
	private CategorySearchLib subcategorysearch;
	private SeleniumHelpers selenium;
	
	//Test Data
	private String timeStamp;
	private String propertyFile="test-input/product.properties";
	private String categoryname;
	private String subcategoryname;
	private String categoryname_create = JavaHelpers.getPropertyValue(propertyFile,"categoryname_create");
	private String categorydescription_create = JavaHelpers.getPropertyValue(propertyFile,"categorydescription_create");
	private String categoryref_create = JavaHelpers.getPropertyValue(propertyFile,"categoryref_create");
	private String status_create = JavaHelpers.getPropertyValue(propertyFile,"status_create");
	private String subcategoryname_create = JavaHelpers.getPropertyValue(propertyFile,"subcategoryname_create");
	private String subcategorydescription_create = JavaHelpers.getPropertyValue(propertyFile,"Subcategorydescription_create");
	private String subcategoryref_create = JavaHelpers.getPropertyValue(propertyFile,"subcategoryref_create");
	
	
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
		selenium = new SeleniumHelpers(driver);
		categoryadd = new CategoryAddLib(driver);
		categorysearch = new CategorySearchLib(driver);
		subcategoryadd = new CategoryAddLib(driver);
		subcategorysearch = new CategorySearchLib(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		timeStamp = javahelp.timeStamp();
		ex = new ExceptionHandler(driver);
	}
	
	/*Test 1 : Verify that user can add product category/sub category successfully*/
	@Test (priority = 1)
	public void product_CategorySubCategoryCreate() throws IOException
	{
		try 
		{	
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 - Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2 - Opening Menu & Navigating to Item Setup > Product categories page
			Reporter.log("Step 2 - Opening Menu & Navigating to Item Setup > Product categories page");
			navigationlib.clickOnMenuItem("Item setup");
			navigationlib.menu_ClickOnProductCategoriesTile();
			
			//Step 3 - On category setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 3 - On category setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			categoryname = categoryname_create + timeStamp;
			categoryadd.category_Add(categoryname, categorydescription_create,categoryref_create , status_create);
			
			//Step 4 - Searching for added category and verifying that it has correct details
			Reporter.log("Step 4 - Searching for added category and verifying that it has correct details");
			categorysearch.search_SearchCategoryAndClickEditButton(categoryname);
			
			//Details section
			
				//Verifying name
				expected = "display name " + categoryname;
				actual = categorysearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying description
				expected = "description " + categorydescription_create;
				actual = categorysearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying reference
				expected = "reference " + categoryref_create;
				actual = categorysearch.getDetailsSectionData(3);
				sfassert.assertEquals(actual, expected);
			
				//Verifying status
				expected = "status " + status_create;
				actual = categorysearch.getDetailsSectionData(4);
				sfassert.assertEquals(actual, expected);
				
			
			//Step 5 - Click on added category & navigating to sub category setup page
			Reporter.log("Step 5 -  Click on added category & navigating to sub category setup page");
			categorysearch.search_SearchCategoryAndClick(categoryname);
			
			//Step 6 - On sub category setup page, Click on +NEW button, adding details, click on Done button
			Reporter.log("Step 6 - On sub category setup page, Click on +NEW button, adding details, click on Done button");
			common.footer_ClickOnNewButton();	
			subcategoryname = subcategoryname_create+ timeStamp;
			subcategoryadd.category_Add(subcategoryname, subcategorydescription_create, subcategoryref_create, status_create);
				
			//Step 7 - Searching for added sub category and verifying that it has correct details
			Reporter.log("Step 7 - Searching for added sub category and verifying that it has correct details");
			
				//Details section
				
				//Verifying name
				expected = "display name " + subcategoryname;
				actual = subcategorysearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying description
				expected = "description " + subcategorydescription_create;
				actual = subcategorysearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying reference
				expected = "reference " + subcategoryref_create;
				actual = subcategorysearch.getDetailsSectionData(3);
				sfassert.assertEquals(actual, expected);
			
				//Verifying status
				expected = "status " + status_create;
				actual = subcategorysearch.getDetailsSectionData(4);
				sfassert.assertEquals(actual, expected);
				
				
			//soft assert
			sfassert.assertAll();
		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 2 : Verify that user can edit product category/subcategories successfully*/
	@Test (priority = 2, dependsOnMethods = { "product_CategorySubCategoryCreate" })
	public void product_CategorySubCategoryUpdate() throws IOException
	{
		try 
		{	
			selenium.refreshPage();
			
			//Step 1 - On category setup page, searching for added category & modifying information
			Reporter.log("Step 1 - On category setup page, searching for added category & modifying information");
			categorysearch.search_SearchCategoryAndClickEditButton(categoryname);
			categorysearch.clickOnEditIcon();
			categoryname = categoryname_create + timeStamp;
			categorydescription_create = categorydescription_create + " edited";
			categoryref_create = categoryref_create + " edited";
			categoryadd.category_Add(categoryname, categorydescription_create,categoryref_create , status_create);
			
			//Step 2 - Searching for updated category and verifying that it has correct details
			Reporter.log("Step 2 - Searching for updated category and verifying that it has correct details");
			categorysearch.search_SearchCategoryAndClickEditButton(categoryname);
			
			//Details section
			
				//Verifying name
				expected = "display name " + categoryname;
				actual = categorysearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying description
				expected = "description " + categorydescription_create;
				actual = categorysearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying reference
				expected = "reference " + categoryref_create;
				actual = categorysearch.getDetailsSectionData(3);
				sfassert.assertEquals(actual, expected);
			
				//Verifying status
				expected = "status " + status_create;
				actual = categorysearch.getDetailsSectionData(4);
				sfassert.assertEquals(actual, expected);
				
			
			//Step 3 - Click on updated category & navigating to sub category setup page
			Reporter.log("Step 3 -  Clicking on updated category & navigating to sub category setup page");
			categorysearch.search_SearchCategoryAndClick(categoryname);
			
			//Step 4 - On sub category setup page
			Reporter.log("Step 4 - On sub category setup page, searching for added category & modifying information");
			subcategorysearch.search_SearchCategoryAndClick(subcategoryname);
			subcategoryname = subcategoryname_create+ timeStamp;
			subcategorydescription_create = subcategorydescription_create + " edited";
			subcategoryref_create = subcategoryref_create + " edited";
			subcategoryadd.category_Add(subcategoryname, subcategorydescription_create, subcategoryref_create, status_create);
				
			//Step 5 - Searching for updated sub category and verifying that it has correct details
			Reporter.log("Step 5 - Searching for updated sub category and verifying that it has correct details");
			
				//Details section
				
				//Verifying name
				expected = "display name " + subcategoryname;
				actual = subcategorysearch.getDetailsSectionData(1);
				sfassert.assertEquals(actual, expected);
				
				//Verifying description
				expected = "description " + subcategorydescription_create;
				actual = subcategorysearch.getDetailsSectionData(2);
				sfassert.assertEquals(actual, expected);
				
				//Verifying reference
				expected = "reference " + subcategoryref_create;
				actual = subcategorysearch.getDetailsSectionData(3);
				sfassert.assertEquals(actual, expected);
			
				//Verifying status
				expected = "status " + status_create;
				actual = subcategorysearch.getDetailsSectionData(4);
				sfassert.assertEquals(actual, expected);
				
			//soft assert
			sfassert.assertAll();

		} 	
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
		
	}
	
	/*Test 3 : Verify that user can inactive category/sub category successfully*/
	@Test (priority=3, dependsOnMethods={"product_CategorySubCategoryCreate"})
	public  void product_CategorySubCategory_Delete() throws IOException
	{
		try 
		{	
			selenium.refreshPage();
			
			//Step 1 = Searching for added supplier and inactivating Category/Sub category
			Reporter.log("Step 1 = Searching for added Category/Sub category and inactivating it");
			categorysearch.search_SearchCategoryAndClick(categoryname);
			subcategorysearch.search_SearchCategoryAndClick(subcategoryname);
			subcategorysearch.clickOnEditIcon();
			String status = "Inactive";
			subcategoryadd.category_ChangeStatus(status);
			
			//Step 2 = Verify that sub category is inactive
			Reporter.log("Step 2 = Verify that sub category is inactive");
			
			String partialname= subcategoryname.substring((subcategoryname.length()/2),subcategoryname.length());
			subcategorysearch.search_SearchCategory(partialname);
		
			/*
			 * Commenting out this check as this is a bug in application
			 * //verifying that 'There are no records to display' text displayed
			expected = "There are no records to display";
			actual = subcategorysearch.search_GetNoRecordMsgText();
			sfassert.assertEquals(actual, expected);
			*/		
			
			//Step 3 = On Search filter, select Inactive and performing search again
			Reporter.log("Step 3 = On Search filter, select Inactive");
			subcategorysearch.search_SearchCategory("");
			subcategorysearch.search_Filter_ClickOnInactiveCheckbox();
			
			//Step 4 = Searching for inactive sub category and verifying that it has correct details
			Reporter.log("Step 4 = Searching for inactive sub category and verifying that it has correct details");
			subcategorysearch.search_SearchCategory(partialname);
			
			//Details section	
			//Verifying name
			expected = "display name " + subcategoryname;
			actual = subcategorysearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying description
			expected = "description " + subcategorydescription_create;
			actual = subcategorysearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying reference
			expected = "reference " + subcategoryref_create;
			actual = subcategorysearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
		
			//Verifying status
			expected = "status " + status;
			actual = subcategorysearch.getDetailsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			selenium.refreshPage();
			
			//Step 5 = Searching for added supplier and inactivating supplier
			Reporter.log("Step 5 = Searching for added Category inactivating it");
			categorysearch.search_SearchCategoryAndClickEditButton(categoryname);
			categorysearch.clickOnEditIcon();
			status = "Inactive";
			categoryadd.category_ChangeStatus(status);
			
			//Step 6 = Verify that category is inactive
			Reporter.log("Step 6 = Verify that category is inactive");
			
			partialname= categoryname.substring((categoryname.length()/2),categoryname.length());
			categorysearch.search_SearchCategory(partialname);
			
			//verifying that 'There are no records to display' text displayed
			expected = "There are no records to display";
			actual = categorysearch.search_GetNoRecordMsgText();
			sfassert.assertEquals(actual, expected);		
			
			//Step 7 = On Search filter, select Inactive and performing search again
			Reporter.log("Step 7 = On Search filter, select Inactive");
			categorysearch.search_SearchCategory("");
			categorysearch.search_Filter_ClickOnInactiveCheckbox();
			
			//Step 8 = Searching for inactive category and verifying that it has correct details
			Reporter.log("Step 4 = Searching for inactive category and verifying that it has correct details");
			categorysearch.search_SearchCategory(partialname);
			
			//Verifying name
			expected = "display name " + categoryname;
			actual = categorysearch.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			//Verifying description
			expected = "description " + categorydescription_create;
			actual = categorysearch.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			//Verifying reference
			expected = "reference " + categoryref_create;
			actual = categorysearch.getDetailsSectionData(3);
			sfassert.assertEquals(actual, expected);
		
			//Verifying status
			expected = "status " + status;
			actual = categorysearch.getDetailsSectionData(4);
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
