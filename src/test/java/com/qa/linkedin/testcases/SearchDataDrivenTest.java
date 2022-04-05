package com.qa.linkedin.testcases;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.pages.LinkedinHomePage;
import com.qa.linkedin.pages.LinkedinLoggedinPage;
import com.qa.linkedin.pages.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDataDrivenTest extends TestBase {
	private Logger log = Logger.getLogger(SearchDataDrivenTest.class);
	private String path = System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\linkedin\\data\\searchData.xlsx";
	LinkedinHomePage lhmPg;
	LinkedinLoggedinPage llPg;
	SearchResultsPage srPg;

	
	@Test(priority=1)
	public void doLoginTest() throws InterruptedException, IOException {
		log.debug("executing the doLoginTest()....");
		log.debug("************verify linkedin home page elements & Title**************");
		lhmPg.verifyLinkedinHomePgHeaderText();
		lhmPg.verifyLinkedinHomePgTitle();
		lhmPg.verifyLinkedinLogo();
		log.debug("Click on Sign in link");
		lhmPg.clickSigninLink();
		log.debug("*********Verify Linedin login page elements & Title****************");
		lhmPg.verifyLinkedinSigninPgHeaderText();
		lhmPg.verifyLinkedinSigninPgTitle();
		log.debug("perfrom the login test");
		llPg=lhmPg.doLogin(readPropertyValue("username"), readPropertyValue("pwd"));
	
	}
	
	
	@Test(dataProvider = "dp",priority=2)
	public void dosearchPeopleTest(String s) throws InterruptedException {
		log.debug("**********Perform dosearchPeopleTest for :"+s);
		llPg.verifyLinkedinLoggedInPgTitle();
		llPg.verifyProfileRaildCard();
		log.debug("Type the value  "+s+"  in Search editbox");
		srPg=llPg.doPeopleSearch(s);
		log.debug("fetch the search results count for:"+s);
		try {
			long count=srPg.getResultsCount();
			log.debug("searc hresults count for:"+s+" is:"+count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("click on Home tb");
		srPg.clickOnHomeTab();
	}

	@DataProvider
	public Object[][] dp() throws InvalidFormatException, IOException {
		log.debug("reading the excel sheet data to @Dataprovider annotation");
		Object[][] data = new ExcelUtils().getTestData(path, "Sheet1");

		return data;
	}

	@BeforeClass
	public void initializeObjects() {
		log.debug("Initilize the page objects");
		lhmPg = new LinkedinHomePage();
		llPg = new LinkedinLoggedinPage();
		srPg = new SearchResultsPage();
	}

	@AfterClass
	public void logoutTest() throws InterruptedException {
		
		log.debug("logout from the application");
		llPg.doLogOut();
		
	}

}
