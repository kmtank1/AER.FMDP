package au.com.shortcuts.live.functionLibrary.pos;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.pos.ReceiptSetupPO;


public class ReceiptSetupLib 
{
	WebDriver driver;
	CommonPO help ;
	ReceiptSetupPO receiptsetup;
	
	public ReceiptSetupLib(WebDriver driver)
	{	 
        this.driver = driver;
        receiptsetup = new ReceiptSetupPO(driver);
        help = new CommonPO(driver);
	}
	
	//Edit data
	
		/**
		 * Update the Receipt setup information.
		 * Method is responsible to update existing receipt setup profile.
		 * 
		 * @param headingtext				Heading of receipt	e.g. Heading added by AT _20180110_101704
		 * @param customreceiptmessagetext	Custom message on receipt	e.g. This is a custom message by AT _20180110_101704 {{Customer Last Name}}
		 * @param fieldvalue				Field value to be added in custom message	e.g. Customer first name, Customer last name
		 * @param defaultsizevalue			Default size of receipt	e.g. A4, Docket
		 * @param tobeprintedvalue			Allow receipts to be printed	e.g. Yes, No
		 * @param tobeemailedvalue			Allow receipts to be emailed	e.g. Yes, No
		 * @param noreceiptvalue			Allow no receipts	e.g Yes, No
		 * @param hideclientvalue			Hide clients details on receipts	e.g. Yes, No
		 * @param includeapptsvalue			Include future appointments	on receipts	e.g. Yes, No
		 * 
		 */
		public void receiptsetup_Edit(String headingtext, String customreceiptmessagetext, String fieldvalue, String defaultsizevalue, String tobeprintedvalue, String tobeemailedvalue, String noreceiptvalue, String hideclientvalue, String includeapptsvalue)
		{
			receiptsetup.enterReceiptHeading(headingtext);
			receiptsetup.enterReceiptCustomMessage(customreceiptmessagetext);
			receiptsetup.selectReceiptField(fieldvalue);
			receiptsetup.selectReceiptDefaultSize(defaultsizevalue);
			receiptsetup.setReceiptAllowToBePrinted(tobeprintedvalue);
			receiptsetup.setReceiptAllowToBeEmailed(tobeemailedvalue);
			receiptsetup.setReceiptNotAllowed(noreceiptvalue);
			receiptsetup.setReceiptHideClientDetails(hideclientvalue);
			receiptsetup.setReceiptIncludeFutureAppointments(includeapptsvalue);
			help.footer_ClickOnDone();
		}
	

		/**
		 * Click on Edit button
		 */
		public void clickOnEditIcon()
		{
			receiptsetup.clickOnEditIcon();
		}
	
		
	//Read Data
	
		/**
		 * Read Details section data row wise including labels
		 * @param row
		 * @return data for given row
		 */
		public String getDetailsSectionData(int row)
		{
			return receiptsetup.getDetailsSectionData(row);
		}
	
		
		/**
		 * Read Options section data row wise including labels
		 * @param row
		 * @return data for given row
		 */
		public String getOptionsSectionData(int row)
		{
			return receiptsetup.getOptionsSectionData(row);
		}
		
		
	
}
