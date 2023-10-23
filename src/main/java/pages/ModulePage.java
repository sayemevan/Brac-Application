package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tools.Logger;
import tools.WaitUtil;

public class ModulePage {
    private Logger logger = Logger.getInstance();
    private WebDriver driver;

    @FindBy(xpath = "//div[normalize-space() = 'Loading...' and @aria-hidden = 'false']")
    private WebElement loading;

    @FindBy(id = "logout")
    private WebElement logout;

    public ModulePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public boolean waitForLoading(){
        try {
            logger.info("Wait until visibility of loading sign");
            WaitUtil.waitUntilElementIsVisible(loading);

            logger.info("Wait until invisibility of loading sign");
            WaitUtil.waitUntilElementIsInvisible(loading);

            return true;
        } catch (Exception exception){
            exception.printStackTrace();
            return false;
        }

    }

    public LoginPage logout(){
        logger.info("Click on logout button");
        logout.click();
        return new LoginPage(this.driver);
    }
}
