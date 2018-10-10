package au.com.shortcuts.live.pageObjects.pos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ReceiptSetupPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public ReceiptSetupPO(WebDriver driver)
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
	
	//Read View:
	
	//Details section 
		@FindBy(css=".editableSection i.icon-pencil")
		private WebElement editIcon;
		
		
	//Edit mode:
		
	//Details section : 
	
		@FindBy(id="receipt_heading")
		private WebElement heading;
		
		@FindBy(id="receipt_message")
		private WebElement message;
		
		@FindBy(id="fields")
		private WebElement insertfield;
		
		
	//Options section :
		
		@FindBy(id="default_receipt_format_code")
		private WebElement defaultsize;
		
		@FindBy(css="label[for='is_receipt_print_enabled'] + *")
		private WebElement tobeprinted;
		
		@FindBy(id="is_receipt_print_enabled")
		private WebElement isprinted;
		
		@FindBy(css="label[for='is_receipt_email_enabled'] + *")
		private WebElement tobeemailed;
		
		@FindBy(id="is_receipt_email_enabled")
		private WebElement isemailed;
		
		@FindBy(css="label[for='is_receipt_none_enabled'] + *")
		private WebElement noreceipt;
		
		@FindBy(id="is_receipt_none_enabled")
		private WebElement isnoreceipt;
		
		@FindBy(css="label[for='print_client_details'] + *")
		private WebElement hideclientdetails;
		
		@FindBy(id="print_client_details")
		private WebElement ishidden;
		
		@FindBy(css="label[for='include_future_appointments'] + *")
		private WebElement includefutureappts;
		
		@FindBy(id="include_future_appointments")
		private WebElement isincluded;
		
	
		
	//Edit 	view
		public void enterReceiptHeading(String headingtext)
		{
			heading.clear();
			heading.sendKeys(headingtext);
		}
		
		public void enterReceiptCustomMessage(String customreceiptmessagetext)
		{
			message.clear();
			message.sendKeys(customreceiptmessagetext);
		}
		
		public void selectReceiptField(String fieldvalue)
		{
			selenium.selectDropdownValueByText(insertfield, fieldvalue);
		}
	
		public void selectReceiptDefaultSize(String defaultsizevalue)
		{
			selenium.selectDropdownValueByText(defaultsize, defaultsizevalue);
		}
		
		public void setReceiptAllowToBePrinted(String tobeprintedvalue)
		{
			help.setToggleValue(tobeprinted, isprinted, tobeprintedvalue);
		}
		
		public void setReceiptAllowToBeEmailed(String tobeemailedvalue)
		{
			help.setToggleValue(tobeemailed, isemailed, tobeemailedvalue);
		}
		
		public void setReceiptNotAllowed(String noreceiptvalue)
		{
			help.setToggleValue(noreceipt, isnoreceipt, noreceiptvalue);
		}
		
		public void setReceiptHideClientDetails(String hideclientvalue)
		{
			help.setToggleValue(hideclientdetails, ishidden, hideclientvalue);
		}
		
		public void setReceiptIncludeFutureAppointments(String includeapptsvalue)
		{
			help.setToggleValue(includefutureappts, isincluded, includeapptsvalue);
		}
		
		
	//Read view
				
		//Details section
		public String getDetailsSectionData(int row)
		{
			String cssSelector = "fieldset[data-link='#receipt_heading'] ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//Options section
		public String getOptionsSectionData(int row)
		{
			String cssSelector = "fieldset[data-link='#receipt_options'] ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//Click on Edit icon
		public void clickOnEditIcon()
		{
			editIcon.click();
			help.waitTillPageLoaded();
		}
		
}
