package pageObjects;
 
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.BaseClass;
 
public class AccountDetailsFormPage extends BasePage{
 
	// Constructor
	public AccountDetailsFormPage(WebDriver driver) {
		super(driver);
	}
	// Locators
	//@FindBy(xpath = "//input[@placeholder='Full name']")
	//private WebElement fullNameInput;
	
	@FindBy(xpath="//div[@data-e2e=\"full-name-input\"]/input")
	private WebElement fullNameInput;
	@FindBy(xpath = "//input[@aria-label='Business email']")
	private WebElement emailInput;
	@FindBy(xpath = "//input[@aria-label='Job title']")
	private WebElement jobInput;
	@FindBy(xpath = "//input[@aria-label='Organization name']")
	private WebElement organizationNameInput;
	@FindBy(xpath="//span[contains(text(),'Continue')]")
	private WebElement continueButton;
	@FindBy(xpath="//span[normalize-space()='Please enter a valid email.']")
	private WebElement errorMsg;
	@FindBy(xpath="//div[@data-e2e='organization-type-select']")
	private WebElement orgType;
	@FindBy(xpath="(//div[@class=\"cds-selectOption-container\"])")
	private List<WebElement> orgValue;
	
	//Actions
	Actions action = new Actions(driver);
	public void fillFormwithNegativeData() throws InterruptedException {
		BaseClass baseobj = new BaseClass();
		Thread.sleep(3000);
		fullNameInput.sendKeys(baseobj.randomeString());
		emailInput.sendKeys(baseobj.randomeString());
		jobInput.sendKeys("Engineer");
		organizationNameInput.sendKeys(baseobj.randomeString());
		
		
		action.moveToElement(continueButton).click().perform();
		}
	

	public boolean isEmailErrorDisplayed() {
        // Wait for the error message to be present in the DOM
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    	// Using XPath to locate the second error message element
    	WebElement emailErrorElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Please enter a valid email.']")));
        System.out.println(errorMsg.getText());
    	// Check if the error message is displayed
        return emailErrorElement.isDisplayed();
    }
}
