package au.com.shortcuts.live.pageObjects.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ServiceSearchPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	JavaHelpers java;
	
	
	public ServiceSearchPO(WebDriver driver)
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
	
	
		//Details section 
			
			@FindBy(css=".editableSection i.icon-pencil")
			private WebElement editIcon;
			
			
		//Details section 
			public String getDetailsSectionData(int row)
			{
				String cssSelector = "fieldset[data-link='#identity-section'] ul li:nth-of-type(" + row + ")";
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			
			public String getDefaultDuration()
			{
				String cssSelector = "#default_duration_minutes";
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			
			public String getBreakDuration()
			{
				String cssSelector = "#break_duration_minutes";
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			
			
			//Click on Edit icon
			public void clickOnEditIcon()
			{
				editIcon.click();
				help.waitTillPageLoaded();
			}
		
			
		//pricing section	
			public String getPricingSectionData(int row, int column)
			{
				String cssSelector = "#price-section ul:nth-of-type(" + row + ") li:nth-of-type(" + column + ")";
				WebElement e = driver.findElement(By.cssSelector(cssSelector));
				selenium.pageScrollInView(e);
				selenium.waitTillElementIsVisible(e);
				return e.getText().trim();
			}
			
				
}
