package au.com.shortcuts.live.pageObjects.customer;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class CustomerAddPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public CustomerAddPO(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}
	
	
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 	
	
	
	//Custom field section :
		@FindBy(css="#customer_fields li")
		private List<WebElement> customfieldsection;

		
		/**
		 * Capture all custom field from customer page and
		 * compare with actual added custom field 
		 *  
		 * @param customfieldname	Name of added custom field e.g. AT_Customer_CF_20180109_185617
		 * @return					true or false
		 */
		
		public boolean IsCustomFieldPresent(String customfieldname)
		{	
			boolean b = false;
			for (WebElement e : customfieldsection)
			{
				selenium.pageScrollInView(e);
				if (e.getText().equalsIgnoreCase(customfieldname))
				{	
					b = true;
					break;
				}
			}
			return b;
		}

}
