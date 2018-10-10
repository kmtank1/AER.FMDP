package au.com.shortcuts.live.functionLibrary.pos;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.pos.PaymentTypeAddPO;


public class PaymentTypeAddLib 
{
	WebDriver driver;
	CommonPO help ;
	PaymentTypeAddPO paymenttypeadd;
	
	public PaymentTypeAddLib(WebDriver driver)
	{	 
        this.driver = driver;
        paymenttypeadd = new PaymentTypeAddPO(driver);
        help = new CommonPO(driver);
	}
	
	
	/**
	 * Edit payment type by providing information in all the fields. 
	 * @param statusvalue				Status of Payment type	e.g. Active, Inactive
	 * @param creditsbankaccountvalue	Status of Credits bank account	e.g. Active, Inactive
	 */
		public void paymenttype_Add(String statusvalue, String creditsbankaccountvalue)
		{
			paymenttypeadd.setPaymentTypeStatus(statusvalue);
			paymenttypeadd.setPaymentTypeCreditBankAccount(creditsbankaccountvalue);
			help.footer_ClickOnDone();
		}
	
	/**
	 * Set payment type status i.e. Active or Inactive.
	 * @param status	status value of payment type e.g. Active or Inactive
	 * 
	 */
		public void paymenttype_ChangeStatus(String status)
		{
			paymenttypeadd.setPaymentTypeStatus(status);
			help.footer_ClickOnDone();
		}
	
}
