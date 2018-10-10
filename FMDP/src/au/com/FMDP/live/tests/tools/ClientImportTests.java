package au.com.shortcuts.live.tests.tools;

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

import au.com.FMDP.live.functionLibrary.common.TopNavigationLib;
import au.com.FMDP.live.functionLibrary.login.LoginLib;
import au.com.FMDP.live.functionLibrary.tools.ClientImportLib;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class ClientImportTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib ;
	//private CommonLib common;
	private JavaHelpers javahelp;
	private SoftAssert sfassert;
	private String expected;
	private String actual;
	private ExceptionHandler ex;
	private ClientImportLib clientimport;
	//private SeleniumHelpers selenium;
	private String timeStamp;
	
	
	//Test Data
	private String propertyFile="test-input/clientimport.properties";
	private String templateLocation = Constants.currentDir + Constants.downloadFilePath;
	private String templateName = JavaHelpers.getPropertyValue(propertyFile, "templateName");
	private String templateHeader = JavaHelpers.getPropertyValue(propertyFile, "templateHeader");
	private List<String> clientDetails = Arrays.asList(JavaHelpers.getPropertyValue(propertyFile, "clientDetails").split(","));
	private String clientDetailsInCSV;
	private String confirmationText = JavaHelpers.getPropertyValue(propertyFile, "confirmationText");
	private String successText = JavaHelpers.getPropertyValue(propertyFile, "successText");
	private int rownumber = Integer.parseInt(JavaHelpers.getPropertyValue(propertyFile, "rownumber"));
	private String lastname;
	private String lastname_update;
	private String email;
	private String email_update;
	private String reference;
	private String reference_update;
	boolean actualFile;
	boolean expectedFile;
	
		
	@BeforeClass
	@Parameters({"node", "browser"})
	public void setUp(@Optional("local") String node, @Optional("chrome") String browser) throws Exception
	{
		drivermanager = new WebDriverManager();
		driver = drivermanager.setUp(node,browser); 
		loginlib = new LoginLib(driver);
		navigationlib = new TopNavigationLib(driver);
		//common = new CommonLib(driver);
		javahelp = new JavaHelpers();
		clientimport = new ClientImportLib(driver);
		//selenium = new SeleniumHelpers(driver);
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		timeStamp = javahelp.timeStamp();
		ex = new ExceptionHandler(driver);
	}
	
	/*Test 1 : Verify that user can download Template, add details in template, and upload template successfully*/
	@Test (priority = 1)
	public void client_Import() throws IOException
	{
		try 
		{	
			//Step 1 - Navigating and Logging in to Live Application
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with correct credentails");
			loginlib.loginToApplication(Constants.LIVE_URL,Constants.LIVE_USERNAME, Constants.LIVE_PASSWORD);
			
			//Step 2 - Opening Menu & Navigating to Tools > Client Import page
			Reporter.log("Step 2 = Opening Menu & Navigating to Tools > Client Import page");
			navigationlib.clickOnMenuItem("Tools");
			navigationlib.menu_ClickOnClientImportTile();
			
			//Step 3 - Before download new template, delete existing files from directory then click on Download Template button
			Reporter.log("Step 3 = Before download new template, delete existing files from directory then click on Download Template button");
			javahelp.deleteAllFilesFromDirectory(templateLocation);
			clientimport.downloadNewTemplate();
			
			//Step 4 - Verifying that template is downloaded  successfully and contains correct header 
			Reporter.log("Step 4 = Verifying that template is downloaded  successfully and contains correct header");
			
			//Verifying file
			expectedFile = true;			
			actualFile = clientimport.isFileDownloaded(templateLocation, templateName);
			sfassert.assertEquals(actualFile, expectedFile);
			
			//Verifying header
			expected = templateHeader;
			actual = clientimport.getCSVData(templateLocation + templateName, ",", 0);
			sfassert.assertEquals(actual, expected);
		
			//Step 5 - Write data in to downloaded CSV file at sepecifc row number and verify client details added in template
			Reporter.log("Step 5 = Write data in to downloaded CSV file at sepecifc row number and verify client details added in template");
			
			//Update last name
			lastname = clientDetails.get(1);
			lastname_update = lastname + timeStamp;
			clientDetails.set(1, lastname_update);
			
			//Update email
			email = clientDetails.get(4);
			email_update = email + timeStamp + "@mailinator.com";
			clientDetails.set(4, email_update);
			
			//Update customer reference
			reference = clientDetails.get(20);
			reference_update = reference + timeStamp;
			clientDetails.set(20, reference_update);
				
			clientimport.insertClientDetailsInDownloadedTemplate(templateLocation + templateName, clientDetails, rownumber);
			
			//Verifying inserted client information
			clientDetailsInCSV = String.join(",", clientDetails);
			expected = clientDetailsInCSV;
			actual = clientimport.getCSVData(templateLocation + templateName, ",", 1);
			sfassert.assertEquals(actual, expected);
							
			//Step 6 - Upload updated template to create new client
			Reporter.log("Step 6 = Upload updated template to create new client");		
			clientimport.uploadCSVTemplate(templateLocation + templateName, confirmationText, successText);
			
			
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
