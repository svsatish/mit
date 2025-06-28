package com.yourcompany.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SidebarMenu {

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(SidebarMenu.class);

    public SidebarMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        logger.info("SidebarMenu initialized.");
    }

    @FindBy(xpath = "//div[@id='mySidebar']//a[contains(@class, 'drawer-menu_closebtn')]")
    private WebElement closeButton;

    @FindBy(xpath = "//div[@id='mySidebar']//a[text()='Find Status API']")
    private WebElement findStatusApiLink;

    @FindBy(xpath = "//div[@id='mySidebar']//a[text()='Find Status Apigee API']")
    private WebElement findStatusApigeeApiLink;

    @FindBy(xpath = "//div[@id='mySidebar']//a[contains(text(), 'MPP From Anywhere')]")
    private WebElement mppFromAnywhereLink;

    @FindBy(xpath = "//div[@id='mySidebar']//a[contains(text(), 'Widget Page')]")
    private WebElement widgetPageLink;

    @FindBy(xpath = "//div[@id='mySidebar']//a[contains(text(), 'Generic Widget')]")
    private WebElement genericWidgetLink;

    @FindBy(xpath = "//div[@id='mySidebar']//a[text()='POS DBuy Tip']")
    private WebElement posDbuyTipLink;

    @FindBy(xpath = "//div[@id='mySidebar']//a[text()='Logout']")
    private WebElement logoutLink;

    // --- Actions ---

    public void closeSidebar() {
        logger.info("Clicking sidebar close button");
        closeButton.click();
    }

    public void goToFindStatusApi() {
        logger.info("Navigating to 'Find Status API'");
        findStatusApiLink.click();
    }

    public void goToFindStatusApigeeApi() {
        logger.info("Navigating to 'Find Status Apigee API'");
        findStatusApigeeApiLink.click();
    }

    public void goToMppFromAnywhere() {
        logger.info("Navigating to 'MPP From Anywhere Email Link'");
        mppFromAnywhereLink.click();
    }

    public void goToWidgetPage() {
        logger.info("Navigating to 'Widget Page'");
        widgetPageLink.click();
    }

    public void goToGenericWidget() {
        logger.info("Navigating to 'Generic Widget'");
        genericWidgetLink.click();
    }

    public void clickPosDbuyTip() {
        logger.info("Clicking 'POS DBuy Tip'");
        posDbuyTipLink.click();
    }

    public void logout() {
        logger.info("Clicking 'Logout'");
        logoutLink.click();
    }
}
