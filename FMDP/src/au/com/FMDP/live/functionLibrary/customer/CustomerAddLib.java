package au.com.shortcuts.live.functionLibrary.customer;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.customer.CustomerAddPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class CustomerAddLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help ;
	CustomerAddPO customeradd;
	ItemListPO itemlist;
	JavaHelpers java;
	
	
	public CustomerAddLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        customeradd = new CustomerAddPO(driver);
        help = new CommonPO(driver);
        itemlist = new ItemListPO(driver);
        java = new JavaHelpers();
	}
	
	/**
	 * Capture all custom field from customer page and
	 * compare with actual added custom field 
	 *  
	 * @param customfieldname	Name of added custom field e.g. AT_Customer_CF
	 * @return					true or false
	 */
	
	public boolean IsCustomFieldPresent(String customfieldname)
	{	
		return customeradd.IsCustomFieldPresent(customfieldname);
	}
	
	
	
}
