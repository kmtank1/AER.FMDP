package au.com.shortcuts.live.functionLibrary.employee;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.functionLibrary.common.CommonLib;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.employee.EmployeeSearchPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class EmployeeSearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonLib help;
	EmployeeSearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public EmployeeSearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonLib(driver);
        java = new JavaHelpers();
        searchpo = new EmployeeSearchPO(driver);
        itemlist = new ItemListPO(driver);
	}
	
	//Search section
	
		/**
		 * Search Employee by alias and click
		 * @param alias employee alias name
		 * @throws InterruptedException
		 */
		public void search_SearchEmployeeAndClick(String alias) throws InterruptedException
		{
			int i=0;
			boolean isfirstitem = false;

			do {
				itemlist.search_SearchWithText(alias);
				
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
				Assert.fail("FAIL : On Employee search, no search result appeared!");
			}
		}
		
		/**
		 * Search employee by alias
		 * @param alias employee alias name
		 */
		public void search_SearchEmployee(String alias)
		{
			itemlist.search_SearchWithText(alias);
		}
		
		/**
		 * Click on Inactive box on filter
		 */
		public void search_Filter_ClickOnInactiveCheckbox()
		{
			itemlist.search_ClickOnFilterIcon();
			itemlist.search_ClickOnInactiveCheckbox();
			help.waitTillPageLoaded();
		}
		
		/**
		 * Get no record text
		 * @return no record text
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
		 * Read Phone section data row wise including labels
		 * @param row
		 * @return data for given row
		 */
		public String getPhoneSectionData(int row)
		{
			return searchpo.getPhoneSectionData(row);
		}
		
		/**
		 * Read Address section data row wise including labels
		 * @param row
		 * @return data for given row
		 */
		public String getAddressSectionData()
		{
			return searchpo.getAddressSectionData();
		}
		
		/**
		 * Read Appointment Book section data row wise including labels
		 * @param row
		 * @return data for given row
		 */
		public String getAppointmentBookSectionData(int row)
		{
			return searchpo.getAppointmentBookSectionData(row);
		}
				
		
	//Edit
		/**
		 * Click on Edit button
		 */
		public void clickOnEditIcon()
		{
			searchpo.clickOnEditIcon();
		}
		
}
