package au.com.shortcuts.live.functionLibrary.settings;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.settings.TaxSetupPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class TaxSetupLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	TaxSetupPO taxadd;
	CommonPO help ;
	
	
	public TaxSetupLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        taxadd = new TaxSetupPO(driver);
        help = new CommonPO(driver);
	}
	
	/**
	 * Setup new tax category by providing information in all the fields.
	 * Update tax information from added tax category.
	 *  
	 * @param name			Name of tax	category	e.g. AT_Tax_20171228_143354
	 * @param status		Status of tax category	e.g. Active, Inactive
	 * @param purchase		Tax Applies to purchases	e.g. Yes, No
	 * @param sale			Tax Applies to sales	e.g. Yes, No
	 * @param service		Tax applicable to Services	e.g. Yes, No
	 * @param product		Tax applicable to Products	e.g. Yes, No
	 * @param sundry		Tax applicable to Sundries	e.g. Yes, No
	 * @param expense		Tax applicable to Expenses	e.g. Yes, No
	 * @param date			Tax effective from date		e.g. 28 Dec 2017
	 * @param ratename		Tax rate category name	e.g. AT_rate
	 * @param ratepercent	Tax rate percentage		e.g. 15.00% 
	 * 
	 */
	public void tax_Add(String name,String status,String purchase,String sale,String service,String product,String sundry,String expense,String date,String ratename,String ratepercent)
	{
		taxadd.enterTaxName(name);
		taxadd.setTaxStatus(status);
		taxadd.setTaxAppliesToPurchase(purchase);
		taxadd.setTaxAppliesToSale(sale);
		taxadd.setTaxService(service);
		taxadd.setTaxProduct(product);
		taxadd.setTaxSundry(sundry);
		taxadd.setTaxExpense(expense);
		if(date.length()>0)
		{
			taxadd.enterTaxEffectiveDate(date);
		}
		taxadd.enterCurrentTaxName(ratename);
		taxadd.enterCurrentTaxRate(ratepercent);
		help.footer_ClickOnDone();
	}


	/**
	 * Set tax category status i.e. Active or Inactive.
	 * @param status	Provide desired status value of tax category e.g. Active or Inactive
	 */
	public void tax_ChangeStatus(String status)
	{
		taxadd.setTaxStatus(status);
	}
}
