package au.com.FMDP.live.pageObjects.appointment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.Constants;
import au.com.FMDP.utilities.SeleniumHelpers;

public class AppointmentBookPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public AppointmentBookPO(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}
	
	
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 	
	
	//Appointments Grid
		By hoursinfo = By.className("appointmentbookhouroverlay");
		
		By appointmentbookmessage = By.className("appointmentbookmessage");
	
	
	//Timebar section 
		
		public String getNthTimeSlot(int nth)
		{
			String cssSelector = "#TimeBackground > text:nth-of-type(" + nth + ")";
			return driver.findElement(By.cssSelector(cssSelector)).getText().trim();
		}
		
		
	//Appointments Grid
		
		//get hour text
		public String getHourText()
		{
			return selenium.waitTillElementIsClickable(hoursinfo,5).getText().trim();
		}

		//get appointment message
		public String getAppointmentBookMessage()
		{
			return selenium.waitTillElementIsClickable(appointmentbookmessage, 5).getText().trim();
		}
		
}
