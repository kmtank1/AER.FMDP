package au.com.shortcuts.live.functionLibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.service.ServiceAddPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;


public class ServiceAddLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help ;
	ServiceAddPO serviceadd;
	JavaHelpers java;
	
	
	public ServiceAddLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        serviceadd = new ServiceAddPO(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
	}
	

		/**
		 * Setup new service by providing information in all the fields.
		 * Update information from added service.
		 * 
		 * @param namevalue				Name of service	e.g. AT_ser_20171218_132042
		 * @param descriptiontext		Description of service	e.g. Description
		 * @param statusvalue			Status of service	e.g. Active, Inactive
		 * @param bookablevalue			Status of bookable	e.g. Yes, No 
		 * @param onlinebookingvalue	Status of online booking	e.g. Yes, No
		 * @param walkinvalue			Status of walk in	e.g. Yes, No
		 * @param selfcheckinvalue		Status of selfc checkin	e.g. Yes, No
		 * @param visitnotesvalue		Status of visit note required	e.g. Yes, No
		 * @param durationvalue			Default duration of service	e.g. 30
		 * @param breakvalue			Break duration of service	e.g. 30
		 * @param pricevalue			Price value of service	e.g. 100
		 * @param costvalue				Costs value of service	e.g. 70
		 * @param taxvalue				Tax type of service	e.g. VAT, GST, Tax Exempt
		 * @param futurepricevalue		Future price value of service	e.g. 150
		 * @param futurecostvalue		Future costs value of service	e.g. 100
		 * @param effectivefromvalue	Effective from date of service	e.g. 13/12/2018
		 * @param futuretaxvalue		Future tax type of service	e.g. VAT, GST, Tax Exempt
		 * @return						Selected value in tax drop-down
		 * 
		 */
		public List<String> service_Add(String namevalue, 
										String descriptiontext, 
										String statusvalue,
										String bookablevalue,
										String onlinebookingvalue, 
										String walkinvalue, 
										String selfcheckinvalue, 
										String visitnotesvalue, 
										String durationvalue, 
										String breakvalue, 
										String pricevalue, 
										String costvalue, 
										int taxvalue, 
										String futurepricevalue, 
										String futurecostvalue, 
										String effectivefromvalue, 
										int futuretaxvalue)
		{
			List<String> taxdata = new ArrayList<String>(); 
			serviceadd.enterServiceName(namevalue);
			serviceadd.enterServiceDescription(descriptiontext);
			serviceadd.setServiceStatus(statusvalue);
			serviceadd.setServiceBookableStatus(bookablevalue);
			serviceadd.setServiceOnlineBookingStatus(onlinebookingvalue);
			serviceadd.setServiceWalkinStatus(walkinvalue);
			serviceadd.setServiceSelfCheckin(selfcheckinvalue);	
			serviceadd.setServiceVisitNotes(visitnotesvalue);
			serviceadd.enterServiceDefaultDuration(durationvalue);
			serviceadd.enterServiceBreakDuration(breakvalue);
			serviceadd.enterServicePrice(pricevalue);
			serviceadd.enterServiceCosts(costvalue);
			String selectedtax = serviceadd.selectServiceTax(taxvalue);
			taxdata.add(selectedtax);
			serviceadd.enterServiceFutureCosts(futurecostvalue);
			serviceadd.enterServiceEffectiveFromDate(effectivefromvalue);
			selectedtax = serviceadd.selectServiceFutureTax(futuretaxvalue);
			taxdata.add(selectedtax);
			help.footer_ClickOnDone();
			return taxdata;
		}
	
	
		/**
		 * Set supplier's status i.e. Active or Inactive.
		 * @param status service status i.e. Active or Inactive
		 * 
		 */
		public void service_ChangeStatus(String status)
		{
			serviceadd.setServiceStatus(status);
			help.footer_ClickOnDone();
		}
	
	
}
