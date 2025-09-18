package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case 16 - Login Before Checkout
 *
 * This test case verifies that a registered user can log in,
 * add products to the cart, proceed to checkout, and place an order successfully. 
 *
 * Application Under Test: Automation Exercise
 * Tools/Framework: Selenium WebDriver + TestNG
 * Design Pattern: Page Object Model (POM)
 *
 * Author: Omar Tarek
 */
public class TC16_LoginBeforeCheckout {
    
    // Page Object references
    Home_Page Homepage;
    Login_Page validLogin_Page;
    AccountDeleted_Page AccountDeleted_Page;
    Products_Page productsPage;
    Cart_Page CartPage;
    Checkout_Page checkoutPage;
    
    // Array holding user credentials loaded from JSON file
    static LoginUsers[] ListOfUsers;
    
    /**
     * Data Provider: Supplies valid login data from JSON file
     * @return array of LoginUsers (email + password)
     */
    @DataProvider(name = "validLoginData")
    public LoginUsers[] userDataProvider() {
        return ListOfUsers;
    }
    
    /**
     * Test: Verify that login before checkout allows successful order placement.
     */
    @Test(dataProvider = "validLoginData")
    public void TC16_LoginBeforeCheckout(LoginUsers validLoginUsers) {
        
        // Step 1: Navigate to homepage
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");
        
        // Step 2: Go to login page
        Homepage.clickSignUp_Login();
        assertTrue(validLogin_Page.LoginHeader().contains("Login to your account"),
                "'Login to your account' header is not visible");
        System.out.println("'Login to your account' header is visible");
        
        // Step 3: Login using valid credentials
        validLogin_Page.login(validLoginUsers.LoginEmail, validLoginUsers.LoginPassword);
        System.out.println("You logged in successfully as " + validLoginUsers.LoginEmail);
        
        // Step 4: Add products to cart
        Homepage.clickProducts();
        String[] productIds = {"1", "2"};  // add only these products
        productsPage.addProductsToCartByIds(productIds);

        // Step 5: Proceed to cart and checkout
        productsPage.clickCart();
        checkoutPage.clickCheckoutButton();       
        checkoutPage.addCommentBeforeCheckout("this is a test");
        checkoutPage.clickPlaceOrderButton();
        
        // Step 6: Enter payment details and confirm order
        checkoutPage.enterPaymentDetails("Omar Tarek", "4111111111111111", "123", "12", "2028");
        checkoutPage.clickPayAndConfirmOrder(); 
        
        // Step 7: Validate successful order placement
        assertTrue(checkoutPage.getSuccessPaymentMsg().contains("Congratulations!"),
                "Your order has not been placed successfully!");
        System.out.println("Your order has been placed successfully!");
        checkoutPage.clickContinueAfterSuccess();
        
        
        
        // Step 8 (Optional Cleanup): Delete account after test
        /*
        Homepage.DeleteAccount();
        assertEquals(BrowserUtils.GetCurrentLink(), "https://automationexercise.com/delete_account");
        System.out.println("The account is deleted");
        
        AccountDeleted_Page.clickContinue();
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is not visible after account deletion");
        */
         
    }

    /**
     * Setup before running the test class.
     */
    @BeforeClass
    public void setUpClass() throws FileNotFoundException, IOException {
        System.out.println("CLASS START");
        ListOfUsers = HelperClass.ReadLoginUsers("ValidLoginData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
    }

    /**
     * Teardown after running the test class.
     */
    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END");
    }

    /**
     * Setup before each test method.
     */
    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC16 - START");
        Homepage = new Home_Page("chrome");
        validLogin_Page = new Login_Page();
        AccountDeleted_Page = new AccountDeleted_Page();
        productsPage = new Products_Page();
        CartPage = new Cart_Page();
        checkoutPage = new Checkout_Page();
    }

    /**
     * Teardown after each test method.
     */
    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC16 - END");
        base.BaseDriver.quitDriver();   
    }
}
