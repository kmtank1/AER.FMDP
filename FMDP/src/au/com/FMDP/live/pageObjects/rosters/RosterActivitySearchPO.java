package au.com.shortcuts.live.pageObjects.rosters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class RosterActivitySearchPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	JavaHelpers java;
	
	public RosterActivitySearchPO(WebDriver driver)
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
			
			@FindBy(id="display_color")
			private WebElement color;
			
			@FindBy(css=".editableSection i.icon-pencil")
			private WebElement editIcon;
			

			
		//Details section
				
			//Details section
			public String getDetailsSectionData(int row)
			{
				String cssSelector = "#roster_activity_name ul li:nth-of-type(" + row + ")";
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			//Get color style
			public String getColorStyleFromDetailsSection()
			{
				return color.getAttribute("style");
			}
			
			//Click on Edit icon
			public void clickOnEditIcon()
			{
				editIcon.click();
				help.waitTillPageLoaded();
			}
			
			
				
}
