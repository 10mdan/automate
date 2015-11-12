package com.walmart.assignment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class Helper extends RemoteWebElement {
  private WebDriver _driver;

  public Helper() {

  }
  public Helper(WebDriver driver) {
    _driver = driver;
  }


  public void open(String link) throws Exception {
    getDriver().get(link);
  }

  public void type (String txt, String locator) {
    WebElement element = findElement(locator);
    element.sendKeys(txt);
  }
  public boolean isElementPresent (String locator) {
    WebElement element = findElement(locator);
    return element.isDisplayed();
  }

  public void click(String locator) throws Exception {
    WebElement element = findElement(locator);
    element.click();
    waitForPage();
  }

  public void clickAndWait(String locator) throws Exception {
    WebElement element = findElement(locator);
    element.click();
    waitForPage();
  }

  public void jsclick(String locator) throws Exception {
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", findElement(locator));
  }

  public void scrollIntoViewAndClick(String locator) throws Exception {
    JavascriptExecutor jse = (JavascriptExecutor)getDriver();
    jse.executeScript("scroll(0, 250)");
    waitForElement(locator);
    jsclick(locator);
  }

  public WebElement findElement(String locator) {
    System.out.println(locator);
    try {
      _driver = getDriver();
    } catch (Exception e) {
      // TODO Auto-generated catch block
    }
    WebElement element=null;
    if (locator.startsWith("xpath=")) {
      System.out.println(locator.substring("xpath=".length()));
     element = _driver.findElement(By.xpath(locator.substring("xpath=".length())));
     System.out.println(element.isDisplayed());
    }
    if (locator.startsWith("id=")) {
      element = _driver.findElement(By.id(locator.substring("id=".length())));
     }
    if (locator.startsWith("class=")) {
      element = _driver.findElement(By.className(locator.substring("class=".length())));
     }
    if (locator.startsWith("css=")) {
      element = _driver.findElement(By.cssSelector(locator.substring("css=".length())));
     }
    return element;
  }

  protected WebDriver getDriver() throws Exception {
    try {
      return _driver != null ? _driver : SessionManager.getDriver();
    } catch (Exception e) {
      throw new Exception("Error getting driver: " + e.getMessage());
    }
  }

  /**
   * Maximizes the window
   */
  @SuppressWarnings("unchecked")
  public void maximizeWindow() {
    String script = "return [window.screen.availWidth, window.screen.availHeight];";
    List<Long> dimensions = (List<Long>) getJavascriptExecutor().executeScript(script);
    resizeWindow(dimensions.get(0).intValue(), dimensions.get(1).intValue());
  }

  public JavascriptExecutor getJavascriptExecutor(){
    JavascriptExecutor js = null;
    try {
      if (getDriver() instanceof JavascriptExecutor) {
        js = (JavascriptExecutor) getDriver();
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
    }
    return js;
  }

  public void resizeWindow(int width, int height) {
    // Resize browser window for mobile web
    try {
      getDriver().manage().window().setPosition(new Point(0, 0));
      getDriver().manage().window().setSize(new Dimension(width, height));
    } catch (Exception e) {
      // TODO Auto-generated catch block
    }
  }

  public void refreshAndWait() throws Exception {
      getDriver().navigate().refresh();
      waitForPage();
  }

  public String getText(String locator) {
    WebElement element = findElement(locator);
    return element.getText();

  }

  public void moveTo(String locator) throws Exception {
    WebElement element = findElement(locator);
    Actions action = new Actions(getDriver());
    action.moveToElement(element).perform();
    Thread.sleep(10000);
  }

  public void waitForPage() throws Exception {
    int defaultTimeout = 120000;
    waitForPage(defaultTimeout);
  }

  public void waitForPage(int timeout) throws Exception {
    try {
      new WebDriverWait(getDriver(), timeout).until(new Predicate<WebDriver>() {
        @Override
        public boolean apply(WebDriver driver) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            // TODO Auto-generated catch block
          }
          return isPageLoaded();
        }
      });
    } catch (Exception e) {
      throw new Exception("Timed out waiting for the page after " + timeout);
    }
  }

  public boolean isPageLoaded() {
    boolean pageLoad = false;
    JavascriptExecutor js = getJavascriptExecutor();
    String pageState = (String) js.executeScript("return document.readyState;");
    if (pageState.equals("complete")) {
      pageLoad = true;
    }
    return pageLoad;
  }

  public void waitForElement( String locator) throws Exception {
    int timeout = 120000;
    try {

      new WebDriverWait(getDriver(), timeout).until(new Predicate<WebDriver>() {
        @Override
        public boolean apply(WebDriver driver) {
          WebElement element = null;
          try {
            element = findElement(locator);
            Thread.sleep(1000);
            System.out.println("waiting for element"+ element.isEnabled());

          } catch (InterruptedException e) {

          }
          return element.isEnabled();

        }
      });
    } catch (Exception e) {
      throw new Exception("Element is not present even after " + timeout);
    }
  }

  public void typeEachKey(String text, String locator) throws Exception {
    WebElement element = findElement(locator);
    for (int i = 0; i < text.length(); ++i) {
      element.sendKeys(Character.toString(text.charAt(i)));
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new Exception("Error calling typeEachKey: " + e.getMessage());
      }
    }
  }

}


