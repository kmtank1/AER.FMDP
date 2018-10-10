package au.com.shortcuts.live.functionLibrary.appointment;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.appointment.AppointmentSetupPO;
import au.com.FMDP.live.pageObjects.common.CommonPO;

public class AppointmentSetupLib 
{
	WebDriver driver;
	CommonPO help ;
	AppointmentSetupPO appointmentsetup;
	
	public AppointmentSetupLib(WebDriver driver)
	{	 
        this.driver = driver;
        appointmentsetup = new AppointmentSetupPO(driver);
        help = new CommonPO(driver);
	}
	
	
	/**
	 * Click on Edit button
	 */
	public void clickOnEditIcon()
	{
		appointmentsetup.clickOnEditIcon();
	}
		
	//Appointments section
	/**
	 * Change booking interval
	 * @param interval booking interval e.g. 40,60
	 * @throws InterruptedException
	 */
	public void appointmentsetup_ChangeBookingIntervalAndClickOnDone(String interval)
	{
		appointmentsetup.selectBookingInterval(interval);
		help.footer_ClickOnDone();
	}
	
	/***
	 * Set value of 'Allow appointments to be created on a day with no roster entries' toggle
	 * @param value yes or no
	 * @throws InterruptedException
	 */
	public void appointmentsetup_setIsAppointmentCreationWithNoRosterEnabledAndClickOnDone(String value)
	{
		appointmentsetup.setIsAppointmentCreationWithNoRosterEnabled(value);
		help.waitTillPageLoaded();
		help.footer_ClickOnDone();
	}
	
	//Read Data
	/**
	 * Read Appointment section data row wise including lables
	 * @param row
	 * @return data for given row
	 */
	public String getAppointmentsSectionData(int row)
	{
		return appointmentsetup.getAppointmentSectionData(row);
	}
	


	
}
