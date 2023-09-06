package utilityFolder;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.Logger;

public class Operations {
	private WebDriver driver;
	private WaitClass wait;
	public JavaScriptExecutorClass jse;
	private Logger log;

	public Operations(WebDriver driver) {
		this.driver = driver;
		wait = new WaitClass(driver);
		jse = new JavaScriptExecutorClass(driver);
		log = LogManager.getLogger(Operations.class);
	}

	public String getPageTitleOperation() {
		return driver.getTitle();
	}

	public boolean elementVisiblityOperation(WebElement element) {
		boolean actual = false;
		try {
			wait.elementToBeVisible(element);
			actual = true;
		} catch (Exception e) {
			log.warn("Element Visiblity operation failed.. :" + e.getClass());
		}
		return actual;
	}

	public boolean clickOperation(WebElement element) {
		boolean actual = false;
		try {
			wait.elementToBeClickable(element);
			element.click();
			actual = true;
		} catch (NoSuchElementException e3) {
			log.error("No such Element in Page so click operation not performed.... ");
		} catch (Exception e) {
			log.error("click operation do not performed: "+ e.getClass());
		}
		return actual;
	}

	public boolean sendkeysOperation(WebElement element, String text) {
		boolean actual = false;
		{
			try {
				wait.elementToBeVisible(element);
				element.sendKeys(text);
				actual = true;
			} catch (NoSuchElementException e) {
				log.error("No such Element in Page....: ");
			} catch (Exception e1) {
				try {
					jse.sendkeysWithJSE(element, text);
					actual = true;
				} catch (Exception e) {
					log.error("Sendkeys operation does not performed.... : " + e.getClass());
				}
			}
		}
		return actual;
	}
}
