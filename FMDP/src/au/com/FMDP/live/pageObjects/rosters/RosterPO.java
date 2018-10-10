package au.com.shortcuts.live.pageObjects.rosters;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class RosterPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	
	public RosterPO(WebDriver driver)
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
	
		//Roster grid
		private By allrosteritems = By.cssSelector("g[id^='roster_']");
		
		@FindBy(css="g[id^='roster_']")
		private WebElement rosteritem;
		
		@FindBy(className="todayOverlay")
		private WebElement todayrosterline;
		
		@FindBy(className="roster-cell")
		private WebElement rostercell;
		
		
		//Create/Edit Roster
		
		private By rostersidebar = By.id("roster-sidebar-content");
	
		@FindBy(css="#shift_start_time + *")
		private WebElement starttime;
		
		@FindBy(css="#shift_end_time + *")
		private WebElement endtime;
		
		@FindBy(id="activity")
		private WebElement activity;
		
		@FindBy(id="break_duration_minutes")
		private WebElement breakduration;
	
		@FindBy(css="i[class='icon-white icon-remove']")
		private WebElement deleteicon;
		
		@FindBy(id="done")
		private WebElement donebtn;
		
		
		//Roster grid
			
		public int getTotalRosterItemsCount()
		{
			try {
				return driver.findElements(allrosteritems).size();
			} 
			catch (Exception e) {
				return 0;
			}
		}
		
		public void clickOnTodaysRosterLine()
		{
			todayrosterline.click();
		}
		
		public void clickOnRosterItem()
		{
			rosteritem.click();
			help.waitTillPageLoaded();
			rosteritem.click();
		}
		
		public void deleteAllRosters()
		{
			if(getTotalRosterItemsCount() > 0)
			{
				List<WebElement> rosteritems = driver.findElements(allrosteritems);
				for(WebElement e:rosteritems)
				{
					selenium.pageScrollInView(e);
					e.click();
					help.waitTillPageLoaded();
					e.click();
					help.waitTillPageLoaded();
					clickOnDeleteButton();
					help.validationBottomRightPopUp_ClickOnYes();
				}
			}
		}
		
		public String getRosterCellText()
		{
			return rostercell.getText().trim();
		}
		
		
		//Create/Edit roster
		
		public void waitTillRosterSidebarLoaded()
		{
			selenium.waitTillElementIsVisible(rostersidebar);
		}
		
		public void enterStartTime(String time)
		{
			selenium.waitTillElementIsClickable(starttime);
			help.waitTillPageLoaded();
			starttime.clear();
			starttime.sendKeys(time);
		}
		
		public void enterEndTime(String time)
		{
			selenium.waitTillElementIsClickable(endtime);
			endtime.click();
			endtime.clear();
			endtime.sendKeys(time);
		}
		
		public void selectActivity(String activityname)
		{
			selenium.selectDropdownValueByText(activity,activityname);
		}
		
		public String getselectedActivity()
		{
			help.waitTillPageLoaded();
			selenium.waitTillElementIsClickable(activity);
			return selenium.getSelectedDropdownValue(activity);
		}
		
		public void selectBreakDuration(String duration)
		{
			selenium.selectDropdownValueByText(breakduration,duration);
		}
		
		public String getselectedBreakDuration()
		{
			return selenium.getSelectedDropdownValue(breakduration);
		}
		
		public void clickOnDoneButton()
		{
			selenium.waitTillElementIsVisible(donebtn);
			donebtn.click();
		}
		
		public void clickOnDeleteButton()
		{
			deleteicon.click();
		}
		
		
}
