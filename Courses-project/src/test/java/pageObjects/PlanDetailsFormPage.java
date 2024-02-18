package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import testBase.BaseClass;

public class PlanDetailsFormPage extends BasePage {

	public PlanDetailsFormPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//input[@aria-label='Number of users']")
	private WebElement numberOfUsersInput;

	@FindBy(xpath = "//button[@class='cds-128 cds-button-disableElevation cds-button-primary css-efrgiy']")
	private WebElement continueButton;

	@FindBy(xpath="//span[contains(text(), \"No thanks\")]")
	private WebElement discountClose_loc;

	//Filling up Plan Details
	public void fillPlanDetails() {
		
		try {
			discountClose_loc.click();
		} catch (Exception e) {
		}
		
		BaseClass bobj = new BaseClass();
		
		//Storing a random number between 5 and 50
		String randomNumberOfUsersString = bobj.randomeNumberRange(5,50);
		
		numberOfUsersInput.sendKeys(randomNumberOfUsersString);
		
		//click on continue button 
		continueButton.click();
	}
}
