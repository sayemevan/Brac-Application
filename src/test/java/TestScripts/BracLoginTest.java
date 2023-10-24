package TestScripts;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ModulePage;
import tools.DriverFactory;
import tools.Logger;

public class BracLoginTest extends BaseTest{

    private WebDriver driver;

    @BeforeTest
    public void commonActions(){
        driver = DriverFactory.getDriver();
        driver.get(properties.getValue("url"));

    }

    @Test(priority = 1, description = "Login Scenario with valid username and password.")
    public void loginTest() {

        LoginPage loginPage;
        ModulePage modulePage;

        //init login page
        loginPage = new LoginPage(driver);

        //Wait for fully loaded login page
        loginPage.waitForBodyContent();

        //login and go to module page
        modulePage = loginPage.login(properties.getValue("username"), properties.getValue("password"));

        Assert.assertTrue(true, "Login successfully");
        //wait for fully loaded module page
        Assert.assertTrue(modulePage.waitForLoading(), "Module page visible");

        Logger.getInstance().info("Login Test Success ");


    }

    @Test(priority = 2)
    public void logoutTest(){
        System.out.println("Logout Test");
    }
}
