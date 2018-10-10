package au.com.shortcuts.live.pageObjects.service;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ServiceCategoryAddPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public ServiceCategoryAddPO(WebDriver driver)
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
	
	//Details section : 
		@FindBy(id="display_name")
		private WebElement name;
	
		@FindBy(css="label[for='is_active'] + *")
		private WebElement status;
		
		@FindBy(id="is_active")
		private WebElement isactive;
		
		@FindBy(css="label[for='is_customer_bookable'] + *")
		private WebElement onlinebooking;
		
		@FindBy(id="is_customer_bookable")
		private WebElement isonlinebooking;
		
		@FindBy(id="reference")
		private WebElement reference;
	
		
	//Field validations
		@FindBy(css="div.app-errorContainer span")
		private List<WebElement> fieldValidatons;
		
		
		public void enterServiceCategoryName(String namevalue)
		{
			name.click();
			name.clear();
			name.sendKeys(namevalue);
		}
		
		public void setServiceCategoryStatus(String statusvalue)
		{
			help.setToggleValue(status, isactive, statusvalue);
		}
		
		public void setServiceCategoryOnlineBookingStatus(String onlinebookingvalue)
		{
			help.setToggleValue(onlinebooking, isonlinebooking, onlinebookingvalue);
		}
		
		public void enterServiceCategoryReference(String referencevalue)
		{
			reference.clear();
			reference.sendKeys(referencevalue);
		}
		
		
		//All fields validation messages
		
		/**
		 * Retrieve all the validation messages from page. 
		 * 
		 * @return	Validation message to validate	e.g. Name is required.
		 * 
		 */
		public String getAllFieldValidationMessages()
		{
			String msg="";
			
			for(WebElement e:fieldValidatons)
			{
				msg = msg + e.getText().trim();
			}
			return msg;
		}	
		
}
