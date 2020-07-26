package util;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.testBase;


public class Extra_testCases extends testBase {


	public int listSize =0;
	@BeforeTest
	public void preAction()
	{
		openBrowser();
		openURL() ;
		Assert.assertTrue(driver.getTitle().contains(config_getproperty("title")));
	}
	@Test
	public void Scenario_1() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver,50);

		//to handle model content popup
		//getObject("modelContent");
		//driver.switchTo().activeElement();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uc-banner-centered")));
		if(driver.findElement(By.id("uc-banner-centered")).isDisplayed()) {
			driver.findElement(By.id("uc-btn-accept-banner")).click();

			WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div[2]/div/header/nav/ul/li[2]/a")));
element1.click();



			/*synchronized (driver)
			{
				driver.wait(2000);
			}
			/*WebElement ele = driver.findElement(By.cssSelector("a[title=Parfum]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", ele);*/
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("top-search")));
			//WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.left[name=q]")));
			//Actions action = new Actions(driver);
			//action.moveToElement(element1);

			//element1.click();
			//driver.findElement(By.id("top-search")).click();

			//driver.findElement(By.cssSelector("input.left[name=q]")).sendKeys("abc");
			//wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(config_getproperty("URL")))); //waiting till url is changed

			//driver.findElement(By.cssSelector("div.product-item-box[data-webtrekk-link-id= product-list.5.80049191-C]")).click();

			//WebElement admin = driver.findElement(By.cssSelector("a[title=Parfum]"));
			//((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", admin);

			//new Actions(driver).moveToElement(admin).perform();

			//WebElement userManagement = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("a[title=Top Marken]"))));
			//userManagement.click();

		}

  }




  @AfterTest
  public void cleanup()
  {
	  //closeBrowser();
	  //Advance:- screenshots saving //extent Reports implementation
  }
}

	