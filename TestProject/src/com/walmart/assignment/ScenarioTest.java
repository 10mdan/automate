package com.walmart.assignment;



import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import pages.SignInPage;

public class ScenarioTest extends BaseTest {
  Helper _elemHelper = new Helper();
  CartPage _cartPage = new CartPage();
  CheckoutPage _checkoutPage = new CheckoutPage();
  HomePage _homePage = new HomePage();
  SignInPage signin = new SignInPage();

  @Test
  public void TestScenario() throws Exception {
    try {
      _elemHelper.open(_homePage.BASE_URL);

      //Search the item
      _elemHelper.typeEachKey("Iphone", _homePage.SEARCH_TEXTBAR);
      _elemHelper.clickAndWait(_homePage.DROP_DOWN_ITEM);

      //Add first item to cart
      AddIphoneItem();

      String item = _elemHelper.getText(_cartPage.ITEM_NAME);
      _elemHelper.clickAndWait(_cartPage.BACK_BUTTON);

      //signin
      _homePage.goToSignIn();

      signin.login();

      //view cart
      _cartPage.goToViewCart();

    //  Assert.assertTrue(_elemHelper.isElementPresent(_cartPage.IPHONE_CART_ITEM));

      //Check for item is same as added missing
      checkoutToHome();

      _cartPage.goToViewCart();

      //remove the
      _cartPage.removeItemFromCart(_cartPage.REMOVE_ITEM_FROM_CART);


    } catch (Exception e) {
      throw new Exception(e.getMessage());
      // TODO Auto-generated catch block
    }
    System.out.println("Testing");

  }

  public void checkoutToHome() throws Exception{

    _elemHelper.click(_checkoutPage.CHECKOUT);
    _elemHelper.waitForElement(_checkoutPage.CONTINUE_CHECKOUT);
    _elemHelper.clickAndWait(_checkoutPage.CONTINUE_CHECKOUT);
    _elemHelper.waitForElement(_checkoutPage.PREFERED_SHIPPING_ADDRESS_CONTAINER);
    //verify the preferred address
    String address = _elemHelper.getText(_checkoutPage.PREFERED_SHIPPING_ADDRESS);
    System.out.println(address);

    //click on continue to payments
    _elemHelper.clickAndWait(_checkoutPage.CONTINUE_TO_PAYMENT);
    //verify the payement
    _elemHelper.waitForElement(_checkoutPage.PAYMENT_VIEW);
    Assert.assertTrue(_elemHelper.isElementPresent(_checkoutPage.PAYMENT_VIEW));


  }

  public void AddIphoneItem() throws Exception {
    _elemHelper.waitForElement(_homePage.SHIPABLEITEM);
    _elemHelper.jsclick(_homePage.SHIPABLEITEM);

    _elemHelper.waitForElement(_homePage.IPHONE_COLOR);
    _elemHelper.clickAndWait(_homePage.IPHONE_COLOR);

    _elemHelper.waitForElement(_cartPage.ADD_TO_CART_BUTTON);
    _elemHelper.clickAndWait(_cartPage.ADD_TO_CART_BUTTON);
  }

}
