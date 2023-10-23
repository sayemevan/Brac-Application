package TestScripts;

import enums.FilePaths;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import tools.DriverFactory;
import tools.PropertiesUtil;

import static report.ExtentTestManager.startTest;

public class BaseTest {

    public PropertiesUtil properties;

    @BeforeSuite
    public void init_Driver(ITestContext context){
        properties = new PropertiesUtil(FilePaths.CONFIG_FILE.getPath());
        new DriverFactory().init_driver(properties.getValue("browser"));
    }

    @BeforeMethod
    public  void beforeMethod(ITestResult context) {
        startTest(context.getMethod().getMethodName(), context.getMethod().getDescription());
    }

    @AfterSuite
    public void tear_Down(ITestContext context){
        DriverFactory.getDriver().quit();
    }
}
