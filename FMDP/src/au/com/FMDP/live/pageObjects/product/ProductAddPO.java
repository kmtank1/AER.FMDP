package au.com.shortcuts.live.pageObjects.product;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ProductAddPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public ProductAddPO(WebDriver driver)
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
		
		@FindBy(id="product_code")
	    public WebElement itemref;
		
		@FindBy(id="is_retail")
	    public WebElement productusage;
		
		@FindBy(css="label[for='is_active'] + *")
		private WebElement status;
		
		@FindBy(id="is_active")
		private WebElement isactive;
		
		@FindBy(id="stored_unit")
	    public WebElement storedunits;
		
		
	//Sale price section : 
	
		@FindBy(id="sell_price")
	    public WebElement sellprice;
		
		@FindBy(id="future_sell_price")
	    public WebElement futuresaleprice;
		
		@FindBy(id="future_price_level_effective_from")
	    public WebElement effectivefrom;
		
		@FindBy(id="sell_tax_categories")
	    public WebElement taxcategory;
		
	
	//Hierarchy section
		
		@FindBy(id="category_name")
	    public WebElement category;

		//Category side bar
		@FindBy(css="#side-bar-content #product-category-list #search")
	    public WebElement categorysidebar_search;
		
		//Sub Category side bar
		@FindBy(css="#product-subcategory-list #search")
	    public WebElement subcategorysidebar_search;
	
	
	//Supplier
		@FindBy(id="displaySupplierList")
	    public WebElement supplier_add;

		//supplier side bar
		@FindBy(id="supplier-search")
	    public WebElement suppliersidebar_search;
	
		@FindBy(id="buy_price")
	    public WebElement buyprice;
			
		@FindBy(id="future_buy_price")
	    public WebElement futurebuyprice;
		
		@FindBy(id="future_buy_price_effective_from")
	    public WebElement futurebuyprice_effectivefrom;
		
		@FindBy(id="buy_tax")
	    public WebElement futurebuyprice_taxcategory;
		
		@FindBy(id="supplier_code")
	    public WebElement productcode;
		
		@FindBy(id="buy_units")
	    public WebElement orderunits;
		
		@FindBy(id="conversion")
	    public WebElement coversiontostoredunits;
		
		
	//Field validations		
		@FindBy(css="div.app-errorMessage span")
	    public List<WebElement> fieldValidatons;
		
	
		//Details section
		public void enterProductName(String productname)
		{
			name.click();
			name.clear();
			name.sendKeys(productname);
		}
		
		public void clickOnProductName()
		{
			name.click();
		}
		
		public void enterProductDescription(String descriptiontext)
		{
			description.clear();
			description.sendKeys(descriptiontext);
		}
		
		public void enterProductItemReference(String itemrefvalue)
		{
			itemref.clear();
			itemref.sendKeys(itemrefvalue);
		}
		
		public void selectProductUsage(String productusagevalue)
		{
			selenium.selectDropdownValueByText(productusage,productusagevalue);
		}
		
		public void setProductStatus(String statusvalue)
		{
			help.setToggleValue(status, isactive, statusvalue);
		}
		
		public void selectProductStoredUnits(String storedunitvalue)
		{
			selenium.selectDropdownValueByText(storedunits, storedunitvalue);
		}
		
		
		//Sale price section
		public void enterProductSellPrice(String sellpricevalue)
		{
			sellprice.clear();
			sellprice.sendKeys(sellpricevalue);
		}
		
		public void enterProductFuturePrice(String futurepricevalue)
		{
			futuresaleprice.clear();
			futuresaleprice.sendKeys(futurepricevalue);
		}
		
		public void enterProductEffectiveDate(String effectivefromvalue)
		{
			effectivefrom.clear();
			effectivefrom.sendKeys(effectivefromvalue);
			name.click();
		}
		
		public void selectCategoryAndSubCategory(String category,String subcategory)
		{
			selectCategorySubCategory(category,subcategory);
		}
		
		//Supplier section
		public void selectProductSupplier(String supplier)
		{
			selectSupplier(supplier);
		}
		
		public void enterProductSupplierBuyPrice(String buypricevalue)
		{
			selenium.pageScrollInView(buyprice);
			buyprice.clear();
			buyprice.sendKeys(buypricevalue);
		}
		
		public void enterProductSupplierFuturePrice(String buyfuturepricevalue)
		{
			futurebuyprice.clear();
			futurebuyprice.sendKeys(buyfuturepricevalue);
		}
		
		public void enterProductSupplierEffectiveDate(String buyeffectivefromvalue)
		{
			futurebuyprice_effectivefrom.clear();
			futurebuyprice_effectivefrom.sendKeys(buyeffectivefromvalue);
			buyprice.click();
		}
		
		public String selectProductTaxCategory(int taxcategoryindex)
		{
			selenium.pageScrollInView(sellprice);
			return selenium.selectDropdownValueByIndex(taxcategory, taxcategoryindex);
		}
		
		public String selectFutureBuyTaxCategory(int buytaxcategoyindex)
		{
			selenium.pageScrollInView(buyprice);
			return selenium.selectDropdownValueByIndex(futurebuyprice_taxcategory, buytaxcategoyindex);
		}
		
		public void enterProductCode(String buyproductcode)
		{
			productcode.clear();
			productcode.sendKeys(buyproductcode);
		}
		
		public void selectProductOrderUnits(String orderunitsvalue)
		{
			selenium.selectDropdownValueByText(orderunits, orderunitsvalue);
		}
		
		public void enterProductConversionToStoredUnits(String coversionvalue)
		{
			coversiontostoredunits.clear();
			coversiontostoredunits.sendKeys(coversionvalue);
		}
		
		/**
		 * 
		 * @param categoryname
		 * @param subcategoryname
		 */
		public void selectCategorySubCategory(String categoryname,String subcategoryname)
		{
			category.click();
			help.waitTillPageLoaded();
			selenium.pageScrollInView(categorysidebar_search);
			categorysidebar_search.sendKeys(categoryname);
			help.waitTillPageLoaded();
			
			//Selecting first category displayed after search
			selenium.waitTillElementIsClickable(By.cssSelector("div#product-category-list ul li:nth-of-type(1)")).click();;
			
			help.waitTillPageLoaded();
			
			subcategorysidebar_search.sendKeys(subcategoryname);
			help.waitTillPageLoaded();
			
			//Selecting first sub-category displayed after search
			selenium.waitTillElementIsClickable(By.cssSelector("div#product-subcategory-list ul li:nth-of-type(1)")).click();
			help.waitTillPageLoaded();
			
		}
		
		/**
		 * 
		 * @param suppliername
		 */
		public void selectSupplier(String suppliername)
		{
			selenium.pageScrollInView(supplier_add);
			supplier_add.click();
			help.waitTillPageLoaded();
			selenium.waitTillElementIsClickable(suppliersidebar_search).sendKeys(suppliername);
			help.waitTillPageLoaded();
			
			//Selecting first supplier displayed after search
			selenium.waitTillElementIsClickable(driver.findElement(By.cssSelector("#side-bar-content ul.ctrl-clickableList li:nth-of-type(1) span"))).click();
			help.waitTillPageLoaded();
		}
		
	
		public String getAllFieldValidationMessages()
		{
			String msg="";
			
			for(WebElement e:fieldValidatons)
			{
				selenium.pageScrollInView(e);
				msg = msg + e.getText().trim();
			}
			return msg;
		}
	
}
