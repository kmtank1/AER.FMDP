package au.com.shortcuts.live.functionLibrary.settings.customfields;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.settings.customfields.CustomFieldSearchPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class CustomFieldSearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	CustomFieldSearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public CustomFieldSearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        searchpo = new CustomFieldSearchPO(driver);
        itemlist = new ItemListPO(driver);
	}
	
	
	/**
	 * Search added custom field using name.
	 * Select first item in search result.
	 * 
	 * @param customfieldname		Custom field name for search	e.g. AT_Customer_CF_20180109_185617
	 * @return						None
	 */
		public void search_SearchCustomFieldAndClick(String customfieldname) throws InterruptedException
		{
			try 
			{
				itemlist.search_SearchWithText(customfieldname);
				itemlist.search_ClickOnFirstItem();
			} 
			catch (Exception e) 
			{
				selenium.refreshPage();
				help.waitTillPageLoaded();
				itemlist.search_SearchWithText(customfieldname);
				itemlist.search_ClickOnFirstItem();
			}
		}
			
	
	/**
	 * Only search custom field using name.
	 * This method mainly used to search record with partial text.
	 *  
	 * @param customfieldname		Custom field name for search	e.g. AT_Customer_CF_20180109_185617  OR  20180109_185617  
	 * @return						None
	 */
		public void search_SearchCustomField(String customfieldname) throws InterruptedException
		{
			itemlist.search_SearchWithText(customfieldname);
		}
	
	
	/**
	 * Use filter option to select inactive checkbox.
	 * @return	None
	 */
		public void search_Filter_ClickOnInactiveCheckbox() throws InterruptedException
		{
			itemlist.search_ClickOnFilterIcon();
			itemlist.search_ClickOnInactiveCheckbox();
			help.waitTillPageLoaded();
		}
		
	
	/**
	 * Retrieve the "There are no records to display" text
	 * to verify that, no record found during search.
	 * 
	 * @return	No record found message	i.e. There are no records to display
	 */
		public String search_GetNoRecordMsgText()
		{
			return itemlist.search_GetNoRecordMsgText();
		}
		

	/**
	 * Retrieve the Details section data Row by Row.
	 * Collect field "Label" and respective "Value". 
	 * 
	 * @param row	Row number in details section	e.g. 1, 2, 3
	 * @return		Details section value by row	e.g. Name AT_Customer_CF_20180109_185617, Status Active
	 */
		public String getDetailsSectionData(int row)
		{
			return searchpo.getDetailsSectionData(row);
		}
			
	
	/**
	 * Retrieve the Definition section data Row by Row.
	 * Collect field "Label" and respective "Value".
	 * 
	 * @param row	Row number in details section e.g. 1, 2, 3
	 * @return		Definition section value by row e.g. Data type Text, Number of characters 40
	 */
		public String getDefinitionSectionData(int row)
		{
			return searchpo.getDefinitionSectionData(row);
		}
		
	/**
	 * Click on Edit button
	 */
		public void clickOnEditIcon()
		{
			searchpo.clickOnEditIcon();
		}
}
