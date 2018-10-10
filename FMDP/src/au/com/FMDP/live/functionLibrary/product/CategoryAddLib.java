package au.com.shortcuts.live.functionLibrary.product;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.product.CategoryAddPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class CategoryAddLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help ;
	CategoryAddPO categoryadd;
	ItemListPO itemlist;
	JavaHelpers java;
	
	
	public CategoryAddLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        categoryadd = new CategoryAddPO(driver);
        help = new CommonPO(driver);
        itemlist = new ItemListPO(driver);
        java = new JavaHelpers();
	}
	
	
	/**
	 * Setup product category by providing information in all the fields.
	 * Update information from added product category.
	 * 
	 * @param namevalue		Name of the product category	e.g. Cat_20171226_151038
	 * @param description	Description of the product category	e.g. Automation Testing
	 * @param ref			Reference of the product category	e.g. Reference added by automation testing
	 * @param statusvalue	Status of the product category	e.g. Active, Inactive
	 * 
	 */
		public void category_Add(String namevalue, String description, String ref, String statusvalue)
		{
			categoryadd.enterCategoryDisplayName(namevalue);
			categoryadd.enterCategoryDescription(description);
			categoryadd.enterCategoryReference(ref);
			categoryadd.setCategoryStatus(statusvalue);
			help.footer_ClickOnDone();
		}
		
	/**
	 * Set product category status 
	 * @param status i.e. active or inactive
	 * 
	 */
		public void category_ChangeStatus(String status)
		{
			categoryadd.setCategoryStatus(status);
			help.footer_ClickOnDone();
		}
	
	
	
}
