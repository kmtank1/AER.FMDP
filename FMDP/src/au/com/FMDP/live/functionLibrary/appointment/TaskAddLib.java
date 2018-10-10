package au.com.shortcuts.live.functionLibrary.appointment;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.appointment.TaskAddPO;
import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class TaskAddLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help ;
	TaskAddPO taskadd;
	
	
	public TaskAddLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        taskadd = new TaskAddPO(driver);
        help = new CommonPO(driver);
	}
	
	/**
	 * Add Task
	 * @param namevalue  task name
	 * @param statusvalue task status i.e. active, inactive
	 * @param descriptiontext description text
	 * @param defaultdurationvalue task default duration e.g. 30
	 */
	public void task_Add(String namevalue, String statusvalue, String descriptiontext, String defaultdurationvalue)
	{
		taskadd.enterTaskName(namevalue);
		taskadd.setStatus(statusvalue);
		taskadd.enterDescription(descriptiontext);
		taskadd.enterDuration(defaultdurationvalue);
		help.footer_ClickOnDone();
	}
		
	/**
	 * Change task status
	 * @param statusvalue status value i.e. active,inactive
	 */
	public void task_changeStatus(String statusvalue)
	{
		taskadd.setStatus(statusvalue);
		help.footer_ClickOnDone();
	}

	
}
