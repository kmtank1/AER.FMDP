package au.com.FMDP.live.pageObjects.appointment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.Constants;
import au.com.FMDP.utilities.SeleniumHelpers;

public class TaskAddPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public TaskAddPO(WebDriver driver)
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
	
	//Name/Details section : 
		@FindBy(id="display_name")
		private WebElement name;
	
		@FindBy(css="label[for='is_active'] + *")
		private WebElement status;
		
		@FindBy(id="is_active")
		private WebElement isactive;
			
		@FindBy(id="description")
		private WebElement description;
	
		@FindBy(id="default_duration_minutes")
		private WebElement duration;
		
		
		public void enterTaskName(String namevalue)
		{
			name.clear();
			name.sendKeys(namevalue);
		}
		
		public void setStatus(String statusvalue)
		{
			help.setToggleValue(status, isactive, statusvalue);	
		}
	
		public void enterDescription(String descriptiontext)
		{
			description.clear();
			description.sendKeys(descriptiontext);
		}
		
		public void enterDuration(String defaultdurationvalue)
		{
			duration.clear();
			duration.sendKeys(defaultdurationvalue);
		}
		
		
}
