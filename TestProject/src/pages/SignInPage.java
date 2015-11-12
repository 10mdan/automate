package pages;

import com.walmart.assignment.Helper;

public class SignInPage {

  public final String HEADER_SIGNIN = "class=header-account-signin";
  public final String EMAIL_SIGN_IN = "id=login-username";
  public final String PASSWORD_SIGN_IN = "id=login-password";
  public final String SIGN_IN_BUTTON = "xpath=//button[@data-automation-id='login-sign-in']";
  public final String SIGNOUT = "xpath=//a[@href='https://www.walmart.com/account/logout']";

  HomePage homePage = new HomePage();



 public void login() throws Exception {

   Helper elemHelper  = new Helper();
   elemHelper.waitForElement(EMAIL_SIGN_IN);
   elemHelper.type("wallmarttestbot@gmail.com", EMAIL_SIGN_IN);
   elemHelper.type("10test10", PASSWORD_SIGN_IN);
   elemHelper.click(SIGN_IN_BUTTON);
   elemHelper.waitForPage();
 }


 public void logout() throws Exception {
   Helper elemHelper  = new Helper();
   elemHelper.waitForElement(homePage.MENU_BUTTON);
   elemHelper.clickAndWait(homePage.MENU_BUTTON);
   elemHelper.scrollIntoViewAndClick(SIGNOUT);

 }


}
