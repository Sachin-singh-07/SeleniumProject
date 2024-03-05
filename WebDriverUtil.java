package CommonUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtil {

	public void maximize(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void implicitwait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}

	// to org.. dropdown
	public void HandleDropdown(WebElement element, String targetedelement) {
		Select s = new Select(element);
		s.selectByVisibleText(targetedelement);
	}

	public void mousehover(WebDriver driver, WebElement element) {
		Actions a = new Actions(driver);
		a.moveToElement(element);
		a.perform();
	}

	// to contact dropdown
	public void HandleDropdowncontact(WebElement element, String targetedelement) {
		Select ss = new Select(element);
		ss.selectByVisibleText(targetedelement);
	}

	// For transfer the window parent to child method
	public void switchwindow(WebDriver driver, String ExpectedUrl) {

		Set<String> ids = driver.getWindowHandles();

		for (String e : ids) {

			String ActualUrl = driver.switchTo().window(e).getCurrentUrl();

			if (ActualUrl.contains(ExpectedUrl)) {
				break;
			}
		}

	}

	// To take screenshot method

	public File screenshot(WebDriver driver, String ScreenshotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File tempfile = ts.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File("./Screenshot/" + ScreenshotName + ".png");
		FileUtils.copyFile(tempfile, destinationFile);
		return destinationFile;
	}

	// to contact LEADS
	public void HandleDropdownleads(WebElement element, String targetedelement) {
		Select sss = new Select(element);
		sss.selectByVisibleText(targetedelement);
	}

}
