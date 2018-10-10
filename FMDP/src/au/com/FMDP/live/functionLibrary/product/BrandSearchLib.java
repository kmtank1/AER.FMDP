package au.com.shortcuts.live.functionLibrary.product;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.product.BrandSearchPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class BrandSearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	BrandSearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public BrandSearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        searchpo = new BrandSearchPO(driver);
        itemlist = new ItemListPO(driver);
        
	}
	
		//Search section
		
			/**
			 * Search brand with name & click on 
			 * @param name brand name
			 * @throws InterruptedException
			 */
			public void search_SearchBrandAndClick(String name) throws InterruptedException
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
					Assert.fail("FAIL : On Brand search, no search result appeared!");
				}
			}
			
			/**
			 * Search brand with name & click on Edit button 
			 * @param name brand name
			 * @throws InterruptedException
			 */
			public void search_SearchBrandAndClickEditButton(String name) throws InterruptedException
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
					Assert.fail("FAIL : On Brand search, no search result appeared!");
				}
			}
			
			/***
			 * Search brand with name
			 * @param name brand name
			 */
			public void search_SearchBrand(String name)
			{
				itemlist.search_SearchWithText(name);
			}
			
			/***
			 * Click on Inactive checkbox on filter
			 */
			public void search_Filter_ClickOnInactiveCheckbox()
			{
				itemlist.search_ClickOnFilterIcon();
				itemlist.search_ClickOnInactiveCheckbox();
				help.waitTillPageLoaded();
			}
			
			/***
			 * Getting no record text
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
			 * Click on Edit button
			 */
			public void clickOnEditIcon()
			{
				searchpo.clickOnEditIcon();
			}
			
}
