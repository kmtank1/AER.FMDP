package au.com.shortcuts.live.pageObjects.settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class TaxSearchPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	JavaHelpers java;
	
	public TaxSearchPO(WebDriver driver)
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
		
		//Details section :
			
			@FindBy(css="fieldset[data-link='#details-section']")
			private WebElement detailsSection;
			
			@FindBy(css="fieldset[data-link='#items-section']")
			private WebElement applicableToSection;	
			
			@FindBy(css="fieldset[data-link='#rates-section']")
			private WebElement currentRateSection;
		
			@FindBy(css="fieldset[data-link='#future-rates-section']")
			private WebElement futureRateSection;
			
			@FindBy(css=".editableSection i.icon-pencil")
			private WebElement editIcon;
			
			
		//Details section
			
			//Details section
			public String getDetailsSectionData(int row)
			{
				String cssSelector = "fieldset[data-link='#details-section'] ul li:nth-of-type(" + row + ")";
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			//Applicable To section :
			public String getApplicableToSectionData(int row)
			{
				String cssSelector = "#items-section ul li:nth-of-type(" + row + ")";
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			//Current rate section :
			public String getCurrentRateSectionData(int row)
			{
				String cssSelector = "#current_rates ul li:nth-of-type(" + row + ")";
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
				//Rate section :
				public String getCurrentRateData(int row, int column)
				{
					String cssSelector = "#current_rates div table tbody tr:nth-of-type(" + row + ") td:nth-of-type(" + column + ")";;
					WebElement e = driver.findElement(By.cssSelector(cssSelector));
					selenium.pageScrollInView(e);
					return e.getText().trim();
				}
			
			//Future rate section :
			public String getFutureRateSectionData(int row)
			{
				String cssSelector = "#future_rates ul li:nth-of-type(" + row + ")";
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}

				//Rate section :
				public String getFutureRateData(int row, int column)
				{
					String cssSelector = "#future_rates div table tbody tr:nth-of-type(" + row + ") td:nth-of-type(" + column + ")";;
					WebElement e = driver.findElement(By.cssSelector(cssSelector));
					selenium.pageScrollInView(e);
					return e.getText().trim();
				}
		
			//Click on Edit icon
			public void clickOnEditIcon()
			{
				editIcon.click();
				help.waitTillPageLoaded();
			}				
}
