package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;


import pageObjects.WebDevelopmentCourses;
import testBase.BaseClass;
import utilities.ExcelUtility;

public class TC_003_WebDevelopmentCourses extends BaseClass{
	
	WebDevelopmentCourses wobj;
	@Test(priority=8,groups= {"regression"})
	public void course_name() {
		logger.info("--------Starting TC_003_WebDevelopment Courses---------");
		try {
			logger.info("--------Web Development Course Successfully Searched--------");
			wobj = new WebDevelopmentCourses(driver);
			Assert.assertEquals(wobj.validateWebDevelopmentCourse(),true);
		}catch(Exception e) {
			logger.error("--------Web Development Course not Searched--------");
			e.printStackTrace();
			Assert.fail();
			
		}
	}
	@Test(priority=9,groups= {"regression"})
	public void FirstTwoCourses() {
		try {
			logger.info("--------Getting first two Courses--------");
			wobj.getFirstTwoCourses();
			//wobj.getSecondCourseDetails();
			
		}catch(Exception e) {
			logger.error("-------Error in getting first two courses---------");
			e.printStackTrace();
			Assert.fail();
		}
	
	logger.info("--------Ending TC_003_WebDevelopment Courses---------");
	}

}
