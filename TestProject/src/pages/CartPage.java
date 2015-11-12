package pages;

import com.walmart.assignment.Helper;

public class CartPage {

  public final String ADD_TO_CART_BUTTON = "id=WMItemAddToCartBtn";
  public final String VIEW_CART_BUTTON = "xpath=//a[contains(@href,'/cart')]";
  public final String REMOVE_ITEM_FROM_CART = "xpath=//button[@id='CartRemItemBtn']";
  public final String IPHONE_CART_ITEM = "xpath=//div[@class='cart-item-name js-product-title']/a[@data-us-item-id='39671223']";
  public final String CART_LIST = "css=div.cart-list.cart-list-active";
  public final String ITEM_NAME = "css=div.cart-item-name.js-product-title.truncated";
  public final String BACK_BUTTON= "css=i.wmicon.wmicon-angle-left.copy-small";
  public final String CART_TOTAL_ITEMS = "css=h3.cart-list-title span";

  public void goToViewCart() throws Exception {
    Helper elemHelper  = new Helper();
    elemHelper.waitForElement(VIEW_CART_BUTTON);
    elemHelper.clickAndWait(VIEW_CART_BUTTON);

    elemHelper.waitForElement(CART_LIST);
  }

  public void removeItemFromCart(String itemLocator) throws Exception {
    Helper elemHelper  = new Helper();
    elemHelper.scrollIntoViewAndClick(REMOVE_ITEM_FROM_CART);

  }
}
