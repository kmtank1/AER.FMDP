package au.com.shortcuts.live.functionLibrary.settings;
import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.settings.BusinessSetupPO;

public class BusinessSetupLib 
{
	WebDriver driver;
	CommonPO help ;
	BusinessSetupPO business;
	
	public BusinessSetupLib(WebDriver driver)
	{	 
        this.driver = driver;
        business = new BusinessSetupPO(driver);
        help = new CommonPO(driver);
	}
	
	
	//Edit
	
	/**
	 * Update the business information.
	 * Method is responsible to update existing business profile.
	 * 
	 * @param namevalue					Name of business	e.g. FitnessFreak(FF)
	 * @param abnvalue					Business ABN info	e.g. AABBCCDD
	 * @param refvalue					Reference of business	e.g. Piyush
	 * @param sitecodevalue				Site code of business	e.g. ABBC
	 * @param startofweekvalue			Business' start of week e.g. Monday
	 * @param street1value				Stree1 information of business address	e.g. 166 Knapp Street
	 * @param street2value				Stree2 information of business address	e.g. Lan No. 105
	 * @param suburbvalue				Suburb information of business address	e.g. Fortitude Valley
	 * @param statevalue				State information of business address	e.g. California
	 * @param pcodevalue				Postal code of business address	e.g. 10025
	 * @param sunday					Sunday's Status and opening/closing hours	e.g. Sunday: "Closed"  
	 * @param monday					Monday's Status and opening/closing hours	e.g. Sunday: "Open" or "Closed", Opening: 09:00 AM, Closing: 09:00 PM
	 * @param tuesday					Tuesday's Status and opening/closing hours	e.g. Sunday: "Open" or "Closed", Opening: 09:00 AM, Closing: 09:00 PM
	 * @param wednessday				Wednesday's Status and opening/closing hours	e.g. Sunday: "Open" or "Closed", Opening: 09:00 AM, Closing: 09:00 PM
	 * @param thursday					Thursday's Status and opening/closing hours	e.g. Sunday: "Open" or "Closed", Opening: 09:00 AM, Closing: 09:00 PM
	 * @param friday					Friday's Status and opening/closing hours	e.g. Sunday: "Open" or "Closed", Opening: 09:00 AM, Closing: 09:00 PM
	 * @param saturaday					Saturdays's Status and opening/closing hours	e.g. Sunday: "Open" or "Closed", Opening: 09:00 AM, Closing: 09:00 PM
	 * @param phonevalue				Business phone information	e.g. 9876543210
	 * @param faxvalue					Business fax information	e.g. 9012345678
	 * @param emailvalue				Business email information	e.g. fitnessfreak@mailinator.com
	 * @param websitevalue				Business website URL	e.g. https://www.fitnessfreak.com/
	 * @param shortdescriptionvalue		Local salon sort description	e.g. A new tool to Shortcuts this year, Customized Forms enables clinics and spas...
	 * @param longdescriptionvalue		Local salon long description	e.g. All the client needs to do is sit back, complete and sign the form on a tablet. Once completed, and the terms and conditions are accepted...
	 * @param pricingbandvalue			Pricing band of business	e.g. Premium, Budget, Mainstream
	 * 
	 */
		public void businesssetup_Edit(String namevalue,String abnvalue,String refvalue,String sitecodevalue,
									   String startofweekvalue,String street1value,String street2value,String suburbvalue,String statevalue,String pcodevalue,
									   String[] sunday,String[] monday,String[] tuesday,String[] wednessday,String[] thursday,String[] friday,String[] saturaday,
									   String phonevalue,String faxvalue,String emailvalue,String websitevalue,
									   String shortdescriptionvalue,String longdescriptionvalue,String pricingbandvalue)
		{
			business.enterBusinessName(namevalue);
			business.enterBusinessAbn(abnvalue);
			business.enterBusinessReference(refvalue);
			business.enterBusinessSiteCode(sitecodevalue);
			business.selectBusinessStartOfWeek(startofweekvalue);
			
			business.enterBusinessAddressStreet1(street1value);
			business.enterBusinessAddressStreet2(street2value);
			business.enterBusinessAddressSuburb(suburbvalue);
			business.enterBusinessAddressState(statevalue);
			business.enterBusinessAddressPcode(pcodevalue);
						
			business.setBusinessOpeningHours(1, sunday[0], sunday[1], sunday[2]);
			business.setBusinessOpeningHours(2, monday[0], monday[1], monday[2]);
			business.setBusinessOpeningHours(3, tuesday[0], tuesday[1], tuesday[2]);
			business.setBusinessOpeningHours(4, wednessday[0], wednessday[1], wednessday[2]);
			business.setBusinessOpeningHours(5, thursday[0], thursday[1], thursday[2]);
			business.setBusinessOpeningHours(6, friday[0], friday[1], friday[2]);
			business.setBusinessOpeningHours(7, saturaday[0], saturaday[1], saturaday[2]);
			
			business.enterBusinessPhone(phonevalue);
			business.enterBusinessFax(faxvalue);
			business.enterBusinessEmail(emailvalue);
			business.enterBusinessWebsite(websitevalue);
			
			business.enterBusinessShortDescription(shortdescriptionvalue);
			business.enterBusinessLongDescription(longdescriptionvalue);
			business.selectBusinessPriceBand(pricingbandvalue);
			
			help.footer_ClickOnDone();
		}
	
