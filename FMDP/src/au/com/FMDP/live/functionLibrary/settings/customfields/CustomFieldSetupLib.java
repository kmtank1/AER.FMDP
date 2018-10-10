package au.com.shortcuts.live.functionLibrary.settings.customfields;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.settings.customfields.CustomFieldSetupPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class CustomFieldSetupLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CustomFieldSetupPO customerfieldadd;
	CommonPO help ;
	
	public CustomFieldSetupLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        customerfieldadd = new CustomFieldSetupPO(driver);
        help = new CommonPO(driver);
	}

	/**
	 * Create new custom field for customer.
	 * Update added custom field for customer. 
	 * 
	 * @param name				Custom field name	e.g. AT_Customer_CF_20180108_164530
	 * @param status			Status of added custom field	i.e. Active or Inactive
	 * @param datatype			Data type of custom field	e.g. Text, Number, Yes/No  
	 * @param fieldlength		Default length of custom field	e.g. 10 - To enter Number
	 * @param mandatorystatus	Custom field mandatory status	e.g. Yes, No
	 * @param defaultvalue		Default value of custom field	e.g. Default Value - Employee
	 * @return					None
	 */
	public void customerfield_Add(String name,String status,String datatype,String fieldlength,String mandatorystatus,String defaultvalue) throws InterruptedException
	{
		customerfieldadd.enterCustomFieldName(name);
		customerfieldadd.setCustomFieldStatus(status);
		customerfieldadd.selectCustomFieldDataType(datatype);
		
		if (fieldlength.length() > 0)
		{
			customerfieldadd.enterCustomFieldLength(fieldlength);	
		}
		customerfieldadd.setMandatoryStatus(mandatorystatus);
		
		if (datatype.equalsIgnoreCase("Text"))
		{
			customerfieldadd.enterCustomFieldDefaultTextValue(defaultvalue);
		}
		else if (datatype.equalsIgnoreCase("Number"))
		{
			customerfieldadd.enterCustomFieldDefaultIntValue(defaultvalue);
		}
		else if (datatype.equalsIgnoreCase("Yes/No"))
		{
			customerfieldadd.setCustomFieldDefaultToggleValue(defaultvalue);
		}
		
		help.footer_ClickOnDone();
	}


	/**
	 * Set custom field status
	 * @param status	Inactive or Active.
	 * @return			None
	 */
	public void customfield_ChangeStatus(String status)
	{
		customerfieldadd.setCustomFieldStatus(status);
	}
}