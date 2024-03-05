package VtigerCRM;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtil;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

public class ContectTestNG {

	WebDriverUtil wutil = new WebDriverUtil();
	PropertyFileUtil putil = new PropertyFileUtil();
	ExcelUtil eutil = new ExcelUtil();

	@Test
	public void contacttest() throws IOException, InterruptedException {

		final WebDriver driver = new ChromeDriver();
		wutil.maximize(driver);
		wutil.implicitwait(driver);

		final String URL = putil.getDataFromPropertyFile("Url");
		final String USERNAME = putil.getDataFromPropertyFile("username");
		final String PASSWORD = putil.getDataFromPropertyFile("Password");

		driver.get(URL);

		// adding username
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);

		// adding password
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);

		// to click on login button
		driver.findElement(By.id("submitButton")).click();

//		Click on contacts
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		// To click on create contacts..(+)
		driver.findElement(By.cssSelector("img[alt='Create Contact...']")).click();

		// To read data from Excel File
		Thread.sleep(2000);
		final String CONTACTFIRST = eutil.getDataFromExcel("Contact", 0, 1);
		final String CONTACTLAST = eutil.getDataFromExcel("Contact", 1, 1);
		final String CONGROUP = eutil.getDataFromExcel("Contact", 2, 1);
		final String ORGNAME = eutil.getDataFromExcel("Contact", 3, 1);

		// to enter data from excel
		Thread.sleep(2000);
		driver.findElement(By.name("firstname")).sendKeys(CONTACTFIRST);
		driver.findElement(By.name("lastname")).sendKeys(CONTACTLAST);

		// When we want to fail test script hear
		final WebElement notificheckbox = driver.findElement(By.name("notify_owner"));
		Assert.assertTrue(notificheckbox.isSelected());

		// In assigned to click on group
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();

		// In the dropdown select Support Group WebElement condrop
		Thread.sleep(2000);
		driver.findElement(By.name("assigned_group_id"));

		// manage dropdown
		Thread.sleep(2000);
		final WebElement condrop = driver.findElement(By.name("assigned_group_id"));
		wutil.HandleDropdowncontact(condrop, CONGROUP);

		// Click on (+ symbol) to pass orginasization name Text field
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();

//		Transfer the driver control from Parent window to Child window
		Thread.sleep(2000);
		wutil.switchwindow(driver,
				"http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=");

		// To enter Organization Name in search
		Thread.sleep(2000);
		driver.findElement(By.id("search_txt")).sendKeys(ORGNAME);

//		To click on Organization Name
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Qspider & Jspider']")).click();

		// To transfer contol child to parent
		wutil.switchwindow(driver,
				"http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing");

//		// To take screenshot//		Thread.sleep(2000);
//		wutil.screenshot(driver, "Contact");

		// cLICK ON SAVE BUTTON
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();

		// Mouse hover on image WebElement image
		Thread.sleep(2000);
		final WebElement image = driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		wutil.mousehover(driver, image);

		// Click on signout
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}

}
