package au.com.shortcuts.live.functionLibrary.pos;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.pos.POSGridPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class POSGridLib
{
	WebDriver driver;
	SeleniumHelpers selenium;
	JavaHelpers java;
	CommonPO common;
	ItemListPO itemlist;
	POSGridPO posgrid;
	
	public POSGridLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        java = new JavaHelpers(); 
        common = new CommonPO(driver);
        itemlist = new ItemListPO(driver);
        posgrid = new POSGridPO(driver);
	}
	
	
	//Common
	
		/**
		 * Footer - Click on In Service button
		 */
		public void footer_ClickOnInServiceButton()
		{
			posgrid.footer_ClickOnInServiceButton();
		}
	
		
		/**
		 * Footer - Click on New Sale button
		 */
		public void footer_ClickOnNewSaleButton()
		{
			posgrid.footer_ClickOnNewSaleButton();
		}
		
		
		/**
		 * Footer - Click on New Customer button
		 */
		public void footer_ClickOnNewCustomerButton()
		{
			posgrid.footer_ClickOnNewCustomerButton();
		}
	
		
		/**
		 * Footer - Click on New Walk-In button
		 */
		public void footer_ClickOnRetailButton()
		{
			posgrid.footer_ClickOnRetailButton();
		}
	
	
	//Employee
	
		/**
		 * Update employee for Product or Service.
		 * @param row	Item row number in grid	e.g. 1, 2, 3
		 * @param employeename	Employee Name	e.g. AT_20180402_120434
		 */
		public void employeeDetail_Udpate(int row, String employeename)
		{
			posgrid.openEmployeeSideBar(row);
			itemlist.search_SearchWithText(employeename, posgrid.GetSearch_Input());
			itemlist.search_ClickOnFirstItem(posgrid.GetFirstItem_List());
		}
		
		
	//Price
		
		/**
		 * Update price information of Product or Service.
		 * @param row	Item row number in grid	e.g. 1, 2, 3
		 * @param pricevalue	Price Information	e.g. 185.00, 250.00
		 */
		public void priceDetail_Update(int row, String pricevalue)
		{
			posgrid.openPriceSideBar(row);
			posgrid.enterPrice(pricevalue);
			posgrid.footer_ClickOnApplyButton();
			common.waitTillPageLoaded();
		}
		
		
		/**
		 * Update price information of Product or Service using Keypad.
		 * @param row	Item row number in grid	e.g. 1, 2, 3
		 * @param price	Price Information	e.g. 105.00, 80.00, 150.00
		 */
		public void priceDetail_KeypadUpdate(int row, int price)
		{
			posgrid.openPriceSideBar(row);
			posgrid.numericKeypad(price);
			posgrid.footer_ClickOnApplyButton();
			common.waitTillPageLoaded();
		}
		
	
	//Quantity
		
		/**
		 * Increase quantity of Product or Service.
		 * @param row	Item row number in grid	e.g. 1, 2, 3
		 * @param quantity	Quantity value	e.g. 2, 3, 4  (Default is 1)
		 */
		public void quantityIncrease(int row, int quantity)
		{
			posgrid.quantityIncrease(row, quantity);
		}
		
		
		/**
		 * Decrease quantity of Product or Service.
		 * @param row	Item row number in grid	e.g. 1, 2, 3
		 * @param quantity	Quantity value	e.g. 2, 3, 4  (Default is 1)	
		 */
		public void quantityDecrease(int row, int quantity)
		{
			posgrid.quantityDecrease(row, quantity);
		}
		
		
		/**
		 * Update quantity information of Product or Service.
		 * @param row	Item row number in grid	e.g. 1, 2, 3
		 * @param quantity	Quantity value	e.g. 2, 3, 4  (Default is 1)
		 */
		public void quantityDetail_Update(int row, String quantity)
		{
			posgrid.openQuantitySideBar(row);
			posgrid.enterQuantity(quantity);
			posgrid.footer_ClickOnApplyButton();
			common.waitTillPageLoaded();
		}
		
		
		/**
		 * Update quantity information of Product or Service using Keypad.
		 * @param row	Item row number in grid	e.g. 1, 2, 3
		 * @param quantity	Quantity value	e.g. 2, 3, 4  (Default is 1)
		 */
		public void quantityDetail_KeypadUpdate(int row, int quantity)
		{
			posgrid.openQuantitySideBar(row);
			posgrid.numericKeypad(quantity);
			posgrid.footer_ClickOnApplyButton();
			common.waitTillPageLoaded();
		}
		
		
	//Discount
		
		/**
		 * Select default discount for Product or Service.
		 * @param row	Item row number in grid	e.g. 1, 2, 3
		 * @param discountvalue Discount value e.g. 10%, 20%
		 */
		public void discountPercentage_Default(int row, String discountvalue)
		{
			posgrid.openDiscountSideBar(row);
			posgrid.selectDiscount(discountvalue);
			common.waitTillPageLoaded();
		}
		
		
		/**
		 * Add discount amount for Product or Service.
		 * @param row					Item row number in grid	e.g. 1, 2, 3
		 * @param discountamount		Discount amount	e.g. 75, 60, 110 
		 * @throws InterruptedException	
		 */
		public void discountAmount_Add(int row, String discountamount) throws InterruptedException
		{
			posgrid.openDiscountSideBar(row);
			posgrid.ClikOnAmount();
			posgrid.enterDiscountAmount(discountamount);
			posgrid.footer_ClickOnApplyButton();
		}
	
		
		/**
		 * Add discount amount for Product or Service using Keypad.
		 * @param row					Item row number in grid	e.g. 1, 2, 3
		 * @param discountamount		Discount amount	e.g. 75, 60, 110
		 * @throws InterruptedException
		 */
		public void discountAmount_Keypad(int row, int discountamount) throws InterruptedException
		{
			posgrid.openDiscountSideBar(row);
			posgrid.ClikOnAmount();
			posgrid.numericKeypad(discountamount);
			posgrid.footer_ClickOnApplyButton();
		}
		
		
		/**
		 * Add discount percentage for Product or Service.
		 * @param row					Item row number in grid	e.g. 1, 2, 3
		 * @param discountpercentage	Discount percentage	e.g. 10%, 25%, 15.50%
		 * @throws InterruptedException
		 */
		public void discountPercentage_Add(int row, String discountpercentage) throws InterruptedException
		{
			posgrid.openDiscountSideBar(row);
			posgrid.ClickOnPercentage();
			posgrid.enterDiscountPercentage(discountpercentage);
			posgrid.footer_ClickOnApplyButton();
		}
		

		/**
		 * Add discount percentage for Product or Service using Keypad.
		 * @param row					Item row number in grid	e.g. 1, 2, 3
		 * @param discountpercentage	Discount percentage	e.g. 10%, 25%, 15.50%
		 * @throws InterruptedException
		 */
		public void discountPercentage_Keypad(int row, int discountpercentage) throws InterruptedException
		{
			posgrid.openDiscountSideBar(row);
			posgrid.ClickOnPercentage();
			posgrid.numericKeypad(discountpercentage);
			posgrid.footer_ClickOnApplyButton();
		}
		
	
		/**
		 * Select discount option
		 * @param entityname   i.e. Amount, Percentage
		 * @throws InterruptedException
		 */
		public void clickOnEntityItem(String entityname) throws InterruptedException
		{
			if(entityname.equalsIgnoreCase("amount"))
			{
				posgrid.ClikOnAmount();
			}
			if(entityname.equalsIgnoreCase("percentage"))
			{
				posgrid.ClickOnPercentage();
			}
		}
		
		
	//Delete
		
		/**
		 * Delete item from grid.
		 * @param row	Item row number in grid	e.g. 1, 2, 3
		 */
		public void deleteItem(int row)
		{
			posgrid.deleteItem(row);
		}
	
		
	//Get Grid Section Details
	
		/**
		 * Read Grid section data row & column wise
		 * @param row
		 * @param column
		 * @return	data for given row and column
		 */
		public String getGridSectionData(int row, int column)
		{
			return posgrid.getItemGridData(row, column);
		}
	
		
	//Get Summary Section Details
		
		/**
		 * Read Summary section data section, row & column wise
		 * @param section
		 * @param row
		 * @param column
		 * @return	data for given section, row and column
		 */
		public String getSummarySectionData(int section, int row, int column)
		{
			return posgrid.getSummaryDetailsData(section, row, column);
		}
}

