package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class AccountNumberLookupPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(AccountNumberLookupPage.class);

    // --- Locators for Account Lookup fields ---
    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "zipcode")
    private WebElement zipCodeInput;

    @FindBy(id = "ssn4Digit")
    private WebElement ssn4DigitInput;

    @FindBy(id = "submitAnl1Id")
    private WebElement continueButton;

    // --- Locators for Card Information fields ---
    @FindBy(id = "cardNumber")
    private WebElement cardNumberInput;

    @FindBy(id = "expDate")
    private WebElement expDateInput;

    @FindBy(id = "cvv")
    private WebElement cvvInput;

    // --- Error message locators ---
    @FindBy(id = "firstNameError")
    private WebElement firstNameError;

    @FindBy(id = "lastNameError")
    private WebElement lastNameError;

    @FindBy(id = "zipcodeError")
    private WebElement zipCodeError;

    @FindBy(id = "ssn4DigitError")
    private WebElement ssn4DigitError;

    @FindBy(id = "cardNumberError")
    private WebElement cardNumberError;

    @FindBy(id = "expDateError")
    private WebElement expDateError;

    @FindBy(id = "cvvError")
    private WebElement cvvError;

    // --- Constructor ---
    public AccountNumberLookupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        logger.info("AccountNumberLookupPage initialized");
    }

    // --- Account Lookup Methods ---
    public void enterFirstName(String firstName) {
        logger.debug("Entering first name: {}", firstName);
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName != null ? firstName : "");
    }

    public void enterLastName(String lastName) {
        logger.debug("Entering last name: {}", lastName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName != null ? lastName : "");
    }

    public void enterZipCode(String zipCode) {
        logger.debug("Entering zip code: {}", zipCode);
        zipCodeInput.clear();
        zipCodeInput.sendKeys(zipCode != null ? zipCode : "");
    }

    public void enterSSN4Digit(String ssn4) {
        logger.debug("Entering last 4 digits of SSN: {}", ssn4);
        ssn4DigitInput.clear();
        ssn4DigitInput.sendKeys(ssn4 != null ? ssn4 : "");
    }

    // --- Card Info Methods ---
    public void enterCardNumber(String cardNumber) {
        logger.debug("Entering card number: {}", cardNumber);
        cardNumberInput.clear();
        cardNumberInput.sendKeys(cardNumber != null ? cardNumber : "");
    }

    public void enterExpDate(String expDate) {
        logger.debug("Entering expiration date: {}", expDate);
        expDateInput.clear();
        expDateInput.sendKeys(expDate != null ? expDate : "");
    }

    public void enterCVV(String cvv) {
        logger.debug("Entering CVV: {}", cvv);
        cvvInput.clear();
        cvvInput.sendKeys(cvv != null ? cvv : "");
    }

    // --- Action Methods ---
    public void clickContinue() {
        logger.info("Clicking Continue button");
        continueButton.click();
    }

    // --- Composite Methods ---
    public void fillAccountLookupForm(String firstName, String lastName, String zipCode, String ssn4) {
        logger.info("Filling Account Lookup form");
        enterFirstName(firstName);
        enterLastName(lastName);
        enterZipCode(zipCode);
        enterSSN4Digit(ssn4);
    }

    public void fillCardInfoForm(String cardNumber, String expDate, String cvv) {
        logger.info("Filling Card Info form");
        enterCardNumber(cardNumber);
        enterExpDate(expDate);
        enterCVV(cvv);
    }

    // --- Error Message Getters ---
    public String getFirstNameError() {
        return firstNameError.getText();
    }

    public String getLastNameError() {
        return lastNameError.getText();
    }

    public String getZipCodeError() {
        return zipCodeError.getText();
    }

    public String getSSN4DigitError() {
        return ssn4DigitError.getText();
    }

    public String getCardNumberError() {
        return cardNumberError.getText();
    }

    public String getExpDateError() {
        return expDateError.getText();
    }

    public String getCVVError() {
        return cvvError.getText();
    }

    // --- Utility: Wait for error message (optional) ---
    public void waitForErrorMessage(WebElement errorElement) {
        logger.debug("Waiting for error message to be visible");
        wait.until(ExpectedConditions.visibilityOf(errorElement));
    }
}