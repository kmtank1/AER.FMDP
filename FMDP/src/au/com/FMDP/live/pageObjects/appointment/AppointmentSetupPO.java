package au.com.FMDP.live.pageObjects.appointment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.Constants;
import au.com.FMDP.utilities.SeleniumHelpers;

public class AppointmentSetupPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public AppointmentSetupPO(WebDriver driver)
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
	
	//Read View:
	
		//Appointments section 
		@FindBy(css=".editableSection i.icon-pencil")
		private WebElement editIcon;
			
		
	//Edit view:
		
		//Appointments section : 
		@FindBy(id="interval_minutes")
		private WebElement bookinginterval;
		
		@FindBy(css="label[for='is_appointment_creation_with_no_roster_enabled'] + *")
		private WebElement appointment_creation_with_no_roster_enabled;
		
		@FindBy(id="is_appointment_creation_with_no_roster_enabled")
		private WebElement is_appointment_creation_with_no_roster_enabled_selected;
		
		
	//Read view
	
		//Appointment section
		public String getAppointmentSectionData(int row)
		{
			String cssSelector = "fieldset[data-link='#booking-section'] ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		
		public void setIsAppointmentCreationWithNoRosterEnabled(String value)
		{
			help.setToggleValue(appointment_creation_with_no_roster_enabled, is_appointment_creation_with_no_roster_enabled_selected, value);
		}
		
		
		//Click on Edit icon
		public void clickOnEditIcon()
		{
			editIcon.click();
			help.waitTillPageLoaded();
		}			
		
		
	//Edit 	view
		public void selectBookingInterval(String interval)
		{
			selenium.selectDropdownValueByText(bookinginterval, interval);	
			help.waitTillPageLoaded();
		}
		
		
	
}
