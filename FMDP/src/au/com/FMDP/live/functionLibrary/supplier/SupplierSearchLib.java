package au.com.shortcuts.live.functionLibrary.supplier;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.supplier.SupplierSearchPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class SupplierSearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	SupplierSearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public SupplierSearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        searchpo = new SupplierSearchPO(driver);
        itemlist = new ItemListPO(driver);
        
	}
	
		//Search section
		
			/**
			 * Search Supplier by name and click
			 * @param name supplier name
			 * @throws InterruptedException
			 */
			public void search_SearchSupplierAndClick(String name) throws InterruptedException
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
					Assert.fail("FAIL : On Supplier search, no search result appeared!");
				}
			}
			
			/**
			 * Search supplier by name
			 * @param supplier supplier name
			 */
			public void search_SearchSupplier(String name)
			{
				itemlist.search_SearchWithText(name);
			}
			
			/**
			 * Click on Inactive box on filter
			 */
			public void search_Filter_ClickOnInactiveCheckbox()
			{
				itemlist.search_ClickOnFilterIcon();
				itemlist.search_ClickOnInactiveCheckbox();
				help.waitTillPageLoaded();
			}
			
			/**
			 * Get no record text
			 * @return no record text
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
			
			/**
			 * Read Contact section data row wise including labels
			 * @param row
			 * @return data for given row
			 */
			public String getContactSectionData(int row)
			{
				return searchpo.getContactSectionData(row);
			}
			
			/**
			 * Read Address section data row wise including labels
			 * @param row
			 * @return data for given row
			 */
			public String getAddressSectionData()
			{
				return searchpo.getAddressSectionData();
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
