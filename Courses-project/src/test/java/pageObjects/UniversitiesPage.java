package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class UniversitiesPage  extends BasePage{
	
	public UniversitiesPage(WebDriver driver) {
		super(driver);
	}
	
	
		@FindBy(xpath="//div[@aria-label='Banner']//a[3]")
		private WebElement universityBtn_loc;
		@FindBy(xpath = "//a[normalize-space()='Solutions']")
		private WebElement solutionsLink;
		@FindBy(xpath = "//p[normalize-space()='Upskill teams of 5 to 125 employees']")
		private WebElement upskillTeam;
		@FindBy(xpath = "//span[@class='cds-button-label'][normalize-space()='Get Started'][1]")
		private WebElement getStartedButton;
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		Actions actions = new Actions(driver);

		//Navigate to fill up form
		public void navigateToForm() {
			actions.moveToElement(universityBtn_loc).click().perform();
			actions.moveToElement(solutionsLink).click().perform();
			actions.moveToElement(upskillTeam).click().perform();
			actions.moveToElement(getStartedButton).click().perform();
		}

}
