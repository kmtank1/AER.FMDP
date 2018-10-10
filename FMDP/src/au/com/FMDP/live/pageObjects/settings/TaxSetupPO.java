package au.com.shortcuts.live.pageObjects.settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class TaxSetupPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public TaxSetupPO(WebDriver driver)
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
		private WebElement name;
		
		@FindBy(css="label[for='is_active'] + *")
		private WebElement status;
		
		@FindBy(id="is_active")
		private WebElement isactive_status;
		
		@FindBy(css="label[for='is_valid_for_purchase'] + *")
		private WebElement purchase;
		
		@FindBy(id="is_valid_for_purchase")
		private WebElement isAppliesToPurchase;
		
		@FindBy(css="label[for='is_valid_for_sale'] + *")
		private WebElement sale;
		
		@FindBy(id="is_valid_for_sale")
		private WebElement isAppliesToSale;
		
	//Applicable to section : 
		
		@FindBy(css="label[for='applies_to_service'] + *")
		private WebElement service;
		
		@FindBy(id="applies_to_service")
		private WebElement isApplicableToService;
		
		@FindBy(css="label[for='applies_to_product'] + *")
		private WebElement product;
		
		@FindBy(id="applies_to_product")
		private WebElement isApplicableToProduct;
		
		@FindBy(css="label[for='applies_to_sundry'] + *")
		private WebElement sundry;
		
		@FindBy(id="applies_to_sundry")
		private WebElement isApplicableToSundry;
		
		@FindBy(css="label[for='applies_to_expense'] + *")
		private WebElement expense;
		
		@FindBy(id="applies_to_expense")
		private WebElement isApplicableToExpense;
		
	//Current rates section :
		
		@FindBy(className="rateName")
		private WebElement ratename;
		
		@FindBy(className="ratePercent")
		private WebElement ratepercent;
		
		@FindBy(css="#taxRates i.icon-plus")
		private WebElement addrow;
		
	//Future rates section :
		
		@FindBy(id="effective_from_date")
		private WebElement effectivedate;
		
		@FindBy(css="#taxRates input.name")
		private WebElement futureratename;
		
		@FindBy(css="#taxRates input.rate_percent")
		private WebElement futureratepercent;
		
		
		public void enterTaxName(String namevalue)
		{
			name.clear();
			name.sendKeys(namevalue);
		}
		
		public void setTaxStatus(String statusvalue)
		{
			help.setToggleValue(status, isactive_status, statusvalue);
		}
		
		public void setTaxAppliesToPurchase(String purchasevalue)
		{
			help.setToggleValue(purchase, isAppliesToPurchase, purchasevalue);
		}
		
		public void setTaxAppliesToSale(String salevalue)
		{
			help.setToggleValue(sale, isAppliesToSale, salevalue);	
		}
		
		public void setTaxService(String servicevalue)
		{
			help.setToggleValue(service, isApplicableToService, servicevalue);
		}
		
		public void setTaxProduct(String prodcutvalue)
		{
			help.setToggleValue(product, isApplicableToProduct, prodcutvalue);
		}
		
		public void setTaxSundry(String sundryvalue)
		{
			help.setToggleValue(sundry, isApplicableToSundry, sundryvalue);
		}
		
		public void setTaxExpense(String expensevalue)
		{
			help.setToggleValue(expense, isApplicableToExpense, expensevalue);
		}
		
		public void enterTaxEffectiveDate(String effectivedatevalue)
		{
			effectivedate.clear();
			effectivedate.sendKeys(effectivedatevalue);
		}
		
		public void enterCurrentTaxName(String taxratename)
		{
			ratename.clear();			
			ratename.sendKeys(taxratename);
		}
		
		public void enterCurrentTaxRate(String taxratepercent)
		{
			ratepercent.clear();
			ratepercent.sendKeys(taxratepercent);
		}
		
		public void enterFutureTaxName(String futuretaxratename)
		{
			futureratename.clear();
			futureratename.sendKeys(futuretaxratename);
		}
		
		public void enterFutureTaxRate(String futuretaxratepercent)
		{
			futureratepercent.clear();
			futureratepercent.sendKeys(futuretaxratepercent);
		}		
}
