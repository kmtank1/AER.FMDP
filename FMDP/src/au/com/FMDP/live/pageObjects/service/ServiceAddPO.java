package au.com.shortcuts.live.pageObjects.service;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ServiceAddPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public ServiceAddPO(WebDriver driver)
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
	
	//Details section : 
	
		@FindBy(id="display_name")
	    public WebElement name;
		
		@FindBy(id="description")
	    public WebElement description;
		
		@FindBy(css="label[for='is_active'] + *")
		private WebElement status;
		
		@FindBy(id="is_active")
		private WebElement isactive;
		
		@FindBy(css="label[for='is_bookable'] + *")
		private WebElement bookable;
		
		@FindBy(id="is_bookable")
		private WebElement isbookable;
		
		@FindBy(css="label[for='is_customer_bookable'] + *")
		private WebElement onlinebooking;
		
		@FindBy(id="is_customer_bookable")
		private WebElement isonlinebooking;
		
		@FindBy(css="label[for='is_available_walkin'] + *")
		private WebElement walkins;
		
		@FindBy(id="is_available_walkin")
		private WebElement iswalkins;
		
		@FindBy(css="label[for='is_customer_available_walkin'] + *")
		private WebElement selfcheckin;
		
		@FindBy(id="is_customer_available_walkin")
		private WebElement isselfcheckin;
		
		@FindBy(css="label[for='is_visit_note_required'] + *")
		private WebElement visitnotes;
		
		@FindBy(id="is_visit_note_required")
		private WebElement isvisitnotes;
		
		@FindBy(id="default_duration_minutes")
	    public WebElement defaultduration;
		
		@FindBy(id="break_duration_minutes")
	    public WebElement breakduration;
		
	
	//price section : 
	
		@FindBy(id="default_sell_price")
	    public WebElement price;
		
		@FindBy(id="default_cost_price")
	    public WebElement cost;
		
		@FindBy(id="sell_tax_category")
	    public WebElement taxcategory;
		
		@FindBy(id="future_sell_price")
	    public WebElement futureprice;
			
		@FindBy(id="future_cost_price")
	    public WebElement futurecost;
		
		@FindBy(id="future_effective_from_date")
	    public WebElement effectivefrom;
		
		@FindBy(id="future_sell_tax_category")
	    public WebElement future_tax;
		
		
	//Details section
		public void enterServiceName(String namevalue)
		{
			name.clear();
			name.sendKeys(namevalue);
		}
		
		public void enterServiceDescription(String descriptiontext)
		{
			description.clear();
			description.sendKeys(descriptiontext);
		}
		
		public void setServiceStatus(String statusvalue)
		{
			help.setToggleValue(status, isactive, statusvalue);
		}
		
		public void setServiceBookableStatus(String bookablevalue)
		{
			help.setToggleValue(bookable, isbookable, bookablevalue);
		}
		
		public void setServiceOnlineBookingStatus(String onlinebookingvalue)
		{
			help.setToggleValue(onlinebooking, isonlinebooking, onlinebookingvalue);
		}
		
		public void setServiceWalkinStatus(String walkinvalue)
		{
			help.setToggleValue(walkins, iswalkins, walkinvalue);
		}
	
		public void setServiceSelfCheckin(String selfcheckinvalue)
		{
			help.setToggleValue(selfcheckin, isselfcheckin, selfcheckinvalue);
		}
	
		public void setServiceVisitNotes(String visitnotesvalue)
		{
			help.setToggleValue(visitnotes, isvisitnotes, visitnotesvalue);
		}
		
		public void enterServiceDefaultDuration(String durationvalue)
		{
			defaultduration.clear();
			defaultduration.sendKeys(durationvalue);
		}
		
		public void enterServiceBreakDuration(String breakvalue)
		{
			breakduration.clear();
			breakduration.sendKeys(breakvalue);
		}
		
		
	//Price section
		public void enterServicePrice(String pricevalue)
		{
			price.clear();
			price.sendKeys(pricevalue);
		}
		
		public void enterServiceCosts(String costvalue)
		{
			cost.clear();
			cost.sendKeys(costvalue);
		}

		public void enterServiceFuturePrice(String futurepricevalue)
		{
			futureprice.clear();
			futureprice.sendKeys(futurepricevalue);
		}
		
		public void enterServiceFutureCosts(String futurecostvalue)
		{
			futurecost.clear();
			futurecost.sendKeys(futurecostvalue);
		}
		
		public void enterServiceEffectiveFromDate(String effectivefromvalue)
		{
			effectivefrom.clear();
			effectivefrom.sendKeys(effectivefromvalue);
			try 
			{ 
				breakduration.click();
			} 
			catch (Exception e) 
			{
				future_tax.click();
			}
		}
		
		public String selectServiceTax(int taxvalue)
		{
			return selenium.selectDropdownValueByIndex(taxcategory, taxvalue);
		}
		
		public String selectServiceFutureTax(int futuretaxvalue)
		{
			return selenium.selectDropdownValueByIndex(future_tax, futuretaxvalue);
		}
	
		
}
