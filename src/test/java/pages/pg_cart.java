package pages;

import org.openqa.selenium.By;
import org.testng.Assert;

public class pg_cart extends testBase{

    By lbl_cartPrice = By.cssSelector("span.price-sale");

    //Boolean isPriceSame =false;
    public void comparePrice()
    {
        try{
          if(driver.getCurrentUrl().contains("warenkorb"))
          {
              String cartPrice = driver.findElement(lbl_cartPrice).getText();
              if (pg_main.price != null && pg_main.price.length() > 0 ) {  //removing* from price
                  pg_main.price = pg_main.price.substring(0, pg_main.price.length() - 1);
              }
              Assert.assertEquals(pg_main.price,cartPrice);
              /*if(cartPrice.equals(pg_main.price))
                 isPriceSame =true;
              else
                  isPriceSame=false;*/
          }
          else
              Assert.fail("wrong page loaded");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
