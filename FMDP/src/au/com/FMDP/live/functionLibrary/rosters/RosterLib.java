package au.com.shortcuts.live.functionLibrary.rosters;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.rosters.RosterPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class RosterLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	RosterPO roster;
	CommonPO help ;
	
	public RosterLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        roster = new RosterPO(driver);
        help = new CommonPO(driver);
	}
	
	
	/***
	 * Add new roster for today with default data
	 */
	public void addRosterForToday()
	{
		roster.clickOnTodaysRosterLine();
		roster.waitTillRosterSidebarLoaded();
		roster.clickOnDoneButton();
		help.waitTillPageLoaded();
	}

	/**
	 * Add new roster for today with new data
	 * @param starttime start time 
	 * @param endtime end time
	 * @param activityname activity name
	 * @param duration duration
	 */
	public void addRosterForToday(String starttime, String endtime,String activityname, String breakduration)
	{
		roster.clickOnTodaysRosterLine();
		roster.waitTillRosterSidebarLoaded();
		roster.enterStartTime(starttime);
		roster.enterEndTime(endtime);
		roster.selectActivity(activityname);
		roster.selectBreakDuration(breakduration);
		roster.clickOnDoneButton();
		help.waitTillPageLoaded();
	}
	
	/**
	 * Edit roster for today with new data
	 * @param starttime start time 
	 * @param endtime end time
	 * @param activityname activity name
	 * @param duration duration
	 */
	public void editRosterForToday(String starttime, String endtime,String activityname, String breakduration)
	{
		roster.enterStartTime(starttime);
		roster.enterEndTime(endtime);
		roster.selectActivity(activityname);
		roster.selectBreakDuration(breakduration);
		roster.clickOnDoneButton();
		help.waitTillPageLoaded();
	}
	
	/**
	 * Get total Roster items count
	 * @return
	 */
	public int getTotalRosterItemsCount()
	{
		return roster.getTotalRosterItemsCount();
	}
	
	/** 
	 * Delete all rosters
	 */
	public void DeleteAllRosters()
	{
		roster.deleteAllRosters();
	}
	
	/**
	 * Click on Roster item
	 */
	public void clickOnRosterItem()
	{
		roster.clickOnRosterItem();
		roster.waitTillRosterSidebarLoaded();
	}
	
	/**
	 * Get roster cell text
	 * @return roster cell text
	 */
	public String GetRosterCellText()
	{
		return roster.getRosterCellText();
	}
	
	
	/**
	 * On Roster sidebar, getting selected value for Activity field
	 * @return activity text
	 */
	public String getselectedActivity()
	{
		return roster.getselectedActivity();
	}
	
	/**
	 * On Roster sidebar, getting selected value for Break duration field
	 * @return break duration text
	 */
	public String getselectedBreakDuration()
	{
		return roster.getselectedBreakDuration();
	}
	
	/**
	 * On Roster sidebar, Click on delete button
	 */
	public void clickOnDeletebutton()
	{
		roster.clickOnDeleteButton();
		help.validationBottomRightPopUp_ClickOnYes();
	}

}
