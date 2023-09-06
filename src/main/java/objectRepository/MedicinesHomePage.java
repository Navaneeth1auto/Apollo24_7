package objectRepository;

import java.util.NoSuchElementException;

import javax.naming.directory.InvalidAttributesException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilityFolder.Operations;

public class MedicinesHomePage {
	private WebDriver driver;
	private Operations operation;
	private static Logger log;
	private String expextedPageTitle="Online Medical Store, Online Medicine Order, Fastest Delivery - Apollo Pharmacy";
	public MedicinesHomePage(WebDriver driver) {
		this.driver=driver;
		log=LogManager.getLogger(MedicinesHomePage.class);
		operation=new Operations(driver);
		PageFactory.initElements(driver, this);
	}
	// ///////////////////////Delivery Address Module//////////////////////////
	@FindBy(how=How.XPATH, using = "//label[text()='Delivery Address']")
	private WebElement deliveryAddress_text;		// Delivery address text In Header part of page.
	
	@FindBy(how=How.XPATH, using = "//label[text()='Delivery Address']/parent::div")
	private WebElement deliveryAddress_button ;     // Delivery address button in Header part of page.
	
	@FindBy(how=How.XPATH, using = "//p[text()='Enter Delivery Pincode']/parent::div")   
	private WebElement enterDeliveryPincode_button;
	
	@FindBy(how=How.XPATH, using = "//h2[text()='Help us serve you better!']")   
	private WebElement helpUsServeYouBetter_text_popupWindow;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Delivery Pincode']/following-sibling::div/input")  
	private WebElement deliveryPincode_inputField;
	
	@FindBy(how=How.XPATH, using = "//span[text()='Submit']")   
	private WebElement submit_button;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Delivery Pincode']/parent::div/div[@class='Q_']/span")   
	private WebElement errorMSGDeliveryPincode_text;
	
	@FindBy(how=How.XPATH, using = "//label[text()='Delivery Address']/following-sibling::div/p")   
	private WebElement deliveryAddressPincode_text;
	
	//******************************End of Delivery Address Module*************
	
	// method to verify the current page is Medicines Home page.
	public boolean verifyMedicinesPage() {
		boolean actual =false;
		String actualPageTitle=operation.getPageTitleOperation();
		if(actualPageTitle.equalsIgnoreCase(expextedPageTitle)) {
			log.info("Medicines Home page opened successfully.");
			actual=true;
		}else {
			log.error("The page is Not Medicines home page and current page title is : "+actualPageTitle);
		}
		return actual;
	}
	
	//Method to verify the Delivery Address text displayed in header part.
	public boolean verifyDeliveryAddress() {
		boolean actual =false;
		if (operation.elementVisiblityOperation(deliveryAddress_text)) {
			log.info("Delivery Address text displayed in Page.");
			actual=true;
		} else {
			log.error("Delivery Address text Not displayed in page...");
		}
		return actual;
	}
	
	public boolean clickOnDeliveryAddress() {
		boolean actual=false;
		if(operation.clickOperation(deliveryAddress_button)) {
			log.info("Clicked on Delivery Address button.");
		}else {
			log.error("Not able to perform Click on Delivery Address button");
			throw new ElementClickInterceptedException("Not able to perform Click on Delivery Address button");
		}
		if(operation.elementVisiblityOperation(enterDeliveryPincode_button)) {
			log.info("Enter Delivery Pincode button displayed");
			actual=true;
		}
		return actual;
	}
	
	public boolean clickOnEnterDeliveryPincode() {
		boolean actual =false;
		if(operation.clickOperation(enterDeliveryPincode_button)) {
			log.info("Enter Delivery Pincode button clicked");
		}else {
			log.error("Enter Delivery Pincode button not clicked...");
		}
		if (operation.elementVisiblityOperation(helpUsServeYouBetter_text_popupWindow)) {
			log.info("Pop-up window displayed with text : help Us Serve You Better");
			actual=true;
		} else {
			log.error("Pop-up window not displayed...");
			throw new ElementClickInterceptedException("Pop-up window not displayed...");
		}
		return actual;
	}
	
	public void enterPincodeInDeliveryPincode(String pincode){
		boolean actual=false;
		if(operation.elementVisiblityOperation(deliveryPincode_inputField)) {
			if (operation.sendkeysOperation(deliveryPincode_inputField, pincode)) {
				log.info(pincode+" is placed in Delivery Pincode input field.");
			} else {
				log.error("Not able to send pincode to 'Delivery Pincode'... ");
				throw new ElementNotInteractableException("Sendkey not performed....");
			}
		}else {
			log.error("Delivery Pincode input field do not displayed in page....");
			throw new NoSuchElementException("Delivery Pincode input field...");
		}
		if (operation.clickOperation(submit_button)) {
			log.info("Submit is enabled and clicked.");
		} else {
			log.error("The entered pincode is of invalid size : "+pincode.length()+" digets.");
			throw new ElementClickInterceptedException("Submit button not enabled....");
		}
		if(operation.elementVisiblityOperation(errorMSGDeliveryPincode_text)) {
			log.error("Error message: "+errorMSGDeliveryPincode_text.getText());
			throw new InvalidArgumentException(errorMSGDeliveryPincode_text.getText());
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String actualPincode=deliveryAddressPincode_text.getText();
		System.out.println(actualPincode);
		if (actualPincode.contains(pincode)) {
			log.info("PASS");
		} else {
			log.error("FAIL");
		}
	}
}
