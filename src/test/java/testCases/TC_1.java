package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.DeleteSession;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.pg_cart;
import pages.pg_main;


public class TC_1 extends pages.testBase {

	pg_main obj_pgMain = new pg_main();
	pg_cart obj_pgCart = new pg_cart();

	@BeforeTest
	public void preAction()
	{
		openBrowser();
		openURL() ;
		Assert.assertTrue(driver.getTitle().contains(config_getproperty("title")));
	}
	@Test(priority=2)
	public void Scenario_1() throws InterruptedException
	{
		obj_pgMain.addToCart();
		obj_pgMain.verifyPerfumeAdded();
    }

    @Test(priority = 3 ,dependsOnMethods ="Scenario_1" )
    public void scenario_2()
    {
	  obj_pgCart.comparePrice();
    }
    @Test(priority=1)
	public void scenerio_3()
	{
		//pg_main obj_pgMain = new pg_main();
		obj_pgMain.clickPopUp();
		obj_pgMain.verifyHeaderLinks();
	}

  @AfterTest
  public void cleanup()
  {
	  closeBrowser();
	  //Advance:- screenshots saving //extent Reports implementation
  }
}

	