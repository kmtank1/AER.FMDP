package au.com.shortcuts.live.functionLibrary.appointment;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.functionLibrary.common.CommonLib;
import au.com.FMDP.live.pageObjects.appointment.AppointmentCreateDetailsTabPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class AppointmentCreateServicesTabLib
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonLib help;
	AppointmentCreateDetailsTabPO detailstabpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public AppointmentCreateServicesTabLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonLib(driver);
        java = new JavaHelpers();
        detailstabpo = new AppointmentCreateDetailsTabPO(driver);
        itemlist = new ItemListPO(driver);
	}
	
	
	/**
	 * Setup appointment by providing information in details tab
	 * @param status  status of appointment	e.g. Booked, Confirmed
	 * @param tagname	tag of appointment e.g. AT_Test
	 */
	public void detailsTab_Add(String status, String tagname)
	{
		detailstabpo.selectOptionsStatus(status);
		detailstabpo.selectDetailsTag(tagname);
	}
	
}
