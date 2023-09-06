package baseFolder;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import objectRepository.MedicinesHomePage;

public class RoughWork extends BaseClass{
	MedicinesHomePage medicines;
	public static void main(String[] args) {
		RoughWork rough=new RoughWork();
		rough.getPageTitle();
		
	}
	//              @FindBy(how=How.XPATH, using = "")   private WebElement ;
	public void getPageTitle() {
		openBrowser("chrome");
		openApplication();
		System.out.println(driver.getTitle());
		medicines=new MedicinesHomePage(driver);
		medicines.verifyMedicinesPage();
		medicines.verifyDeliveryAddress();
		medicines.clickOnDeliveryAddress();
		
		medicines.clickOnEnterDeliveryPincode();
		medicines.enterPincodeInDeliveryPincode("111111");
	}
}
