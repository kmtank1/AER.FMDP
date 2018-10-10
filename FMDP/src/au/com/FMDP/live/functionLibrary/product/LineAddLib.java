package au.com.shortcuts.live.functionLibrary.product;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.product.LineAddPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;


public class LineAddLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help ;
	LineAddPO lineadd;
	JavaHelpers java;
	
	
	public LineAddLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        lineadd = new LineAddPO(driver);
        help = new CommonPO(driver);
        java = new JavaHelpers();
	}
	
	/**
	 * Setup line by providing information in all the fields.
	 * Update information from added line.
	 * 
	 * @param namevalue		Name of the line	e.g. AT_line_20171207_115734
	 * @param productusage	Product usage of the line	e.g. Retail, Professional
	 * @param statusvalue	Status of the line	e.g. Active, Inactive
	 * 
	 */
		public void line_Add(String namevalue, String productusage, String statusvalue)
		{
			lineadd.enterLineName(namevalue);
			lineadd.selectLineDefaultProductUsage(productusage);
			lineadd.setLineStatus(statusvalue);
			help.footer_ClickOnDone();
		}
		
	
	/**
	 * Set line status i.e. Active or Inactive.
	 * @param status	status i.e. Active or Inactive
	 * 
	 */
		public void brand_ChangeStatus(String status)
		{
			lineadd.setLineStatus(status);
			help.footer_ClickOnDone();
		}
	
}
