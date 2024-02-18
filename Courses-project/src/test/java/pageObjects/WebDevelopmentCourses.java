package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExcelUtility;

public class WebDevelopmentCourses extends BasePage {

	public WebDevelopmentCourses(WebDriver driver) {
		super(driver);
	}

	
	@FindBy(xpath = "//div[@class='cds-ProductCard-content']//h3")
	private List<WebElement> courseName;

	@FindBy(xpath = "//p[@class=\"cds-119 css-11uuo4b cds-121\"][1]")
	private List<WebElement> rating;

	@FindBy(xpath = "//div[@class=\"cds-CommonCard-metadata\"]/p")
	private List<WebElement> duration;
	
	@FindBy(xpath = "(//div[@class='cds-227 cds-formGroup-groupWrapper'])[3]//div[contains(@class, 'css-zf4w52')]")
	private List<WebElement> TotalLevels;
	
	@FindBy(xpath = "//h2[@role=\"heading\"]")
	private WebElement heading;
	
	WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(4));
	Pattern pattern;
	String[] matches;
	WebElement Courses_heading;

	// Validating heading is displayed
	public boolean validateWebDevelopmentCourse() {
		try {
			Courses_heading = waits.until(ExpectedConditions.visibilityOf(heading));
			if (Courses_heading.isDisplayed()) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// scrolling up
	public void scroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth'});", Courses_heading);
	}
	
	// Printing first two courses details
	public void getFirstTwoCourses() {
		waits = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> courses = waits.until(ExpectedConditions.visibilityOfAllElements(courseName));
		List<WebElement> ratings = waits.until(ExpectedConditions.visibilityOfAllElements(rating));
		List<WebElement> durations = waits.until(ExpectedConditions.visibilityOfAllElements(duration));

		try {
			ExcelUtility et = new ExcelUtility(System.getProperty("user.dir") + "\\testData\\WebDevDetails2.xlsx");
			System.out.println("Web Development Course");
			et.setCellData("Course",0,0,"Web Development Course");
			boolean Onecourse = true;
			
			pattern = Pattern.compile(".·.");
			scroll();
			
			for (int i = 0; i < 2; i++) {
				System.out.println("Course Name: " + courses.get(i).getText());
				
				System.out.println("Rating: " + ratings.get(i).getText());
				
				matches = pattern.split(durations.get(i).getText());
				
				System.out.println("Duration: " + matches[2].trim());
				
				if(Onecourse) {
					et.setCellData("Course",1,0,"Course Name: " + courses.get(i).getText());
					et.setCellData("Course",2,0,"Rating: " + ratings.get(i).getText());
					et.setCellData("Course",3,0,"Duration: " + matches[2].trim());
					Onecourse=false;
				}else {
					et.setCellData("Course",4,0,"Course Name: " + courses.get(i).getText());
					et.setCellData("Course",5,0,"Rating: " + ratings.get(i).getText());
					et.setCellData("Course",6,0,"Duration: " + matches[2].trim());
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
