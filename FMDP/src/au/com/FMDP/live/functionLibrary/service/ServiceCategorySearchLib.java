package au.com.shortcuts.live.functionLibrary.service;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.service.ServiceCategorySearchPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ServiceCategorySearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	ServiceCategorySearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public ServiceCategorySearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        searchpo = new ServiceCategorySearchPO(driver);
        itemlist = new ItemListPO(driver);
        
	}
	
		//Search section
	
			/**
			 * Search service category by name & click
			 * @param name service name
			 * @throws InterruptedException
			 */
			public void search_SearchCateogryAndClick(String name) throws InterruptedException
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
					Assert.fail("FAIL : On Service Category search, no search result appeared!");
				}
			}
			
			/**
			 * Search service category by name & click on Edit button
			 * @param name service category name
			 */
			public void search_SearchCategoryAndClickEditButton(String name) throws InterruptedException
			{
				int i=0;
				boolean isfirstitem = false;

				do {
					itemlist.search_SearchWithText(name,searchpo.GetSearch_Input());
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
					Assert.fail("FAIL : On Service Category search, no search result appeared!");
				}
			}
			
			/**
			 * Search service category by name
			 * @param name service category name
			 */
			public void search_SearchCategory(String name) throws InterruptedException
			{
				itemlist.search_SearchWithText(name,searchpo.GetSearch_Input());
			}
			
			/**
			 * click on Inactive checkbox on filter
			 */
			public void search_Filter_ClickOnInactiveCheckbox() throws InterruptedException
			{
				itemlist.search_ClickOnFilterIcon();
				itemlist.search_ClickOnInactiveCheckbox();
				java.sleep(3);
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
			 * Click on Edit button 
			 */
			public void clickOnEditIcon()
			{
				searchpo.clickOnEditIcon();
			}
			
}
