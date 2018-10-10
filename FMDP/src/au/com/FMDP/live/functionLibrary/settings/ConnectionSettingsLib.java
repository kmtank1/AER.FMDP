package au.com.shortcuts.live.functionLibrary.settings;
import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.settings.ConnectionSettingsPO;


public class ConnectionSettingsLib 
{
	WebDriver driver;
	CommonPO help ;
	ConnectionSettingsPO connection;
	
	public ConnectionSettingsLib(WebDriver driver)
	{	 
        this.driver = driver;
        connection = new ConnectionSettingsPO(driver);
        help = new CommonPO(driver);
	}
	
	/**
	 * Update the connection setting of marketing section.
	 * @param id		Parter id to establish connection	e.g. 48559bf6ca184ae1b73e4606d1552570
	 * @param username	Username for authentication	e.g. sherellietest
	 * @param password	Password for authentication	e.g. sure****
	 * 
	 */
	public void marketing_Edit(String id, String username, String password)
	{
		connection.selectMarketing();
		clickOnEditIcon();
		connection.enterMarketingParterId(id);
		connection.enterMarketingUsername(username);
		connection.enterMarketingPassword(password);
		help.footer_ClickOnDone();
	}
	
	
	/**
	 * Update the connection setting of SMS section.
	 * @param username	Username for authentication	e.g. sherellietest22
	 * @param password	Password for authentication	e.g. sure****
	 * 
	 */
	public void sms_Edit(String username, String password)
	{
		connection.selectSMS();
		clickOnEditIcon();
		connection.enterSmsUsername(username);
		connection.enterSmsPassword(password);
		help.footer_ClickOnDone();
	}
	
	
	/**
	 * Click on Edit button
	 */
	public void clickOnEditIcon()
	{
		connection.clickOnEditIcon();
	}
	
	
	//Read Data
	/**
	 * Retrieve the Connection details section data Row by Row for mareking and sms section.
	 * Collect field "Label" and respective "Value". 
	 * 
	 * @param row	Row number in details section	e.g. 1, 2, 3
	 * @return		Connection details section value by row	e.g. Username sherellietest, Partner id 48559bf6ca184ae1b73e4606d1552570
	 * 
	 */
	public String getConnectionDetailsSectionData(int row)
	{
		return connection.getConnectionDetailsSectionData(row);
	}

}
