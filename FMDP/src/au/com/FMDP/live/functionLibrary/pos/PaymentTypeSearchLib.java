package au.com.shortcuts.live.functionLibrary.pos;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.pos.PaymentTypeSearchPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class PaymentTypeSearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	PaymentTypeSearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public PaymentTypeSearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        searchpo = new PaymentTypeSearchPO(driver);
        itemlist = new ItemListPO(driver);
	}
	
	
	/**
	 * Search payment type by name & select first item
	 * @param name	Payment type name e.g. Bank Transfer, Cheque, Cash	
	 * 
	 */
		public void search_SearchPaymentTypeAndClick(String name) throws InterruptedException
		{
			int i=0;
			boolean isfirstitem = false;

			do {
				itemlist.search_SearchWithText(name);
				
				if (itemlist.search_IsFirstItemDisplayed()) 
				{
					itemlist.search_ClickOnFirstItem();
					isfirstitem = true;
					break;
				}
				else 
				{
					i++;
					selenium.refreshPage();
					help.waitTillPageLoaded();
				}
	
			} while (i < Constants.MaxAttemptForLocatingElement);
			
			//if search result not found then failing test with message 
			if(!isfirstitem)
			{
				Assert.fail("FAIL : On Payment Type search, no search result appeared!");
			}
		}
	
		
	/**
	 * Search payment type by name
	 * @param name	Payment type name e.g. Bank Transfer, Cheque, Cash	
	 * 
	 */
		public void search_SearchPaymentType(String name)
		{
			itemlist.search_SearchWithText(name);
		}
		
		
	/**
	 * Click on inactive checkbox on filter
	 */
		public void search_Filter_ClickOnInactiveCheckbox()
		{
			itemlist.search_ClickOnFilterIcon();
			itemlist.search_ClickOnInactiveCheckbox();
			help.waitTillPageLoaded();
		}
		
		
	/**
	 * Retrieve the text "There are no records to display" to verify that, no record found during search.
	 * @return	No record found message		i.e. There are no records to display
	 * 
	 */
		public String search_GetNoRecordMsgText()
		{
			return itemlist.search_GetNoRecordMsgText();
		}
		
	
	//Details section
		
		/**
		 * Read Details section data row wise including labels
		 * @param row
		 * @return data for given row
		 */
		public String getDetailsSectionData(int row)
		{
			return searchpo.getDetailsSectionData(row);
		}
		
		
	//Edit
		/**
		 * Click on Edit button
		 */
		public void clickOnEditIcon()
		{
			searchpo.clickOnEditIcon();
		}
			
}
