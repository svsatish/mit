package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WidgetPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(WidgetPage.class);

    // Widget containers for each offer
    @FindBy(css = "div.widget-box")
    private List<WebElement> widgetBoxes;

    // All offer images (by id)
    @FindBy(id = "Revolving")
    private WebElement revolvingCardImage;

    @FindBy(id = "SetPAY")
    private WebElement payLaterImage;

    // All offer content blocks
    @FindBy(css = "div.offer-content.syf-offer")
    private List<WebElement> offerContents;

    // All "Learn how" links
    @FindBy(css = "a.syf-cta")
    private List<WebElement> learnHowLinks;

    // Informational text at the bottom
    @FindBy(xpath = "//div[@id='syf-promo']//span[contains(text(),'Select your preferred financing option')]")
    private WebElement infoText;

    public WidgetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("WidgetPage initialized");
    }

    /** Returns the number of financing widgets/offers displayed */
    public int getWidgetCount() {
        return widgetBoxes.size();
    }

    /** Returns the offer text for a given widget index (0-based) */
    public String getOfferText(int index) {
        if (index >= 0 && index < offerContents.size()) {
            return offerContents.get(index).getText();
        }
        logger.warn("Invalid widget index: {}", index);
        return null;
    }

    /** Clicks the "Learn how" link for a given widget index (0-based) */
    public void clickLearnHow(int index) {
        if (index >= 0 && index < learnHowLinks.size()) {
            logger.info("Clicking 'Learn how' link for widget index {}", index);
            learnHowLinks.get(index).click();
        } else {
            logger.warn("Invalid widget index for 'Learn how': {}", index);
        }
    }

    /** Returns the alt text of the offer image by widget index (0-based) */
    public String getOfferImageAltText(int index) {
        if (index == 0) return revolvingCardImage.getAttribute("alt");
        if (index == 1) return payLaterImage.getAttribute("alt");
        logger.warn("Invalid widget index for image alt text: {}", index);
        return null;
    }

    /** Returns the info text at the bottom of the widget */
    public String getInfoText() {
        return infoText.getText();
    }

    /** Clicks the offer image by id ("Revolving" or "SetPAY") */
    public void clickOfferImageById(String imageId) {
        WebElement img = null;
        if ("Revolving".equals(imageId)) img = revolvingCardImage;
        else if ("SetPAY".equals(imageId)) img = payLaterImage;
        if (img != null) {
            logger.info("Clicking offer image with id '{}'", imageId);
            img.click();
        } else {
            logger.warn("No image found with id '{}'", imageId);
        }
    }

    /** Clicks the "Learn how" link for a given offer name (e.g., "Pay Later") */
    public void clickLearnHowByOfferName(String offerName) {
        for (int i = 0; i < offerContents.size(); i++) {
            if (offerContents.get(i).getText().contains(offerName)) {
                clickLearnHow(i);
                return;
            }
        }
        logger.warn("No offer found with name '{}'", offerName);
    }
}