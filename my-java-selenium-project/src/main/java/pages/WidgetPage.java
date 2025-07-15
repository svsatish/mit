package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsumerOfferPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(ConsumerOfferPage.class);

    // All radio buttons for offers
    @FindBy(xpath = "//input[@type='radio' and @offertype]")
    private List<WebElement> offerRadioButtons;

    // Offer containers (for possible further validation)
    @FindBy(css = "div.col-md-12.listContent.ifToggle")
    private List<WebElement> offerContainers;

    // Continue button at the bottom
    @FindBy(id = "offersContbtnBtn")
    private WebElement continueButton;

    // Confirm Selections & Submit Payment (multi-offer)
    @FindBy(id = "multiOffersContbtnBtn")
    private WebElement confirmSelectionsButton;

    // Activate Card button (card activation)
    @FindBy(id = "cardActivationBtn")
    private WebElement activateCardButton;

    // Confirm & Submit Payment (review confirmation)
    @FindBy(id = "reviewConfirmationD2dBtnMob")
    private WebElement confirmAndSubmitPaymentButton;

    public ConsumerOfferPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("ConsumerOfferPage initialized");
    }

    /**
     * Selects the offer radio button based on the given offerType attribute.
     * @param offerType The value of the offerType attribute to match (e.g., "EPNI", "WPDI").
     * @return true if found and selected, false otherwise.
     */
    public boolean selectOfferByOfferType(String offerType) {
        logger.info("Selecting offer radio button with offerType: {}", offerType);
        for (WebElement radio : offerRadioButtons) {
            String attr = radio.getAttribute("offertype");
            if (attr != null && attr.equalsIgnoreCase(offerType)) {
                if (!radio.isSelected()) {
                    radio.click();
                    logger.info("Radio button with offerType '{}' selected.", offerType);
                }
                return true;
            }
        }
        logger.warn("No radio button found with offerType: {}", offerType);
        return false;
    }

    /**
     * Selects the offer radio button based on the given tenure and offerType.
     * @param tenure The tenure attribute value (e.g., "12", "48").
     * @param offerType The offerType attribute value.
     * @return true if found and selected, false otherwise.
     */
    public boolean selectOfferByTenureAndOfferType(String tenure, String offerType) {
        logger.info("Selecting offer radio button with tenure: {} and offerType: {}", tenure, offerType);
        for (WebElement radio : offerRadioButtons) {
            String attrType = radio.getAttribute("offertype");
            String attrTenure = radio.getAttribute("tenure");
            if (attrType != null && attrTenure != null &&
                attrType.equalsIgnoreCase(offerType) &&
                attrTenure.equals(tenure)) {
                if (!radio.isSelected()) {
                    radio.click();
                    logger.info("Radio button with tenure '{}' and offerType '{}' selected.", tenure, offerType);
                }
                return true;
            }
        }
        logger.warn("No radio button found with tenure: {} and offerType: {}", tenure, offerType);
        return false;
    }

    /**
     * Clicks the Continue button (single offer flow).
     */
    public void clickContinue() {
        logger.info("Clicking Continue button");
        if (continueButton.isEnabled()) {
            continueButton.click();
        } else {
            logger.warn("Continue button is disabled.");
        }
    }

    /**
     * Clicks the Confirm Selections & Submit Payment button (multi-offer flow).
     */
    public void clickConfirmSelections() {
        logger.info("Clicking Confirm Selections & Submit Payment button");
        if (confirmSelectionsButton.isEnabled()) {
            confirmSelectionsButton.click();
        } else {
            logger.warn("Confirm Selections button is disabled.");
        }
    }

    /**
     * Clicks the Activate Card button (card activation flow).
     */
    public void clickActivateCard() {
        logger.info("Clicking Activate Card button");
        if (activateCardButton.isEnabled()) {
            activateCardButton.click();
        } else {
            logger.warn("Activate Card button is disabled.");
        }
    }

    /**
     * Clicks the Confirm & Submit Payment button (review confirmation flow).
     */
    public void clickConfirmAndSubmitPayment() {
        logger.info("Clicking Confirm & Submit Payment button");
        if (confirmAndSubmitPaymentButton.isEnabled()) {
            confirmAndSubmitPaymentButton.click();
        } else {
            logger.warn("Confirm & Submit Payment button is disabled.");
        }
    }

    /**
     * Gets the number of available offers.
     */
    public int getOfferCount() {
        return offerRadioButtons.size();
    }

    /**
     * Gets the offer name for a given offerType.
     */
    public String getOfferNameByOfferType(String offerType) {
        for (WebElement radio : offerRadioButtons) {
            String attr = radio.getAttribute("offertype");
            if (attr != null && attr.equalsIgnoreCase(offerType)) {
                return radio.getAttribute("offername");
            }
        }
        return null;
    }
}