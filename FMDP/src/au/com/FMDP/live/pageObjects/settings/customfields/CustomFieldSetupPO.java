package au.com.shortcuts.live.pageObjects.settings.customfields;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class CustomFieldSetupPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	JavaHelpers java;
	
	public CustomFieldSetupPO(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}
	
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 	
	
	//Details section : 
	
		@FindBy(id="display_name")
		private WebElement name;
		
		@FindBy(css="label[for='is_active'] + *")
		private WebElement status;
		
		@FindBy(id="is_active")
		private WebElement isactive_status;
		
	//Definition section : 
		
		@FindBy(id="data_type_code")
		private WebElement selectdatatype;
		
		@FindBy(id="field_length")
		private WebElement customfieldlength;
				
		@FindBy(css="label[for='is_mandatory'] + *")
		private WebElement mandatorystatus;
		
		@FindBy(id="is_mandatory")
		private WebElement isactive_mandatory;
		
		@FindBy(id="default_text_value")
		private WebElement enterdefaulttextvalue;
		
		@FindBy(id="default_int_value")
		private WebElement enterdefaultintvalue;
		
		@FindBy(css="label[for='default_bit_value'] + *")
		private WebElement defaultyesnovalue;
		
		@FindBy(id="default_bit_value")
		private WebElement isactive_defaultyesnovalue;
		
		
		public void enterCustomFieldName(String customfieldnamevalue)
		{
			name.clear();
			name.sendKeys(customfieldnamevalue);
		}
		
		public void setCustomFieldStatus(String customfieldstatusvalue)
		{
			help.setToggleValue(status, isactive_status, customfieldstatusvalue);
		}
		

		/**
		 * Select data-type
		 * @param customfielddatatypevalue		Data type of custom field e.g. Text, Number, Yes/No
		 * @return					None
		 */
		public void selectCustomFieldDataType(String customfielddatatypevalue) throws InterruptedException
		{
			selenium.selectDropdownValueByText(selectdatatype, customfielddatatypevalue);
			help.waitTillPageLoaded();
			
			//if there is confirmation message
			if (driver.getPageSource().contains("Click Yes to continue."))
			{
				try 
				{
					help.validationBottomRightPopUp_ClickOnYes();
					help.waitTillPageLoaded();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		public void enterCustomFieldLength(String customfieldlenghvalue)
		{
			customfieldlength.clear();
			customfieldlength.sendKeys(customfieldlenghvalue);
		}
		
		public void setMandatoryStatus(String customfieldmandatorystatusvalue)
		{
			help.setToggleValue(mandatorystatus, isactive_mandatory, customfieldmandatorystatusvalue);
		}
		
		public void enterCustomFieldDefaultTextValue(String customfielddefaultvalue)
		{
			enterdefaulttextvalue.clear();
			enterdefaulttextvalue.sendKeys(customfielddefaultvalue);
		}
		
		public void enterCustomFieldDefaultIntValue(String customfielddefaultvalue)
		{
			enterdefaultintvalue.clear();
			enterdefaultintvalue.sendKeys(customfielddefaultvalue);
		}
		
		public void setCustomFieldDefaultToggleValue(String customfielddefaultvalue)
		{
			help.setToggleValue(defaultyesnovalue, isactive_defaultyesnovalue, customfielddefaultvalue);
		}
}

