package au.com.shortcuts.live.functionLibrary.settings;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.settings.TaxSearchPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class TaxSearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	TaxSearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public TaxSearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        searchpo = new TaxSearchPO(driver);
        itemlist = new ItemListPO(driver);
	}
	
		//Search section
		
			/**
			 * Search tax by name & click
			 * @param taxname tax name
			 * @throws InterruptedException
			 */
			public void search_SearchTaxAndClick(String taxname) throws InterruptedException
			{
				int i=0;
				boolean isfirstitem = false;

				do {
					itemlist.search_SearchWithText(taxname);
					
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
					Assert.fail("FAIL : On Tax search, no search result appeared!");
				}
			}
			
			/**
			 * Search tax with name
			 * @param taxname tax name
			 */
			public void search_SearchTax(String taxname)
			{
				itemlist.search_SearchWithText(taxname);
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
			
		//Applicable To section
			/**
			 * Read Applicable To section data row wise including labels
			 * @param row
			 * @return data for given row
			 */
			public String getApplicableToSectionData(int row)
			{
				return searchpo.getApplicableToSectionData(row);
			}
			
		//Current rates
			/**
			 * Read Current rates section data row wise including labels
			 * @param row
			 * @return data for given row
			 */
			public String getCurrentRateSectionData(int row)
			{
				return searchpo.getCurrentRateSectionData(row);
			}
			
			public String getCurrentRateData(int row, int column)
			{
				return searchpo.getCurrentRateData(row, column);
			}
			
		//Future rates	
			/**
			 * Read Future rates section data row wise including labels
			 * @param row
			 * @return data for given row
			 */
			public String getFutureRateSectionData(int row)
			{
				return searchpo.getFutureRateSectionData(row);
			}
			
			public String getFutureRateData(int row, int column)
			{
				return searchpo.getFutureRateData(row, column);
			}
			
			
			/**
			 * Click on Edit button
			 */
			public void clickOnEditIcon()
			{
				searchpo.clickOnEditIcon();
			}
			
}
