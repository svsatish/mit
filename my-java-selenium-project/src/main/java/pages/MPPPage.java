package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MPPPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(MPPPage.class);

    // Header and logo
    @FindBy(css = ".modal-header .zetailimg")
    private WebElement headerLogo;

    @FindBy(css = ".modal-header .modal-title")
    private WebElement headerTitle;

    // Amount entry
    @FindBy(css = ".amountEntryInput-text")
    private WebElement amountInput;

    @FindBy(css = ".amountEntryInput-dollar")
    private WebElement amountDollarLabel;

    @FindBy(css = ".amountEntryInput .pencil-icon")
    private WebElement pencilIcon;

    // Featured Offers section
    @FindBy(xpath = "//h1[contains(text(),'Financing Options Available Now')]")
    private WebElement financingOptionsHeader;

    @FindBy(css = ".availableSub-heading")
    private WebElement availableSubHeading;

    @FindBy(css = ".mpp-any-where-label")
    private WebElement enterAmountLabel;

    // Offer cards (both featured and other offers)
    @FindBy(css = ".borderNoneoffer.card")
    private List<WebElement> offerCards;

    // Offer titles (e.g., $21/mo for 48 months)
    @FindBy(css = ".borderNone.card-header .verticalCenter .testClass b")
    private List<WebElement> offerTitles;

    // "Learn How" buttons for each offer
    @FindBy(css = ".learmoreBtn .learnHow")
    private List<WebElement> learnHowButtons;

    // "Learn How" labels
    @FindBy(css = ".learmoreBtn span")
    private List<WebElement> learnHowLabels;

    // Offer expansion panels (details)
    @FindBy(css = ".accordion-collapse .card-body")
    private List<WebElement> offerDetailsBodies;

    // "View Less" buttons in expanded offer details
    @FindBy(css = ".viewExtraBtn.viewLess")
    private List<WebElement> viewLessButtons;

    // Existing cardholder info
    @FindBy(css = ".existingCard-holder span")
    private WebElement existingCardHolderInfo;

    // "PREQUALIFY FOR AVAILABLE OPTIONS" button
    @FindBy(css = ".prequaliBtn")
    private WebElement prequalifyButton;

    // Footer links
    @FindBy(id = "agreement")
    private WebElement usageAgreementLink;

    @FindBy(id = "onlinePrivacyPolicy")
    private WebElement privacyPolicyLink;

    @FindBy(id = "accessibility")
    private WebElement accessibilityLink;

    // Offer message at the bottom
    @FindBy(css = ".offer-msg")
    private WebElement offerMsg;

    public MPPPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("MPPPage initialized");
    }

    // --- Methods ---

    public String getHeaderTitle() {
        return headerTitle.getText();
    }

    public String getHeaderLogoSrc() {
        return headerLogo.getAttribute("src");
    }

    public void setAmount(String amount) {
        logger.info("Setting amount: {}", amount);
        amountInput.clear();
        amountInput.sendKeys(amount);
    }

    public String getAmount() {
        return amountInput.getAttribute("value");
    }

    public void clickPencilIcon() {
        pencilIcon.click();
    }

    public String getAvailableSubHeading() {
        return availableSubHeading.getText();
    }

    public int getOfferCount() {
        return offerCards.size();
    }

    public String getOfferTitle(int index) {
        if (index >= 0 && index < offerTitles.size()) {
            return offerTitles.get(index).getText();
        }
        return null;
    }

    public void clickLearnHow(int index) {
        if (index >= 0 && index < learnHowButtons.size()) {
            logger.info("Clicking 'Learn How' button for offer index {}", index);
            learnHowButtons.get(index).click();
        }
    }

    public String getOfferDetails(int index) {
        if (index >= 0 && index < offerDetailsBodies.size()) {
            return offerDetailsBodies.get(index).getText();
        }
        return null;
    }

    public void clickViewLess(int index) {
        if (index >= 0 && index < viewLessButtons.size()) {
            logger.info("Clicking 'View Less' button for offer index {}", index);
            viewLessButtons.get(index).click();
        }
    }

    public String getExistingCardHolderInfo() {
        return existingCardHolderInfo.getText();
    }

    public void clickPrequalifyButton() {
        logger.info("Clicking 'PREQUALIFY FOR AVAILABLE OPTIONS' button");
        prequalifyButton.click();
    }

    public void clickUsageAgreementLink() {
        usageAgreementLink.click();
    }

    public void clickPrivacyPolicyLink() {
        privacyPolicyLink.click();
    }

    public void clickAccessibilityLink() {
        accessibilityLink.click();
    }

    public String getOfferMsg() {
        return offerMsg.getText();
    }

    // Parameterized: Click "Learn How" by offer title text
    public boolean clickLearnHowByOfferTitle(String offerTitleText) {
        for (int i = 0; i < offerTitles.size(); i++) {
            if (offerTitles.get(i).getText().contains(offerTitleText)) {
                clickLearnHow(i);
                return true;
            }
        }
        logger.warn("No offer found with title containing '{}'", offerTitleText);
        return false;
    }
}