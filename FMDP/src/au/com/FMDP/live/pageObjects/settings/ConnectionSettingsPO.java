package au.com.shortcuts.live.pageObjects.settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ConnectionSettingsPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public ConnectionSettingsPO(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        help = new CommonPO(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}
	
	
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 	
	
	//Read View:
	
	//Labels section 
	
		@FindBy(css=".editableSection i.icon-pencil")
		private WebElement editIcon;
			

	//Edit mode:
		
	//Selection section : 
	
		@FindBy(id="marketing-link")
		private WebElement marketing;
		
		@FindBy(id="sms-link")
		private WebElement sms;
		
		
	//Marketing section :
		@FindBy(id="marketing_partner_id")
		private WebElement partnerid;
		
		@FindBy(id="marketing_username")
		private WebElement marketing_username;
		
		@FindBy(id="marketing_password")
		private WebElement marketing_password;
		
	//SMS section
		@FindBy(id="sms_username")
		private WebElement sms_username;
		
		@FindBy(id="sms_password")
		private WebElement sms_password;
		
	
		
	//Selection
		
		public void selectMarketing()
		{
			marketing.click();
			help.waitTillPageLoaded();
		}
		
		public void selectSMS()
		{
			sms.click();
			help.waitTillPageLoaded();
		}
		
		
	//Edit 	view
		
		public void enterMarketingParterId(String id)
		{
			partnerid.clear();
			partnerid.sendKeys(id);
		}
		
		public void enterMarketingUsername(String username)
		{
			marketing_username.clear();
			marketing_username.sendKeys(username);
		}
		
		public void enterMarketingPassword(String password)
		{
			marketing_password.clear();
			marketing_password.sendKeys(password);
		}
		
		
		public void enterSmsUsername(String username)
		{
			sms_username.clear();
			sms_username.sendKeys(username);
		}
		
		public void enterSmsPassword(String password)
		{
			sms_password.clear();
			sms_password.sendKeys(password);
		}
		
		
	//Read view
				
		//Connection details section
		public String getConnectionDetailsSectionData(int row)
		{
			String cssSelector = "fieldset[data-link='#connection-section'] ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		

		//Click on Edit icon
		public void clickOnEditIcon()
		{
			editIcon.click();
			help.waitTillPageLoaded();
		}
		
	
			
}
