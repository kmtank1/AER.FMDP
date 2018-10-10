package au.com.shortcuts.live.pageObjects.supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class SupplierAddPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public SupplierAddPO(WebDriver driver)
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
		private WebElement suppliername;
	
		@FindBy(id="reference")
		private WebElement supplierreference;
		
		@FindBy(css="label[for='is_active'] + *")
		private WebElement status;
		
		@FindBy(id="is_active")
		private WebElement isactive;
		
		@FindBy(id="business_number")
		private WebElement businessnumber;
	
		@FindBy(id="account_number")
		private WebElement accountnumber;
		
		@FindBy(id="website_url")
		private WebElement websiteurl;
	
		@FindBy(id="lead_time_days")
		private WebElement leadtimedays;
		
		
	//Contact Section		
		@FindBy(id="contact_contact_first_name")
		private WebElement firstname;
	
		@FindBy(id="contact_contact_last_name")
		private WebElement lastname;
		
		@FindBy(id="contact_email")
		private WebElement email;
		
		@FindBy(id="business")
		private WebElement businessphone;
	
		@FindBy(id="mobile")
		private WebElement mobilephone;
		
		@FindBy(id="fax")
		private WebElement fax;
		
		
	//Physical address 
		@FindBy(id="physical_address_street_1")
		private WebElement physicaladdressstreet1;
	
		@FindBy(id="physical_address_street_2")
		private WebElement physicaladdressstreet2;
		
		@FindBy(id="physical_address_suburb")
		private WebElement physicaladdresssuburb;
		
		@FindBy(id="physical_address_state")
		private WebElement physicaladdressstate;
	
		@FindBy(id="physical_address_postal_code")
		private WebElement physicaladdresspcode;
		
		
	//Postal address 
		@FindBy(id="postal_address_street_1")
		private WebElement postaladdressstreet1;
	
		@FindBy(id="postal_address_street_2")
		private WebElement postaladdressstreet2;
		
		@FindBy(id="postal_address_suburb")
		private WebElement postaladdresssuburb;
		
		@FindBy(id="postal_address_state")
		private WebElement postaladdressstate;
	
		@FindBy(id="postal_address_postal_code")
		private WebElement postaladdresspcode;


	//Details section :
		public void enterSupplierName(String name)
		{
			suppliername.clear();
			suppliername.sendKeys(name);
		}
		
		public void enterSupplierReference(String reference)
		{
			supplierreference.clear();
			supplierreference.sendKeys(reference);
		}

		public void setSupplierStatus(String statusvalue)
		{
			help.setToggleValue(status, isactive, statusvalue);
		}
		
		public void enterSupplierBusinessNumber(String businessnumbervalue)
		{
			businessnumber.clear();
			businessnumber.sendKeys(businessnumbervalue);
		}
		
		public void enterSupplierAccountNumber(String accountnumbervalue)
		{
			accountnumber.clear();
			accountnumber.sendKeys(accountnumbervalue);
		}
		
		public void enterSupplierWebsiteUrl(String website)
		{
			websiteurl.clear();
			websiteurl.sendKeys(website);
		}
		
		public void enterSupplierLeadTimeDays(String leadtimedaysvalue)
		{
			leadtimedays.clear();
			leadtimedays.sendKeys(leadtimedaysvalue);
		}
	
	//Contact section :
		public void enterSupplierContactFirstName(String firstnamevalue)
		{
			firstname.clear();
			firstname.sendKeys(firstnamevalue);
		}
		
		public void enterSupplierContactLastName(String lastnamevalue)
		{
			lastname.clear();
			lastname.sendKeys(lastnamevalue);
		}
		
		public void enterSupplierContactEmail(String emailvalue)
		{
			email.clear();
			email.sendKeys(emailvalue);
		}
		
		public void enterSupplierContactBusinessPhone(String businessphonevalue)
		{
			businessphone.clear();
			businessphone.sendKeys(businessphonevalue);
		}
		
		public void enterSupplierContactMobilePhone(String mobilephonevalue)
		{
			mobilephone.clear();
			mobilephone.sendKeys(mobilephonevalue);
		}
		
		public void enterSupplierContactFax(String faxvalue)
		{
			fax.clear();
			fax.sendKeys(faxvalue);
		}
		

	//Physical address section :
		public void enterSupplierPhysicalAddressStreet1(String street1)
		{
			physicaladdressstreet1.clear();
			physicaladdressstreet1.sendKeys(street1);
		}
		
		public void enterSupplierPhysicalAddressStreet2(String street2)
		{
			physicaladdressstreet2.clear();
			physicaladdressstreet2.sendKeys(street2);
		}
		
		public void enterSupplierPhysicalAddressSuburb(String suburb)
		{
			physicaladdresssuburb.clear();
			physicaladdresssuburb.sendKeys(suburb);
		}
		
		public void enterSupplierPhysicalAddressState(String state)
		{
			physicaladdressstate.clear();
			physicaladdressstate.sendKeys(state);
		}
		
		public void enterSupplierPhysicalAddressPcode(String pcode)
		{
			physicaladdresspcode.clear();
			physicaladdresspcode.sendKeys(pcode);
		}		
		
	//Postal address section :
		public void enterSupplierPostalAddressStreet1(String street1)
		{
			postaladdressstreet1.clear();
			postaladdressstreet1.sendKeys(street1);
		}
		
		public void enterSupplierPostalAddressStreet2(String street2)
		{
			postaladdressstreet2.clear();
			postaladdressstreet2.sendKeys(street2);
		}
		
		public void enterSupplierPostalAddressSuburb(String suburb)
		{
			postaladdresssuburb.clear();
			postaladdresssuburb.sendKeys(suburb);
		}
		
		public void enterSupplierPostalAddressState(String state)
		{
			selenium.pageScrollInView(postaladdressstate);
			postaladdressstate.clear();
			postaladdressstate.sendKeys(state);
		}
		
		public void enterSupplierPostalAddressPcode(String pcode)
		{
			selenium.pageScrollInView(postaladdressstate);
			postaladdresspcode.clear();
			postaladdresspcode.sendKeys(pcode);
		}		
					
		
}
