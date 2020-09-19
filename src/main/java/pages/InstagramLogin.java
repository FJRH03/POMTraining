package pages;

import org.openqa.selenium.By;

public class InstagramLogin extends BasePage {
	
	By signUpButton = By.xpath("//span[contains(text(),'Sign up')]");
	
	
	public void clickSignUpButton() {
		clickElemet(signUpButton);
	}

}
