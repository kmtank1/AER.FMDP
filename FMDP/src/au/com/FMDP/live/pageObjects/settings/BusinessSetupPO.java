package au.com.shortcuts.live.pageObjects.settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class BusinessSetupPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public BusinessSetupPO(WebDriver driver)
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
	
	//Details section 
	
		@FindBy(css="#details i.icon-pencil")
		private WebElement editIcon;
			

	//Edit view:
		
	//Details section :
		@FindBy(id="business_name")
		private WebElement name;
		
		@FindBy(id="business_registration_number")
		private WebElement abn;
		
		@FindBy(id="reference")
		private WebElement reference;
		
		@FindBy(id="site_code")
		private WebElement sitecode;
		
		@FindBy(id="calendar_start_day_of_week")
		private WebElement startofweek;
		
		
	//Address section :
		@FindBy(id="address_street_1")
		private WebElement street1;
	
		@FindBy(id="address_street_2")
		private WebElement street2;
		
		@FindBy(id="address_suburb")
		private WebElement suburb;
		
		@FindBy(id="address_state")
		private WebElement state;
	
		@FindBy(id="address_postal_code")
		private WebElement pcode;
		
		
	//Contact section :
		@FindBy(id="business_number")
		private WebElement phone;
	
		@FindBy(id="fax_number")
		private WebElement fax;
		
		@FindBy(id="email")
		private WebElement email;
		
		@FindBy(id="web_address")
		private WebElement website;
		
		
	//mylocalsalon section:
		@FindBy(id="short_description")
		private WebElement shortdescription;
	
		@FindBy(id="long_description")
		private WebElement longdescription;
		
		@FindBy(id="pricing_band")
		private WebElement pricingband;
		

	//Edit view
		
		//Details section :
			public void enterBusinessName(String namevalue)
			{
				name.clear();
				name.sendKeys(namevalue);
			}
			
			public void enterBusinessAbn(String abnvalue)
			{
				abn.clear();
				abn.sendKeys(abnvalue);
			}
			
			public void enterBusinessReference(String refvalue)
			{
				reference.clear();
				reference.sendKeys(refvalue);
			}
			
			public void enterBusinessSiteCode(String sitecodevalue)
			{
				sitecode.clear();
				sitecode.sendKeys(sitecodevalue);
			}
			
			public void selectBusinessStartOfWeek(String startofweekvalue)
			{
				selenium.selectDropdownValueByText(startofweek, startofweekvalue);
			}
			
	
		//Address section :
			public void enterBusinessAddressStreet1(String street1value)
			{
				street1.clear();
				street1.sendKeys(street1value);
			}
			
			public void enterBusinessAddressStreet2(String street2value)
			{
				street2.clear();
				street2.sendKeys(street2value);
			}
			
			public void enterBusinessAddressSuburb(String suburbvalue)
			{
				suburb.clear();
				suburb.sendKeys(suburbvalue);
			}
			
			public void enterBusinessAddressState(String statevalue)
			{
				state.clear();
				state.sendKeys(statevalue);
			}
			
			public void enterBusinessAddressPcode(String pcodevalue)
			{
				pcode.clear();
				pcode.sendKeys(pcodevalue);
			}
			
		//Contact section :
			public void enterBusinessPhone(String phonevalue)
			{
				phone.clear();
				phone.sendKeys(phonevalue);
			}
			
			public void enterBusinessFax(String faxvalue)
			{
				fax.clear();
				fax.sendKeys(faxvalue);
			}
			
			public void enterBusinessEmail(String emailvalue)
			{
				email.clear();
				email.sendKeys(emailvalue);
			}
			
			public void enterBusinessWebsite(String websitevalue)
			{
				website.clear();
				website.sendKeys(websitevalue);
			}
			
		//My local salon section :
			public void enterBusinessShortDescription(String shortdescriptionvalue)
			{
				shortdescription.clear();
				shortdescription.sendKeys(shortdescriptionvalue);
			}
			
			public void enterBusinessLongDescription(String longdescriptionvalue)
			{
				longdescription.clear();
				longdescription.sendKeys(longdescriptionvalue);
			}
			
			public void selectBusinessPriceBand(String pricingbandvalue)
			{
				selenium.selectDropdownValueByText(pricingband, 

pricingbandvalue);
			}
			
		//Opening hours section :
			
			/**
			 * Set the status of salon (Open or Closed) of weekday wise.
			 * Set the opening and closing hours, if salon status is "Open". 
			 * 
			 * @param row			Row number in the table	e.g. 1, 2, 3
			 * @param isopen		Salon status on specific day	e.g. Open, Closed  
			 * @param openingtime	Salon opening time	e.g. 9:00 AM
			 * @param closingtime	Salon closing time	e.g. 9:00 PM
			 */
			public void setBusinessOpeningHours(int row, String isopen, String openingtime, String closingtime)
			{
				//Switch status
				String cssSelector = "#openingHours tbody tr:nth-of-type(" + row + ") td:nth-of-type(2) div.switch input";
				WebElement e = driver.findElement(By.cssSelector(cssSelector));
				selenium.pageScrollInView(e);
				
				String selectedvalue="yes";
				String currentstatus = e.getAttribute("checked");			
				if(currentstatus == null)
				{
					selectedvalue = "no";
				}
				
				//Switch
				cssSelector = "#openingHours tbody tr:nth-of-type(" + row + ") td:nth-of-type(2) div.switch";
				e = driver.findElement(By.cssSelector(cssSelector));
				
				if(!isopen.equalsIgnoreCase(selectedvalue))
				{
					selenium.pageScrollInView(e);
					e.click();
				}
	
				if (isopen.equalsIgnoreCase("yes")) 
				{
					//Opening time
					cssSelector = "#openingHours tbody tr:nth-of-type(" + row + ") input.open_time + *";
					e = driver.findElement(By.cssSelector(cssSelector));
					selenium.pageScrollInView(e);
					e.clear();
					e.sendKeys(openingtime);
					
					//Closing time
					cssSelector = "#openingHours tbody tr:nth-of-type(" + row + ") input.close_time + *";
					e = driver.findElement(By.cssSelector(cssSelector));
					e.clear();
					e.sendKeys(closingtime);
				}
			}
			
			//Click on Edit icon
			public void clickOnEditIcon()
			{
				editIcon.click();
				help.waitTillPageLoaded();
			}

			
	//Read view
				
		//Details section
		public String getDetailsSectionData(int row)
		{
			String cssSelector = "#details ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//Opening hours section
		public String getOpeningHoursSectionData(int row)
		{
			String cssSelector = "#hours ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//Address section
		public String getAddressSectionData(int row)
		{
			String cssSelector = "#address ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//contact section
		public String getContactSectionData(int row)
		{
			String cssSelector = "#contact ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//mylocal salon section
		public String getMylocalSalonSectionData(int row)
		{
			String cssSelector = "#mls-section ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
					
}
