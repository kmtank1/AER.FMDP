package au.com.shortcuts.live.functionLibrary.appointment;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.functionLibrary.common.CommonLib;
import au.com.FMDP.live.pageObjects.appointment.AppointmentCreateServicesTabPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class AppointmentCreateDetailsTabLib
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonLib help;
	AppointmentCreateServicesTabPO servicestabpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public AppointmentCreateDetailsTabLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonLib(driver);
        java = new JavaHelpers();
        servicestabpo = new AppointmentCreateServicesTabPO(driver);
        itemlist = new ItemListPO(driver);
	}
	
	
	//Search section
	
		/**
		 * Search category by name and click
		 * @param categoryname Service category name
		 * @throws InterruptedException
		 */
		public void search_SearchCategoryAndClick(String categoryname) throws InterruptedException
		{
			int i=0;
			boolean isfirstitem = false;

			do {
				itemlist.search_SearchWithText(categoryname);
				
				if (itemlist.search_IsFirstItemDisplayed()) 
				{
					itemlist.search_ClickOnFirstItem();
					isfirstitem = true;
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
		
		
		/**
		 * Search service by name and click
		 * @param servicename  service name
		 * @throws InterruptedException
		 */
		public void search_SearchServiceAndClick(String servicename) throws InterruptedException
		{
			int i=0;
			boolean isfirstitem = false;

			do {
				itemlist.search_SearchWithText(servicename);
				
				if (itemlist.search_IsFirstItemDisplayed()) 
				{
					itemlist.search_ClickOnFirstItem();
					isfirstitem = true;
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
		
	
	//Search section
			
		/**
		 * Read Selected service data row and column wise
		 * @param row
		 * @param column
		 * @return data for given row and column
		 */
		public String getSelectedServiceData(int row, int column)
		{
			return servicestabpo.getSelectedServiceData(row, column);
		}
		
	
}
