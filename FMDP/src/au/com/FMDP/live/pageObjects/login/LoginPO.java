package au.com.FMDP.live.pageObjects.login;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.utilities.Constants;
import au.com.FMDP.utilities.SeleniumHelpers;

public class LoginPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	
	public LoginPO(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);

        //This initElements method will create all WebElements
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, Constants.PAGEFACTORY_WAIT_DURATION), this);
	}
	
	
	/*
	 * All WebElements are identified by @FindBy annotation
	 * @FindBy can accept tagName, partialLinkText, name, linkText, id, css, className, xpath as attributes.
	 */ 	
	
		@FindBy(id="txtUserName")
	    private WebElement userName;
		
		@FindBy(id="txtPwd")
		private WebElement passWord;
		
		@FindBy(id="btnLogin")
		private WebElement login;
		
		@FindBy(id="errorbox")
		private WebElement errorMessage;
		
		@FindBy(id="errorbox")
		private List<WebElement> fieldValidatons;
		
		
		public void enterUsername(String username)
		{
			userName.clear();
			userName.sendKeys(username);
		}
		
		public void enterPassword(String password)
		{
			passWord.clear();
			passWord.sendKeys(password);
		}
		
		public void clickOnLogin()
		{
			login.click();
		}
		
		
		public String getErrorMessage()
		{
			return errorMessage.getText().trim();
		}
	
		public String getAllFieldValidationMessages()
		{
			String msg="";
			
			for(WebElement e:fieldValidatons)
			{
				msg = msg + e.getText().trim();
			}
			return msg;
		}
		
}
