package pages;

import core.CommonMethods;
import core.TestSetup;

public class BasePage extends CommonMethods{
	
	public void launchApp() {
		String url = TestSetup.getProperty("appURL");
		driver.navigate().to(url);
	}

}
