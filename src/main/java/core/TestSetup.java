package core;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestSetup {
	
	static ExtentReports report;
	static ExtentTest test;
	protected static WebDriver driver;
	
	public static String getProperty(String key) {
		
		File configFile = new File("src/main/resources/runconfig/run.properties");
		
		try {
			
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);
			
			String property = props.getProperty(key);
			reader.close();
			
			return property;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ExtentTest getTest() {
		return test;
	}
	
	@BeforeClass
	public static void setup() {
		
		System.out.println("Before class"); // we can remove this line
		String browser = getProperty("browser");
		report = Report.startReports("Windows", browser);
	}

	@BeforeMethod
	public void beforeTest(Method method) {
		System.out.println("Before Test");
		String browser = getProperty("browser");
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			
			capabilities.setCapability("marionette", true);
			FirefoxOptions options = new FirefoxOptions();
			options.setCapability("marionette", true);
			driver = new FirefoxDriver(options);
			
		}else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.setCapability("InPrivate", true);
			driver = new EdgeDriver();
		}else if (browser.equalsIgnoreCase("firefox-esr")) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");
			File pathBinary = new File(getProperty("firefoxesrPath"));
			FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
			DesiredCapabilities desired = DesiredCapabilities.firefox();
			FirefoxOptions options = new FirefoxOptions();
			desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
			driver = new FirefoxDriver(options);
		}
		
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(getProperty("defaultTimeout")), TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		test = report.createTest(method.getName());
		TestListener.setDriver(driver);
		TestListener.setTest(test);
	}
	
	@AfterMethod
	public void afterTest() {
		
	}
	
	public static  WebDriver getDriver() {
		return driver;
	}
	
	@AfterClass
	public static void tearDown() {
		report.flush();
		driver.close();
	}
	
	protected void stepInfo(String message) {
		test.log(Status.INFO, "<font size='1'><b>" + message + "</b></font>");
	}
	
}
