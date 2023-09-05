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
		this.driver=driver;
		wait=new WaitClass(driver);
		jse=new JavaScriptExecutorClass(driver);
		log=LogManager.getLogger(Operations.class);
	}
	
	public boolean clickOperation(WebElement element) {
		 boolean actual = false;
		try {
			wait.elementToBeClickable(element);
			element.click();
			actual =true;
		} catch (Exception e) {
			try {
				log.warn("In Operation click try 2 block : "+e.getMessage());
				jse.clickWithJSE(element);
				actual=true;
			} catch (Exception e2) {
				System.out.println("Click operation Element not performed...: "+e2.getMessage());
				log.error(e2.getMessage());
			}
		}
		return actual;
	}
	
	public boolean sendkeysOperation(WebElement element, String text) {
		boolean actual=false;
		{
			try {
				wait.elementToBeVisible(element);
				element.sendKeys(text);
				actual=true;
			}catch (NoSuchElementException e) {
				log.error("No such Element in Page....: ");
			}
			catch (Exception e1) {
				try {
					jse.sendkeysWithJSE(element, text);
					actual=true;
				} catch (Exception e) {
					log.error("Sendkeys operation does not performed.... : "+e.getMessage());
				}
			}
		}
		return actual;
	}
}
