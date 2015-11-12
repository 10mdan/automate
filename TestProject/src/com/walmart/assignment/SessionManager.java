package com.walmart.assignment;


import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


public class SessionManager {
   static WebDriver driver= null;

  public static WebDriver startWebSession(Method testMethod, Map<String, Object> customCapabilities) throws Exception {
    try {
//      System.out.println("Starts the Desktop Web Browser session on " + ConfigManager.getInstance().getProperty("browser"));
      DesiredCapabilities capabilities = getWebCaps(testMethod, customCapabilities);
      driver = getWebDriver(capabilities);
      new Helper(driver).maximizeWindow();
      driver.manage().window().setSize(new Dimension(414, 736));

    } catch (Exception e) {

        throw new Exception("Unable to create Session due to " + e.getMessage());
    }
    return driver;
  }


  private static DesiredCapabilities getWebCaps(Method testMethod, Map<String, Object> customCapabilities) throws Exception{
    //String browserType = ConfigManager.getInstance().getProperty("browser");
    String browserType= "chrome";
    DesiredCapabilities capabilities = null;
    switch (browserType) {
      case "chrome":
        ChromeOptions options = createChromeOptions();
        capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        try {
          String os = null;
          String chromeDriver = "chromedriver";
          if (SystemUtils.IS_OS_MAC_OSX) {
            os = "mac";
          } else if (SystemUtils.IS_OS_LINUX) {
            os = "linux";
            chromeDriver += System.getProperty("sun.arch.data.model");
          } else if (SystemUtils.IS_OS_WINDOWS) {
            os = "windows";
          }
          System.setProperty("webdriver.chrome.driver"
              , "src/resources/chromedriver");
} catch (Exception e) {
          throw new Exception("Unable to find chromdriver");
        }
        break;
      case "safari":
        //capabilities = DesiredCapabilities.internetExplorer();
        break;
    }

    return capabilities;
  }

  private static ChromeOptions createChromeOptions() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--user-agent=" + "Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4");
    return chromeOptions;
  }

  public static void killSession(){
    driver.quit();
  }

  private static WebDriver getWebDriver(DesiredCapabilities capabilities) throws Exception{
    try {
      //String browserType = ConfigManager.getInstance().getProperty("browser");
      String browserType = "chrome";
        switch (browserType) {
          case "safari":
            return new SafariDriver();
          case "chrome":
            return new ChromeDriver(createChromeOptions());
          default:
            throw new Exception("Invalid browser: " + browserType);
        }
      } catch (MalformedURLException e) {
      throw new Exception("Unable to get web driver instance: " + e.getMessage());
    }
  }

  public static WebDriver getDriver() {
    return driver;
  }
}
