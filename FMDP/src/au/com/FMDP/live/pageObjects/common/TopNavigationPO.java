package au.com.shortcuts.live.pageObjects.common;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class TopNavigationPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO common;
	JavaHelpers java;
	
	public TopNavigationPO(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        common = new CommonPO(driver);
        java = new JavaHelpers();
        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}
	
	
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 	
	
	//Profile Badge	
		@FindBy(css="a.topBarUser span")
	    public WebElement loggedInUserName;
		
		@FindBy(css="a.topBarUser")
	    public WebElement profileBadge;
		
		
		@FindBy(id="btnChangePassword")
	    public WebElement changePassword;
		
			//Change Password
			@FindBy(id="current_password")
		    public WebElement currentPassword;
			
			@FindBy(id="new_password")
		    public WebElement newPassword;
			
			@FindBy(id="new_password_repeat")
		    public WebElement reenternewPassword;
			
		@FindBy(id="btnLogout")
	    public WebElement logout;
			
		
	//Navigation shortcuts
		@FindBy(css="div.topItems section")
	    public List<WebElement> navigationShortcuts;
		
	//Menu tree
		
		@FindBy(css="ul.menu")
	    public WebElement menuTree;
		
		@FindBy(css="ul.menu li")
	    public List<WebElement> menuTreeItems;

	
	//Menu Tiles
		
		@FindBy(css="div.menuItems a[href='#appointments']")
	    public WebElement appointmentBookTile;	
		
		@FindBy(css="div.menuItems a[href='#customers']")
		public WebElement customerTile;
		
		@FindBy(css="div.menuItems a[href='#employees']")
		public WebElement employeesTile;
		
		@FindBy(css="div.menuItems a[href='#roster']")
		public WebElement rosterTile;
		
		@FindBy(css="div.menuItems a[href='#roster_activities']")
		public WebElement rosterActivitiesTile;
		
		@FindBy(css="div.menuItems a[href='#products']")
		public WebElement productsTile;

		@FindBy(css="div.menuItems a[href='#service-categories']")
		public WebElement servicesTile;
		
		@FindBy(css="div.menuItems a[href='#suppliers']")
		public WebElement suppliersTile;
		
		@FindBy(css="div.menuItems a[href='#product_categories']")
		public WebElement productCategoriesTile;
		
		@FindBy(css="div.menuItems a[href='#payment_types']")
		public WebElement paymentTypesTile;
		
		@FindBy(css="div.menuItems a[href='#receipt']")
		public WebElement receiptTile;
		
		@FindBy(css="div.menuItems a[href='#tasks']")
		public WebElement tasksTile;
		
		@FindBy(css="div.menuItems a[href='#appointments_configuration']")
	    public WebElement appointmentSetupTile;
		
		@FindBy(css="div.menuItems a[href='#contact_methods']")
		public WebElement contactSetupTile;
		
		@FindBy(css="div.menuItems a[href='#tax']")
		public WebElement taxTile;
		
		@FindBy(css="div.menuItems a[href='#regional_settings']")
		public WebElement regionalSettingsTile;
		
		@FindBy(css="div.menuItems a[href='#connection_settings']")
		public WebElement connectionSettingsTile;
		
		@FindBy(css="div.menuItems a[href='#configuration']")
		public WebElement businessSetupTile;
		
		@FindBy(css="div.menuItems a[href='#roles']")
	    public WebElement roleTile;
		
		@FindBy(css="div.menuItems a[href='#user_field_definitions']")
	    public WebElement customFieldTile;
		
		@FindBy(css="div.menuItems a[href='#client_import']")
		public WebElement ClientImportTile;
            
	//Profile Badge
		public String getLoggedInUserName()
		{
			selenium.waitTillElementIsClickable(loggedInUserName);
			return loggedInUserName.getText().trim();
		}
		
		public boolean isProfileBadgeDisplayed()
		{
			try 
			{
				return profileBadge.isDisplayed();
			} 
			catch (Exception e) 
			{
				return false;
			}
		}
		
		public void clickOnProfileBadge()
		{
			profileBadge.click();
		}
		
		public void clickOnChangePassword()
		{
			changePassword.click();
			common.waitTillPageLoaded();
		}
		
			//Change your password sidebar
			public void enterValueForCurrentPassword(String password)
			{
				selenium.javascript_SetValue(currentPassword,password);
				currentPassword.clear();
				currentPassword.sendKeys(password);
			}
			
			public void enterValueForNewPassword(String password)
			{
				newPassword.sendKeys(password);
			}
			
			public void enterValueForReenterPassword(String password)
			{
				reenternewPassword.sendKeys(password);
			}
		
		public void clickOnLogout()
		{
			logout.click();
		}
		
	
		
	//Click on Navigation shortcuts e.g. Menu, Appointments, Employee etc
		public void clickOnNavigationShortcut(String shortcutname)
		{
			for(WebElement e:navigationShortcuts)
			{
				if(e.getText().trim().equalsIgnoreCase(shortcutname))
				{
						e.click();
						common.waitTillPageLoaded();
						break;
				}
			}
		}
		
		
	//Click on item on Menu tree e.g. Operations, Employee and Rosters etc
		public void clickOnMenuItem(String menuitemname) throws InterruptedException
		{
			clickOnNavigationShortcut("MENU");
			
			try {
					selenium.waitTillElementIsVisible(menuTree);
				} 
			catch (Exception e) 
			{
				selenium.refreshPage();
				clickOnNavigationShortcut("MENU");
				selenium.waitTillElementIsVisible(menuTree);
			}
			
			for(WebElement e:menuTreeItems)
			{
				if(e.getText().trim().equalsIgnoreCase(menuitemname))
				{
						selenium.waitTillElementIsClickable(e);
						e.click();
						common.waitTillPageLoaded();
						break;
				}
			}
		}


	//Operations
		
        //Click On Appointment Book
		public void menu_ClickOnAppointmentBookTile()
		{
			selenium.waitTillElementIsVisible(appointmentBookTile);
			appointmentBookTile.click();
			common.waitTillPageLoaded();
		}
        
		//Click On Customers
		public void menu_ClickOnCustomerTile()
		{
			selenium.waitTillElementIsVisible(customerTile);
			customerTile.click();
			common.waitTillPageLoaded();
		}
		
       
	//Employees and Rosters
		
		//Click On Employees
		public void menu_ClickOnEmployeesTile()
		{
			selenium.waitTillElementIsVisible(employeesTile);
			employeesTile.click();
			common.waitTillPageLoaded();
		}
		
		//Click On Roster
		public void menu_ClickOnRosterTile()
		{
			selenium.waitTillElementIsVisible(rosterTile);
			rosterTile.click();
			common.waitTillPageLoaded();
		}
		
		//Click On Roster activities
		public void menu_ClickOnRosterActivitiesTile()
		{
			selenium.waitTillElementIsClickable(rosterActivitiesTile);
			rosterActivitiesTile.click();
			common.waitTillPageLoaded();
		}
		
		
	//Item Setup
		
		//Click On Products
		public void menu_ClickOnProductsTile()
		{
			selenium.waitTillElementIsVisible(productsTile);
			productsTile.click();
			common.waitTillPageLoaded();
		}
		
		//Click On Services
		public void menu_ClickOnServicesTile()
		{
			selenium.waitTillElementIsVisible(servicesTile);
			servicesTile.click();
			common.waitTillPageLoaded();
		}
				
		//Click On Suppliers
		public void menu_ClickOnSuppliersTile()
		{
			selenium.waitTillElementIsVisible(suppliersTile);
			suppliersTile.click();
			common.waitTillPageLoaded();
		}
		
		//Click On Product categories
		public void menu_ClickOnProductCategoriesTile()
		{
			selenium.waitTillElementIsVisible(productCategoriesTile);
			productCategoriesTile.click();
			common.waitTillPageLoaded();
		}

	//Tools
		
		//Click On Client Import
		public void menu_ClickOnClientImportTile()
		{
			selenium.waitTillElementIsVisible(ClientImportTile);
			ClientImportTile.click();
			common.waitTillPageLoaded();
		}
				
	//POS setup
		
		//Click On Payment Types
		public void menu_ClickOnPaymentTypes()
		{
			selenium.waitTillElementIsVisible(paymentTypesTile);
			paymentTypesTile.click();
			common.waitTillPageLoaded();
		}

		//Click On Receipt
		public void menu_ClickOnReceipt()
		{
			selenium.waitTillElementIsVisible(receiptTile);
			receiptTile.click();
			common.waitTillPageLoaded();
		}

		
	//Appointment Book Setup
	
    	//Click On Appointment setup
		public void menu_ClickOnAppointmentSetupTile()
		{
			selenium.waitTillElementIsVisible(appointmentSetupTile);
			appointmentSetupTile.click();
			common.waitTillPageLoaded();
		}
        
		//Click On Tasks
		public void menu_ClickOnTasksTile()
		{
			selenium.waitTillElementIsVisible(tasksTile);
			tasksTile.click();
			common.waitTillPageLoaded();
		}
		
		//Click On Contact Setup
		public void menu_ClickOnContactSetupTile()
		{
			selenium.waitTillElementIsVisible(contactSetupTile);
			contactSetupTile.click();
			common.waitTillPageLoaded();
		}

		
	//General Settings
	
		//Click On Tax
		public void menu_ClickOnTaxTile()
		{
			selenium.waitTillElementIsVisible(taxTile);
			taxTile.click();
			common.waitTillPageLoaded();
		}
		
		//Click On regional settings
		public void menu_ClickOnRegionalSettingsTile()
		{
			selenium.waitTillElementIsVisible(regionalSettingsTile);
			regionalSettingsTile.click();
			common.waitTillPageLoaded();
		}
	
		//Click On connection settings
		public void menu_ClickOnConnectionSettingsTile()
		{
			selenium.waitTillElementIsVisible(connectionSettingsTile);
			connectionSettingsTile.click();
			common.waitTillPageLoaded();
		}
	
		//Click On business setup
		public void menu_ClickOnBusinessSetupTile()
		{
			selenium.waitTillElementIsVisible(businessSetupTile);
			businessSetupTile.click();
			common.waitTillPageLoaded();
		}
			
    	//Click On roles
		public void menu_ClickOnRolesTile()
		{
			selenium.waitTillElementIsVisible(roleTile);
			roleTile.click();
			common.waitTillPageLoaded();
		}
		
		//Click On Custom fields
		public void menu_ClickOnCustomFieldsTile()
		{
			selenium.waitTillElementIsVisible(customFieldTile);
			customFieldTile.click();
			common.waitTillPageLoaded();	
		}
}
		

