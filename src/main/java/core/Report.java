package core;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report {

	static // builds a new report using the html template
	ExtentHtmlReporter htmlReporter;

	static ExtentReports extent;
	ExtentTest test;

	static String reportFolder;

	public static ExtentReports startReports(String OS, String browser) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		String timeStamp = dateFormat.format(new Date());
		reportFolder = "src/main/resources/reports/" + timeStamp + "/";
		new File(reportFolder).mkdirs();

		htmlReporter = new ExtentHtmlReporter(reportFolder + "testReport.html");

		// initialize ExtentReports and attach the HTMLReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// To add system or environment info by using the setSystemInfo method
		extent.setSystemInfo("OS", OS);
		extent.setSystemInfo("Browser", browser);

		// congiguration items to change the look and feel
		// add content, manage tets etc
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report Title");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

		return extent;

	}

	
	public static String getReportFolder() {
		return reportFolder;
	}
	
}
