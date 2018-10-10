package au.com.FMDP.live.functionLibrary.appointment;
import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.appointment.AppointmentBookPO;
import au.com.FMDP.live.pageObjects.common.CommonPO;

public class AppointmentBookLib 
{
	WebDriver driver;
	CommonPO help ;
	AppointmentBookPO appointmentbook;
	
	public AppointmentBookLib(WebDriver driver)
	{	 
        this.driver = driver;
        appointmentbook = new AppointmentBookPO(driver);
        help = new CommonPO(driver);
	}
	
	
	/**
	 * Get nth appointment book timeslot
	 */
	public String getNthTimeslot(int nth)
	{
		return appointmentbook.getNthTimeSlot(nth);
	}
		
	
	
	//Appointment Grid
	/***
	 * Is hour displayed on grid
	 * @return true or false
	 */
	public boolean isHourDisplayedOnGrid()
	{
		boolean b;
		try 
		{
			appointmentbook.getHourText();
			b= true;
		} 
		catch (Exception e) 
		{
			b= false;
		}
		return b;
	}
	
	/***
	 * get appointment message 
	 * @return text
	 */
	public String getAppointmentBookMessage()
	{
		return appointmentbook.getAppointmentBookMessage();
	}
	
}
