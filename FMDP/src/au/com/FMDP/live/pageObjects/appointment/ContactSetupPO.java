package au.com.FMDP.live.pageObjects.appointment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.Constants;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ContactSetupPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public ContactSetupPO(WebDriver driver)
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
	
	//Read View:
	
	//Details section 
	
		@FindBy(css=".editableSection i.icon-pencil")
		private WebElement editIcon;
			
		
	//Edit mode:
		
	//Template section : 
	
		@FindBy(id="contact_message_text")
		private WebElement message;
		
		@FindBy(id="fields")
		private WebElement insertfield;
		
		
	//Reply phrases section :
		
		@FindBy(id="confirmation_reply_text")
		private WebElement confirm;

		@FindBy(id="cancellation_reply_text")
		private WebElement cancel;
		
	
		
	//Edit 	view
		public void contact_EnterTemplateMessageData(String remindermessagetext)
		{
			message.clear();
			message.sendKeys(remindermessagetext);
		}
		
		public void contact_SelectTemplateInsertField(String fieldvalue)
		{
			selenium.selectDropdownValueByText(insertfield, fieldvalue);	
		}
		
		public void contact_EnterReplyPhrasesConfirmTextData(String confirmtext)
		{
			confirm.clear();
			confirm.sendKeys(confirmtext);
		}
		
		public void contact_EnterReplyPhrasesCancelTextData(String canceltext)
		{
			cancel.clear();
			cancel.sendKeys(canceltext);
		}
		
		
	//Read view
				
		//Details section
		public String getDetailsSectionData(int row)
		{
			String cssSelector = "fieldset[data-link='#identity-section'] ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//Template section
		public String getTemplateSectionData(int row)
		{
			String cssSelector = "fieldset[data-link='#template-section'] ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		
		//Reply phrases section
		public String getReplyPhrasesSectionData(int row)
		{
			String cssSelector = "fieldset[data-link='#replies-section'] ul li:nth-of-type(" + row + ")";
			return selenium.waitTillElementIsVisible(By.cssSelector(cssSelector)).getText().trim();
		}
		
		
		//Click on Edit icon
		public void clickOnEditIcon()
		{
			editIcon.click();
		}
		
	
			
}
