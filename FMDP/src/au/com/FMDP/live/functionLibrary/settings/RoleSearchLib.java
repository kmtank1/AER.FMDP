package au.com.shortcuts.live.functionLibrary.settings;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.settings.RoleSearchPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class RoleSearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	RoleSearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public RoleSearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        searchpo = new RoleSearchPO(driver);
        itemlist = new ItemListPO(driver);
	}
	
		//Search section
		
			/**
			 * Search role & click on it
			 * @param rolename role name
			 * @throws InterruptedException
			 */
			public void search_SearchRoleAndClick(String rolename) throws InterruptedException
			{
				int i=0;
				boolean isfirstitem = false;

				do {
					itemlist.search_SearchWithText(rolename);
					
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
					Assert.fail("FAIL : On Role search, no search result appeared!");
				}
			}
			
			/**
			 * Search role
			 * @param rolename role name
			 * @throws InterruptedException
			 */
			public void search_SearchRole(String rolename) throws InterruptedException
			{
				itemlist.search_SearchWithText(rolename);
			}
			
			/**
			 * On Search filter, select inactive checkbox
			 * @throws InterruptedException
			 */
			public void search_Filter_ClickOnInactiveCheckbox() throws InterruptedException
			{
				itemlist.search_ClickOnFilterIcon();
				itemlist.search_ClickOnInactiveCheckbox();
				help.waitTillPageLoaded();
			}
			
			/**
			 * Get text when no records are displayed
			 * @return text
			 */
			public String search_GetNoRecordMsgText()
			{
				return itemlist.search_GetNoRecordMsgText();
			}
		
			
		//Details section
			
			/**
			 * Get Details section data by each row
			 * @param row row number of given data e.g. 1,2 etc
			 * @return text
			 */
			public String getDetailsSectionData(int row)
			{
				return searchpo.getDetailsSectionData(row);
			}
			
			
		//Employees section
			
			/**
			 * Get Employee section data by each row
			 * @param row row number of given data e.g. 1,2 etc
			 * @return text
			 */
			public String getEmployeesSectionData(int row)
			{
				return searchpo.getEmployeesSectionData(row);
			}
			
			
		//Permissions section
			
			/**
			 * Get Appointment book section data by each row
			 * @param row row number of given data e.g. 1,2 etc
			 * @return text
			 */
			public String getAppointmentBookSectionData(int row)
			{
				return searchpo.getAppointmentBookSectionData(row);
			}
			
			/**
			 * Get Configuration section data by each row
			 * @param row row number of given data e.g. 1,2 etc
			 * @return text
			 */
			public String getConfigurationSectionData(int row)
			{
				return searchpo.getConfigurationSectionData(row);
			}
			
			/**
			 * Get General section data by each row
			 * @param row row number of given data e.g. 1,2 etc
			 * @return text
			 */
			public String getGeneralSectionData(int row)
			{
				return searchpo.getGeneralSectionData(row);
			}
			
			/**
			 * Get Customer section data by each row
			 * @param row row number of given data e.g. 1,2 etc
			 * @return text
			 */
			public String getCustomerSectionData(int row)
			{
				return searchpo.getCustomerSectionData(row);
			}
			
			/**
			 * Get Employee section data by each row
			 * @param row row number of given data e.g. 1,2 etc
			 * @return text
			 */	
			public String getEmployeeSectionData(int row)
			{
				return searchpo.getEmployeeSectionData(row);
			}
			
			/**
			 * Get Point Of Sale section data by each row
			 * @param row row number of given data e.g. 1,2 etc
			 * @return text
			 */
			public String getPointOfSaleSectionData(int row)
			{
				return searchpo.getPointOfSaleSectionData(row);
			}
			
			/**
			 * Get Report section data by each row
			 * @param row row number of given data e.g. 1,2 etc
			 * @return text
			 */
			public String getReportsSectionData(int row)
			{
				return searchpo.getReportsSectionData(row);
			}
			
			/**
			 * Get Tools section data by each row
			 * @param row row number of given data e.g. 1,2 etc
			 * @return text
			 */
			public String getToolsSectionData(int row)
			{
				return searchpo.getToolsSectionData(row);
			}
			
			/**
			 * Get Walk in section data by each row
			 * @param row row number of given data e.g. 1,2 etc
			 * @return text
			 */
			public String getWalkinManagerSectionData(int row)
			{
				return searchpo.getWalkinManagerSectionData(row);
			}
			
			/**
			 * Get Services section data by each row
			 * @param row row number of given data e.g. 1,2 etc
			 * @return text
			 */
			public String getServicesSectionData(int row)
			{
				return searchpo.getServicesSectionData(row);
			}
			
			
		//Edit	
			/**
			 * Click on Edit button
			 */
			public void clickOnEditIcon()
			{
				searchpo.clickOnEditIcon();
			}
			
			/**
			 * Click on Details text to open edit view
			 */
			public void clickOnDetailsText()
			{
				searchpo.clickOnDetailsText();
			}
}
