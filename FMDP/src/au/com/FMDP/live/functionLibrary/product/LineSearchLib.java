package au.com.shortcuts.live.functionLibrary.product;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.product.LineSearchPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class LineSearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	LineSearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public LineSearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        searchpo = new LineSearchPO(driver);
        itemlist = new ItemListPO(driver);
        
	}
	
		//Search section
		
			/**
			 * Search line by name & click
			 * @param name line name
			 * @throws InterruptedException
			 */
			public void search_SearchLineAndClick(String name) throws InterruptedException
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
					Assert.fail("FAIL : On Line search, no search result appeared!");
				}
			}
			
			/**
			 * Search line by name & click on Edit button
			 * @param name line name
			 * @throws InterruptedException
			 */
			public void search_SearchLineAndClickEditButton(String name) throws InterruptedException
			{
				int i=0;
				boolean isfirstitem = false;

				do {
					itemlist.search_SearchWithText(name);
					
					if (itemlist.search_IsFirstItemDisplayed()) 
					{
						itemlist.search_EditFirstItem();
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
					Assert.fail("FAIL : On Line search, no search result appeared!");
				}
			}
			
			/**
			 * Search line by name
			 * @param name line name
			 */
			public void search_SearchLine(String name)
			{
				itemlist.search_SearchWithText(name);
			}
			
			/**
			 * Click on Inactive checkbox on filter
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
							
			/***
			 * Click on Edit button
			 */
			public void clickOnEditIcon()
			{
				searchpo.clickOnEditIcon();
			}
			
}
