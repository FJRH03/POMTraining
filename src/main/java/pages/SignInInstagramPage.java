package pages;

import org.openqa.selenium.By;

public class SignInInstagramPage extends BasePage
{

	By emailField = By.xpath("//input[contains(@name,'emailOrPhone')]");
	By fullNameField = By.xpath("//input[contains(@name,'fullName')]");
	By userNameField = By.xpath("//input[contains(@name,'username')]");
	By passwordField = By.xpath("//input[contains(@name,'password')]");
	By signUpButton = By.xpath("//button[contains(text(),'Sign up')]");
	
	public void enterEmail(String email)
	{
		waitForElementToBePresent(emailField).clear();
		enterText(emailField, email);
	}
	
	public void enterFullName(String fullName)
	{
		waitForElementToBePresent(fullNameField).clear();
		enterText(fullNameField, fullName);
	}
	
	public void enterUsername(String username)
	{
		waitForElementToBePresent(userNameField).clear();
		enterText(userNameField, username);
	}
	
	public void enterPassword(String password)
	{
		waitForElementToBePresent(passwordField).clear();
		enterText(passwordField, password);
	}
	
	public void clickSingUpButton() {
		clickElemet(signUpButton);
	}
	
}
