package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class testBase {
    public static WebDriver driver = null;
    public static boolean isBrowserOpened = false;
    public static boolean isURLOpened = false;

       // Code to load configuration property file
    public static String config_getproperty(String propertyname) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//config//config.properties");
            prop.load(input);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        // Return the property value
        return prop.getProperty(propertyname);
    }

//loading test data
    public static String testData_getproperty(String propertyname) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//testData//testData.properties");
            // load a properties file
            prop.load(input);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // Return the property value
        return prop.getProperty(propertyname);
    }

    //Code for opening the browser
    public void openBrowser() {
        if (!isBrowserOpened) {
            if (config_getproperty("browserType").equals("MOZILLA")){
                //DesiredCapabilities caps = new FirefoxOptions().setProfile(new FirefoxProfile()).addTo(DesiredCapabilities.firefox());
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\geckodriver.exe");
                driver = new FirefoxDriver();
                System.out.println("MOZZILA Browser opened");
            }
            else if (config_getproperty("browserType").equals("IE")) {
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                System.out.println("IE Browser opened");
            } else if (config_getproperty("browserType").equals("CHROME")) {
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments(config_getproperty("LanguageType"));
                driver = new ChromeDriver();
                System.out.println("CHROME Browser opened");
            }
            isBrowserOpened = true;
            driver.manage().window().maximize();
            System.out.println("window Maximised");
        }
    }

    public boolean openURL() {
        driver.get(config_getproperty("URL")); //can mention in config properity
        try {
            System.out.println("URL Opened");
        }
        catch (Throwable t) {
            System.out.println("URL NOt Opening");
            return false;
        }
        isURLOpened = true;
        return isURLOpened;
    }

    /*public static WebElement getObject(String xpathKey) {
        try {
            new FluentWait<WebDriver>(driver).withTimeout(25000, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathKey)));
            WebElement x = driver.findElement(By.xpath(xpathKey));
            return x;
        }
        catch (Throwable t) {
            return null;
        }
    }*/

    public void switchWin() {
        // String winHandle = driver.getWindowHandles().toString();
        for (String winHandle1 : driver.getWindowHandles())
            driver.switchTo().window(winHandle1);
    }

    // Code to close Browser
    public void closeBrowser() {
        driver.quit();
        isBrowserOpened = false;
    }

}
