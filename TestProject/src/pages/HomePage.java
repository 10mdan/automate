package pages;

import com.walmart.assignment.Helper;

public class HomePage {

  public final String MENU_BUTTON="css=i.wmicon.wmicon-menu";
  public final String SIGNIN_MENU_BUTTON = "css=span.nav-main-login-text.js-nav-main-login-text";
  public final String BASE_URL = "http://mobile.walmart.com";
  public final String SEARCH_TEXTBAR = "id=search";
  public final String POP_UP_WINDOW = "xpath=//button[@class='Modal-closeButton hide-content display-block-m js-modal-close']";
  public final String DROP_DOWN_ITEM = "xpath=//span[@class='tt-suggestions']/div[1]/a[2]";
  public final String SHIPABLEITEM = "xpath=//li[contains(text(),'FREE shipping')]";
  public final String IPHONE_COLOR = "xpath=//label[@for='product-swatch-actual_color-0']/span";



  public void goToSignIn() throws Exception{
    Helper elemHelper  = new Helper();
    elemHelper.waitForElement(MENU_BUTTON);

    elemHelper.clickAndWait(MENU_BUTTON);
    elemHelper.waitForElement(SIGNIN_MENU_BUTTON);

    elemHelper.clickAndWait(SIGNIN_MENU_BUTTON);
  }

}
