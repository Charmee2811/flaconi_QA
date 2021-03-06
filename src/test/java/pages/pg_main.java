package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.DeleteSession;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class pg_main extends testBase {

	By model_dialogBox = By.id("uc-banner-centered");
	By link_parfum = By.cssSelector("a[title='Parfum']");
	By btn_dialogCancel = By.id("uc-btn-accept-banner");
	By link_selectPerfume = By.cssSelector("li[id='30150229']"); // advance :- dynamic_get this id from test data
	By btn_addToCart = By.cssSelector("button.button-primary loaderbox-trigger");
	By lbl_price = By.cssSelector(("span.price price-sale"));
	By model_dialoAaddToCart = By.cssSelector("div.columns[id='htmlData']");
	By btn_continueToCart = By.cssSelector("btn.button-secondary pull-left close");
	By lbl_perfumeName = By.cssSelector("a.brand[title=Mehr von Giorgio Armani]");
			//By.cssSelector("div.product-name");
	By btn_checkout = By.cssSelector("a.button-primary[id='to-checkout-btn-1']");
	By lbl_perfumeName_cartPage = By.cssSelector("div.details");

	By link_navigationBar = By.cssSelector(".nav-main * > a[data-webtrekk-link-id = 'header.nav']");
	//a[data-webtrekk-link-id=header.nav] //ul.nav-main * //

	public static String  price = "";
    String perfumeName ="";

	public void clickPopUp()
	{
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(model_dialogBox));
		driver.findElement(btn_dialogCancel).click();
	}
	public void addToCart() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.elementToBeClickable(link_parfum));
			driver.findElement(link_parfum).click();
			String currURL = driver.getCurrentUrl();
			Assert.assertTrue(currURL.contains("parfum"));

			if (driver.findElement(link_selectPerfume).isDisplayed()) {
				driver.findElement(link_selectPerfume).click();
				wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currURL)));//wait till url change

				if (driver.findElement(btn_addToCart).isEnabled()) {
					price = driver.findElement(lbl_price).getText();
					perfumeName = driver.findElement(lbl_perfumeName).getText().trim();
					driver.findElement(btn_addToCart).click();

					wait.until(ExpectedConditions.visibilityOfElementLocated(model_dialoAaddToCart));
					Assert.assertTrue(true, "Item added to add to cart dialog");
					driver.findElement(btn_continueToCart).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(btn_checkout));

				} else
					Assert.fail("perfume not avaiable to select");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void verifyPerfumeAdded()
	{
		if (driver.findElement(lbl_perfumeName_cartPage).isDisplayed()) {
			if (driver.findElement(lbl_perfumeName_cartPage).getText().contains(perfumeName))
				Assert.assertTrue(true, "perfume added in cart");
			else
				Assert.fail("Name of perfume do not match with item added in cart ");
		}
	}
	public void verifyHeaderLinks() {
		//driver.findElement(By.cssSelector("ul > li:nth-child(1)"))
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String currUrl ="";
		String currLink ="";
		List<WebElement> allLinks = driver.findElements(link_navigationBar);
		//List<WebElement> allLinks = driver.findElements(By.tagName("a"));
		System.out.println("***DEBUG_LOGS:  Links are " + allLinks);
		System.out.println("***DEBUG_LOGS:  Count of all links are " + allLinks.size());
		try {
			if(allLinks.size()>0) {
				for (WebElement link : allLinks) {
					currLink = link.getAttribute("title");
					link.click();
					wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(config_getproperty("URL"))));
					currUrl = driver.getCurrentUrl();
					if (currUrl.contains(currLink))
						Assert.assertTrue(true, currLink + "   is clicked and is working");
					else
						Assert.fail(currLink + "    is clicked and navigated to wrong page. Link not working");
				}
			}
			else
				Assert.fail("no links present ");
		}
     catch(Exception e)
	 {
	 	e.printStackTrace();
	 }
	}

	public boolean retryingFindClick(By by) {
		boolean result = false;
		int attempts = 0;
		while(attempts < 2) {
			try {
				driver.findElement(by).click();
				result = true;
				break;
			} catch(Exception e) {
				e.getMessage();
			}
			attempts++;
		}
		return result;
	}
}
