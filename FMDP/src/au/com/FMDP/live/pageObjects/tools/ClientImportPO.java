package au.com.shortcuts.live.pageObjects.tools;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ClientImportPO
{

	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public ClientImportPO(WebDriver driver)
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
		@FindBy(id="downloadTemplate")
		private WebElement downloadTemplate;
	
		@FindBy(id="csv")
		private WebElement chooseFile; 
		
		@FindBy(id="done")
		private WebElement continueButton; 
		
		@FindBy(css=".parseCompleteMessage")
		private WebElement importConfirmation;
		
		@FindBy(css=".importProgressIndicator")
		private WebElement importSuccess;
		
		@FindBy(css=".ctrl-tabular.hScrollable.config")
		private WebElement tableHeader;


	//Details section :
	public void downloadTemplate()
	{	
		downloadTemplate.click();
	}
	
	public void uploadCSVTemplate(String filepath, String confirmationmessage, String importsuccessmessage)
	{
		chooseFile.sendKeys(filepath);
		selenium.waitTillElementIsVisible(continueButton);
		continueButton.click();
		help.waitTillPageLoaded();
		importConfirmation.getText().contains(confirmationmessage);
		continueButton.click();
		help.waitTillPageLoaded();
		importSuccess.getText().contains(importsuccessmessage);
	}
		
}
