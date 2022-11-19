package com.restapi.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.restapi.testcase.BaseClass;

public class ExtentListener extends BaseClass implements ITestListener {

	ExtentSparkReporter html;
	ExtentReports report;
	ExtentTest test;
	ThreadLocal<ExtentTest> extenttestthread = new ThreadLocal<ExtentTest>();

	public void ConfigReports() {

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		String reportname = "RestApi" + timestamp + ".html";
		String reportpath = System.getProperty("user.dir") + "//Reports//" + reportname;

		html = new ExtentSparkReporter(reportpath);
		html.config().setDocumentTitle("RestApi Result");
		html.config().setReportName("Api Test Result");
		html.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(html);
		report.setSystemInfo("Java Version", System.getProperty("java.version"));
		report.setSystemInfo("User Name", System.getProperty("user.name"));
		report.setSystemInfo("Operating System", System.getProperty("os.name"));

	}

	@Override
	public void onStart(ITestContext context) {

		ConfigReports();
		System.out.println("Test Class Invoked");
	}

	@Override
	public void onFinish(ITestContext context) {

		System.out.println("Test Class Completed");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("Test Started:" + result.getName());
		test = report.createTest(result.getName());
		extenttestthread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		System.out.println("Test Success :" + result.getName());
		extenttestthread.get().log(Status.PASS,
				MarkupHelper.createLabel("Test Passed :" + result.getName(), ExtentColor.GREEN));
	}

	@Override
	public void onTestFailure(ITestResult result) {

		System.out.println("Test Failed :" + result.getName());
		extenttestthread.get().log(Status.FAIL,
				MarkupHelper.createLabel("Test Passed :" + result.getName(), ExtentColor.RED));
		extenttestthread.get().fail(result.getThrowable());

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		System.out.println("Test Skipped :" + result.getName());
		extenttestthread.get().log(Status.SKIP,
				MarkupHelper.createLabel("Test Passed :" + result.getName(), ExtentColor.YELLOW));
	}

}
