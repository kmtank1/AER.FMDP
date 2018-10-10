package au.com.shortcuts.live.pageObjects.product;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class BrandAddPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public BrandAddPO(WebDriver driver)
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
		private WebElement isactive;
		
	
		
	//Field validations
		@FindBy(css="div.app-errorContainer span")
		private List<WebElement> fieldValidatons;
		
		//All fields validation messages 
		public String getAllFieldValidationMessages()
		{
			String msg="";
			
			for(WebElement e:fieldValidatons)
			{
				msg = msg + e.getText().trim();
			}
			return msg;
		}

		
		public void enterBrandName(String namevalue)
		{
			name.click();
			name.clear();
			name.sendKeys(namevalue);
		}
		
		public void setBrandStatus(String statusvalue)
		{
			help.setToggleValue(status, isactive, statusvalue);
		}
			
}
