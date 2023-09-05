package utilityFolder;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptExecutorClass {
	private WebDriver driver;
	public JavascriptExecutor jse;
	public JavaScriptExecutorClass(WebDriver driver) {
		this.driver=driver;
		jse=(JavascriptExecutor)driver;
	}
	
	public void clickWithJSE(WebElement element) {
		jse.executeScript("arguments[0].click();", element);
	}
	
	public void sendkeysWithJSE(WebElement element, String text) {
		jse.executeScript("arguments[0].value='"+text+"';", element);
	}
	
	public void scrollIntoView(WebElement element) {
		jse.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public void scrollToPageTop() {
		jse.executeScript("window.scrollBy(0,0)");
	}
	
	public void scrollToPageBottom() {
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
}
