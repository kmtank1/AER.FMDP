package au.com.shortcuts.live.pageObjects.employee;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.functionLibrary.common.CommonLib;
import au.com.FMDP.utilities.SeleniumHelpers;

public class EmployeeAddPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonLib help;
	
	public EmployeeAddPO(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonLib(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}
	
	
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 	
	
	//Details section : 
		@FindBy(id="display_name")
		private WebElement alias;
	
		@FindBy(id="first_name")
		private WebElement firstName;
		
		@FindBy(id="last_name")
		private WebElement lastName;
		
		@FindBy(id="gender_code")
		private WebElement genderdrop;
		
		@FindBy(id="start_date")
		private WebElement startDate;
		
		@FindBy(id="biography_text")
		private WebElement biography;
		
		@FindBy(css="label[for='is_active'] + *")
		private WebElement status;
		
		@FindBy(id="is_active")
		private WebElement isactive;
		
		@FindBy(css="label[for='is_available_for_walkin'] + *")
		private WebElement availableForWalkins;
		
		@FindBy(id="is_available_for_walkin")
		private WebElement IsAvailableForWalkins;
		
	//Custom field section
		@FindBy(css="#employee_fields li")
		private List<WebElement> customfieldsection;
		
        
	//Contact Section
		@FindBy(id="mobile")
		private WebElement mobile;
	
		@FindBy(id="home")
		private WebElement home;
		
		@FindBy(id="email")
		private WebElement email;
		
		
	//Home address 
		@FindBy(id="address_street_1")
		private WebElement street1;
	
		@FindBy(id="address_street_2")
		private WebElement street2;
		
		@FindBy(id="address_suburb")
		private WebElement suburb;
		
		@FindBy(id="address_state")
		private WebElement state;
	
		@FindBy(id="address_postal_code")
		private WebElement pcode;
		
		
	//Appointment Book
		@FindBy(id="booking_interval_minutes")
		private WebElement interval;
			
		@FindBy(css="label[for='is_appointment_reminders_enabled'] + *")
		private WebElement sendConfirmationToClients;
		
		@FindBy(id="is_appointment_reminders_enabled")
		private WebElement isSendConfirmationToClients;
		
		@FindBy(css="label[for='app_book_available'] + *")
		private WebElement showinAppointmentBook;
		
		@FindBy(id="app_book_available")
		private WebElement isShowinAppointmentBook;
		
		
	//Field validations
		@FindBy(css="div.app-errorContainer span")
		private List<WebElement> fieldValidatons;
		
		//All fields validation messages 
		public String getAllFieldValidationMessages()
		{
			String msg="";
			
			for(WebElement e:fieldValidatons)
			{
				msg = msg + e.getText().trim();
			}
			return msg;
		}

		
	//Details section : 
		public void enterEmployeeAlias(String aliasvalue)
		{
			alias.click();
			alias.clear();
			alias.sendKeys(aliasvalue);
		}
		
		public void enterEmployeeFirstName(String firstname)
		{
			firstName.clear();
			firstName.sendKeys(firstname);
		}
		
		public void enterEmployeeLastName(String lastname)
		{
			lastName.clear();
			lastName.sendKeys(lastname);
		}
		
		public void selectEmployeeGender(String gender)
		{
			selenium.selectDropdownValueByText(genderdrop, gender);
		}
		
		public void enterEmployeeStartDate(String startdate)
		{
			startDate.clear();
			startDate.sendKeys(startdate);
		}
		
		public void enterEmployeeBiography(String biographydetails)
		{
			biography.clear();
			biography.sendKeys(biographydetails);
		}
		
		public void setEmployeeStatus(String statusvalue)
		{
			help.setToggleValue(status, isactive, statusvalue);
		}
		
		public void setEmployeeWalkin(String walkin)
		{
			help.setToggleValue(availableForWalkins, IsAvailableForWalkins, walkin);		
		}
		
		public void enterEmployeeMobile(String mobilevalue)
		{
			selenium.pageScrollInView(mobile);
			mobile.clear();
			mobile.sendKeys(mobilevalue);
		}
        
	//Contact Section
        public void clickOnEmployeeMobile()
		{
			mobile.click();
		}
        
		public void enterEmployeeHomeNumber(String homevalue)
		{
			home.clear();
			home.sendKeys(homevalue);
		}
		
		public void enterEmployeeEmail(String emailvalue)
		{
			email.clear();
			email.sendKeys(emailvalue);
		}
		
		
	//Home address 	
		public void enterEmployeeHomeAddressStreet1(String street1value)
		{
			street1.clear();
			street1.sendKeys(street1value);
		}
		
		public void enterEmployeeHomeAddressStreet2(String street2value)
		{
			street2.clear();
			street2.sendKeys(street2value);
		}
		
		public void enterEmployeeHomeAddressSuburb(String suburbvalue)
		{
			suburb.clear();
			suburb.sendKeys(suburbvalue);
		}
		
		public void enterEmployeeHomeAddressState(String statevalue)
		{
			state.clear();
			state.sendKeys(statevalue);
		}
		
		public void enterEmployeeHomeAddressPcode(String pcodevalue)
		{
			pcode.clear();
			pcode.sendKeys(pcodevalue);
		}
		
		
	//Appointment Book	
		public void selectEmployeeAppointmentInterval(String intervalvalue)
		{
			selenium.selectDropdownValueByText(interval, intervalvalue);
		}
		
		public void setEmployeeSendConfirmationToClients(String sendconfirmation)
		{
			help.setToggleValue(sendConfirmationToClients, isSendConfirmationToClients, sendconfirmation);
		}
		
		public void setEmployeeShowInAppointmentBook(String showinappointmentbook)
		{
			help.setToggleValue(showinAppointmentBook, isShowinAppointmentBook, showinappointmentbook);
		}


	//Custom Fields
		/**
		 * Capture all custom field from employee page and
		 * compare with actual added custom field 
		 *  
		 * @param customfieldname	Name of added custom field e.g. AT_Employee_CF
		 * @return					true or false
		 */
		public boolean IsCustomFieldPresent(String customfieldname)
		{	
			boolean b=false;
			for (WebElement e : customfieldsection)
			{
				selenium.pageScrollInView(e);
				if (e.getText().equalsIgnoreCase(customfieldname))
				{	
					b= true;
					break;
				}
			}
			return b;
		}	
			
}
