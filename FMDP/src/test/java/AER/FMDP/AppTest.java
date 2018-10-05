package AER.FMDP;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class AppTest {

	public WebDriver driver;

	@Test
	public void openMyBlog() {
		driver.get("https://www.softwaretestingmaterial.com/");
	}

	@BeforeClass
	public void beforeClass() {

		String driverPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", driverPath + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}