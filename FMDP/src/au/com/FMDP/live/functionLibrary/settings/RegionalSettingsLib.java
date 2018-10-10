package au.com.shortcuts.live.functionLibrary.settings;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.settings.RegionalSettingsPO;


public class RegionalSettingsLib 
{
	WebDriver driver;
	CommonPO help ;
	RegionalSettingsPO regional;
	
	public RegionalSettingsLib(WebDriver driver)
	{	 
        this.driver = driver;
        regional = new RegionalSettingsPO(driver);
        help = new CommonPO(driver);
	}
	
	/**
	 * Update the Regional setting.
	 * Method is responsible to update existing regional setting.
	 * 
	 * @param shorttaxvalue		Short tax information of the regional	e.g. GST
	 * @param longtaxvalue		Long tax information of the regional	e.g. GST
	 * @param taxvalue			Tax number of the regional	e.g. ABN
	 * @param statevalue		State information of the regional	e.g. California
	 * @param postcodevalue		Postal code of the regional	e.g. 10015
	 * @param suburbvalue		Suburb information of the regional	e.g. SanDiego
	 * @param roundingvalue		Currency Rounding value	e.g. 0.05, 0.25 
	 * @param directionvalue	Currency rounding direction	e.g. Nearest, Up, Down 
	 * 
	 */
	public void receiptsetup_Edit(String shorttaxvalue, String longtaxvalue, String taxvalue, String statevalue, String postcodevalue , String suburbvalue, String roundingvalue, String directionvalue)
	{
		regional.enterRegionalShortTax(shorttaxvalue);
		regional.enterRegionalLongTax(longtaxvalue);
		regional.enterRegionalTaxNumber(taxvalue);
		regional.enterRegionalState(statevalue);
		regional.enterRegionalPostalCode(postcodevalue);
		regional.enterRegionalSuburb(suburbvalue);
		regional.selectRegionalCurrencyRounding(roundingvalue);
		regional.seletRegionalCurrencyDirection(directionvalue);
		help.footer_ClickOnDone();
	}
	
	/**
	 * click on Edit button
	 */
	public void clickOnEditIcon()
	{
		regional.clickOnEditIcon();
	}
	
	
	//Read Data
	/**
	 * Retrieve the Labels section data Row by Row.
	 * Collect field "Label" and respective "Value".
	 * 
	 * @param row	Row number in labels section	e.g. 1, 2, 3
	 * @return		Labels section value by row	e.g. Short tax GST, Tax number ABN
	 * 
	 */
	public String getLabelsSectionData(int row)
	{
		return regional.getLabelsSectionData(row);
	}
	
	
	/**
	 * Retrieve the Currency section data Row by Row.
	 * Collect field "Label" and respective "Value".
	 * 
	 * @param row	Row number in currency section	e.g. 1, 2, 3
	 * @return		Currency section value by row	e.g. Rounding 0.05, Direction Nearest
	 * 
	 */
	public String getCurrencySectionData(int row)
	{
		return regional.getCurrencySectionData(row);
	}


}
