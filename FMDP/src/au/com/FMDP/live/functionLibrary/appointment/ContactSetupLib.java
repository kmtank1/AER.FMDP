package au.com.shortcuts.live.functionLibrary.appointment;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.appointment.ContactSetupPO;
import au.com.FMDP.live.pageObjects.common.CommonPO;

public class ContactSetupLib 
{
	WebDriver driver;
	CommonPO help ;
	ContactSetupPO contactsetup;
	
	public ContactSetupLib(WebDriver driver)
	{	 
        this.driver = driver;
        contactsetup = new ContactSetupPO(driver);
        help = new CommonPO(driver);
	}
	
	//Edit 
	
	/**
	 * Click on Edit button
	 */
	public void clickOnEditIcon()
	{
		contactsetup.clickOnEditIcon();
		help.waitTillPageLoaded();
	}
		
	/**
	 * Edit Contact setup page data
	 * @param remindermessagetext reminder message text
	 * @param fieldvalue insert field name
	 * @param confirmtext confirm text
	 * @param canceltext cancel text
	 * @throws InterruptedException
	 */
	public void contactsetup_Edit(String remindermessagetext, String fieldvalue, String confirmtext, String canceltext) throws InterruptedException
	{
		contactsetup.contact_EnterTemplateMessageData(remindermessagetext);
		contactsetup.contact_SelectTemplateInsertField(fieldvalue);
		contactsetup.contact_EnterReplyPhrasesConfirmTextData(confirmtext);
		contactsetup.contact_EnterReplyPhrasesCancelTextData(canceltext);
		help.footer_ClickOnDone();
	}
	

	//Read

	/**
	 * Read Details section data row wise including labels
	 * @param row
	 * @return data for given row
	 */
	public String getDetailsSectionData(int row)
	{
		return contactsetup.getDetailsSectionData(row);
	}
	
	/**
	 * Read Template section data row wise including labels
	 * @param row
	 * @return data for given row
	 */
	public String getTemplateSectionData(int row)
	{
		return contactsetup.getTemplateSectionData(row);
	}
	
	/**
	 * Read Reply Phrases section data row wise including labels
	 * @param row
	 * @return data for given row
	 */
	public String getReplyPhrasesSectionData(int row)
	{
		return contactsetup.getReplyPhrasesSectionData(row);
	}
	

	
}
