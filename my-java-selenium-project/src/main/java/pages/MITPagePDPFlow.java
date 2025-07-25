package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MITPagePDPFlow {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(MITPagePDPFlow.class);

    @FindBy(xpath = "//span[contains(text(),'SYF Partner ID')]/following-sibling::input")
    private WebElement syfPartnerIdInput;

    @FindBy(xpath = "//span[contains(text(),'Trans Amount')]/following-sibling::input")
    private WebElement transAmountInput;

    @FindBy(xpath = "//span[contains(text(),'PromoOverRideFlag')]/following-sibling::select")
    private WebElement promoOverrideFlagDropdown;

    @FindBy(xpath = "//span[contains(text(),'MPP From Anywhere')]/following-sibling::input")
    private WebElement mppFromAnywhereInitialAmountInput;

    @FindBy(xpath = "//span[contains(text(),'Promotional Tags')]/following-sibling::textarea")
    private WebElement promotionalTagsTextarea;

    @FindBy(xpath = "//span[contains(text(),'Product Info(eCart Only)')]/following-sibling::textarea")
    private WebElement productInfoTextarea;

    @FindBy(xpath = "//span[contains(text(),'Child SYF PartnerId')]/following-sibling::input")
    private WebElement childSyfPartnerIdInput;

    @FindBy(xpath = "//span[contains(text(),'Partner Code')]/following-sibling::input")
    private WebElement partnerCodeInput;

    @FindBy(xpath = "//span[contains(text(),'Child Merchant Number')]/following-sibling::input")
    private WebElement childMerchantNumberInput;

    @FindBy(xpath = "//span[contains(text(),'OfferNumber1')]/following-sibling::input")
    private WebElement offerNumber1Input;

    @FindBy(xpath = "//span[contains(text(),'Client Transaction Id')]/following-sibling::input")
    private WebElement clientTransactionIdInput;

    @FindBy(xpath = "//span[contains(text(),'Site Code')]/following-sibling::input")
    private WebElement siteCodeInput;

    @FindBy(xpath = "//span[contains(text(),'Browser Friendly Payment Token')]/following-sibling::input")
    private WebElement browserFriendlyPaymentTokenInput;

    @FindBy(xpath = "//span[contains(text(),'Authorization Number')]/following-sibling::input")
    private WebElement authorizationNumberInput;

    @FindBy(xpath = "//span[contains(text(),'Operator Id')]/following-sibling::input")
    private WebElement operatorIdInput;

    @FindBy(xpath = "//button[contains(text(),'Start PDP Flow')]")
    private WebElement startPDPFlowButton;

    @FindBy(xpath = "//button[contains(text(),'Populate Default Data')]")
    private WebElement populateDefaultDataButton;

    public MITPagePDPFlow(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("MITPagePDPFlow initialized");
    }

    public void setSyfPartnerId(String value) {
        logger.debug("Setting SYF Partner ID: {}", value);
        syfPartnerIdInput.clear();
        syfPartnerIdInput.sendKeys(value);
    }

    public void setTransAmount(String value) {
        logger.debug("Setting Trans Amount: {}", value);
        transAmountInput.clear();
        transAmountInput.sendKeys(value);
    }

    public void selectPromoOverrideFlag(String value) {
        logger.debug("Selecting PromoOverRideFlag: {}", value);
        new Select(promoOverrideFlagDropdown).selectByVisibleText(value);
    }

    public void setPromotionalTags(String value) {
        logger.debug("Setting Promotional Tags: {}", value);
        promotionalTagsTextarea.clear();
        promotionalTagsTextarea.sendKeys(value);
    }

    // ... Add similar setters for other fields as needed ...

    public void clickStartPDPFlow() {
        logger.info("Clicking Start PDP Flow button");
        startPDPFlowButton.click();
    }

    public void clickPopulateDefaultData() {
        logger.info("Clicking Populate Default Data button");
        populateDefaultDataButton.click();
    }
}