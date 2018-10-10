package au.com.shortcuts.live.pageObjects.settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class RoleSearchPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	JavaHelpers java;
	ItemListPO itemlist;
	
	public RoleSearchPO(WebDriver driver)
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
		
			@FindBy(css=".editableSection i.icon-pencil")
			private WebElement editIcon;
			
			@FindBy(css="a[data-link='#identity-section'] span")
			private WebElement detailsText;
			
			@FindBy(css="input#display_name")
			private WebElement rolenameinput;
					
			
		//Details section :
			
			//Details section
			public String getDetailsSectionData(int row)
			{
				String cssSelector = "fieldset[class='ctrl-details view']:nth-child(1) li:nth-of-type(" + row + ")";
				selenium.pageScrollInView(driver.findElement(By.cssSelector(cssSelector)));
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
		
		//Employees section :
			
			//Employees section
			public String getEmployeesSectionData(int row)
			{
				String cssSelector = "#employees ul li:nth-of-type(" + row + ") span";
				selenium.pageScrollInView(driver.findElement(By.cssSelector(cssSelector)));
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
	
		//Permissions section :
			
			//Permissions > Appointment book section
			public String getAppointmentBookSectionData(int row)
			{
				String cssSelector = "#appointment_book li:nth-of-type(" + row + ") span";
				selenium.pageScrollInView(driver.findElement(By.cssSelector(cssSelector)));
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			//Permissions > Configuration section
			public String getConfigurationSectionData(int row)
			{
				String cssSelector = "#configuration li:nth-of-type(" + row + ") span";
				selenium.pageScrollInView(driver.findElement(By.cssSelector(cssSelector)));
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			//Permissions > General section
			public String getGeneralSectionData(int row)
			{
				String cssSelector = "#general li:nth-of-type(" + row + ") span";
				selenium.pageScrollInView(driver.findElement(By.cssSelector(cssSelector)));
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			//Permissions > Customer section
			public String getCustomerSectionData(int row)
			{
				String cssSelector = "#customer li:nth-of-type(" + row + ") span";
				selenium.pageScrollInView(driver.findElement(By.cssSelector(cssSelector)));
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			//Permissions > Employee section
			public String getEmployeeSectionData(int row)
			{
				String cssSelector = "#employee li:nth-of-type(" + row + ") span";
				selenium.pageScrollInView(driver.findElement(By.cssSelector(cssSelector)));
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			//Permissions > Point of sale section
			public String getPointOfSaleSectionData(int row)
			{
				String cssSelector = "#point_of_sale li:nth-of-type(" + row + ") span";
				selenium.pageScrollInView(driver.findElement(By.cssSelector(cssSelector)));
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			//Permissions > Reports section
			public String getReportsSectionData(int row)
			{
				String cssSelector = "#reports li:nth-of-type(" + row + ") span";
				selenium.pageScrollInView(driver.findElement(By.cssSelector(cssSelector)));
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			//Permissions > Tools section
			public String getToolsSectionData(int row)
			{
				String cssSelector = "#tools li:nth-of-type(" + row + ") span";
				selenium.pageScrollInView(driver.findElement(By.cssSelector(cssSelector)));
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			//Permissions > Walk-in Manager section
			public String getWalkinManagerSectionData(int row)
			{
				String cssSelector = "#walkin li:nth-of-type(" + row + ") span";
				selenium.pageScrollInView(driver.findElement(By.cssSelector(cssSelector)));
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
			
			//Permissions > Services section
			public String getServicesSectionData(int row)
			{
				String cssSelector = "#services li:nth-of-type(" + row + ") span";
				selenium.pageScrollInView(driver.findElement(By.cssSelector(cssSelector)));
				return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
			}
		

		//Click on Edit icon
			public void clickOnEditIcon()
			{
				editIcon.click();
				help.waitTillPageLoaded();
			}
			
		//Click on Details Text
			public void clickOnDetailsText()
			{
				detailsText.click();
				help.waitTillPageLoaded();
				selenium.waitTillElementIsClickable(rolenameinput);
			}				
}
