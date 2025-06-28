package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MITPage {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(MITPage.class);

    @FindBy(xpath = "//span[contains(text(),'Merchant Type')]/following-sibling::select")
    private WebElement merchantTypeDropdown;

    @FindBy(xpath = "//span[contains(text(),'Flow Type')]/following-sibling::select")
    private WebElement flowTypeDropdown;

    public MITPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("MITPage initialized");
    }

    public void selectMerchantType(String merchantType) {
        logger.info("Selecting Merchant Type: {}", merchantType);
        new Select(merchantTypeDropdown).selectByVisibleText(merchantType);
    }

    public void selectFlowType(String flowType) {
        logger.info("Selecting Flow Type: {}", flowType);
        new Select(flowTypeDropdown).selectByVisibleText(flowType);
    }
}