package core;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class CommonMethods {

	protected WebDriver driver;
	int defaultTimeout;

	public CommonMethods() {
		driver = TestSetup.getDriver();
		defaultTimeout = Integer.parseInt(TestSetup.getProperty("defaultTimeout"));
	}

	public void clickElemet(By locator) {
		if (driver instanceof FirefoxDriver) {
			waitForPageLoaded();
			WebElement element = waitForElementToBeClickable(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element);
		} else {
			waitForPageLoaded();
			waitForElementToBeClickable(locator).click();
		}
	}

	public void enterText(By locator, String text) {
		waitForElementToBeClickable(locator).sendKeys(text);
	}

	protected WebElement waitForElementToBeClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}

	protected WebElement waitForElementToBePresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}

	protected WebElement waitForElementToBeVisible(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}
	
	public void scrollToElement(By locator)
	{
		WebElement element = waitForElementToBePresent(locator);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void scrollToElement(WebElement element)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	/***
	 * Returns a list of the text from a list of elements
	 * 
	 * @param locator
	 * @return
	 */

	protected List<String> getTextFromElements(By locator) {

		if (driver instanceof FirefoxDriver) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		List<WebElement> elements = driver.findElements(locator);
		List<String> textList = new ArrayList<String>();
		for (WebElement element : elements) {
			String text = element.getText();
			if (!text.isEmpty()) {
				textList.add(text);
			}
		}

		return textList;
	}

	/**
	 * Use JavaScript executor to wait for page to load
	 */

	public void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		try {
			WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
			wait.until(expectation);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}
	
	
	public static void logSuccess(String message) {
		TestSetup.getTest().log(Status.PASS, message);
	}
	
	public static void logFailure(String message) {
		TestSetup.getTest().log(Status.FAIL, message);
	}
	
	public static void logInfo(String message) {
		TestSetup.getTest().log(Status.INFO, message);
	}
	
	public static void logWarning(String message) {
		TestSetup.getTest().log(Status.WARNING, message);
	}
	
}
