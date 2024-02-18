package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.*;

public class Enterprise extends BasePage {

	public Enterprise(WebDriver driver) {
		super(driver);
	}
	/*
	@FindBy(xpath = "//a[@href='https://www.coursera.org/campus']")
	private WebElement forUniversitiesLink;
	*/
	@FindBy(xpath="//a[normalize-space()='For Enterprise']")
	private WebElement forUniversitiesLink;

	public void clickForUniversities() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click;", forUniversitiesLink);
		//forUniversitiesLink.click();
	}

}
