package au.com.shortcuts.live.functionLibrary.pos;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.pos.POSSetupPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class POSSetupLib
{

	WebDriver driver;
	SeleniumHelpers selenium;
	JavaHelpers java;
	CommonPO common;
	POSSetupPO possetup;
	
	public POSSetupLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        java = new JavaHelpers(); 
        common = new CommonPO(driver);
        possetup = new POSSetupPO(driver);
	}
	
	/**
	 * Add customer by providing information in all the fields.
	 * 
	 * @param titlevalue			Title of customer e.g. AT_20171117_124056
	 * @param firstname				First name of customer	e.g. John
	 * @param lastname				Last name of customer	e.g. Carry
	 * @param preferredfirstname	Preferred first name of customer	e.g. AT_Pre_20171117_124056
	 * @param gendervalue			Gender information of customer	e.g. Male, Female
	 * @param mobilephone			Mobile number of customer	e.g. 9545452568
	 * @param homephone				Home number of customer	e.g. 12564258652
	 * @param emailvalue			Email address of customer	e.g. ATemail_20171218_182732@mailinator.com
	 * @param offerandpromotions	Offer and promotions status of customer	e.g. Yes, No
	 * @param appointmentreminder	Appointment reminder status of customer	e.g. Yes, No
	 * @param reminderviasms		Reminder via SMS status of customer	e.g. Yes, No
	 * @param reminderviaemail		Reminder via Email status of customer	e.g. Yes, No
	 * @param street1value			Address Street1	e.g. Cybage Software
	 * @param street2value			Address Street2	e.g. Infocity
	 * @param suburbvalue			Address Suburb		e.g. Gandhinagar
	 * @param statevalue			Address State		e.g. Gujarat
	 * @param postalcodevalue		Address PCode		e.g. 31500
	 * @param birthday				Birth Day of customer	e.g. 25, 15, 12
	 * @param birthmonth			Birth Month of customer	e.g. May, April
	 * @param birthyear				Birth Year of customer	e.g. 1989, 1992
	 * @param alertmessagevalue		Alert message information	e.g. AT_Alert_Message
	 * @param referral				Referral information	e.g. Internet, Social Media
	 * @param referencevalue		Reference value	e.g. Reference_20171117_124056
	 * @param taxexempt				Tax exempt status of customer	e.g. Yes, No
	 */
	public void customer_Add(String titlevalue, String firstname, String lastname, String preferredfirstname, String gendervalue,String mobilephone, String homephone,
							 String emailvalue, String offerandpromotions, String appointmentreminder, String reminderviasms, String reminderviaemail,
							 String street1value, String street2value, String suburbvalue, String statevalue, String postalcodevalue,
							 String birthday, String birthmonth, String birthyear, String alertmessagevalue, String referral, String referencevalue, String taxexempt)
	{
		possetup.enterCustomerTitle(titlevalue);
		possetup.enterCustomerFirstName(firstname);
		possetup.enterCustomerLastName(lastname);
		possetup.enterCustomerPreferredFirstName(preferredfirstname);
		possetup.selectCustomerGender(gendervalue);
		possetup.enterCustomerContactMobile(mobilephone);
		possetup.enterCustomerContactHome(homephone);
		possetup.enterCustomerContactEmail(emailvalue);
		possetup.setCustomerSendOffersAndPromotions(offerandpromotions);
		possetup.setCustomerSendAppointmentReminders(appointmentreminder);
		possetup.setCustomerReminderViaSms(reminderviasms);
		possetup.setCustomerReminderViaEmail(reminderviaemail);
		possetup.enterCustomerAddress1(street1value);
		possetup.enterCustomerAddress2(street2value);
		possetup.enterCustomerSuburb(suburbvalue);
		possetup.enterCustomerState(statevalue);
		possetup.enterCustomerPostalCode(postalcodevalue);
		possetup.selectCustomerBirthDay(birthday);
		possetup.selectCustomerBirthMonth(birthmonth);
		possetup.selectCustomerBirthYear(birthyear);
		possetup.enterCustomerAlertMessage(alertmessagevalue);
		possetup.selectCustomerReferral(referral);
		possetup.enterCustomerReference(referencevalue);
		possetup.setTaxExempt(taxexempt);
		common.footer_ClickOnDone();
	}
	
	
	/**
	 * Select entity 
	 * @param entityname   i.e. Service, Product
	 * @throws InterruptedException
	 */
	public void clickOnEntityItem(String entityname) throws InterruptedException
	{
		if(entityname.equalsIgnoreCase("service"))
		{
			possetup.ClickOnService();
		}
		if(entityname.equalsIgnoreCase("product"))
		{
			possetup.ClickOnProduct();
		}
	}



}
