package au.com.FMDP.live.tests.login;

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
import au.com.FMDP.utilities.Constants;
import au.com.FMDP.utilities.ExceptionHandler;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;
import au.com.FMDP.utilities.WebDriverManager;

public class LoginTests 
{
	private WebDriver driver;
	private WebDriverManager drivermanager;
	private LoginLib loginlib;
	private TopNavigationLib navigationlib;
	private CommonLib common ;
	private SeleniumHelpers selenium;
	private SoftAssert sfassert;
	private ExceptionHandler ex;
	
	//Test Data
	private String propertyFile="test-input/login.properties";
	private String username = JavaHelpers.getPropertyValue(propertyFile,"username_" + Constants.Env);
	private String password = JavaHelpers.getPropertyValue(propertyFile,"password1_" + Constants.Env);
	private String newpassword = JavaHelpers.getPropertyValue(propertyFile,"password2_" + Constants.Env);
	private String name = JavaHelpers.getPropertyValue(propertyFile,"name_" + Constants.Env);
	private String errorMessage = JavaHelpers.getPropertyValue(propertyFile,"loginErrorMessage");
	
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
	}	
	

	@BeforeMethod
	public void beforeMethod()
	{
		sfassert = new SoftAssert();
		ex = new ExceptionHandler(driver);
	}
	
	
	/*Test 1 : Verify that user can login/logout/change password successfully*/
	@Test (priority = 2)
	public void login_ChangePassword() throws IOException
	{
		try 
		{
			Reporter.log("Step 1 = Login to application");
			loginlib.loginToApplication(username, password);
			if(loginlib.isErrorMessageDispalyed())
			{
				loginlib.loginToApplication(username, newpassword);
				String temp = password;
				password = newpassword;
				newpassword = temp;
			}
			common.waitAfterLogin();
			
			Reporter.log("Step 2 = Verify that user is logged in or not");
			sfassert.assertEquals(navigationlib.getLoggedInUserName(), name);
			
			Reporter.log("Step 3 = Click on profile image and performing change password");
			navigationlib.changePassword_FillDetailsAndDone(password, newpassword);
			
			Reporter.log("Step 4 = Click on profile image and logout, and login as new password");
			navigationlib.logout();			
			loginlib.loginToApplication(Constants.LIVE_URL,username, newpassword);
			
			Reporter.log("Step 5 = Verify that user is logged in or not");
			sfassert.assertEquals(navigationlib.getLoggedInUserName(), name);
			
			//soft assert
			sfassert.assertAll();
		} 
		catch (AssertionError | Exception e) 
		{
			ex.TakeScreenshotAndLogException(e.getMessage());
		}
	}
	
	
	/*Test 2 : Verify that user can't login with incorrect credentials */
	@Test (priority = 1)
	public void login_Failure() throws IOException
	{
		try {
			Reporter.log("Step 1 = Login to application at : " + Constants.LIVE_URL + " and loging in with incorrect credentails");
			selenium.navigateToPage(Constants.LIVE_URL);
			loginlib.loginToApplication("random@mail.com", "password");
			
			Reporter.log("Step 2 = Verify the validation message displayed");
			sfassert.assertEquals(loginlib.getErrorMessage(), errorMessage);
			
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
