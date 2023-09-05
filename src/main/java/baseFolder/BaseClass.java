package baseFolder;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	private static ChromeOptions chromeOption;
	private static FirefoxOptions firefoxOption;
	public static final String screenShotPath = "reports/screenShots";

	private void setChromeOptions() {
		chromeOption=new ChromeOptions();
		chromeOption.setImplicitWaitTimeout(Duration.ofSeconds(2));
		chromeOption.setPageLoadTimeout(Duration.ofSeconds(20));
		chromeOption.addArguments("disable-infobars").addArguments("disable-popup-blocking");
	}
	
	private void setFirefoxOptions() {
		firefoxOption=new FirefoxOptions();
		firefoxOption.setImplicitWaitTimeout(Duration.ofSeconds(2));
		firefoxOption.setPageLoadTimeout(Duration.ofSeconds(20));
	}
	
	public WebDriver openBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			setChromeOptions();
			driver = new ChromeDriver(chromeOption);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			setFirefoxOptions();
			driver = new FirefoxDriver(firefoxOption);
		} else {
			System.out.println(browserName + " ERROR...");
		}
		return driver;
	}

	public void openApplication() {
		driver.get("https://www.apollo247.com/");
	}

	public String screenShot() {
		String timeStamp = new SimpleDateFormat("hh_mm_ss_SSS_dd_MM_yyyy").format(new Date());
		String destPath = screenShotPath + "/" + timeStamp + ".png";

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(destPath));
		} catch (IOException e) {
			System.out.println("ERROR in screen shot file creation : " + e.getMessage());
		}
		return destPath;
	}
}
