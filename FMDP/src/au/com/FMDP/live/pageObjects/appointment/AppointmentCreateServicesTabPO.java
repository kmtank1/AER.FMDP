package au.com.FMDP.live.pageObjects.appointment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.Constants;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class AppointmentCreateServicesTabPO
{

	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	JavaHelpers java;
	
	public AppointmentCreateServicesTabPO(WebDriver driver)
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
	
	
	//Search section
	
		@FindBy(id="categories-searchBox")
		private WebElement searchcategory_Input;
	
		@FindBy(id="services-searchBox")
		private WebElement searchservice_Input;
	
		@FindBy(css=".leftColumn > div > ul li > span")
		private WebElement search_FirstCategoryItem;
		
		@FindBy(css=".rightColumn> div > ul li > span")
		private WebElement search_FirstServiceItem;
		
		@FindBy(className="isEmployeeRequested")
		private WebElement request;
	

	//Search Category
		public void search_SearchCategoryWithText(String categoryname)
		{
			searchcategory_Input.clear();
			searchcategory_Input.sendKeys(categoryname);
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
			searchservice_Input.clear();
			searchservice_Input.sendKeys(servicename);
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

			
	//Search selected Service
		public String getSelectedServiceData(int row, int column)
		{
			String cssSelector = "#sidebar-workflow-content table tbody tr:nth-of-type(" + row + ") td:nth-of-type(" + column + ")";;
			WebElement e = driver.findElement(By.cssSelector(cssSelector));
			selenium.pageScrollInView(e);
			return e.getText().trim();
		}
		
}

