package au.com.shortcuts.live.functionLibrary.settings;

import java.util.List;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.settings.RoleSetupPO;
import au.com.FMDP.utilities.SeleniumHelpers;

public class RoleSetupLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	RoleSetupPO roleadd;
	CommonPO help ;
	ItemListPO itemlist;
		
	public RoleSetupLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        roleadd = new RoleSetupPO(driver);
        help = new CommonPO(driver);
	}
	
	/**
	 * Add or Edit role
	 * @param name 					name of role
	 * @param status 				status of role i.e. active or inactive
	 * @param employeename 			name of an employee
	 * @param employeestatus 		employee status  i.e. yes or no
	 * @param appointmentbook 		appointment book section name
	 * @param appointmentdata 		appointment book permissions
	 * @param configuration 		configuration section name
	 * @param configurationdata 	configuration permissions 
	 * @param general				general section name
	 * @param generaldata			general permissions 
	 * @param customer				customer section name
	 * @param customerdata			customer permissions 
	 * @param employee				employee section name
	 * @param employeedata			employee permissions 
	 * @param pointofsale			point of sale section name
	 * @param pointofsaledata		point of sale permissions 
	 * @param reports				reports section name
	 * @param reportsdata			reports permissions 
	 * @param tools					tools section name
	 * @param toolsdata				tools permissions 
	 * @param walkinmanager			walk in manager section name
	 * @param walkinmanagerdata		walk in manager permissions 
	 * @param services				services section name
	 * @param servicesdata			services permissions 
	 * @throws InterruptedException
	 */
	public void roledetails_Add(String name,String status, String employeename, String employeestatus,
								String appointmentbook, List<String> appointmentdata, String configuration, List<String> configurationdata,
								String general, List<String> generaldata, String customer, List<String> customerdata,
								String employee, List<String> employeedata, String pointofsale, List<String> pointofsaledata,
								String reports, List<String> reportsdata, String tools, List<String> toolsdata,
								String walkinmanager, List<String> walkinmanagerdata, String services, List<String> servicesdata) throws InterruptedException
								{
									roleadd.enterRoleName(name);
									roleadd.setStatus(status);
									roleadd.selectEmployee(employeename, employeestatus);
									roleadd.selectPermissionSection(appointmentbook, appointmentdata);
									roleadd.selectPermissionSection(configuration, configurationdata);
									roleadd.selectPermissionSection(general, generaldata);
									roleadd.selectPermissionSection(customer, customerdata);
									roleadd.selectPermissionSection(employee, employeedata);
									roleadd.selectPermissionSection(pointofsale, pointofsaledata);
									roleadd.selectPermissionSection(reports, reportsdata);
									roleadd.selectPermissionSection(tools, toolsdata);
									roleadd.selectPermissionSection(walkinmanager, walkinmanagerdata);
									roleadd.selectPermissionSection(services, servicesdata);
									help.footer_ClickOnDone();							
								}	
	
	/**
	 * Change role 	status
	 * @param status 	role status i.e. active or inactive
	 */
	public void role_ChangeStatus(String status)
	{
		roleadd.setStatus(status);
	}
}
