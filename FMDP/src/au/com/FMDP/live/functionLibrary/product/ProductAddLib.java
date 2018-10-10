package au.com.shortcuts.live.functionLibrary.product;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.product.ProductAddPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;


public class ProductAddLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help ;
	ProductAddPO productadd;
	JavaHelpers java;
	
	
	public ProductAddLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        productadd = new ProductAddPO(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
	}
	
	/**
	 * Setup product by providing information in all the fields.
	 * Update information from added product.
	 * 
	 * @param productname				Name of the product	e.g. AT_product_20171207_115734
	 * @param descriptiontext			Description of the product	e.g. Product description added by automated testing
	 * @param itemrefvalue				Products's Item Reference	e.g. New
	 * @param productusagevalue			Product usage information	e.g. Retail, Professional
	 * @param statusvalue				Status of the product	e.g. Active, Inactive
	 * @param storedunitvalue			Store units value of product	e.g. Pack, Litre, Kilogram
	 * @param sellpricevalue			Sell price of product	e.g. 100, 150
	 * @param futurepricevalue			Future price of product	e.g. 135, 160
	 * @param effectivefromvalue		Effective from date	e.g. 13/12/2017
	 * @param taxcategoryindex			Tax category of product	e.g. GST, VAT, Tax Exempt
	 * @param category					Product category information	e.g. Cat_20171226_151038, Automation_Category
	 * @param subcategory				Product sub-category information	e.g. SubCat_20171226_151038, Automation_SubCategory
	 * @param supplier					Supplier name information	e.g. Automation_Supplier, AT_supp_20171222_154621
	 * @param buypricevalue				Buy price information	e.g. 90, 110
	 * @param buyfuturepricevalue		Buy future price information	e.g. 140, 180
	 * @param buyeffectivefromvalue		Buy effective from date	e.g. 20/01/2018
	 * @param buytaxcategoyindex		Buy tax category information	e.g. GST, VAT, Tax Exempt
	 * @param buyproductcode			Buy product code	e.g. AA, BHD, G002
	 * @param orderunitsvalue			Order units information	e.g. Pack, Litre, Kilogram
	 * @param coversionvalue			Conversion to stored units information	e.g. 1, 2
	 * @return							Selected value in tax drop-down
	 * 
	 */
	 public List<String> product_Add(String productname, 
								String descriptiontext, 
								String itemrefvalue, 
								String productusagevalue,
								String statusvalue,
								String storedunitvalue,
								String sellpricevalue, 
								String futurepricevalue, 
								String effectivefromvalue, 
								int taxcategoryindex, 
								String category, 
								String subcategory, 
								String supplier, 
								String buypricevalue, 
								String buyfuturepricevalue, 
								String buyeffectivefromvalue, 
								int buytaxcategoyindex, 
								String buyproductcode, 
								String orderunitsvalue, 
								String coversionvalue)
		{
			List<String> taxdata = new ArrayList<String>(); 
			productadd.enterProductName(productname);
			productadd.enterProductDescription(descriptiontext);
			productadd.enterProductItemReference(itemrefvalue);
			productadd.selectProductUsage(productusagevalue);
			productadd.setProductStatus(statusvalue);
			productadd.selectProductStoredUnits(storedunitvalue);
			productadd.enterProductSellPrice(sellpricevalue);
			productadd.enterProductFuturePrice(futurepricevalue);
			productadd.enterProductEffectiveDate(effectivefromvalue);
			productadd.clickOnProductName();
			String selectedtax = productadd.selectProductTaxCategory(taxcategoryindex);
			taxdata.add(selectedtax);
			productadd.selectCategoryAndSubCategory(category, subcategory);
			productadd.selectProductSupplier(supplier);
			productadd.enterProductSupplierBuyPrice(buypricevalue);
			productadd.enterProductSupplierFuturePrice(buyfuturepricevalue);
			productadd.enterProductSupplierEffectiveDate(buyeffectivefromvalue);
			selectedtax = productadd.selectFutureBuyTaxCategory(buytaxcategoyindex);
			taxdata.add(selectedtax);
			productadd.enterProductCode(buyproductcode);
			productadd.selectProductOrderUnits(orderunitsvalue);
			productadd.enterProductConversionToStoredUnits(coversionvalue);
			help.footer_ClickOnDone();
			return taxdata;
		}
	

	/**
	 * Set product status 
	 * @param status status i.e. Active or Inactive 
	 * 
	 */
	 public void product_ChangeStatus(String status)
	{
		productadd.setProductStatus(status);
		help.footer_ClickOnDone();
	}
	
	
	
}
