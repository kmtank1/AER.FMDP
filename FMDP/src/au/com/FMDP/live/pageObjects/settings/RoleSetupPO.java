package au.com.shortcuts.live.pageObjects.settings;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class RoleSetupPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	JavaHelpers java;
	
	public RoleSetupPO(WebDriver driver)
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
	
	//Details section : 
	
		@FindBy(id="display_name")
		private WebElement name;
		
		@FindBy(css="label[for='is_active'] + *")
		private WebElement status;
		
		@FindBy(id="is_active")
		private WebElement isactive_status;
		
	//Employees section : 
		
		@FindBy(id="searchEmployee")
		private WebElement searchemployee;

		@FindBy(css="li.employee-link label")
		private WebElement status_employee;
		
		@FindBy(name="user_is_active")
		private WebElement isactive_employee;
	
		
	//Permissions section :
		
		@FindBy(css="#securityPermissions > li")
	    private List<WebElement> sections;
		
		By permissions_available = By.cssSelector("div ul li");
		
		By permissions_switch = By.cssSelector("div.switch");
		
	
		public void enterRoleName(String namevalue)
		{
			name.clear();
			name.sendKeys(namevalue);
		}
		
		public void setStatus(String statusvalue)
		{
			help.setToggleValue(status, isactive_status, statusvalue);
		}
		
		/**
		 * Giving access to Employee for role
		 * @param employeevalue name of an employee
		 * @param employeestatus yes or no 
		 */
		public void selectEmployee(String employeevalue, String employeestatus)
		{
			searchemployee.clear();
			searchemployee.sendKeys(employeevalue);
			help.waitTillPageLoaded();
			help.setToggleValue(status_employee, isactive_employee, employeestatus);
		}
		
		/**
		 * Select permissions from given section
		 * @param sectionname permission section name e.g. Configuration
		 * @param permissionsdata list of permissions
		 * @throws InterruptedException
		 */
		public void selectPermissionSection(String sectionname, List<String> permissionsdata) throws InterruptedException
		{
			List<WebElement> permissions = null;
			
				for (WebElement e : sections)
				{
					selenium.pageScrollInView(e);
					if (e.getText().equalsIgnoreCase(sectionname))
					{
						e.click();
						permissions = e.findElements(permissions_available);
						break;
					}
				}
		
				for (int i = 0; i < permissionsdata.size(); i++)
				{
					for (WebElement e1 : permissions)
					{
						selenium.pageScrollInView(e1);
						if (e1.getText().contains(permissionsdata.get(i)))
						{
							e1.findElement(permissions_switch).click();
							java.sleep(1);
							break;
						}
					}
				}
		}
}
