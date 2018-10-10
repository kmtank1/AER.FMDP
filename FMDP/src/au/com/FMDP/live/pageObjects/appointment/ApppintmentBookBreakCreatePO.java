package au.com.FMDP.live.pageObjects.appointment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.Constants;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ApppintmentBookBreakCreatePO
{

	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	JavaHelpers java;
	
	public ApppintmentBookBreakCreatePO(WebDriver driver)
	{
		this.driver = driver;
		selenium = new SeleniumHelpers(driver);
		help = new CommonPO(driver);
		java = new JavaHelpers();
		
		//This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}
	
	
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 
	
	
		@FindBy(id="breaks-searchBox")
		private WebElement searchbreak_Input;
	
		@FindBy(css="#sidebar-workflow-content > div > ul li")
		private WebElement search_FirstItem;
		
		
	//Search break
		public void search_SearchBreakWithText(String breakvalue)
		{
			searchbreak_Input.clear();
			searchbreak_Input.sendKeys(breakvalue);
			help.waitTillPageLoaded();
		}
		
		public boolean search_IsFirstItemDisplayed()
		{
			try 
			{
	            return search_FirstItem.isDisplayed();
			} 
			catch (Exception e) 
			{
	            return false;
	        }
		}
		
		public void search_ClickOnFirstItem()
		{
			selenium.waitTillElementIsClickable(search_FirstItem).click();
			help.waitTillPageLoaded();
		}


}
