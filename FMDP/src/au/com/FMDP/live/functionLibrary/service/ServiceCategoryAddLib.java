package au.com.shortcuts.live.functionLibrary.service;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.service.ServiceCategoryAddPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ServiceCategoryAddLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help ;
	ServiceCategoryAddPO servicecategoryadd;
	ItemListPO itemlist;
	JavaHelpers java;
	
	
	public ServiceCategoryAddLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        servicecategoryadd = new ServiceCategoryAddPO(driver);
        help = new CommonPO(driver);
        itemlist = new ItemListPO(driver);
        java = new JavaHelpers();
	}
	
	/**
	 * Setup new service category by providing information in all the fields.
	 * Update the information of added service category.
	 * 
	 * @param namevalue				Name of service category	e.g. AT_cat_20171218_125610
	 * @param statusvalue			Status of service category	e.g. Active, Inactive
	 * @param onlinebookingvalue	Online booking status of service category	e.g. Yes, No
	 * @param referencevalue		Reference information of service category	e.g. Hair
	 * 
	 */
	public void servicecategory_Add(String namevalue, String statusvalue, String onlinebookingvalue, String referencevalue)
	{
		servicecategoryadd.enterServiceCategoryName(namevalue);
		servicecategoryadd.setServiceCategoryStatus(statusvalue);
		servicecategoryadd.setServiceCategoryOnlineBookingStatus(onlinebookingvalue);
		servicecategoryadd.enterServiceCategoryReference(referencevalue);
		help.footer_ClickOnDone();
	}
	
	/**
	 * Set Service category status
	 * @param status status i.e. Active or Inactive
	 * 
	 */
	public void servicecategory_ChangeStatus(String status)
	{
		servicecategoryadd.setServiceCategoryStatus(status);
		help.footer_ClickOnDone();
	}
	
	
	
}
