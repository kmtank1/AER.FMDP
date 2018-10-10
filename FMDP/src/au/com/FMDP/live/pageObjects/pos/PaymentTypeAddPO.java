package au.com.shortcuts.live.pageObjects.pos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class PaymentTypeAddPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public PaymentTypeAddPO(WebDriver driver)
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
	
		@FindBy(css="label[for='is_active'] + *")
		private WebElement status;
		
		@FindBy(id="is_active")
		private WebElement isactive;
		
		@FindBy(css="label[for='is_banking'] + *")
		private WebElement Creditsbankaccount;
		
		@FindBy(id="is_banking")
		private WebElement isbanking;
		
		
		public void setPaymentTypeStatus(String statusvalue)
		{
			help.setToggleValue(status, isactive, statusvalue);
		}
		
		public void setPaymentTypeCreditBankAccount(String creditsbankaccountvalue)
		{
			help.setToggleValue(Creditsbankaccount, isbanking, creditsbankaccountvalue);
		}	
}
