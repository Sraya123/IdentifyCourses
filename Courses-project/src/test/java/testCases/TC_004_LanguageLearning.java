package testCases;

import static org.testng.Assert.fail;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.*;

import pageObjects.LanguageLearningPage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtility;

public class TC_004_LanguageLearning extends BaseClass{
	
	
	@Test(priority=10,groups= {"sanity","regression"})
	public void search_Language()
	{
		logger.info("--------Starting TC_004_Language Learning---------");
		try {
		LanguageLearningPage lobj=new LanguageLearningPage(driver);
		logger.info("--------Language Learning successfully searched---------");
		lobj.searchLanguageLearning();
		}catch(Exception e) {
			logger.error("--------Language Learning not searched---------");
			e.printStackTrace();
			Assert.fail();
		}
		
	}
	
	@Test(priority=11,groups= {"regression"})
	public void show_FiveCourses()
	{
		try {
			LanguageLearningPage learnObj = new LanguageLearningPage(driver);
			logger.info("--------Extracting five langauges with the available levels---------");
            ArrayList<ArrayList<String>> storelist =learnObj.getCourses();
            ExcelUtility et = new ExcelUtility(System.getProperty("user.dir") + "\\testData\\LangLearningCourses.xlsx");
    		for(int i=0;i<storelist.size();i++) {
    			for(int j=0;j<storelist.get(i).size();j++)
    			{	
    			et.setCellData("Languages",i,j,storelist.get(i).get(j));
    			}
    		}
			}catch(Exception e) {
				logger.error("--------Extracting five langauges failed---------");
				e.printStackTrace();
				Assert.fail();
			}
		
		logger.info("--------Ending TC_004_Language Learning---------");
	}
}

