# automate
Automation

### Installation Requirement:
Before running the code you need to set up the following tools on your machine
- Java 8
- Eclipse
- Selenium (All the Selenium jar files have been included in the project under the src/resources package)
- TestNG (Included as a jar under src/resources)
- Chromedriver (available under src/resources, but the path is set relatively within the app)

### Project Setup Instruction
- Open Eclipse -> Import -> Existing Projects Into Workspace
- Select Root Directory and then Navigate to the unzipped project folder and make sure that the project shows up in the Projects section
- Click Finish
- You should see the project in your workspace now

### Running Configuration:
- Right click on the test class ScenarioTest.java in src/com.walmart.assignment and Run As -> TestNG Test
 
### Assignment
Automate an end to end user e-commerce transaction flow using any open source tool for mobile.walmart.com<http://mobile.walmart.com> with an existing customer on chrome or safari browser.

Test Flow/Scenarios to be automated:
- 1. Perform a search on home page from a pool of key words given below
- 2. Identify an item from the result set that you can add to cart
- 3. Add the item to cart and then login using existing account which is set up with at least one shipping address
- 4. Validate that item added is present in the cart and is the only item in the cart
- 5. Select Ship to Home as shipping method for your order
- 6. Validate that you are on Payment details page
- 7. Go back to Cart Page, Remove the item from cart and validate cart is empty
- 8. Sign out from your account

Test Data:
- Account details: create your own account
- Search terms: tv, socks, dvd, toys, iPhone
- Shipping Address: 850 Cherry Ave, San Bruno, CA 94066

### Class structure
- /src
	com.wallmart.assignment
	- BaseTest.java
	BeforeTest and AfterTest methods

	- Helper.java
	Wrapper over WebDriver APIs

	- ScenarioTest.java
	Test Class

	- SessionManager.java
	APIs for managing sessions with WebDriver

	pages
	- CartPage.java
	Elements and utility functions for the page

	- CheckoutPage.java
	Elements and utility functions for the page

	- HomePage.java
	Elements and utility functions for the page

	- SignInPage.java
	Elements and utility functions for the page

- /resources
	- chromedriver
	- TestNG
	- selenium-server-standalone.jar


