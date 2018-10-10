package au.com.FMDP.live.pageObjects.appointment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.Constants;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class AppointmentCreateCustomerTabPO
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	JavaHelpers java;
	
	public AppointmentCreateCustomerTabPO(WebDriver driver)
	{
		this.driver = driver;
		selenium = new SeleniumHelpers(driver);
		help = new CommonPO(driver);
		java = new JavaHelpers();
		
		//This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}
	
	
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 

		@FindBy(id="customers-searchBox")
		private WebElement searchcustomer_Input;
			
		@FindBy(id="display_name")
		private WebElement displayname;
	
		@FindBy(css="#item-list > div > ul li")
		private WebElement search_FirstItem;
		
		@FindBy(id="title")
		private WebElement title;
		
		@FindBy(id="first_name")
		private WebElement firstName;
		
		@FindBy(id="last_name")
		private WebElement lastName;
		
		@FindBy(id="preferred_name")
		private WebElement preferredName;
		
		@FindBy(id="gender_code")
		private WebElement genderdrop;
		
		@FindBy(id="mobile_phone")
		private WebElement mobilePhone;
		
		@FindBy(id="home")
		private WebElement homePhone;
		
		@FindBy(id="email")
		private WebElement email;
		
		@FindBy(id="birth_day")
		private WebElement birthDay;
		
		@FindBy(id="birth_month")
		private WebElement birthMonth;
		
		@FindBy(id="birth_year")
		private WebElement birthYear;
		
		@FindBy(css="label[for='is_not_to_receive_marketing'] + *")
		private WebElement sendOffersAndPromotions;
		
		@FindBy(id="is_not_to_receive_marketing")
		private WebElement isSendOffersAndPromotions;
		
		@FindBy(css="label[for='is_not_to_receive_appointment_reminders'] + *")
		private WebElement sendAppointmentReminder;
		
		@FindBy(id="is_not_to_receive_appointment_reminders")
		private WebElement isSendAppointmentReminder;
		
		@FindBy(css="label[for='is_reminder_via_sms'] + *")
		private WebElement sendReminderViaSms;
		
		@FindBy(id="is_reminder_via_sms")
		private WebElement isSendReminderVisSms;
		
		@FindBy(css="label[for='is_reminder_via_email'] + *")
		private WebElement sendReminderViaEmail;
		
		@FindBy(id="is_reminder_via_email")
		private WebElement isSendReminderViaEmail;
		
		@FindBy(id="referrer")
		private WebElement referrerdrop;
		
	
	//Search customer
		public void search_SearchCustomerWithText(String customername)
		{
			searchcustomer_Input.clear();
			searchcustomer_Input.sendKeys(customername);
			help.waitTillPageLoaded();
		}
		
		public boolean search_IsFirstItemDisplayed()
		{
			try 
			{
	            return search_FirstItem.isDisplayed();
			} 
			catch (Exception e) 
			{
	            return false;
	        }
		}
		
		public void search_ClickOnFirstItem()
		{
			selenium.waitTillElementIsClickable(search_FirstItem).click();
			help.waitTillPageLoaded();
		}

		
	//Add Customer
		
		public void enterCustomerTitle(String titlevalue)
		{
			title.clear();
			title.sendKeys(titlevalue);
		}
		
		public void enterCustomerFirstName(String firstname)
		{
			firstName.clear();
			firstName.sendKeys(firstname);
		}
		
		public void enterCustomerLastName(String lastname)
		{
			lastName.clear();
			lastName.sendKeys(lastname);
		}
		
		public void enterCustomerPreferredFirstName(String preferredfirstname)
		{
			preferredName.clear();
			preferredName.sendKeys(preferredfirstname);
		}
		
		public void selectCustomerGender(String gendervalue)
		{
			selenium.selectDropdownValueByText(genderdrop, gendervalue);
		}
		
		public void enterCustomerContactMobile(String mobilephone)
		{
			mobilePhone.clear();
			mobilePhone.sendKeys(mobilephone);
		}
		
		public void enterCustomerContactHome(String homephone)
		{
			homePhone.clear();
			homePhone.sendKeys(homephone);
		}
		
		public void enterCustomerContactEmail(String emailvalue)
		{
			email.clear();
			email.sendKeys(emailvalue);
		}
		
		public void selectCustomerBirthDay(String birthday)
		{
			selenium.selectDropdownValueByText(birthDay, birthday);
		}
		
		public void selectCustomerBirthMonth(String birthmonth)
		{
			selenium.selectDropdownValueByText(birthMonth, birthmonth);
		}
		
		public void selectCustomerBirthYear(String birthyear)
		{
			selenium.selectDropdownValueByText(birthYear, birthyear);
		}
		
		public void setCustomerSendOffersAndPromotions(String offerandpromotions)
		{
			help.setToggleValue(sendOffersAndPromotions, isSendOffersAndPromotions, offerandpromotions);
		}
		
		public void setCustomerSendAppointmentReminders(String appointmentreminder)
		{
			help.setToggleValue(sendAppointmentReminder, isSendAppointmentReminder, appointmentreminder);
		}
		
		public void setCustomerReminderViaSms(String reminderviasms)
		{
			help.setToggleValue(sendReminderViaSms, isSendReminderVisSms, reminderviasms);
		}
		
		public void setCustomerReminderViaEmail(String reminderviaemail)
		{
			help.setToggleValue(sendReminderViaEmail, isSendReminderViaEmail, reminderviaemail);
		}
		
		public void selectCustomerReferral(String referral)
		{
			selenium.selectDropdownValueByText(referrerdrop, referral);
		}
		
	
	//Customer detail
		public String getCustomerDisplayName()
		{	
			return displayname.getText();
		}
		
		
		
		
}
