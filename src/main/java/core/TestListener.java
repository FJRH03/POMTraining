package core;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

public class TestListener implements ITestListener, IInvokedMethodListener {

	private static ExtentTest test;
	private static WebDriver driver;
	
	public static void setTest(ExtentTest externalTest)
	{
		test = externalTest;
	}
	
	public static void setDriver(WebDriver externalDriver)
	{
		driver = externalDriver;
	}
	
	@Override
	public void onFinish(ITestContext arg0)
	{
		System.out.printf("%s finished %n", arg0.getName());
	}
	
	@Override
	public void onStart(ITestContext arg0) {
		
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		String timeStamp = dateFormat.format(new Date());
		
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		String filename = arg0.getName().replaceAll("\\s+", "") + timeStamp + ".png";
		File destFile = new File(Report.getReportFolder() + filename);
		
		try {
			FileUtils.copyFile(SrcFile, destFile);
			test.fail(arg0.getName()).addScreenCaptureFromPath(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		driver.quit();
	}
	
	
	@Override
	public void onTestStart(ITestResult arg0) {
		System.out.println("Test Started");
	}
	
	@Override
	public void onTestSuccess(ITestResult arg0) {
		test.pass(arg0.getName());
		driver.quit();
	}
	
	
}
