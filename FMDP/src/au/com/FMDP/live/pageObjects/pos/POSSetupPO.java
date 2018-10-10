package au.com.shortcuts.live.pageObjects.pos;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class POSSetupPO
{

	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	POSGridPO poscommon;
	JavaHelpers java;
	
	public POSSetupPO(WebDriver driver)
	{
		this.driver = driver;
		selenium = new SeleniumHelpers(driver);
		help = new CommonPO(driver);
		poscommon = new POSGridPO(driver);
		java = new JavaHelpers();
		
		//This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}
	
	
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 
	
	//Search Customer
		@FindBy(id="pos-sale-searchBox")
		private WebElement searchcustomer_Input;
	
		@FindBy(css="#preSale-customer-list > div > ul li")
		private WebElement search_FirstCustomerItem;
		
	//Search Service Category
		@FindBy(id="pos-service-category-searchBox")
		private WebElement searchServiceCategory_Input;
		
		@FindBy(css="#items-service-category-list > div > ul li")
		private WebElement search_FirstCategoryItem;
		
	//Search Service
		@FindBy(id="pos-service-searchBox")
		private WebElement searchService_Input;
		
		@FindBy(css="#items-service-list > div > ul li")
		private WebElement search_FirstServiceItem;
		
	//Search Brand
		@FindBy(id="pos-manufacturer-searchBox")
		private WebElement searchBrand_Input;
		
		@FindBy(css="#items-manufacturer-list > div > ul li")
		private WebElement search_FirstBrandItem;
		
	//Search Line
		@FindBy(id="pos-line-searchBox")
		private WebElement searchLine_Input;
		
		@FindBy(css="#items-line-list > div > ul li")
		private WebElement search_FirstLineItem;
		
	//Search Product
		@FindBy(id="items-product-list")
		private WebElement searchProduct_Input;
		
		@FindBy(css="#items-product-list > div > ul li")
		private WebElement search_FirstProductItem;
		
	//Add Customer
		@FindBy(id="title")
		private WebElement title;
		
		@FindBy(id="first_name")
		private WebElement firstName;
		
		@FindBy(id="last_name")
		private WebElement lastName;
		
		@FindBy(id="preferred_name")
		private WebElement preferredFirstName;
		
		@FindBy(id="gender_code")
		private WebElement genderdrop;
		
		@FindBy(id="mobile_phone")
		private WebElement mobilePhone;
		
		@FindBy(id="home")
		private WebElement homePhone;
		
		@FindBy(id="email")
		private WebElement email;
		
		@FindBy(css="label[for='is_not_to_receive_marketing'] + *")
		private WebElement sendOffersAndPromotions;
		
		@FindBy(id="is_not_to_receive_marketing")
		private WebElement isSendOffersAndPromotions;
		
		@FindBy(css="label[for='is_not_to_receive_appointment_reminders'] + *")
		private WebElement sendAppointmentReminder;
		
		@FindBy(id="is_not_to_receive_appointment_reminders")
		private WebElement isSendAppointmentReminder;
		
		@FindBy(css="label[for='is_reminder_via_sms'] + *")
		private WebElement sendReminderViaSms;
		
		@FindBy(id="is_reminder_via_sms")
		private WebElement isSendReminderVisSms;
		
		@FindBy(css="label[for='is_reminder_via_email'] + *")
		private WebElement sendReminderViaEmail;
		
		@FindBy(id="is_reminder_via_email")
		private WebElement isSendReminderViaEmail;
		
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
		
		@FindBy(id="birth_day")
		private WebElement birthDay;
		
		@FindBy(id="birth_month")
		private WebElement birthMonth;
		
		@FindBy(id="birth_year")
		private WebElement birthYear;
		
		@FindBy(id="alert_message")
		private WebElement alertMessage;
		
		@FindBy(id="referrer")
		private WebElement referrerdrop;
		
		@FindBy(id="customer_reference")
		private WebElement reference;
		
		@FindBy(css="label[for='is_tax_exempt'] + *")
		private WebElement taxExempt;
		
		@FindBy(id="is_tax_exempt")
		private WebElement isTaxExempt;
		
	//Item type
		@FindBy(css="#items-item-list li[data-drillthrough='services']")
		public WebElement services;
		
		@FindBy(css="#items-item-list li[data-drillthrough='products']")
		public WebElement products;

		
	//Add Customer
		
		public void enterCustomerTitle(String titlevalue)
		{
			title.clear();
			title.sendKeys(titlevalue);
		}
		
		public void enterCustomerFirstName(String firstname)
		{
			firstName.clear();
			firstName.sendKeys(firstname);
		}
		
		public void enterCustomerLastName(String lastname)
		{
			lastName.clear();
			lastName.sendKeys(lastname);
		}
		
		public void enterCustomerPreferredFirstName(String preferredfirstname)
		{
			preferredFirstName.clear();
			preferredFirstName.sendKeys(preferredfirstname);
		}
		
		public void selectCustomerGender(String gendervalue)
		{
			selenium.selectDropdownValueByText(genderdrop, gendervalue);
		}
		
		public void enterCustomerContactMobile(String mobilephone)
		{
			mobilePhone.clear();
			mobilePhone.sendKeys(mobilephone);
		}
		
		public void enterCustomerContactHome(String homephone)
		{
			homePhone.clear();
			homePhone.sendKeys(homephone);
		}
		
		public void enterCustomerContactEmail(String emailvalue)
		{
			email.clear();
			email.sendKeys(emailvalue);
		}
		
		public void setCustomerSendOffersAndPromotions(String offerandpromotions)
		{
			help.setToggleValue(sendOffersAndPromotions, isSendOffersAndPromotions, offerandpromotions);
		}
		
		public void setCustomerSendAppointmentReminders(String appointmentreminder)
		{
			help.setToggleValue(sendAppointmentReminder, isSendAppointmentReminder, appointmentreminder);
		}
		
		public void setCustomerReminderViaSms(String reminderviasms)
		{
			help.setToggleValue(sendReminderViaSms, isSendReminderVisSms, reminderviasms);
		}
		
		public void setCustomerReminderViaEmail(String reminderviaemail)
		{
			help.setToggleValue(sendReminderViaEmail, isSendReminderViaEmail, reminderviaemail);
		}
		
		public void enterCustomerAddress1(String street1value)
		{
			street1.clear();
			street1.sendKeys(street1value);
		}
		
		public void enterCustomerAddress2(String street2value)
		{
			street2.clear();
			street2.sendKeys(street2value);
		}
		
		public void enterCustomerSuburb(String suburbvalue)
		{
			suburb.clear();
			suburb.sendKeys(suburbvalue);
		}
		
		public void enterCustomerState(String statevalue)
		{
			state.clear();
			state.sendKeys(statevalue);
		}
		
		public void enterCustomerPostalCode(String postalcodevalue)
		{
			pcode.clear();
			pcode.sendKeys(postalcodevalue);
		}
		
		public void selectCustomerBirthDay(String birthday)
		{
			selenium.selectDropdownValueByText(birthDay, birthday);
		}
		
		public void selectCustomerBirthMonth(String birthmonth)
		{
			selenium.selectDropdownValueByText(birthMonth, birthmonth);
		}
		
		public void selectCustomerBirthYear(String birthyear)
		{
			selenium.selectDropdownValueByText(birthYear, birthyear);
		}
		
		public void enterCustomerAlertMessage(String alertmessagevalue)
		{
			alertMessage.clear();
			alertMessage.sendKeys(alertmessagevalue);
		}
		
		public void selectCustomerReferral(String referral)
		{
			selenium.selectDropdownValueByText(referrerdrop, referral);
		}
		
		public void enterCustomerReference(String referencevalue)
		{
			reference.clear();
			reference.sendKeys(referencevalue);
		}

		public void setTaxExempt(String taxexempt)
		{
			help.setToggleValue(taxExempt, isTaxExempt, taxexempt);
		}
			
		
	//Entity type
		
		public void ClickOnService()
		{
			services.click();
		}
		
		public void ClickOnProduct()
		{
			products.click();
		}

		
	//Search customer
		
		public void search_SearchCustomerWithText(String customername)
		{
			searchcustomer_Input.clear();
			searchcustomer_Input.sendKeys(customername);
			help.waitTillPageLoaded();
		}
		
		public boolean search_IsFirstCustomerItemDisplayed()
		{
			try 
			{
	            return search_FirstCustomerItem.isDisplayed();
			} 
			catch (Exception e) 
			{
	            return false;
	        }
		}
		
		public void search_ClickOnFirstCustomerItem()
		{
			selenium.waitTillElementIsClickable(search_FirstCustomerItem).click();
			help.waitTillPageLoaded();
		}
			
		
	//Search Service Category
		
		public void search_SearchCategoryWithText(String categoryname)
		{
			searchServiceCategory_Input.clear();
			searchServiceCategory_Input.sendKeys(categoryname);
			help.waitTillPageLoaded();
		}
		
		public boolean search_IsFirstCategoryItemDisplayed()
		{
			try 
			{
	            return search_FirstCategoryItem.isDisplayed();
			} 
			catch (Exception e) 
			{
	            return false;
	        }
		}
		
		public void search_ClickOnFirstCategoryItem()
		{
			selenium.waitTillElementIsClickable(search_FirstCategoryItem).click();
			help.waitTillPageLoaded();
		}
		
		
	//Search Service
	
		public void search_SearchServiceWithText(String servicename)
		{
			searchService_Input.clear();
			searchService_Input.sendKeys(servicename);
			help.waitTillPageLoaded();
		}
		
		public boolean search_IsFirstServiceItemDisplayed()
		{
			try 
			{
	            return search_FirstServiceItem.isDisplayed();
			} 
			catch (Exception e) 
			{
	            return false;
	        }
		}
		
		public void search_ClickOnFirstServiceItem()
		{
			selenium.waitTillElementIsClickable(search_FirstServiceItem).click();
			help.waitTillPageLoaded();
		}

		
	//Search Brand
	
		public void search_SearchBrandWithText(String brandname)
		{
			searchBrand_Input.clear();
			searchBrand_Input.sendKeys(brandname);
			help.waitTillPageLoaded();
		}
		
		public boolean search_IsFirstBrandItemDisplayed()
		{
			try 
			{
	            return search_FirstBrandItem.isDisplayed();
			} 
			catch (Exception e) 
			{
	            return false;
	        }
		}
		
		public void search_ClickOnFirstBrandItem()
		{
			selenium.waitTillElementIsClickable(search_FirstBrandItem).click();
			help.waitTillPageLoaded();
		}
		
		
	//Search Line
	
		public void search_SearchLineWithText(String linename)
		{
			searchLine_Input.clear();
			searchLine_Input.sendKeys(linename);
			help.waitTillPageLoaded();
		}
		
		public boolean search_IsFirstLineItemDisplayed()
		{
			try 
			{
	            return search_FirstLineItem.isDisplayed();
			} 
			catch (Exception e) 
			{
	            return false;
	        }
		}
		
		public void search_ClickOnFirstLineItem()
		{
			selenium.waitTillElementIsClickable(search_FirstLineItem).click();
			help.waitTillPageLoaded();
		}
		
		
	//Search Product
	
		public void search_SearchProductWithText(String productname)
		{
			searchProduct_Input.clear();
			searchProduct_Input.sendKeys(productname);
			help.waitTillPageLoaded();
		}
		
		public boolean search_IsFirstProductItemDisplayed()
		{
			try 
			{
	            return search_FirstProductItem.isDisplayed();
			} 
			catch (Exception e) 
			{
	            return false;
	        }
		}
		
		public void search_ClickOnProductLineItem()
		{
			selenium.waitTillElementIsClickable(search_FirstProductItem).click();
			help.waitTillPageLoaded();
		}
}
