package au.com.shortcuts.live.functionLibrary.rosters;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.rosters.RosterActivitySearchPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class RosterActivitySearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	RosterActivitySearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public RosterActivitySearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        searchpo = new RosterActivitySearchPO(driver);
        itemlist = new ItemListPO(driver);
	}
	
		//Search section
			/**
			 * Search activity with name & click on 
			 * @param activityname activity name
			 * @throws InterruptedException
			 */
			public void search_SearchActivityAndClick(String activityname) throws InterruptedException
			{
				int i=0;
				boolean isfirstitem = false;

				do {
					itemlist.search_SearchWithText(activityname);
					
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
					Assert.fail("FAIL : On Roster Activity search, no search result appeared!");
				}
			}
			
			/***
			 * Search activity with name
			 * @param activityname activity name
			 */
			public void search_SearchActivity(String activityname) throws InterruptedException
			{
				itemlist.search_SearchWithText(activityname);
			}
			
			/**
			 * Click on Inactive checkbox on filter
			 */
			public void search_Filter_ClickOnInactiveCheckbox() throws InterruptedException
			{
				itemlist.search_ClickOnFilterIcon();
				itemlist.search_ClickOnInactiveCheckbox();
				java.sleep(3);
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
			 * Read Details section > color style
			 * @return colory style text
			 */
			public String getDetailsSectionData_ColorStyle()
			{
				return searchpo.getColorStyleFromDetailsSection();
			}
			
			/**
			 * Click on Edit button
			 */
			public void clickOnEditIcon()
			{
				searchpo.clickOnEditIcon();
			}
			
}
