package au.com.shortcuts.live.functionLibrary.rosters;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.rosters.RosterActivitySetupPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class RosterActivitySetupLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	RosterActivitySetupPO rosteractivityadd;
	CommonPO help ;
	
	
	public RosterActivitySetupLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        rosteractivityadd = new RosterActivitySetupPO(driver);
        help = new CommonPO(driver);
	}
	
	/**
	 * Add/edit roster activity
	 * @param name name 
	 * @param status status i.e. active,inactive
	 * @param colorposition color rgb details e.g. background-color: rgb(227, 20, 20);
	 * @param notes notes
	 * @param bookable bookable flag i.e. active,inactive
	 * @param payable payable flag i.e. active,inactive
	 * @param walkins walkins flag .e. active,inactive
	 */
	public void rosterActivity_Add(	String name,String status,String colorposition,String notes, String bookable,String payable,String walkins )
	{
		rosteractivityadd.enterName(name);
		rosteractivityadd.setStatus(status);
		rosteractivityadd.setColor(colorposition);
		rosteractivityadd.enterNotes(notes);
		rosteractivityadd.selectBookable(bookable);
		rosteractivityadd.selectPayable(payable);
		rosteractivityadd.selectWalkiins(walkins);
		help.footer_ClickOnDone();
	}
	
	/**
	 * Change status
	 * @param status status value i.e. active,inactive
	 */
	public void rosterActivity_ChangeStatus(String status)
	{
		rosteractivityadd.setStatus(status);
	}
	
	
}
