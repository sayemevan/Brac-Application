package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/Spark.html");
        sparkReporter.config().setReportName("Sample Extent Report");
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("App Name", "Brac App");
        extentReports.setSystemInfo("Author", "STL");
        return extentReports;
    }
}
