package au.com.shortcuts.live.functionLibrary.employee;

import org.openqa.selenium.WebDriver;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.employee.EmployeeAddPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class EmployeeAddLib 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help ;
	EmployeeAddPO employeeadd;
	ItemListPO itemlist;
	JavaHelpers java;
	
	
	public EmployeeAddLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        employeeadd = new EmployeeAddPO(driver);
        help = new CommonPO(driver);
        itemlist = new ItemListPO(driver);
        java = new JavaHelpers();
	}
	
	
	/**
	 * Add/edit employee by providing information in all the fields.
	 *
	 * @param aliasvalue			Alias name of employee e.g. AT_20171117_124056
	 * @param firstname				First name of employee	e.g. John
	 * @param lastname				Last name of employee	e.g. Carry
	 * @param gender				Gender information of employee	e.g. Male, Female
	 * @param startdate				Start date of employee	e.g. 02/11/2017
	 * @param biographydetails		Biography of employee	e.g. A biography, or simply bio, is a detailed description of a person's life. 
	 * @param statusvalue			Status of employee	e.g. Active, Inactive
	 * @param mobilevalue			Mobile number of employee	e.g. 9632587411
	 * @param homevalue				Home number of employee	e.g. 1236547981
	 * @param emailvalue			Email address of employee	e.g. ATemail_20171218_182732@mailinator.com
	 * @param street1value			Home Address Street1	e.g. Cybage Software
	 * @param street2value			Home Address Street2	e.g. Infocity
	 * @param suburbvalue			Home Address Suburb		e.g. Gandhinagar
	 * @param statevalue			Home Address State		e.g. Gujarat
	 * @param pcodevalue			Home Address PCode		e.g. 31500
	 * @param intervalvalue			Interval value for appointment book	e.g. 30, 45, 60 
	 * @param sendconfirmation		Send confirmation to client	e.g. Yes, No
	 * @param showinappointmentbook	Show in appointment book	e.g. Yes, No
	 * @param walkin				Available for Walk-in	e.g. Yes, No 
	 */
		public void employee_Add(String aliasvalue, String firstname, String lastname, String gender, String startdate, String biographydetails, String statusvalue, String mobilevalue, String homevalue, String emailvalue, String street1value, String street2value, String suburbvalue, String statevalue, String pcodevalue, String intervalvalue, String sendconfirmation, String showinappointmentbook, String walkin)
		{
			employeeadd.enterEmployeeAlias(aliasvalue);
			employeeadd.enterEmployeeFirstName(firstname);
			employeeadd.enterEmployeeLastName(lastname);
			employeeadd.selectEmployeeGender(gender);
			employeeadd.enterEmployeeStartDate(startdate);
			employeeadd.clickOnEmployeeMobile();
			employeeadd.enterEmployeeBiography(biographydetails);
			employeeadd.setEmployeeStatus(statusvalue);
			employeeadd.setEmployeeWalkin(walkin);
			employeeadd.enterEmployeeMobile(mobilevalue);
			employeeadd.enterEmployeeHomeNumber(homevalue);
			employeeadd.enterEmployeeEmail(emailvalue);
			employeeadd.enterEmployeeHomeAddressStreet1(street1value);
			employeeadd.enterEmployeeHomeAddressStreet2(street2value);
			employeeadd.enterEmployeeHomeAddressSuburb(suburbvalue);
			employeeadd.enterEmployeeHomeAddressState(statevalue);
			employeeadd.enterEmployeeHomeAddressPcode(pcodevalue);
			employeeadd.selectEmployeeAppointmentInterval(intervalvalue);
			employeeadd.setEmployeeSendConfirmationToClients(sendconfirmation);
			employeeadd.setEmployeeShowInAppointmentBook(showinappointmentbook);
			help.footer_ClickOnDone();
		}
	
		
	/**
	 * Setup new employee by providing mandatory informations. 
	 * 
	 * @param aliasvalue	Alias name of employee e.g. AT_20171117_124056
	 * @param firstname		First name of employee	e.g. John
	 * @param lastname		Last name of employee	e.g. Carry
	 * @param gender		Gender information of employee	e.g. Male, Female
	 */
		public void employee_AddMandatoryFields(String aliasvalue, String firstname, String lastname, String gender)
		{
			employeeadd.enterEmployeeAlias(aliasvalue);
			employeeadd.enterEmployeeFirstName(firstname);
			employeeadd.enterEmployeeLastName(lastname);
			employeeadd.selectEmployeeGender(gender);
			help.footer_ClickOnDone();
		}
	
		
	/**
	 * Add multiple employee to validate the maximum allowed limit to create employee.
	 * 
	 * @param nth	 Number of employee limit	e.g. 8, 10 
	 */
		public void employee_AddNthEmployee(int nth)
		{
			int i = itemlist.list_GetTotalItems();
			while ( i <= nth)
			{
				String alias= "AT" +  java.timeStamp();
				help.footer_ClickOnNewButton();
				employee_AddMandatoryFields(alias,"First","Last","Male");
				if (i!=nth)
				{
					help.footer_ClickOnBack();
				}
				i++;
			}
		}
		
	
	/**
	 * Change employee status
	 * @param status Active or Inactive
	 * 
	 */
		public void employee_ChangeStatus(String status)
		{
			employeeadd.setEmployeeStatus(status);
			help.footer_ClickOnDone();
		}
	
    
    /**
	 * Capture all custom field from Employee page and
	 * compare with actual added custom field 
	 *  
	 * @param customfieldname	Name of added custom field e.g. AT_Employee_CF
	 * @return					true or false
	 */
		public boolean IsCustomFieldPresent(String customfieldname)
		{	
			return employeeadd.IsCustomFieldPresent(customfieldname);
		}
	
	
}
