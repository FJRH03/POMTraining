package suite.instagram;

import org.testng.annotations.Test;

import core.TestSetup;
import pages.InstagramLogin;
import pages.SignInInstagramPage;

public class CreateUserTest extends TestSetup{
	
	@Test
	public void testCreateInstagramUser() throws InterruptedException 
	{
		InstagramLogin login = new InstagramLogin();
		SignInInstagramPage signIn = new SignInInstagramPage();
		
		String email = "marcelaherrera825372@gmail.com";
		String fullName = "Marcela Herrera";
		String username = "marcelaherrera825372";
		String password = "cacadevaca";

		login.launchApp();
		login.clickSignUpButton();
		signIn.enterEmail(email);
		signIn.enterFullName(fullName);
		signIn.enterUsername(username);
		signIn.enterPassword(password);
		signIn.clickSingUpButton();
		
		Thread.sleep(10000);
		
	}

}
