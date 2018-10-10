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
import au.com.FMDP.live.functionLibrary.pos.ReceiptSetupLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class ReceiptSetupTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	private ReceiptSetupLib receipt;
	private JavaHelpers javahelp;
	private String timeStamp;
	
	
	@BeforeClass
	@Parameters({"node", "browser"})
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception
	{
		drivermanager = new WebDriverManager();
		driver = drivermanager.setUp(node,browser); 
		loginlib = new LoginLib(driver);
		navigationlib = new TopNavigationLib(driver);
		receipt = new ReceiptSetupLib(driver);
		javahelp = new JavaHelpers();
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		ex = new ExceptionHandler(driver);
	}
	
	
	/*Test 1 : Verify that user can edit receipt setup information successfully*/
	@Test (priority = 1)
	public void receiptsetup_Update() throws IOException
	{
		try 
		{	
		
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2- Opening Menu & Navigating to POS setup > Receipt page
			Reporter.log("Step 2 = Opening Menu & Navigating to POS setup > Receipt page");
			navigationlib.clickOnMenuItem("POS setup");
			navigationlib.menu_menu_ClickOnReceiptTile();
			
			//Step 3- On receipt setup page, editing information
			Reporter.log("Step 3- On receipt setup page, editing information");
			receipt.clickOnEditIcon();
			
			timeStamp = javahelp.timeStamp();
			String heading = "Heading added by AT " + timeStamp ;
			String custommessage = "This is a custom message by AT " + timeStamp;
			String insertfield= "Customer first name";
			String insertfielduppercase= "Customer First Name";
			String defaultsize="Docket";
			String isprinted= "No";
			String isemailed="No";
			String isreceipt="No";
			String hideclients="No";
			String includeappts="No";
			
			receipt.receiptsetup_Edit(heading, custommessage, insertfield, defaultsize, isprinted, isemailed, isreceipt, hideclients, includeappts);
			
			
			//Details section
			
			expected = "heading " + heading;
			actual = receipt.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "custom receipt message " + custommessage + " {{" + insertfielduppercase + "}}";
			actual = receipt.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			
			//Option section
			
			expected = "default receipt size " + defaultsize;
			actual = receipt.getOptionsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "allow receipts to be printed " + isprinted ;
			actual = receipt.getOptionsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "allow receipts to be emailed " + isemailed ;
			actual = receipt.getOptionsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			expected = "allow no receipt " + isreceipt;
			actual = receipt.getOptionsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "hide client details " + hideclients ;
			actual = receipt.getOptionsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			expected = "include future appointments " + includeappts;
			actual = receipt.getOptionsSectionData(6);
			sfassert.assertEquals(actual, expected);
			
			
			
			//Step 4- Again on receipt setup page, editing information with different data
			Reporter.log("Step 4- Again on receipt setup page, editing information with different data");
			receipt.clickOnEditIcon();
			
			timeStamp = javahelp.timeStamp();
			heading = "Heading added by AT " + timeStamp ;
			custommessage = "This is a custom message by AT " + timeStamp;
			insertfield= "Customer last name";
			insertfielduppercase= "Customer Last Name";
			defaultsize="A4";
			isprinted= "Yes";
			isemailed="Yes";
			isreceipt="Yes";
			hideclients="Yes";
			includeappts="Yes";
			
			receipt.receiptsetup_Edit(heading, custommessage, insertfield, defaultsize, isprinted, isemailed, isreceipt, hideclients, includeappts);
			
			
			//Details section
			
			expected = "heading " + heading;
			actual = receipt.getDetailsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "custom receipt message " + custommessage + " {{" + insertfielduppercase + "}}";
			actual = receipt.getDetailsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			
			//Option section
			
			expected = "default receipt size " + defaultsize;
			actual = receipt.getOptionsSectionData(1);
			sfassert.assertEquals(actual, expected);
			
			expected = "allow receipts to be printed " + isprinted ;
			actual = receipt.getOptionsSectionData(2);
			sfassert.assertEquals(actual, expected);
			
			expected = "allow receipts to be emailed " + isemailed ;
			actual = receipt.getOptionsSectionData(3);
			sfassert.assertEquals(actual, expected);
			
			expected = "allow no receipt " + isreceipt;
			actual = receipt.getOptionsSectionData(4);
			sfassert.assertEquals(actual, expected);
			
			expected = "hide client details " + hideclients ;
			actual = receipt.getOptionsSectionData(5);
			sfassert.assertEquals(actual, expected);
			
			expected = "include future appointments " + includeappts;
			actual = receipt.getOptionsSectionData(6);
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
