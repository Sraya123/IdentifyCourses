package testCases;

import org.testng.annotations.Test;

import pageObjects.AccountDetailsFormPage;
import pageObjects.CourseraPage;
import pageObjects.Enterprise;
import pageObjects.PlanDetailsFormPage;
import pageObjects.UniversitiesPage;
import testBase.BaseClass;

public class TC_005_FormDetails extends BaseClass {
	
	@Test(priority=12,groups= {"sanity","regression"})
	 public void navigateToEnterprisePage() {
		logger.info("--------Starting TC_005_FormDetails---------");
		CourseraPage cpobj = new CourseraPage(driver);
		//cpobj.navigateToHomePage();
		cpobj.clickForEnterprise();
	}
	
	@Test(priority=13,groups= {"sanity","regression"})
	public void navigateToForm() {
		Enterprise EnterprisePage = new Enterprise(driver);
		EnterprisePage.clickForUniversities();
		UniversitiesPage UniPage = new UniversitiesPage(driver);
		UniPage.navigateToForm();
	}
	
	@Test(priority=14,groups= {"regression"})
	public void FillFormDetails() throws InterruptedException {
		PlanDetailsFormPage planDetailsPage = new PlanDetailsFormPage(driver);
		planDetailsPage.fillPlanDetails();
		AccountDetailsFormPage accountDetailsPage = new AccountDetailsFormPage(driver);
		accountDetailsPage.fillFormwithNegativeData();
		logger.info("--------Filling up the form---------");

        //validate if error message is displayed 
        if (accountDetailsPage.isEmailErrorDisplayed()) {
            System.out.println("Error Message is displayed");
    		logger.info("--------Error Message is displayed---------");
        } else {
    		logger.info("--------Error Message is not displayed---------");
            System.out.println("Error Message is not displayed");
        }
    	//accountDetailsPage.fillFormwithPositiveData();
    	Thread.sleep(5000);
		logger.info("--------Ending TC_005_FormDetails---------");

	}
	
}