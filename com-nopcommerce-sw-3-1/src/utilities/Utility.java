package utilities;

import browsertesting.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Utility extends BaseTest {

    /**
     * This method will click on element
     */
    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    /**
     * This method will get text from element
     */
    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    /**
     * This method will send text to element
     */
    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }
    //
    public String sendAttributeValue(By by, String attr) {
        return driver.findElement(by).getAttribute(attr);
    }

    //clear text from element
    public void clearElement1(By by) {
        driver.findElement(by).clear();
    }
    //************* select class method **************** /

    /**
     * This method will select the option by visible text
     */
    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }

    //This method will give list of element
    public List<WebElement> listOfWebElementsList(By by) {
        return driver.findElements(by);
    }

    //This method will hovering mouse
    public void mouseHover(By by) {
        Actions actions = new Actions(driver);
        driver.findElement(by);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }

    //This method will match actual and expected message
//    public void assertMatch(String expected, String actual) {
//        Assert.assertEquals("Not navigate to page", expected, actual);
//    }


}
