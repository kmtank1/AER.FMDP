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

public class AppointmentCreateDetailsTabPO
{

	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	JavaHelpers java;
	
	public AppointmentCreateDetailsTabPO(WebDriver driver)
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
	
	
		@FindBy(id="status")
		private WebElement statusdrop;
	
		@FindBy(name="display_name")
		private WebElement tagdrop;
	
	
		public void selectOptionsStatus(String status)
		{
			selenium.selectDropdownValueByText(statusdrop, status);
		}
		
		public void selectDetailsTag(String tagname)
		{
			selenium.selectDropdownValueByText(tagdrop, tagname);
		}
		


}
