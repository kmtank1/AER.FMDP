package au.com.shortcuts.live.functionLibrary.appointment;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.functionLibrary.common.CommonLib;
import au.com.FMDP.live.pageObjects.appointment.ApppintmentBookBreakCreatePO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ApppintmentBookBreakCreateLib
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonLib help;
	ApppintmentBookBreakCreatePO breakcreatepo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public ApppintmentBookBreakCreateLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonLib(driver);
        java = new JavaHelpers();
        breakcreatepo = new ApppintmentBookBreakCreatePO(driver);
        itemlist = new ItemListPO(driver);
	}
	
	
	//Search section
	
		/**
		 * Search break by value and click
		 * @param breakvalue Break value e.g. 15 minutes, 30 minutes 
		 * @throws InterruptedException
		 */
		public void search_SearchBreakAndClick(String breakvalue) throws InterruptedException
		{
			int i=0;
			boolean isfirstitem = false;

			do {
				itemlist.search_SearchWithText(breakvalue);
				
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
