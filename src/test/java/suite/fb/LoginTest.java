package suite.fb;

import org.testng.annotations.Test;

import core.TestSetup;
import pages.FBLoginPage;

public class LoginTest extends TestSetup{

	@Test
	public static void loginToFB() 
	{
		
		FBLoginPage login = new FBLoginPage();
		
		String email = TestSetup.getProperty("email");
		String emailpsw = TestSetup.getProperty("emailpsw");
		
		login.launchApp();
		login.enterEmail(email);
		login.enterPassword(emailpsw);
		login.clickSingIn();
		System.out.println("Login Success!");
		
	}
}
