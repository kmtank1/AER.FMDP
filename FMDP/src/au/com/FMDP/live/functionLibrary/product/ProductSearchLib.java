package au.com.shortcuts.live.functionLibrary.product;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.product.ProductSearchPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ProductSearchLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	ProductSearchPO searchpo;
	JavaHelpers java;
	ItemListPO itemlist;
	
	
	public ProductSearchLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
        searchpo = new ProductSearchPO(driver);
        itemlist = new ItemListPO(driver);
        
	}
	
		//Search section
		
			/**
			 * Search product by name & click
			 * @param name product name
			 * @throws InterruptedException
			 */
			public void search_SearchProductAndClick(String name) throws InterruptedException
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
					Assert.fail("FAIL : On Product search, no search result appeared!");
				}
			}
			
			/**
			 * Search product by name
			 * @param name product name
			 */
			public void search_SearchProduct(String name) 
			{
				itemlist.search_SearchWithText(name);
			}
			
			/**
			 * click on Inactive checkbox on filter
			 */
			public void search_Filter_ClickOnInactiveCheckbox() 
			{
				itemlist.search_ClickOnFilterIcon();
				itemlist.search_ClickOnInactiveCheckbox();
				help.waitTillPageLoaded();
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
			
			/**
			 * Read sale price section data row wise including labels
			 * @param row
			 * @return data for given row
			 */
			public String getSalePriceSectionData(int row)
			{
				return searchpo.getSalePriceSectionData(row);
			}
			
			/**
			 * Read Hierarchy section data row wise including labels
			 * @param row
			 * @return data for given row
			 */
			public String getHierarchySectionData(int row)
			{
				return searchpo.getHierarchySectionData(row);
			}
				
			/**
			 * Read Supplier section data row wise including labels
			 * @param row
			 * @return data for given row
			 */
			public String getSupplierSectionData(int row, int column)
			{
				return searchpo.getSupplierSectionData(row, column);
			}
				
			
}
