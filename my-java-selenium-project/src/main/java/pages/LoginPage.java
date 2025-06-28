package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    @FindBy(id = "formBasicEmail")
    private WebElement userNameInput;

    @FindBy(id = "formBasicPassword")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(@class,'btn-primary') and text()='Login']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("LoginPage initialized");
    }

    public void enterUserName(String userName) {
        logger.debug("Entering user name: {}", userName);
        userNameInput.clear();
        userNameInput.sendKeys(userName != null ? userName : "");
    }

    public void enterPassword(String password) {
        logger.debug("Entering password");
        passwordInput.clear();
        passwordInput.sendKeys(password != null ? password : "");
    }

    public void clickLogin() {
        logger.info("Clicking Login button");
        loginButton.click();
    }

    public MITPage login(String userName, String password) {
        logger.info("Performing login");
        enterUserName(userName);
        enterPassword(password);
        clickLogin();
        return new  MITPage(driver);
    }
}