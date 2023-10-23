package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tools.Logger;
import tools.WaitUtil;

public class LoginPage {
    private Logger logger = new Logger();
    private WebDriver driver;

    @FindBy(id = "content")
    private WebElement bodyContent;

    @FindBy(id = "Username")
    private WebElement usernameField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(xpath = ".//button[normalize-space(text()) = 'Login']")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void waitForBodyContent(){
        logger.info("Wait for body content");
        WaitUtil.waitUntilElementIsVisible(bodyContent);
    }

    public ModulePage login(String username, String password) {
        logger.info("Input username");
        usernameField.sendKeys(username);

        logger.info("Input password");
        passwordField.sendKeys(password);

        logger.info("Wait until visibility of login button then click on it");
        WaitUtil.waitUntilElementIsVisible(loginBtn).click();

        logger.info("Wait until invisibility of login button");
        WaitUtil.waitUntilElementIsInvisible(loginBtn);

        return new ModulePage(driver);
    }

}

