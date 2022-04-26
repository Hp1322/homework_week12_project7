package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class TopMenuTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu){
      //  System.out.println("//ul[@class='top-menu notmobile']//a[contains(text(),'"+menu+"')]");
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'"+menu+"')]"));
    }
    @Test
    public void verifyPageNavigation(){
        List<String> topMenu = new ArrayList<>();

        topMenu.add("Computers");
        topMenu.add("Electronics");
        topMenu.add("Apparel");
        topMenu.add("Digital downloads");
        topMenu.add("Books");
        topMenu.add("Jewelry");
        topMenu.add("Gift Cards");

        for (int i = 0; i < topMenu.size(); i++){
           // System.out.println(topMenu.size());
            selectMenu(topMenu.get(i));
            String actualMessage = getTextFromElement(By.xpath("//h1[contains(text(),'"+topMenu.get(i)+"')]"));
            Assert.assertEquals("not navigate to page", topMenu.get(i), actualMessage);

        }

    }
    @After
    public void tearDown(){
        closeBrowser();
    }


}
