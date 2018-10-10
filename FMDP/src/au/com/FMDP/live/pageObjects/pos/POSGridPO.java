package au.com.shortcuts.live.pageObjects.pos;

import java.util.Arrays;
import java.util.LinkedList;
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

public class POSGridPO 
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	JavaHelpers java;
	
	public POSGridPO(WebDriver driver)
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
	
	//Common buttons
			
		//In Service button
		@FindBy(id="sales")
		public WebElement inServiceBtn;
		
		//New Sale button
		@FindBy(id="customers")
		public WebElement newSaleBtn;
		
		//New Customer button
		@FindBy(id="new_customer")
		public WebElement newCustomerBtn;
		
		//New Walk-In button
		@FindBy(id="retail")
		public WebElement retailBtn;
		
		//Clear button
		@FindBy(id="clear")
		public WebElement clearBtn;
		
		//Apply button
		@FindBy(id="apply")
		public WebElement applyBtn;
		
	//Grid
		@FindBy(css="input[class='searchBox pos-numeric-keypad-input']")
		private WebElement price_Input; 
		
		@FindBy(css=".pos-documentLine-stepQuantity > div > i[class='icon-plus']")
		private WebElement increaseQuantity;
		
		@FindBy(css=".pos-documentLine-stepQuantity > div > i[class='icon-minus']")
		private WebElement decreaseQuantity;
		
		@FindBy(css="input[class='searchBox pos-numeric-keypad-input']")
		private WebElement quantity_Input; 
		
		@FindBy(css=".inc-discount")
		private WebElement discount;
		
		@FindBy(id="sale-sidebar-discountPreset-list")
		private WebElement discountSideBar;
				
		@FindBy(css="#sale-sidebar-discountPreset-list > li")
		private List<WebElement> discountList;
		
		@FindBy(css="#sale-sidebar-discountPreset-list li[data-value='price']")
		private WebElement discountAmount;
		
		@FindBy(css="#sale-sidebar-discountPreset-list li[data-value='percentage']")
		private WebElement discountPercentage;
		
		@FindBy(css="input.searchBox.pos-numeric-keypad-input")
		private WebElement discount_Input;
		
		@FindBy(css="#sale-sidebar-numeric-keypad > div > table > tbody > tr > td")
		private List<WebElement> digit;
		
	//Search Employee
		@FindBy(id="pos-employee-searchBox")
		private WebElement searchemployee_Input;
		
		@FindBy(css="#sale-sidebar-employee-list  ul li > span")
		private WebElement search_FirstEmployeeItem;
		
	//Get Details
		@FindBy(css="#pos-customer-details > h1[class='editable']")
		private WebElement customerName;
		
		
	//Common Buttons
		
		public void footer_ClickOnInServiceButton()
		{
			selenium.waitTillElementIsVisible(inServiceBtn);
			selenium.pageScrollInView(inServiceBtn);
			inServiceBtn.click();
			help.waitTillPageLoaded();
		}
		
		public void footer_ClickOnNewCustomerButton()
		{
			selenium.waitTillElementIsClickable(newSaleBtn);
			selenium.pageScrollInView(newSaleBtn);
			newSaleBtn.click();
			help.waitTillPageLoaded();
		}
		
		public void footer_ClickOnNewSaleButton()
		{
			selenium.waitTillElementIsClickable(newCustomerBtn);
			selenium.pageScrollInView(newCustomerBtn);
			newCustomerBtn.click();
			help.waitTillPageLoaded();
		}
		
		public void footer_ClickOnRetailButton()
		{
			selenium.waitTillElementIsClickable(retailBtn);
			selenium.pageScrollInView(retailBtn);
			retailBtn.click();
			help.waitTillPageLoaded();
		}
		
		public void footer_ClickOnClearButton()
		{
			selenium.waitTillElementIsClickable(clearBtn);
			selenium.pageScrollInView(clearBtn);
			clearBtn.click();
			help.waitTillPageLoaded();
		}
		
		public void footer_ClickOnApplyButton()
		{
			selenium.waitTillElementIsClickable(applyBtn);
			selenium.pageScrollInView(applyBtn);
			applyBtn.click();
			help.waitTillPageLoaded();
		}
	
		
	//Change Employee
	
		public void openEmployeeSideBar(int row)
		{
			row = row + row;			
			String cssSelector = ".pos-documentLine:nth-of-type(" + row + ") td:nth-of-type(2)";
			WebElement e = driver.findElement(By.cssSelector(cssSelector));
			e.click();
			selenium.waitTillElementIsNOTVisible(searchemployee_Input);	
		}
		
		public WebElement GetSearch_Input()
		{
			return searchemployee_Input;
		}
		
		public WebElement GetFirstItem_List()
		{
			return search_FirstEmployeeItem;
		}
		
		
	//Change Price
		
		public void openPriceSideBar(int row)
		{
			row = row + row;			
			String cssSelector = ".pos-documentLine:nth-of-type(" + row + ") td:nth-of-type(4)";
			WebElement e = driver.findElement(By.cssSelector(cssSelector));
			e.click();
			selenium.waitTillElementIsNOTVisible(price_Input);
		}
		
		public void enterPrice(String pricevalue)
		{
			price_Input.sendKeys(pricevalue);
		}
			
		
	//Increase Quantity
		
		public void quantityIncrease(int row, int quantity)
		{
			for (int i = 2; i <= quantity; i++)
			{
				row = row + row;			
				String cssSelector = ".pos-documentLine:nth-of-type(" + row + ") td:nth-of-type(7)";
				WebElement e = driver.findElement(By.cssSelector(cssSelector));
				e.click();
				help.waitTillPageLoaded();
			}
		}
	
		
	//Decrease Quantity
		
		public void quantityDecrease(int row, int quantity)
		{
			for (int i = 2; i <= quantity; i++)
			{
				row = row + row;			
				String cssSelector = ".pos-documentLine:nth-of-type(" + row + ") td:nth-of-type(5)";
				WebElement e = driver.findElement(By.cssSelector(cssSelector));
				e.click();
				help.waitTillPageLoaded();
			}
		}
		
		
	//Add Quantity
		
		public void openQuantitySideBar(int row)
		{
			row = row + row;			
			String cssSelector = ".pos-documentLine:nth-of-type(" + row + ") td:nth-of-type(6)";
			WebElement e = driver.findElement(By.cssSelector(cssSelector));
			e.click();
			selenium.waitTillElementIsNOTVisible(quantity_Input);
		}
		
		public void enterQuantity(String quantityvalue)
		{
			quantity_Input.sendKeys(quantityvalue);
		}
		
		
	//Discount
		
		public void openDiscountSideBar(int row)
		{
			row = row + row;			
			String cssSelector = ".pos-documentLine:nth-of-type(" + row + ") td:nth-of-type(8)";
			WebElement e = driver.findElement(By.cssSelector(cssSelector));
			e.click();
			selenium.waitTillElementIsNOTVisible(discountSideBar);	
		}
		
		public void selectDiscount(String discountpercent)
		{
			for (WebElement e : discountList)
			{
				selenium.pageScrollInView(e);
				if (e.getText().equalsIgnoreCase(discountpercent))
				{
					e.click();
					help.waitTillPageLoaded();
					break;
				}
			}
		}
		
		public void ClikOnAmount()
		{
			discountAmount.click();	
		}
		
		public void ClickOnPercentage()
		{
			discountPercentage.click();
		}
		
		
		public void enterDiscountAmount(String discountamount)
		{
			selenium.waitTillElementIsNOTVisible(discount_Input);
			discount_Input.sendKeys(discountamount);
		}
		
		public void enterDiscountPercentage(String discountpercentage)
		{
			selenium.waitTillElementIsNOTVisible(discount_Input);
			discount_Input.sendKeys(discountpercentage);
		}
		
		public void numericKeypad(int number)
		{
			List<Integer> digits = new LinkedList<Integer>();

			while (number > 0)
			{
				digits.add(0, number % 10);
				number = number / 10;
			}

			String numbers = Arrays.toString(digits.toArray());
			List<String> list = Arrays.asList(numbers);
			
			for (int i = 0; i < list.size(); i++)
			{
				for (WebElement e : digit)
				{
					if (e.getText().contains(list.get(i)))
					{
						e.click();
						break;
					}
				}
			}
		}
		
		
	//Delete Item
		
		public void deleteItem(int row)
		{
			row = row + row;			
			String cssSelector = ".pos-documentLine:nth-of-type(" + row + ") td:nth-of-type(10)";
			WebElement e = driver.findElement(By.cssSelector(cssSelector));
			e.click();
			help.waitTillPageLoaded();
		}
		
		
	//Get data
		
		public String getItemGridData(int row, int column)
		{
			String cssSelector = ".documentLines-table-wrapper table tbody:nth-of-type(" + row + ") td:nth-of-type(" + column + ")";
			WebElement e = driver.findElement(By.cssSelector(cssSelector));
			selenium.pageScrollInView(e);
			return e.getText().trim();
		}
		
		public String getSummaryDetailsData(int section, int row, int column)
		{
			String cssSelector = "#pos-currentDocument > div > section:nth-of-type(" + section + ") > div:nth-of-type(" + row + ") > span:nth-of-type(" + column + ")";
			WebElement e = driver.findElement(By.cssSelector(cssSelector));
			selenium.pageScrollInView(e);
			return e.getText().trim();
		}
		
		public String getSelectedCustomerName()
		{
			return customerName.getText().trim();
		}
		
}