	/**
	 * Click on Edit button
	 */
	public void clickOnEditIcon()
	{
		business.clickOnEditIcon();
	}
	
		
	//Read Data
	
	/**
	 * Retrieve the Details section data Row by Row.
	 * Collect field "Label" and respective "Value". 
	 * 
	 * @param row	Row number in details section	e.g. 1, 2, 3
	 * @return		Details section value by row	e.g. Name FitnessFreak(FF), Start of week Monday
	 * 
	 */
		public String getDetailsSectionData(int row)
		{
			return business.getDetailsSectionData(row);
		}
	
		
	/**
	 * Retrieve the Opening hours section data Row by Row.
	 * Collect field "Label" and respective "Value". 
	 * 
	 * @param row	Row number in opening hours section	e.g. 1, 2, 3
	 * @return		Opening hours section value by row	e.g. Sunday Closed, Monday 9:00 AM – 9:00 PM
	 * 
	 */
		public String getOpeningHoursSectionData(int row)
		{
			return business.getOpeningHoursSectionData(row);
		}
	
		
	/**
	 * Retrieve the Address section data Row by Row.
	 * Collect field "Label" and respective "Value". 
	 * 
	 * @param row	Row number in address section	e.g. 1, 2
	 * @return		Address section value by row	e.g. Address 166 Knapp Street near
	 * 														 	 Fortitude Valley QLD 4006
	 * 
	 */
		public String getAddressSectionData(int row)
		{
			return business.getAddressSectionData(row);
		}
	
		
	/**
	 * Retrieve the Contact section data Row by Row.
	 * Collect field "Label" and respective "Value". 
	 * 
	 * @param row	Row number in contact section	e.g. 1, 2, 3
	 * @return		Contact section value by row	e.g. Email fitnessfreak@mailinator.com, Website https://www.fitnessfreak.com/
	 * 
	 */
		public String getContactSectionData(int row)
		{
			return business.getContactSectionData(row);
		}
	
		
	/**
	 * Retrieve the my local salon section data Row by Row.
	 * Collect field "Label" and respective "Value". 
	 * 
	 * @param row	Row number in my local salon section	e.g. 1, 2, 3
	 * @return		my local salon section value by row	e.g. Short description A new tool to Shortcuts this year, Customized Forms enables clinics and spas...
	 * 														 Long description All the client needs to do is sit back, complete and sign the form on a tablet. Once completed, and the terms and conditions are accepted...
	 * 
	 */
		public String getMylocalSalonSectionData(int row)
		{
			return business.getMylocalSalonSectionData(row);
		}

}
