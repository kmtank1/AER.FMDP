package au.com.FMDP.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class SeleniumHelpers 
{
	WebDriver driver;
	JavaHelpers helper;
	
	public SeleniumHelpers(WebDriver driver)
	{
		this.driver = driver;
		helper = new JavaHelpers();
	}
	
	//Take screenshots
	public void takeScreenshot(String fileName) throws IOException
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(scrFile, new File(Constants.SCREENSHOT_LOCATION + "\\" + fileName + helper.getTimeStamp("_yyyyMMdd_HHmmss")+".png"));	
	}
	
	
	//Navigation
	public void navigateToPage(String url)
	{
		driver.get(url);
	}
	
	public void refreshPage() throws InterruptedException
	{
		driver.navigate().refresh();
		helper.sleep(4);
	}
	
	
	//Waits
	public WebElement waitTillElementIsClickable(WebElement e)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 wait.until(ExpectedConditions.elementToBeClickable(e));
		 return e;
	}
	
	public WebElement waitTillElementIsClickable(WebElement e, int waittime)
	{
		 WebDriverWait wait = new WebDriverWait(driver, waittime);
		 wait.until(ExpectedConditions.elementToBeClickable(e));
		 return e;
	}
	
	public WebElement waitTillElementIsClickable(By by, int waittime)
	{
		 WebDriverWait wait = new WebDriverWait(driver, waittime);
		 WebElement e = wait.until(ExpectedConditions.elementToBeClickable(by));
		 return e;
	}
	
	public WebElement waitTillElementIsClickable(By by)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 WebElement e = wait.until(ExpectedConditions.elementToBeClickable(by));
		 return e;
	}
	
	public void waitTillElementIsVisible(WebElement e)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	public WebElement waitTillElementIsVisible(By by)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		 pageScrollInView(e);
		 return e;
	}
	
	public void waitTillElementIsPresent(By by)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}
	
	public void waitTillElementIsPresent(WebElement e)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 wait.until(ExpectedConditions.invisibilityOf(e));
	}
	
	public void waitTillElementIsNOTVisible(By by)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	public void waitTillElementIsNOTVisible(WebElement e)
	{
		 WebDriverWait wait = new WebDriverWait(driver, Constants.WEBDRIVER_WAIT_DURATION);
		 wait.until(ExpectedConditions.invisibilityOf(e));
	}
	
	
	//Dropdown
	public void selectDropdownValueByText(WebElement e, String text)
	{
		new Select(e).selectByVisibleText(text);
	}
	
	public String getSelectedDropdownValue(WebElement e)
	{
		return new Select(e).getFirstSelectedOption().getText();
	}
	
	public String selectDropdownValueByIndex(WebElement e, int index)
	{
		new Select(e).selectByIndex(index);
		return new Select(e).getFirstSelectedOption().getText();
	}
	
	//Action events
	public void focusOnElement(WebElement e)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(e);
		actions.click();
	}
	
	
	//Page scroll down
	public void pageScrollInView(WebElement e)
	{
	    JavascriptExecutor jse = (JavascriptExecutor) driver;
	    jse.executeScript("arguments[0].scrollIntoView(true);",e);
	}
	
	public void widnowFocus()
	{
	    JavascriptExecutor jse = (JavascriptExecutor) driver;
	    jse.executeScript("window.focus();");
	}
	
	//Set value
	public void javascript_SetValue(WebElement e, String value)
	{
		String script = "arguments[0].value='" + value + "';";
		((JavascriptExecutor) driver).executeScript(script, e);
	}
	
	//click on Element
	public void javascript_ClickOn(WebElement e)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", e);
	}
		
	//Set an attribute 
	public void javascript_SetAnAttribute(WebElement e, String attribute, String value)
	{
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String jsscript = "arguments[0].setAttribute(arguments[1], arguments[2])";
		jse.executeScript(jsscript,e,attribute,value);
	}

}

