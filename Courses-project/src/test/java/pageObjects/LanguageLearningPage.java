package pageObjects;
 
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ExcelUtility;
 
public class LanguageLearningPage extends BasePage{
 
	public LanguageLearningPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath = "//input[contains(@placeholder,'learn')]")
    private WebElement inputBox;
	@FindBy(xpath = "//button[2]/div[@class='magnifier-wrapper']")
	private WebElement searchButton;
	@FindBy(xpath="//button[@aria-label='Show more Language options']")
	private WebElement showMore_loc;
	@FindBy(xpath="(//button[@class='cds-149 cds-button-disableElevation cds-button-primary css-1wio7h1'])[1]")
	private WebElement apply_loc;
	@FindBy(xpath="//div[@id='checkbox-group']//div[@class='cds-checkboxAndRadio-labelText']//span")
	private List<WebElement> allLang_loc;
	@FindBy(xpath="//div[@data-testid=\"search-filter-group-Level\"]//div[@class=\"cds-checkboxAndRadio-labelText\"]")
	private List<WebElement> allLevel_loc;
	@FindBy(xpath="//div[@class=\"rc-SearchResultsHeader\"]//span")
	private WebElement totalCount_loc;
	@FindBy(xpath="//span[normalize-space()='Clear All']")
	private WebElement clearAllBtn_loc;
	@FindBy(xpath="//span[normalize-space()='Close']//span[@class='cds-button-endIcon']")
	private WebElement closeBtn_loc;
	@FindBy(xpath="//div[@aria-label='Select Language options']/div/label/div/span")
	private List<WebElement> AllLanguages1;
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	
	//Search for Language Learning Courses
	public void searchLanguageLearning() throws InterruptedException {
		inputBox.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
		inputBox.sendKeys("Language Learning");
		searchButton.click();
		Thread.sleep(3000);
	}
	
	public List<String> storeFiveLangauges() {
		List<String> Langs = new ArrayList<>(Arrays.asList("English","Arabic","French","German","Greek"));
		return Langs;
	}
	
	public String storeonlyCharacter(String cLang) {
		String result="";
		for(int i=0;i<cLang.length();i++) {
			if(cLang.charAt(i)>='a'&&cLang.charAt(i)<='z'||cLang.charAt(i)>='A'&&cLang.charAt(i)<='Z') {
				result += cLang.charAt(i);
			}
		}
		return result;
	}
	//Extracting any Five languages
	public Set<String> ExtractFiveLanguage() {
		js.executeScript("arguments[0].click()",showMore_loc);
		Set<String> langs = new HashSet<>();
		int i=0;
		for(;i<=5;i++) {
			String value = storeonlyCharacter(AllLanguages1.get(i).getText());
			langs.add(value.trim());
		}
		js.executeScript("arguments[0].click()", closeBtn_loc);
		return langs;
		
	}

	public ArrayList<ArrayList<String>> getCourses() throws InterruptedException, IOException
	{
		List<String> language = new ArrayList<>(ExtractFiveLanguage());
		WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(2));
		
		ArrayList<ArrayList<String>> storelist = new ArrayList<ArrayList<String>>();
		ArrayList<String> details = new ArrayList<>();
		storelist.add(new ArrayList<>(Arrays.asList("Language","Level","Total Count")));
		
		for(int i=0;i<=4;i++)
		{
			js.executeScript("arguments[0].click()",showMore_loc);
			Thread.sleep(2000);
			String store="";
			List<WebElement> AllLanguages = waits.until(ExpectedConditions.visibilityOfAllElements(AllLanguages1));
			String lang = "";
			for(WebElement ele: AllLanguages)
			{
				store = storeonlyCharacter(ele.getText()).trim(); 
				if(store.equalsIgnoreCase(language.get(i)))
				{

					ele.click();
					Thread.sleep(3000);
					System.out.println("Language clicked is: "+ele.getText());
					lang = ele.getText();
					
					apply_loc.click();
					Thread.sleep(3000);
					
					for(WebElement level : allLevel_loc) {
						details.add(lang);
						
						System.out.println("----------------------------------------------");
						//Clicking on checkbox
						js.executeScript("arguments[0].click()", level);  
						Thread.sleep(5000);
						System.out.println(level.getText());
						details.add(level.getText());
						
						String count = totalCount_loc.getText();
						
						System.out.println("Total Count : " + count);
						details.add("Total Count: "+count);
						
						storelist.add(details);
						Thread.sleep(3000);
						// Unchecking the checkbox
						js.executeScript("arguments[0].click()", level);   					
						Thread.sleep(5000);
						
						details = new ArrayList<>();
						
					}
					js.executeScript("arguments[0].click()",showMore_loc);
					Thread.sleep(2000);
					js.executeScript("arguments[0].click()",clearAllBtn_loc);
					Thread.sleep(2000);
					js.executeScript("arguments[0].click()", closeBtn_loc);
					break;
				}
			}
			System.out.println("----------------------------------------------");
		}
		return storelist;
	}
}