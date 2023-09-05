package utilityFolder;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitClass {
	private WebDriver driver;
	private WebDriverWait wait;
	public WaitClass(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(2));
	}
	
	public void elementToBeVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void elementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void listOfElementToBeVisible(List<WebElement> elementsList) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elementsList));
	}
	
	public void locatorToBeVisible(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void locatorToBeClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
}
