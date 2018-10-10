package au.com.shortcuts.live.functionLibrary.appointment;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.functionLibrary.common.CommonLib;
import au.com.FMDP.live.pageObjects.appointment.ApppintmentBookTaskCreatePO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ApppintmentBookTaskCreateLib
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonLib help;
	ApppintmentBookTaskCreatePO taskcreatepo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public ApppintmentBookTaskCreateLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonLib(driver);
        java = new JavaHelpers();
        taskcreatepo = new ApppintmentBookTaskCreatePO(driver);
        itemlist = new ItemListPO(driver);
	}
	
	
	//Search section
	
		/**
		 * Search task by name and click
		 * @param taskname Task name
		 * @throws InterruptedException
		 */
		public void search_SearchTaskAndClick(String taskname) throws InterruptedException
		{
			int i=0;
			boolean isfirstitem = false;

			do {
				itemlist.search_SearchWithText(taskname);
				
				if (itemlist.search_IsFirstItemDisplayed()) 
				{
					itemlist.search_ClickOnFirstItem();
					isfirstitem = true;
					help.footer_ClickOnDone();
					break;
				}
				else 
				{
					i++;
					help.waitTillPageLoaded();
				}
	
			} while (i < Constants.MaxAttemptForLocatingElement);
			
			//if search result not found then failing test with message 
			if(!isfirstitem)
			{
				Assert.fail("FAIL : On Customer search tab, no search result appeared!");
			}
		}
		
		
	
}
