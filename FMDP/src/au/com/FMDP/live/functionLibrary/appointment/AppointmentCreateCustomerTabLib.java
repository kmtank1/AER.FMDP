package au.com.FMDP.live.functionLibrary.appointment;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.functionLibrary.common.CommonLib;
import au.com.FMDP.live.pageObjects.appointment.AppointmentCreateCustomerTabPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.utilities.Constants;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class AppointmentCreateCustomerTabLib
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonLib help;
	AppointmentCreateCustomerTabPO customertabpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public AppointmentCreateCustomerTabLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonLib(driver);
        java = new JavaHelpers();
        customertabpo = new AppointmentCreateCustomerTabPO(driver);
        itemlist = new ItemListPO(driver);
	}
	
	
	//Search section
	
		/**
		 * Search customer by last name and click
		 * @param lastname customer last name
		 * @throws InterruptedException
		 */
		public void search_SearchCustomerAndClick(String lastname) throws InterruptedException
		{
			int i=0;
			boolean isfirstitem = false;

			do {
				itemlist.search_SearchWithText(lastname);
				
				if (itemlist.search_IsFirstItemDisplayed()) 
				{
					itemlist.search_ClickOnFirstItem();
					isfirstitem = true;
					break;
				}
				else 
				{
					i++;
					help.waitTillPageLoaded();
				}
	
			} while (i < Constants.MaxAttemptForLocatingElement);
			
			//if search result not found then failing test with message 
			if(!isfirstitem)
			{
				Assert.fail("FAIL : On Customer search tab, no search result appeared!");
			}
		}
		
	
	//Add Customer
		/**
		 * Add customer by providing information in all the fields.
		 * 
		 * @param title					Title of customer	e.g. AT_20171117_124056
		 * @param firstname				First name of customer	e.g. John
		 * @param lastname				Last name of customer	e.g. Carry
		 * @param preferredfirstname	Preferred fist name	e.g AT_preferred_firstname
		 * @param gender				Gender information	e.g. Male, Female
		 * @param mobilephone			Mobile number of employee	e.g. 9632587411
		 * @param homephone				Home number of employee	e.g. 1236547981
		 * @param email					Email address of employee	e.g. ATemail_20171218_182732@mailinator.com
		 * @param birthday				Birth day	e.g. 23, 28, 30
		 * @param birthmonth			Birth month	e.g. January, March
		 * @param birthyear				Birth year	e.g. 1988, 1991
		 * @param offerandpromotions	Offer and promotions status	e.g. Yes or No
		 * @param appointmentreminder	Appointment reminder status	e.g. Yes or No
		 * @param reminderviasms		Reminder via sms status	e.g. Yes or No
		 * @param reminderviaemail		Reminder via email status	e.g. Yes or No
		 * @param referral				Referral information	e.g. Internet, Social Media
		 * @throws InterruptedException
		 */
		public void customer_Add(String title, String firstname, String lastname, String preferredfirstname, String gender,
								 String mobilephone, String homephone, String email, String birthday, String birthmonth, String birthyear,
								 String offerandpromotions, String appointmentreminder, String reminderviasms, String reminderviaemail, String referral) throws InterruptedException
		{
			customertabpo.enterCustomerTitle(title);
			customertabpo.enterCustomerFirstName(firstname);
			customertabpo.enterCustomerLastName(lastname);
			customertabpo.enterCustomerPreferredFirstName(preferredfirstname);
			customertabpo.selectCustomerGender(gender);
			customertabpo.enterCustomerContactMobile(mobilephone);
			customertabpo.enterCustomerContactHome(homephone);
			customertabpo.enterCustomerContactEmail(email);
			customertabpo.selectCustomerBirthDay(birthday);
			customertabpo.selectCustomerBirthMonth(birthmonth);
			customertabpo.selectCustomerBirthYear(birthyear);
			customertabpo.setCustomerSendOffersAndPromotions(offerandpromotions);
			
			if (appointmentreminder.equalsIgnoreCase("Yes"))
			{
				customertabpo.setCustomerSendAppointmentReminders(appointmentreminder);
				customertabpo.setCustomerReminderViaSms(reminderviasms);
				customertabpo.setCustomerReminderViaEmail(reminderviaemail);
			}
			else if (appointmentreminder.equalsIgnoreCase("No"))
			{
				customertabpo.setCustomerSendAppointmentReminders(appointmentreminder);
			}
		
			customertabpo.selectCustomerReferral(referral);	
			help.footer_ClickOnDone();
		}
		
	
	//Search Details
			
		/**
		 * Read customer display name
		 * @return data of customer name
		 */
		public String getCustomerDisplayName()
		{
			return customertabpo.getCustomerDisplayName();
		}
		


}
