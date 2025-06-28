package tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import pages.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class BuyFlow extends BasePage {
    private Properties props;
    private MITPage mitPage;
    private CheckoutFormPage checkoutFormPage;

    @BeforeClass
    public void setUpTest() throws IOException {
        super.setUp();
        props = new Properties();
        props.load(new FileInputStream("src/test/resources/testdata.properties"));
    }

    @Test
    public void testBuyFlow() {
        // Login
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        // Land on MITPage and select Checkflowtype
        mitPage = new MITPage(driver);
        mitPage.selectFlowType("CHECKOUT");

        // Generate token
        mitPage.clickGenerateToken();

        // Enter 1000 in transaction amount
        checkoutFormPage = new CheckoutFormPage(driver);
        checkoutFormPage.setAmount("1000");

        // Click on Start Checkout button
        checkoutFormPage.clickStartCheckoutFlow();
    }

    @AfterClass
    public void tearDownTest() {
        super.tearDown();
    }
}