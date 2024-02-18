package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Courses;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_002_Courses extends BaseClass{
	
	Courses cobj;
	@Test(priority=3,groups= {"sanity","regression"})
	public void verify_SearchIcon()
	{
		logger.info("--------Starting TC_002_Courses---------");
		try {
		cobj = new Courses(driver);
		if(cobj.verify_searchoption()) {
			
			logger.info("--------Search icon is displayed---------");
			Assert.assertEquals(cobj.verify_searchoption(), true,"Search icon is displayed");
		}else {
			logger.error("--------Search icon is not displayed---------");
			Assert.fail("Search icon is not displayed");
		}
	}catch(Exception e) {
		e.printStackTrace();
		logger.error("---------Search icon is not displayed---------");
		Assert.fail("Search icon is not displayed");
	}
	}
	
	
	@Test(priority=4,groups= {"sanity","regression"}, dataProvider="CourseNameOne",dataProviderClass=DataProviders.class)
	public void search_courses(String CName) throws InterruptedException {
		try {
		logger.info("--------Searching Web Development Courses---------");
		cobj.search_courses();
		cobj.takeInput.sendKeys(CName);
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", cobj.searchBtn);
		}catch(Exception e) {
			logger.error("--------Web Development Courses is not searched---------");
			Assert.fail();
		}
	}
	
	@Test(priority=5,groups= {"regression"})
	public void select_levels() {
		try {
		logger.debug("-------Beginner Level is Selected---------");
		boolean Beginner = cobj.selecting_Levels();
		Assert.assertEquals(Beginner, true);
		}catch(Exception e) {
			logger.error("-------Beginner Level is not Selected---------");
			Assert.fail();
		}
	}
	
	@Test(priority=6,groups= {"regression"})
	public void select_langs() {
		try {
		logger.info("--------Languages: English is Selected---------");
		boolean English = cobj.selecting_Langs();
		Assert.assertEquals(English, true);
		}catch(Exception e) {
			logger.error("--------Languages: English is not Selected---------");
			Assert.fail();
		}
	}
	@Test(priority=7,groups= {"regression"})
	public void scrollUp(){
		try {
			cobj.scrolling_up();
			}catch(Exception e) {
				Assert.fail();
			}
		logger.info("--------Ending TC_002_Courses---------");
	}
}

