package au.com.shortcuts.live.functionLibrary.service;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.service.ServiceSearchPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ServiceSearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	ServiceSearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public ServiceSearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        searchpo = new ServiceSearchPO(driver);
        itemlist = new ItemListPO(driver);
        
	}
	
		//Search section
		
			/**
			 * Search service by name & click
			 * @param name service name
			 * @throws InterruptedException
			 */
			public void search_SearchServiceAndClick(String name) throws InterruptedException
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
					Assert.fail("FAIL : On Service search, no search result appeared!");
				}
			}
				
			/**
			 * Search service by name
			 * @param name service name
			 */
			public void search_SearchService(String name) 
			{
				itemlist.search_SearchWithText(name);
			}
			
			/**
			 * click on Inactive checkbox on filter
			 */
			public void search_Filter_ClickOnInactiveCheckbox()
			{
				itemlist.search_ClickOnFilterIcon();
				itemlist.search_ClickOnInactiveCheckbox();
				help.waitTillPageLoaded();
			}
			
			/**
			 * Get no record text
			 * @return text
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
			 * Get Default duration value
			 * @return default duration
			 */
			public String getDefaultDuration()
			{
				return searchpo.getDefaultDuration();
			}
			
			/**
			 * Get break duration
			 * @return break duration
			 */
			public String getBreakDuration()
			{
				return searchpo.getBreakDuration();
			}
			
			/**
			 * Click on Edit button 
			 */
			public void clickOnEditIcon()
			{
				searchpo.clickOnEditIcon();
			}

			
		//Pricing section	
			/**
			 * Read Pricing section data row wise including labels
			 * @param row
			 * @return data for given row
			 */
			public String getPricingSectionData(int row, int column)
			{
				return searchpo.getPricingSectionData(row,column);
			}
			
		
}
