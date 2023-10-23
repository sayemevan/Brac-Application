package listeners;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import report.ExtentManager;
import tools.DriverFactory;
import tools.Logger;

import java.util.Objects;

import static report.ExtentTestManager.getTest;

public class TestListener implements ITestListener {

    private Logger logger = Logger.getInstance();

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        logger.info("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", DriverFactory.getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger.info("I am in onFinish method " + iTestContext.getName());
        //Do tier down operations for ExtentReports reporting!
        ExtentManager.extentReports.flush();
    }
    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger.info(getTestMethodName(iTestResult) + " test is starting.");
    }
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger.info(getTestMethodName(iTestResult) + " test is succeed.");
        //ExtentReports log operation for passed tests.
        getTest().log(Status.PASS, "Test passed");
    }
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger.info(getTestMethodName(iTestResult) + " test is failed.");
        WebDriver driver = DriverFactory.getDriver();
        //Take base64Screenshot screenshot for extent reports
        String base64Screenshot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log and screenshot operations for failed tests.
        getTest().log(Status.FAIL, iTestResult.getThrowable().getMessage(),
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger.info(getTestMethodName(iTestResult) + " test is skipped.");
        //ExtentReports log operation for skipped tests.
        getTest().log(Status.SKIP, "Test Skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        logger.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
        getTest().log(Status.WARNING, "Test Partially passed");
    }
}
