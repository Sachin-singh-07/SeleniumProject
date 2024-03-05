package CommonUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListnerImplementation implements ITestListener {

	ExtentReports report;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("Test execution is started");
		String methodname = result.getMethod().getMethodName();
		Reporter.log(methodname + "TEstscript start", true);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("Test execution is passed");
		String methodname = result.getMethod().getMethodName();
		Reporter.log(methodname + "TEstscript passed", true);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("Test execution is fail");

		String message = result.getThrowable().toString();
		String methodname = result.getMethod().getMethodName();
		Reporter.log(methodname + message + "TEstscript failer", true);
		


	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("Test execution is skipped");
		String methodname = result.getMethod().getMethodName();
		Reporter.log(methodname + "TEstscript skipped", true);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
//		System.out.println("to fined the execution");
		String methodname = result.getMethod().getMethodName();
		Reporter.log(methodname + "TEstscript fail without ", true);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		String methodname = result.getMethod().getMethodName();
		Reporter.log(methodname + "TEstscript faild start", true);

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
//		System.out.println("Test start the execution");
//		String methodname = result.getMethod().getMethodName();
//		Reporter.log("TEstscript start", true);
		JavaUtil jutil = new JavaUtil();
		// Create the object of ExtentSparkReporter
		// Use ExtentSparkReporter class just to configure extent
		ExtentSparkReporter reporter = new ExtentSparkReporter(
				"./extentreport/report" + jutil.getRandomNumber() + ".html");

		reporter.config().setDocumentTitle("VtigerCRM");
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setReportName("Ajasyy");

		// Use ExtentReports to generate extentreport

		report = new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("OS", "Window");
		report.setSystemInfo("Browser", "chrome");
		report.setSystemInfo("Chromeversion", "121");
		report.setSystemInfo("Author", "sachinn");

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
//		String methodname = result.getMethod().getMethodName();
//		Reporter.log("TEstscript finesed", true);
		report.flush();
	}

}
