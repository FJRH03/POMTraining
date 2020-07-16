package pages;

import org.openqa.selenium.By;

public class FBLoginPage extends BasePage {
	
	By emailField = By.id("email");
	By pswField = By.id("pass");
	By loginBtn = By.id("u_0_b");
	
	public void enterEmail(String mail) {
		enterText(emailField, mail);
	}
	
	public void enterPassword(String psw) {
		enterText(pswField, psw);
	}
	
	public void clickSingIn() {
		clickElemet(loginBtn);
	}
	
	// add more methods e.g. sing on
	

}
