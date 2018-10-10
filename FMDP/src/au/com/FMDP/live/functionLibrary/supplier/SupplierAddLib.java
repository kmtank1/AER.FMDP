package au.com.shortcuts.live.functionLibrary.supplier;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.supplier.SupplierAddPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class SupplierAddLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help ;
	SupplierAddPO supplieradd;
	ItemListPO itemlist;
	JavaHelpers java;
	
	
	public SupplierAddLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        supplieradd = new SupplierAddPO(driver);
        help = new CommonPO(driver);
        itemlist = new ItemListPO(driver);
        java = new JavaHelpers();
	}
	
	/**
	 * Setup new supplier by providing information in all the fields.
	 * Update few information from added supplier.
	 * This method having common value of Physical and Postal address.
	 * 
	 * @param name					Name of supplier	e.g. AT_supp_20171222_153628
	 * @param reference				Supplier's reference value	e.g. Piyush
	 * @param statusvalue			Status of supplier	e.g. Active, Inactive
	 * @param businessnumbervalue	Supplier Business number	e.g. 1234
	 * @param accountnumbervalue	Supplier Account number	e.g. 562341
	 * @param website				Supplier Web-site URL	e.g. https://www.google.com
	 * @param leadtimedaysvalue		Lead time days of supplier	e.g. 5
	 * @param firstnamevalue		First name of supplier's contact	e.g. AT_first
	 * @param lastnamevalue			Last name of supplier's contact	e.g. AT_last
	 * @param emailvalue			Email of supplier's contact		e.g. ATemail_20171222_153313@mailinator.com
	 * @param businessphonevalue	Business phone number of supplier's contact	e.g. 9632585623
	 * @param mobilephonevalue		Mobile phone number of supplier's contact	e.g. 9632587412
	 * @param faxvalue				Fax number of supplier's contact	e.g. 1236547989  
	 * @param street1				Stree1 information in physical and postal address	e.g. Cybage Software 
	 * @param street2				Stree2 information in physical and postal address	e.g. Infocity
	 * @param suburb				Suburb information in physical and postal address	e.g. Gandhinagar
	 * @param state					State information in physical and postal address	e.g. Gujarat
	 * @param pcode					Postal code in physical and postal address	e.g. 32154
	 * 
	 */
		public void supplier_Add(String name,String reference,String statusvalue,String businessnumbervalue,String accountnumbervalue,String website,String leadtimedaysvalue,
								 String firstnamevalue,String lastnamevalue,String emailvalue,String businessphonevalue,String mobilephonevalue,String faxvalue, 
								 String street1,String street2,String suburb,String state,String pcode)
		{
			supplieradd.enterSupplierName(name);
			supplieradd.enterSupplierReference(reference);
			supplieradd.setSupplierStatus(statusvalue);
			supplieradd.enterSupplierBusinessNumber(businessnumbervalue);
			supplieradd.enterSupplierAccountNumber(accountnumbervalue);
			supplieradd.enterSupplierWebsiteUrl(website);
			supplieradd.enterSupplierLeadTimeDays(leadtimedaysvalue);
			supplieradd.enterSupplierContactFirstName(firstnamevalue);
			supplieradd.enterSupplierContactLastName(lastnamevalue);
			supplieradd.enterSupplierContactEmail(emailvalue);
			supplieradd.enterSupplierContactBusinessPhone(businessphonevalue);
			supplieradd.enterSupplierContactMobilePhone(mobilephonevalue);
			supplieradd.enterSupplierContactFax(faxvalue);
			supplieradd.enterSupplierPhysicalAddressStreet1(street1);
			supplieradd.enterSupplierPhysicalAddressStreet2(street2);
			supplieradd.enterSupplierPhysicalAddressSuburb(suburb);
			supplieradd.enterSupplierPhysicalAddressState(state);
			supplieradd.enterSupplierPhysicalAddressPcode(pcode);
			supplieradd.enterSupplierPostalAddressStreet1(street1);
			supplieradd.enterSupplierPostalAddressStreet2(street2);
			supplieradd.enterSupplierPostalAddressSuburb(suburb);
			supplieradd.enterSupplierPostalAddressState(state);
			supplieradd.enterSupplierPostalAddressPcode(pcode);
			help.footer_ClickOnDone();
		}
	
	/**
	 * Set supplier's status
	 * @param statusvalue	status value of supplier i.e. Active or Inactive
	 * 
	 */
		public void supplier_changeStatus(String statusvalue)
		{
			supplieradd.setSupplierStatus(statusvalue);
			help.footer_ClickOnDone();
		}

}
