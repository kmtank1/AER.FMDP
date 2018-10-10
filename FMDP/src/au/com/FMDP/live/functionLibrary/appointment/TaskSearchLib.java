package au.com.shortcuts.live.functionLibrary.appointment;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.pageObjects.appointment.TaskSearchPO;
import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class TaskSearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	TaskSearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public TaskSearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        searchpo = new TaskSearchPO(driver);
        itemlist = new ItemListPO(driver);
        
	}
	
		//Search section
		
			/**
			 * Search Task by name & click on it
			 * @param name task name
			 * @throws InterruptedException
			 */
			public void search_SearchTaskAndClick(String name) throws InterruptedException
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
					Assert.fail("FAIL : On Task search, no search result appeared!");
				}
			}
			
			/**
			 * Search task with name
			 * @param name  task name
			 * @throws InterruptedException
			 */
			public void search_SearchTask(String name) throws InterruptedException
			{
				itemlist.search_SearchWithText(name);
			}
			
			/**
			 * On filter, click on Inactive checkbox
			 * @throws InterruptedException
			 */
			public void search_Filter_ClickOnInactiveCheckbox() throws InterruptedException
			{
				itemlist.search_ClickOnFilterIcon();
				itemlist.search_ClickOnInactiveCheckbox();
				java.sleep(3);
			}
			
			/**
			 * Get no record text on search result
			 * @return
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
			 * Read Name section data row wise including labels
			 * @param row
			 * @return data for given row
			 */
			public String getNameSectionData(int row)
			{
				return searchpo.getNameSectionData(row);
			}
			
			/**
			 * Click on Edit button
			 */
			public void clickOnEditIcon()
			{
				searchpo.clickOnEditIcon();
			}
			
}
