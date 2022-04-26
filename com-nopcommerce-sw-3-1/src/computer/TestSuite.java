package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void testName() {
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Computers ')]"));
        clickOnElement(By.xpath("//li[@class='inactive']//a[contains(text(),'Desktops ')]"));

        List<WebElement> listsAllDefault = listOfWebElementsList(By.xpath("//div[@class='prices']"));

        selectByVisibleTextFromDropDown(By.xpath("//select[@id='products-orderby']"), "Price: Low to High");

        List<WebElement> listsAllAfterSort = listOfWebElementsList(By.xpath("//div[@class='prices']"));

        if (listsAllDefault != listsAllAfterSort) {
            System.out.println("Pass");
        }


    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {
        testName();

        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Computers ')]"));
        clickOnElement(By.xpath("//li[@class='inactive']//a[contains(text(),'Desktops ')]"));

        //  selectByVisibleTextFromDropDown(By.xpath("//select[@id='products-orderby']"), "Price: Low to High");

        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));

        String actualMessage = getTextFromElement(By.xpath("//h2[@class='product-title']//a[text()='Build your own computer']"));
        String expectedMessage = "Build your own computer";
        Assert.assertEquals("Not navigate to correct page", expectedMessage, actualMessage);

        selectByVisibleTextFromDropDown(By.xpath("//select[@name='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");
        selectByVisibleTextFromDropDown(By.xpath("//select[@name='product_attribute_2']"), "8GB [+$60.00]");

        clickOnElement(By.xpath("//input[@value='7']"));//400 gb
        clickOnElement(By.xpath("//input[@value='9']"));//premium
        // clickOnElement(By.xpath("//input[@id='product_attribute_5_10']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));

        String actualPrice = getTextFromElement(By.xpath("//div[@class='product-price']//span[text()='$1,475.00']"));
        String expectedPrice = "$1,475.00";//
        Assert.assertEquals("Not selected correct features", expectedPrice, actualPrice);

        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));

        String actualMsg = getTextFromElement(By.xpath("//p[text()='The product has been added to your ']"));
        String expectedMsg = "The product has been added to your shopping cart";
        Assert.assertEquals("Product not added to shopping cart", expectedMsg, actualMsg);

        clickOnElement(By.xpath("//span[@class='close']"));

        //2.14 Mousehover on shopping cart
        mouseHover(By.xpath("//a[@class='ico-cart']"));
        clickOnElement(By.xpath("//button[text()='Go to cart']"));
        //mouseHover(By.xpath("//button[@class='button-1 cart-button']"));

        //2.15 Verifying message shopping cart
        String actualMsg1 = getTextFromElement(By.xpath("//h1[text()='Shopping cart']"));
        String expectedMsg1 = "Shopping cart";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg1, actualMsg1);

        //2.16 Change the Qty to "2" and Click on "Update shopping cart"
        clearElement1(By.xpath("//input[@class='qty-input']"));
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.xpath("//button[@id='updatecart']"));

        //2.17 Verify the Total"$2,950.00"
        String actualMsg2 = getTextFromElement(By.xpath("//td[@class='subtotal']"));
        String expectedMsg2 = "$2,950.00";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg2, actualMsg2);

        //2.18 click on checkbox “I agree with the terms of service” and click on check out(2.19)
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));

        //2.20 Verify the Text “Welcome, Please Sign In!”
        String actualMsg3 = getTextFromElement(By.xpath("//h1[text()='Welcome, Please Sign In!']"));
        String expectedMsg3 = "Welcome, Please Sign In!";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg3, actualMsg3);

        //2.21 Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[@class='button-1 checkout-as-guest-button']"));

        //2.22 Fill the all mandatory field
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_FirstName']"), "Ram");// sending first name
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_LastName']"), "Shyam");// sending last name
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Email']"), "bam@gmail.com");// sending email

        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "Belgium");// selecting country

        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "Rom");// sending city name
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "12, B");// sending Address 1
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "100-252");// sending zip/postal code
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "999-990-000");// sending phone number

        //2.23 Click on “CONTINUE”
        clickOnElement(By.xpath("//div[@id='billing-buttons-container']//button[@class='button-1 new-address-next-step-button']"));//click on continue button

        //2.24 Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.xpath("//input[@value='Next Day Air___Shipping.FixedByWeightByTotal']"));

        //2.25 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        //2.26 Select Radio Button “Credit Card” and click on it
        clickOnElement(By.xpath("//input[@value='Payments.Manual']"));
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));

        //2.27 Select “Master card” From Select credit card dropdown
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='CreditCardType']"), "Master card");

        //2.28 Fill all the details
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Ram shyam");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5555555555554444");

        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireMonth']"), "04");
        selectByVisibleTextFromDropDown(By.xpath("//select[@id='ExpireYear']"), "2025");

        sendTextToElement(By.xpath("//input[@id='CardCode']"), "420");//send CVV

        //2.29 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        //2.30 Verify “Payment Method” is “Credit Card”
        String actualMsg4 = getTextFromElement(By.xpath("//span[contains(text(),'Credit Card')]"));
        String expectedMsg4 = "Credit Card";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg4, actualMsg4);

        //2.32 Verify “Shipping Method” is “Next Day Air”
        String actualMsg5 = getTextFromElement(By.xpath("//li[@class='shipping-method']//span[contains(text(),'Next Day Air')]"));
        String expectedMsg5 = "Next Day Air";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg5, actualMsg5);

        //2.33 Verify Total is “$2,950.00”
        String actualMsg6 = getTextFromElement(By.xpath("//td[@class='cart-total-right']//span[@class='value-summary']//strong"));
        String expectedMsg6 = "$2,950.00";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg6, actualMsg6);

        //2.34 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[@class='button-1 confirm-order-next-step-button']"));

        //2.35 Verify the Text “Thank You”
        String actualMsg7 = getTextFromElement(By.xpath("//h1[text()='Thank you']"));
        String expectedMsg7 = "Thank you";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg7, actualMsg7);

        //2.36 Verify the message “Your order has been successfully processed!”
        String actualMsg8 = getTextFromElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        String expectedMsg8 = "Your order has been successfully processed!";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg8, actualMsg8);

        //2.37 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 order-completed-continue-button']"));

        //2.37 Verify the text “Welcome to our store”
        String actualMsg9 = getTextFromElement(By.xpath("//h2[text()='Welcome to our store']"));
        String expectedMsg9 = "Welcome to our store";
        Assert.assertEquals("Not navigate to shopping cart page", expectedMsg9, actualMsg9);


    }
    @After
    public void tearDown(){
        closeBrowser();
    }

}
