package au.com.shortcuts.live.pageObjects.common;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ItemListPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	JavaHelpers java;
	
	public ItemListPO(WebDriver driver)
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
	
	
		//Search section
		
			@FindBy(css="input#search")
			private WebElement search_Input;
	
			@FindBy(css="#item-list ul li.app-selectedListItem")
			private WebElement search_SelectedItem;
			
			@FindBy(css="#item-list ul li > span")
			private WebElement search_FirstItem;
			
			@FindBy(css="#item-list ul li button > i.icon-pencil")
			private WebElement search_FirstItem_Edit;
			
			@FindBy(css="#item-list ul li")
			private List<WebElement> search_AllItems;
			
			@FindBy(id="OptionBarButton")
			private WebElement search_FilterIcon;
			
				@FindBy(id="activeRecordState")
				private WebElement search_ActiveCheckbox;
				
				@FindBy(id="inactiveRecordState")
				private WebElement search_InactiveCheckbox;
			
			@FindBy(css="li.noRecords")
			private WebElement search_NoRecordMsg;	
			
		
			
		//Search section
			public void search_SearchWithText(String text)
			{
				search_Input.clear();
				search_Input.sendKeys(text);
				help.waitTillPageLoaded();
			}
			
			public void search_SearchWithText(String text,WebElement e)
			{
				e.clear();
				e.sendKeys(text);
				help.waitTillPageLoaded();
			}
			
			public void search_ClickOnFirstItem()
			{
				selenium.waitTillElementIsClickable(search_FirstItem).click();
				help.waitTillPageLoaded();
			}
			
			public void search_ClickOnFirstItem(WebElement e)
			{
				selenium.waitTillElementIsClickable(e).click();
				help.waitTillPageLoaded();
			}
			
			public boolean search_IsFirstItemDisplayed()
			{
				try 
				{
		            return search_FirstItem.isDisplayed();
				} 
				catch (Exception e) 
				{
		            return false;
		        }
			}
			
			public void search_EditFirstItem()
			{
				selenium.waitTillElementIsClickable(search_FirstItem_Edit).click();
				help.waitTillPageLoaded();
			}
			
			public void search_ClickOnFilterIcon()
			{
				search_FilterIcon.click();
				help.waitTillPageLoaded();
			}
			
			public void search_ClickOnInactiveCheckbox()
			{
				search_InactiveCheckbox.click();
				help.waitTillPageLoaded();
			}
			
			public String search_GetNoRecordMsgText()
			{
				return search_NoRecordMsg.getText().trim();
			}
			
			public int list_GetTotalItems()
			{
				return search_AllItems.size();
			}
			
}

