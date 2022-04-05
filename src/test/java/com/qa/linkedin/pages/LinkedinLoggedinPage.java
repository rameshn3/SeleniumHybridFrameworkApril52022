package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinLoggedinPage extends BasePageWeb{

	private Logger log=Logger.getLogger(LinkedinLoggedinPage.class);
	
	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(@class,'feed-identity-module')]")
	private WebElement profileRailCard;
	
	@FindBy(xpath="//img[contains(@class,'global-nav__me-photo ember-view')]")
	private WebElement profilePicIcon;
	
	@FindBy(xpath="//a[@class='global-nav__secondary-link mv1'][contains(.,'Sign Out')]")
	private WebElement signOutLink;
	
	@FindBy(xpath="//input[contains(@class,'search-global-typeahead')]")
	private WebElement searchEditbox;
	
	String loggedinPgTitle="Feed | LinkedIn";
	
	public void verifyLinkedinLoggedInPgTitle() {
		log.debug("Verify the linkedin loggedin page title:"+loggedinPgTitle);
		wait.until(ExpectedConditions.titleContains(loggedinPgTitle));
		Assert.assertTrue(driver.getTitle().contains(loggedinPgTitle));
		
	}

	public void verifyProfileRaildCard() throws InterruptedException {
		log.debug("Verify the linkedin Profile rail card element");
		Assert.assertTrue(isDisplayedElement(profileRailCard));
	}
	

	public void doLogOut() throws InterruptedException {
		log.debug("started executing the doLogOut().....  ");
		wait.until(ExpectedConditions.elementToBeClickable(profilePicIcon));
		wait.until(ExpectedConditions.visibilityOf(profilePicIcon));
		log.debug("click on profile image icon");
		click(profilePicIcon);
		log.debug("wait for the sign out link");
		wait.until(ExpectedConditions.elementToBeClickable(signOutLink));
		wait.until(ExpectedConditions.visibilityOf(signOutLink));
		log.debug("click on SignOut link");
		click(signOutLink);
	}
	
	public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
		log.debug("Started executing the doPeopleSearch() for the keyword::::"+keyword);
		log.debug("clear the content in the search editbox");
		clearText(searchEditbox);
		log.debug("type the search keyword:"+keyword+" in search editbox");
		sendKey(searchEditbox, keyword);
		log.debug("Press the ENTER key on searchEditbox");
		searchEditbox.sendKeys(Keys.ENTER);
		return new SearchResultsPage();
	}
	
	
	
}
