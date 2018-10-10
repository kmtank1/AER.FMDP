package au.com.shortcuts.live.functionLibrary.settings.customfields;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.settings.customfields.CustomFieldEntityTypePO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class CustomFieldEntityTypeLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	CustomFieldEntityTypePO entitypo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public CustomFieldEntityTypeLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        entitypo = new CustomFieldEntityTypePO(driver);
        itemlist = new ItemListPO(driver);
	}
	
	/**
	 * Select entity 
	 * @param entityname   i.e. Customer, Employee
	 * @throws InterruptedException
	 */
	public void clickOnEntityItem(String entityname) throws InterruptedException
	{
		if(entityname.equalsIgnoreCase("customer"))
		{
			entitypo.ClickOnCustomer();
		}
		if(entityname.equalsIgnoreCase("employee"))
		{
			entitypo.ClickOnEmployee();
		}
	}
}
