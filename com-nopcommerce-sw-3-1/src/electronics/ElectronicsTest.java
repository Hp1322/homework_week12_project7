package electronics;

import com.google.common.base.Verify;
import javafx.scene.control.Tab;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Mouse;
import utilities.Utility;

import java.sql.Timestamp;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void textVerified() {
        //1.1 Mouse Hover on “Electronics” Tab & Mouse Hover on “Cell phones” and click
        mouseHover(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Electronics ']"));
        mouseHover(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Cell phones ']"));

        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Cell phones ']"));

        //  1.3 Verify the text “Cell phones”
        String actualMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Cell phones')]"));
        String expectedMessage = "Cell phones";
        Assert.assertEquals("Not navigate to page", expectedMessage, actualMessage);
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        // 2.1 Mouse Hover on “Electronics”Tab then hover on “Cell phones” and click
        mouseHover(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Electronics ']"));
        mouseHover(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Cell phones ']"));

        Thread.sleep(300);
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Cell phones ']"));

        String actualMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Cell phones')]"));
        String expectedMessage = "Cell phones";
        Assert.assertEquals("Not navigate to page", expectedMessage, actualMessage);

        // 2.4 Click on List View Tab and product name “Nokia Lumia 1020” link(2.5)
       // clickOnElement(By.xpath("//a[@data-viewmode='list']"));
        Thread.sleep(300);
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[3]/div[1]/div[2]/h2[1]/a[1]"));//not working

        //2.6 Verify the text “Nokia Lumia 1020”
        String actualMessage1 = getTextFromElement(By.xpath("//h1[text()='Nokia Lumia 1020']"));
        String expectedMessage1 = "Nokia Lumia 1020";
        Assert.assertEquals("Not navigate to page", expectedMessage1, actualMessage1);

        //2.7 Verify the price “$349.00”
        String actualMessage2 = getTextFromElement(By.xpath("//div[@class='product-price']//span[text()=' $349.00 ']"));
        String expectedMessage2 = "$349.00";
        Assert.assertEquals("Not navigate to page", expectedMessage2, actualMessage2);

        //2.8 Change quantity to 2 and click on “ADD TO CART” tab
        clearElement1(By.xpath("//input[@aria-label='Enter a quantity']"));//clearing field
        sendTextToElement(By.xpath("//input[@aria-label='Enter a quantity']"), "2");//sending quantity 2

        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));//click

        //2.10 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        String actualMessage3 = getTextFromElement(By.xpath("//p[text()='The product has been added to your ']"));
        String expectedMessage3 = "The product has been added to your shopping cart";
        Assert.assertEquals("Not navigate to page", expectedMessage3, actualMessage3);

        clickOnElement(By.xpath("//span[@class='close']"));//click on cross button

        //2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHover(By.xpath("//span[@class='cart-label']"));
        clickOnElement(By.xpath("//button[@class='button-1 cart-button']"));

       //2.12 Verify the message "Shopping cart"
        String actualMessage4 = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        String expectedMessage4 = "Shopping cart";
        Assert.assertEquals("Not navigate to page", expectedMessage4, actualMessage4);

        //2.13 Verify the quantity is 2
        String actualMessage5 = sendAttributeValue(By.xpath("//input[@class='qty-input']"), "value");
        String expectedMessage5 = "2";
        Assert.assertEquals("Not navigate to page", expectedMessage5, actualMessage5);

        //2.14 Verify the Total $698.00
        String actualMessage6 = getTextFromElement(By.xpath("//span//strong[text()='$698.00']"));
        String expectedMessage6 = "$698.00";
        Assert.assertEquals("Not navigate to page", expectedMessage6, actualMessage6);

        //2.15 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@name='termsofservice']"));

        //2.16 Click on checkout
        clickOnElement(By.xpath("//button[@class='button-1 checkout-button']"));

        //2.17 Verify the Text “Welcome, Please Sign In!”
        String actualMessage7 = getTextFromElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
        String expectedMessage7 = "Welcome, Please Sign In!";
        Assert.assertEquals("Not navigate to page", expectedMessage7, actualMessage7);

        //2.18 Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[@class='button-1 register-button']"));

        //2.19 Verify the text “Register”
        String actualMessage8 = getTextFromElement(By.xpath("//h1[text()='Register']"));
        String expectedMessage8 = "Register";
        Assert.assertEquals("Not navigate to page", expectedMessage8, actualMessage8);

        //2.20 Fill the mandatory fields
        sendTextToElement(By.xpath("//input[@id='FirstName']"), "Ram");//sending first name
        sendTextToElement(By.xpath("//input[@id='LastName']"), "Shyam");//sending last name

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String emailAddress = "ram"+timestamp.getTime()+"@domain.com";

        sendTextToElement(By.xpath("//input[@id='Email']"),emailAddress);//sending email

        sendTextToElement(By.xpath("//input[@id='Password']"), "123456");//sending password
        sendTextToElement(By.xpath("//input[@id='ConfirmPassword']"), "123456");//sending confirm password

        //2.21 Click on “REGISTER” Button
        clickOnElement(By.xpath("//button[@id='register-button']"));

        //2.22 Verify the message “Your registration completed”
        String actualMessage9 = getTextFromElement(By.xpath("//div[text()='Your registration completed']"));
        String expectedMessage9 = "Your registration completed";
        Assert.assertEquals("Not navigate to page", expectedMessage9, actualMessage9);

        clickOnElement(By.xpath("//a[text()='Continue']"));// click on continue tab

        //2.24 Verify the text “Shopping cart”
        String actualMessage10 = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        String expectedMessage10 = "Shopping cart";
        Assert.assertEquals("Not navigate to page", expectedMessage10, actualMessage10);

        //2.25 click on checkbox “I agree with the terms of service” and click on checkout
        clickOnElement(By.xpath("//input[@name='termsofservice']"));
        clickOnElement(By.xpath("//button[@class='button-1 checkout-button']"));

        //2.27 Fill the Mandatory fields
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), " ");// sending first name
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), " ");// sending last name
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), " ");// sending email

        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "Belgium");// selecting country

        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "Rom");// sending city name
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "12, B");// sending Address 1
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "100-252");// sending zip/postal code
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "999-990-000");// sending phone number

        //2.28 Click on “CONTINUE”
        clickOnElement(By.xpath("//div[@id='billing-buttons-container']//button[@class='button-1 new-address-next-step-button']"));//click on continue button

        //2.29 Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.xpath("//input[@value='2nd Day Air___Shipping.FixedByWeightByTotal']"));

        //2.30 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //2.31 Select Radio Button “Credit Card” and click on it
        clickOnElement(By.xpath("//input[@value='Payments.Manual']"));
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));

        //2.32 Select “visa” From Select credit card dropdown
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Visa");

        //2.33 Fill all the details
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Ram shyam");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "4111111111111111");

        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "04");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2025");

        sendTextToElement(By.xpath("//input[@id='CardCode']"), "420");//send CVV

        //2.34 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        //2.35 Verify “Payment Method” is “Credit Card”
        String actualMsg11 = getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]"));
        String expectedMsg11 = "Credit Card";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg11, actualMsg11);

        //2.36 Verify “Shipping Method” is “2nd Day Air”
        String actualMsg12 = getTextFromElement(By.xpath("//li[@class='shipping-method']//span[contains(text(),'2nd Day Air')]"));
        String expectedMsg12 = "2nd Day Air";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg12, actualMsg12);

        //2.37 Verify Total is “$698.00”
        String actualMsg13 = getTextFromElement(By.xpath("//td[@class='cart-total-right']//span[@class='value-summary']//strong"));
        String expectedMsg13 = "$698.00";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg13, actualMsg13);

        //2.38 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']"));

        //2.39 Verify the Text “Thank You”
        String actualMsg14 = getTextFromElement(By.xpath("//h1[text()='Thank you']"));
        String expectedMsg14 = "Thank you";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg14, actualMsg14);

        //2.40 Verify the message “Your order has been successfully processed!”
        String actualMsg15 = getTextFromElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        String expectedMsg15 = "Your order has been successfully processed!";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg15, actualMsg15);

        //2.41 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));

        //2.42 Verify the text “Welcome to our store”
        String actualMsg16 = getTextFromElement(By.xpath("//h2[text()='Welcome to our store']"));
        String expectedMsg16 = "Welcome to our store";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg16, actualMsg16);

        //2.43 Click on “Logout” link
        clickOnElement(By.xpath("//a[text()='Log out']"));

      //  2.44 Verify the URL is “https://demo.nopcommerce.com/”

        String expectedMsg17 = "https://demo.nopcommerce.com/";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg17, baseUrl);
    }
    @After
    public void tearDown(){
        closeBrowser();
    }
}
