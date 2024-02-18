package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Courses extends BasePage {

	public Courses(WebDriver driver) {
		super(driver);
	}

	Pattern pattern;
	Matcher matcher;

	@FindBy(xpath = "//input[@role=\"textbox\"]")
	public WebElement takeInput;

	@FindBy(xpath = "//button[@class=\"nostyle search-button\" and @aria-label=\"Submit Search\"]")
	public WebElement searchBtn;

	@FindBy(xpath = "//input[starts-with(@placeholder,'What do you')]")
	WebElement searchOption;

	@FindBy(xpath = "//div[contains(@data-testid,'search-filter-group-Level')]//div[contains(@class,'css-zf4w52')]//label/div/span")
	List<WebElement> Levels;

	@FindBy(xpath = "//button[@aria-label=\"Show more Language options\"]")
	WebElement langShowmore;
	
	@FindBy(xpath="//div[@aria-label='Select Language options']/div/label/div/span")
	List<WebElement> AllLanguages;
	
	@FindBy(xpath="//div[@data-testid='scroll-container']/div[3]/button[1]")
	WebElement Applybtn;

	// verify searchOption is displayed
	public boolean verify_searchoption() throws InterruptedException {
		WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(0));
		try {
			WebElement icon = waits.until(ExpectedConditions.visibilityOf(searchOption));
			if (icon.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	// Search for courses
	public void search_courses() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", takeInput);
	}

	// Selecting Level as Beginner if not selected
	public boolean selecting_Levels() {
		try {
			for (WebElement searchLevel : Levels) {
				pattern = Pattern.compile("\\bBeginner\\b");
				matcher = pattern.matcher(searchLevel.getText());
				if (matcher.find()) {
					searchLevel.click();
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	// click on show more from language 
	public void clickShow_Languages() throws InterruptedException {
		WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement lang = waits.until(ExpectedConditions.visibilityOf(langShowmore));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", langShowmore);

	}

	// Selecting English Language
	public boolean selecting_Langs() {

		try {
			clickShow_Languages();
			for(WebElement Langs: AllLanguages) {
			pattern = Pattern.compile("\\bEnglish\\b");
			matcher = pattern.matcher(Langs.getText());
			if (matcher.find()) {
				Langs.click();
				Applybtn.click();
				return true;
			}
		}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	// scrolling up
	public void scrolling_up() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
	}

}
