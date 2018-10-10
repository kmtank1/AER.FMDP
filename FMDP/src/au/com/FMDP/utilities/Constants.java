package au.com.FMDP.utilities;

public class Constants
{
	public static final String PROPERTYFILE="test-input/constants.properties";	
	
	//File download location
	public static final String downloadFilePath = "\\download\\";
	public static final String currentDir = System.getProperty("user.dir");
	
	//Environment
	public static final String Env = JavaHelpers.getPropertyValue(PROPERTYFILE,"Env");
	
	//Setting up the Shortcuts live URLs
	public static final String LIVE_URL = JavaHelpers.getPropertyValue(PROPERTYFILE,"live_url_" + Env);
	
		//Login Details
		public static final String LIVE_USERNAME = JavaHelpers.getPropertyValue(PROPERTYFILE, "live_username_" + Env);
		public static final String LIVE_PASSWORD = JavaHelpers.getPropertyValue(PROPERTYFILE,"live_password_" + Env);
		public static final String LIVE_NAME = JavaHelpers.getPropertyValue(PROPERTYFILE,"live_name_" + Env);
		
		public static final String LIVE_USERNAME1 = JavaHelpers.getPropertyValue(PROPERTYFILE, "live_username_" + Env + "1");
		public static final String LIVE_PASSWORD1 = JavaHelpers.getPropertyValue(PROPERTYFILE,"live_password_" + Env + "1");
		public static final String LIVE_NAME1 = JavaHelpers.getPropertyValue(PROPERTYFILE,"live_name_" + Env + "1");
		
	//Selenium constants
	public static final int WEBDRIVER_WAIT_DURATION= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE,"WebDriverWaitDuration"));
	public static final int PAGEFACTORY_WAIT_DURATION= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE,"PageFactoryWaitDuration"));
	public static final String SCREENSHOT_LOCATION= JavaHelpers.getPropertyValue(PROPERTYFILE,"ScreenshotLocation");
	public static final int MaxAttemptForLocatingElement= Integer.parseInt(JavaHelpers.getPropertyValue(PROPERTYFILE,"MaxAttemptForLocatingElement"));
	
}


