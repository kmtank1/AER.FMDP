package au.com.shortcuts.live.pageObjects.settings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class RegionalSettingsPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public RegionalSettingsPO(WebDriver driver)
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
		
	//Labels section : 
	
		@FindBy(id="short_tax_label")
		private WebElement shorttax;
		
		@FindBy(id="long_tax_label")
		private WebElement longtax;
		
		@FindBy(id="tax_number_label")
		private WebElement taxnumber;
		
		@FindBy(id="state_label")
		private WebElement state;
		
		@FindBy(id="post_code_label")
		private WebElement postcode;
		
		@FindBy(id="suburb_label")
		private WebElement suburb;
		
	//Currency section :
		
		@FindBy(id="currency_rounding_amount")
		private WebElement rounding;
		
		@FindBy(id="currency_rounding_direction_code")
		private WebElement direction;
			
		
	//Edit 	view
		public void enterRegionalShortTax(String shorttaxvalue)
		{
			shorttax.clear();
			shorttax.sendKeys(shorttaxvalue);
		}
		
		public void enterRegionalLongTax(String longtaxvalue)
		{
			longtax.clear();
			longtax.sendKeys(longtaxvalue);
		}
		
		public void enterRegionalTaxNumber(String taxvalue)
		{
			taxnumber.clear();
			taxnumber.sendKeys(taxvalue);
		}
		
		public void enterRegionalState(String statevalue)
		{
			state.clear();
			state.sendKeys(statevalue);
		}
		
		public void enterRegionalPostalCode(String postcodevalue)
		{
			postcode.clear();
			postcode.sendKeys(postcodevalue);
		}
		
		public void enterRegionalSuburb(String suburbvalue)
		{
			suburb.clear();
			suburb.sendKeys(suburbvalue);
		}
		
		public void selectRegionalCurrencyRounding(String roundingvalue)
		{
			selenium.selectDropdownValueByText(rounding, roundingvalue);
		}
		
		public void seletRegionalCurrencyDirection(String directionvalue)
		{
			selenium.selectDropdownValueByText(direction, directionvalue);
		}
		
		
	//Read view
				
		//Labels section
		public String getLabelsSectionData(int row)
		{
			String cssSelector = "fieldset[data-link='#labels-section'] ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//Currency section
		public String getCurrencySectionData(int row)
		{
			String cssSelector = "fieldset[data-link='#currency-section'] ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//Click on Edit icon
		public void clickOnEditIcon()
		{
			editIcon.click();
			help.waitTillPageLoaded();
		}
				
}
