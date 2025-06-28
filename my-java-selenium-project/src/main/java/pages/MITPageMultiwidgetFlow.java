package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MITPageMultiwidgetFlow {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(MITPageMultiwidgetFlow.class);

    @FindBy(xpath = "//span[contains(text(),'SYF Partner ID')]/following-sibling::input")
    private WebElement syfPartnerIdInput;

    @FindBy(xpath = "//span[contains(text(),'Trans Amounts')]/following-sibling::input")
    private WebElement transAmountsInput;

    @FindBy(xpath = "//span[contains(text(),'MPP From Anywhere')]/following-sibling::input")
    private WebElement mppFromAnywhereInitialAmountInput;

    @FindBy(xpath = "//span[contains(text(),'MultiWidget Tags')]/following-sibling::textarea")
    private WebElement multiWidgetTagsTextarea;

    @FindBy(xpath = "//span[contains(text(),'Ecart Tag Rules')]/following-sibling::textarea")
    private WebElement ecartTagRulesTextarea;

    @FindBy(xpath = "//span[contains(text(),'Client Transaction Id')]/following-sibling::input")
    private WebElement clientTransactionIdInput;

    @FindBy(xpath = "//span[contains(text(),'Site Code')]/following-sibling::input")
    private WebElement siteCodeInput;

    @FindBy(xpath = "//span[contains(text(),'Partner Code')]/following-sibling::input")
    private WebElement partnerCodeInput;

    @FindBy(xpath = "//span[contains(text(),'Child Merchant Number')]/following-sibling::input")
    private WebElement childMerchantNumberInput;

    @FindBy(xpath = "//span[contains(text(),'Child SYF PartnerId')]/following-sibling::input")
    private WebElement childSyfPartnerIdInput;

    @FindBy(xpath = "//span[contains(text(),'Browser Friendly Payment Token')]/following-sibling::input")
    private WebElement browserFriendlyPaymentTokenInput;

    @FindBy(xpath = "//span[contains(text(),'Authorization Number')]/following-sibling::input")
    private WebElement authorizationNumberInput;

    @FindBy(xpath = "//span[contains(text(),'Operator Id')]/following-sibling::input")
    private WebElement operatorIdInput;

    @FindBy(xpath = "//button[contains(text(),'Start MULTIWIDGET Flow')]")
    private WebElement startMultiwidgetFlowButton;

    @FindBy(xpath = "//button[contains(text(),'Populate Default Data')]")
    private WebElement populateDefaultDataButton;

    public MITPageMultiwidgetFlow(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("MITPageMultiwidgetFlow initialized");
    }

    public void setSyfPartnerId(String value) {
        logger.debug("Setting SYF Partner ID: {}", value);
        syfPartnerIdInput.clear();
        syfPartnerIdInput.sendKeys(value);
    }

    public void setTransAmounts(String value) {
        logger.debug("Setting Trans Amounts: {}", value);
        transAmountsInput.clear();
        transAmountsInput.sendKeys(value);
    }

    public void setMultiWidgetTags(String value) {
        logger.debug("Setting MultiWidget Tags: {}", value);
        multiWidgetTagsTextarea.clear();
        multiWidgetTagsTextarea.sendKeys(value);
    }

    // ... Add similar setters for other fields as needed ...

    public void clickStartMultiwidgetFlow() {
        logger.info("Clicking Start MULTIWIDGET Flow button");
        startMultiwidgetFlowButton.click();
    }

    public void clickPopulateDefaultData() {
        logger.info("Clicking Populate Default Data button");
        populateDefaultDataButton.click();
    }
}