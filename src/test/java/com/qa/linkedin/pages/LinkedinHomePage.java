package com.qa.linkedin.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class LinkedinHomePage extends BasePageWeb{

private Logger log=Logger.getLogger(LinkedinHomePage.class);

//create a constructor
public LinkedinHomePage() {
	PageFactory.initElements(driver, this);
}
	
@FindBy(css="a.nav__logo-link")
private WebElement linkedinLogo;

@FindBy(css="a.nav__button-secondary")
private WebElement signinLink;

@FindBy(css="h1[data-test-id='hero__headline']")
private WebElement linkedinHomePgHeaderTxt;

@FindBy(xpath = "//h1[@class='header__content__heading '][contains(.,'Sign in')]")
private WebElement signInHeaderText;

@FindBy(id = "username")
private WebElement emailEditbox;

@FindBy(name = "session_password")
private WebElement passwordEditbox;

@FindBy(xpath = "//button[@type='submit']")
private WebElement signInBtn;


String linkedinHomePgTitle="LinkedIn: Log In or Sign Up";

String linkedinLoginPgTitle="LinkedIn Login, Sign in | LinkedIn";


public void verifyLinkedinHomePgTitle() {
	log.debug("Verify the linkedin home page title:"+linkedinHomePgTitle);
	wait.until(ExpectedConditions.titleIs(linkedinHomePgTitle));
	Assert.assertEquals(driver.getTitle(), linkedinHomePgTitle);
	
}

public void verifyLinkedinHomePgHeaderText() throws InterruptedException {
	log.debug("Verify the linkedin Home page Header text element");
	Assert.assertTrue(isDisplayedElement(linkedinHomePgHeaderTxt));
}

public void verifyLinkedinLogo() throws InterruptedException {
	log.debug("Verify the linkedinlogo element");
	Assert.assertTrue(isDisplayedElement(linkedinLogo));
}

public void clickSigninLink() throws InterruptedException {
	log.debug("click on Linkedin Homepage Signin link");
	click(signinLink);
}

public void verifyLinkedinSigninPgTitle() {
	log.debug("Verify the linkedin Signin page title:"+linkedinLoginPgTitle);
	wait.until(ExpectedConditions.titleIs(linkedinLoginPgTitle));
	Assert.assertEquals(driver.getTitle(), linkedinLoginPgTitle);
	
}
public void verifyLinkedinSigninPgHeaderText() throws InterruptedException {
	log.debug("Verify the linkedin Signin page Header text element");
	Assert.assertTrue(isDisplayedElement(signInHeaderText));
}

public void clickSigninButton() throws InterruptedException {
	log.debug("click on Linkedin Signin page Signin Button");
	click(signInBtn);
}

public LinkedinLoggedinPage doLogin(String uname,String pwd) throws InterruptedException {
	log.debug("started executing the doLogin()...");
	log.debug("clear the content in the email editbox");
	clearText(emailEditbox);
	log.debug("type the "+uname+" in emaileditbox");
	sendKey(emailEditbox, uname);
	log.debug("clear the content in password editbox");
	clearText(passwordEditbox);
	log.debug("type the password in password editbox");
	sendKey(passwordEditbox, pwd);
	clickSigninButton();
	return new LinkedinLoggedinPage();
}



}
