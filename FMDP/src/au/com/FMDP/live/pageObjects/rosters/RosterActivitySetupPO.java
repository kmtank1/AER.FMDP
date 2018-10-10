package au.com.shortcuts.live.pageObjects.rosters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class RosterActivitySetupPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public RosterActivitySetupPO(WebDriver driver)
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
	
		@FindBy(id="display_name")
		private WebElement name;
		
		@FindBy(css="label[for='is_active'] + *")
		private WebElement status;
		
		@FindBy(id="is_active")
		private WebElement isactive;
		
		@FindBy(className="ctrl-colorPicker")
		private WebElement colour;
		
			@FindBy(css="div[class='sp-container sp-light sp-input-disabled sp-palette-disabled sp-initial-disabled'] div.sp-dragger")
			private WebElement colourdraglocation;
			
			@FindBy(css="div[class='sp-container sp-light sp-input-disabled sp-palette-disabled sp-initial-disabled'] button.sp-choose")
			private WebElement colour_choosebtn;
		
		@FindBy(id="notes")
		private WebElement notes;
		
		@FindBy(css="label[for='is_bookable'] + *")
		private WebElement bookable;
		
		@FindBy(id="is_bookable")
		private WebElement isbookable;
		
		@FindBy(css="label[for='is_payable'] + *")
		private WebElement payable;
		
		@FindBy(id="is_payable")
		private WebElement ispayable;
		
		@FindBy(css="label[for='is_available_walkin'] + *")
		private WebElement availablefor_walkin;
		
		@FindBy(id="is_available_walkin")
		private WebElement is_availablefor_walkin;
		
		
		public void enterName(String namevalue)
		{
			name.clear();
			name.sendKeys(namevalue);
		}
		
		public void setStatus(String statusvalue)
		{
			help.setToggleValue(status, isactive, statusvalue);
		}
		
		public void setColor(String colorposition)
		{
			colour.click();
			selenium.javascript_SetAnAttribute(colourdraglocation, "style", colorposition);
			colourdraglocation.click();
			colour_choosebtn.click();	
		}
		
		public void enterNotes(String notevalue)
		{
			notes.clear();
			notes.sendKeys(notevalue);
		}
		
		public void selectBookable(String bookablevalue)
		{
			help.setToggleValue(bookable, isbookable, bookablevalue);
		}
		
		public void selectPayable(String payablevalue)
		{
			help.setToggleValue(payable, ispayable, payablevalue);
		}
		
		public void selectWalkiins(String walkinvalue)
		{
			help.setToggleValue(availablefor_walkin,is_availablefor_walkin, walkinvalue);
		}
		
	
}
