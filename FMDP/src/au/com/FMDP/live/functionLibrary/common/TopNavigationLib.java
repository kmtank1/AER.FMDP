package au.com.shortcuts.live.functionLibrary.common;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.TopNavigationPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class TopNavigationLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO common;
	JavaHelpers java;
	TopNavigationPO topnavigation;
	
	public TopNavigationLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        common = new CommonPO(driver);
        java = new JavaHelpers(); 
        topnavigation = new TopNavigationPO(driver);
	}
	
	
	/**
	 * Profile Badge - get logged in user's name
	 * @return text
	 */
	public String getLoggedInUserName()
	{
		return topnavigation.getLoggedInUserName();
	}
	
	/**
	 * Click on Profile badge
	 * @throws InterruptedException
	 */
	public void clickOnProfileBadge() throws InterruptedException
	{
		int i=0;
		boolean isclicked = false;

		do {
			if(topnavigation.isProfileBadgeDisplayed()) 
			{
				topnavigation.clickOnProfileBadge();
				isclicked = true;
				break;
			}
			else 
			{
				i++;
				selenium.refreshPage();
				common.waitTillPageLoaded();
			}

		} while (i < Constants.MaxAttemptForLocatingElement);
		
		//if search result not found then failing test with message 
		if(!isclicked)
		{
			Assert.fail("FAIL : Profile badge can't be clicked successfully");
		}
	}
	
	/**
	 * Fill & submit Change Password form
	 * @param currentpassword current password
	 * @param newpassword new password
	 * @throws InterruptedException
	 */
	public void changePassword_FillDetailsAndDone(String currentpassword,String newpassword) throws InterruptedException
	{
		common.waitTillPageLoaded();
		clickOnProfileBadge();
		topnavigation.clickOnChangePassword();
		topnavigation.enterValueForCurrentPassword(currentpassword);
		topnavigation.enterValueForNewPassword(newpassword);
		topnavigation.enterValueForReenterPassword(newpassword);
		common.waitTillPageLoaded();
		common.footer_ClickOnDone();
	}
	
	/**
	 * Click on Logout link
	 * @throws InterruptedException
	 */
	public void logout() throws InterruptedException
	{
		topnavigation.clickOnProfileBadge();
		topnavigation.clickOnLogout();
		java.sleep(2);
	}
	
	
	/**
	 * Click on Navigation shortcuts 
	 * @param shortcutname navigation shortcut name e.g.Menu, Appointments, Employee etc
	 * @throws InterruptedException
	 */
	public void clickOnNavigationShortcut(String shortcutname) throws InterruptedException
	{
		topnavigation.clickOnNavigationShortcut(shortcutname);
	}
	
	/**
	 * Click on item on Menu tree 
	 * @param menuitemname menu item name e.g. Operations, Employee and Rosters
	 * @throws InterruptedException
	 */
	public void clickOnMenuItem(String menuitemname) throws InterruptedException
	{
		topnavigation.clickOnMenuItem(menuitemname);		
	}
	
			
	//Operations
	
	/**
	 * Click On Appointment Book
	 */
	public void menu_ClickOnAppointmentBookTile()
	{
		topnavigation.menu_ClickOnAppointmentBookTile();
	}
		
	/**
	 * Click On Customers
	 */
	public void menu_ClickOnCustomerTile()
	{
		topnavigation.menu_ClickOnCustomerTile();
	}
	
	
	//Employees and Rosters
	
	/**
	 * Click On Employees
	 */
	public void menu_ClickOnEmployeesTile()
	{
		topnavigation.menu_ClickOnEmployeesTile();
	}
	
	/**
	 * Click On Roster
	 */
	public void menu_ClickOnRosterTile()
	{
		topnavigation.menu_ClickOnRosterTile();
	}
	
	
	/**
	 * Click On Roster activities
	 */
	public void menu_ClickOnRosterActivitiesTile()
	{
		topnavigation.menu_ClickOnRosterActivitiesTile();
	}
	
	
	//Item Setup
	
	/**
	 * Click On Products
	 */
	public void menu_ClickOnProductsTile()
	{
		topnavigation.menu_ClickOnProductsTile();
	}
	
	/**
	 * Click On Services
	 */
	public void menu_ClickOnServicesTile()
	{
		topnavigation.menu_ClickOnServicesTile();
	}
			
	/**
	 * Click On Suppliers
	 */
	public void menu_ClickOnSuppliersTile()
	{
		topnavigation.menu_ClickOnSuppliersTile();
	}
	
	/**
	 * Click On Suppliers
	 */
	public void menu_ClickOnProductCategoriesTile()
	{
		topnavigation.menu_ClickOnProductCategoriesTile();
	}
		
	//POS setup
	
	/**
	 * Click On Payment Types
	 */
	public void menu_ClickOnPaymentTypesTile()
	{
		topnavigation.menu_ClickOnPaymentTypes();
	}
	
	/**
	 * Click On Receipt
	 */
	public void menu_menu_ClickOnReceiptTile()
	{
		topnavigation.menu_ClickOnReceipt();
	}

	
	//Appointment Book Setup

	/**
	 * Click On Appointment book
	 */
	public void menu_ClickOnAppointmentSetupTile()
	{
		topnavigation.menu_ClickOnAppointmentSetupTile();
	}
	
	/**
	 * Click On Tasks
	 */
	public void menu_ClickOnTasksTile()
	{
		topnavigation.menu_ClickOnTasksTile();
	}

	/**
	 * Click On Contact Setup
	 */
	public void menu_ClickOnContactSetupTile()
	{
		topnavigation.menu_ClickOnContactSetupTile();
	}

	
	//General Settings

	/**
	 * Click On Tax
	 */
	public void menu_ClickOnTaxTile()
	{
		topnavigation.menu_ClickOnTaxTile();
	}
	
	/**
	 * Click On Regional settings
	 */
	public void menu_ClickOnRegionalSettingsTile()
	{
		topnavigation.menu_ClickOnRegionalSettingsTile();
	}
	
	/**
	 * Click On Connection settings
	 */
	public void menu_ClickOnConnectionSettingsTile()
	{
		topnavigation.menu_ClickOnConnectionSettingsTile();
	}
	
	/**
	 * Click On business setup
	 */
	public void menu_ClickOnBusinessSetupTile()
	{
		topnavigation.menu_ClickOnBusinessSetupTile();
	}

	/**
	 * Click On roles
	 */
	public void menu_ClickOnRolesTile()
	{
		topnavigation.menu_ClickOnRolesTile();
	}
	
	/**
	 * Click On Custom fields
	 */
	public void menu_ClickOnCustomFieldsTile()
	{
		topnavigation.menu_ClickOnCustomFieldsTile();
	}

	/**
	 * Click on Client Import
	 */
	public void menu_ClickOnClientImportTile()
	{
		topnavigation.menu_ClickOnClientImportTile();
	}
}
