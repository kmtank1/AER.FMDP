package au.com.shortcuts.live.functionLibrary.product;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.product.BrandAddPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class BrandAddLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help ;
	BrandAddPO brandadd;
	ItemListPO itemlist;
	JavaHelpers java;
	
	
	public BrandAddLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        brandadd = new BrandAddPO(driver);
        help = new CommonPO(driver);
        itemlist = new ItemListPO(driver);
        java = new JavaHelpers();
	}
	
	/**
	 * Setup brand by providing information in all the fields.
	 * Update information from added brand.
	 * 
	 * @param namevalue		Name of the brand	e.g. AT_brand_20171207_101804
	 * @param statusvalue	State of the brand	e.g. Active, Inactive
	 * 
	 */
	public void brand_Add(String namevalue, String statusvalue)
	{
		brandadd.enterBrandName(namevalue);
		brandadd.setBrandStatus(statusvalue);
		help.footer_ClickOnDone();
	}
	
	/***
	 * Change brand status
	 * @param status status value i.e. active/inactive
	 */
	public void brand_ChangeStatus(String status)
	{
		brandadd.setBrandStatus(status);
		help.footer_ClickOnDone();
	}
	
	
}
