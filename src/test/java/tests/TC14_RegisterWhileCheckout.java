package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case 14 - Register While Checkout
 *
 * This test case validates that a user can register a new account 
 * during the checkout process, complete the order successfully, 
 * and delete the account afterwards.
 *
 * Application Under Test: Automation Exercise
 * Tools/Framework: Selenium WebDriver + TestNG
 * Design Pattern: Page Object Model (POM)
 *
 * Author: Omar Tarek
 */
public class TC14_RegisterWhileCheckout {
    
    // Page Object references
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;
    SignUp_Page SignUp_Page;
    AccountCreated_Page accountCreated_Page;
    Checkout_Page checkoutPage;
    AccountDeleted_Page AccountDeleted_Page;
    
    // Array holding user credentials loaded from JSON file
    static CreateAccountUsers[] ListOfUsers;
    
    /**
     * Data Provider that supplies test users from JSON file.
     * @return User[] of user credentials
     */
    @DataProvider(name = "usersData")
    public CreateAccountUsers[] userDataProvider() {
        return ListOfUsers;
    }
    
    /**
     * Test: Register new user during checkout process
     * Steps:
     * 1. Navigate to homepage
     * 2. Add products to cart
     * 3. Proceed to checkout
     * 4. Register a new account
     * 5. Verify account creation
     * 6. Complete order with payment details
     * 7. Verify success message
     * 8. Delete the account
     */
    @Test(dataProvider = "usersData")
    public void TC14_RegisterWhileCheckout(CreateAccountUsers UserTestData)
    {   
        // Step 1: Navigate to homepage
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");
        
        // Step 2: Go to products and add specific items to cart
        Homepage.clickProducts();
        String[] productIds = {"1", "2"};  // select specific products
        productsPage.addProductsToCartByIds(productIds);

        // Step 3: Open cart and verify visibility
        productsPage.clickCart();
        assertTrue(CartPage.getshoppingCartHeader().contains("Shopping Cart"), 
                "Cart page is not displayed");
        System.out.println("Cart page is displayed");
        
        // Step 4: Proceed to checkout
        checkoutPage.clickCheckoutButton();
        checkoutPage.clickCheckoutAndsign();
        
        // Step 5: Verify 'New User Signup!' message
        assertTrue(SignUp_Page.SignupHeader1().contains("New User Signup!"), 
                "The 'New User Signup!' message is not visible");
        System.out.println("'New User SignUp!' is visible");
        
        // Step 6: Start signup process
        SignUp_Page.signup(UserTestData.SignUpName, UserTestData.SignUpEmail);
        assertTrue(SignUp_Page.SignupHeader2().contains("ENTER ACCOUNT INFORMATION"),
                "The 'ENTER ACCOUNT INFORMATION' message is not visible");
        System.out.println("The SignUp header 'ENTER ACCOUNT INFORMATION' is visible");
        
        // Step 7: Fill out registration form
        SignUp_Page.FillAllRegisterationForm(
              UserTestData.gender, UserTestData.SignUpName, UserTestData.password, 
              UserTestData.day, UserTestData.month, UserTestData.year, 
              UserTestData.firstName, UserTestData.lastName, UserTestData.company, 
              UserTestData.address1, UserTestData.address2, UserTestData.country,
              UserTestData.state, UserTestData.city, UserTestData.zipcode, 
              UserTestData.mobileNumber
        );
        System.out.println("The registration process is completed");
        
        // Step 8: Verify account creation success
        assertEquals(BrowserUtils.GetCurrentLink(), 
                "https://automationexercise.com/account_created");
        System.out.println("The account is created");
        accountCreated_Page.clickContinue();

        // Step 9: Verify user is logged in
        assertTrue(Homepage.LoggedAsText().contains(UserTestData.SignUpName));
        
        // Step 10: Checkout with registered account
        Homepage.clickCart();
        checkoutPage.clickCheckoutButton();       
        checkoutPage.addCommentBeforeCheckout("This is a test");
        checkoutPage.clickPlaceOrderButton();
        
        // Step 11: Enter payment details and confirm order
        checkoutPage.enterPaymentDetails("Omar Tarek", "4111111111111111", "123", "12", "2028");
        checkoutPage.clickPayAndConfirmOrder(); 
        
        // Step 12: Verify success message
        assertTrue(checkoutPage.getSuccessPaymentMsg().contains("Congratulations!"),  
                "Your order has not been placed successfully!");
        System.out.println("Your order has been placed successfully!");
        checkoutPage.clickContinueAfterSuccess();
        
        // Step 13: Delete account
        Homepage.DeleteAccount();
        assertEquals(BrowserUtils.GetCurrentLink(), 
                "https://automationexercise.com/delete_account");
        System.out.println("The account is deleted");
        
        AccountDeleted_Page.clickContinue();
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is not visible again");  
    }     
    
    /**
     * Setup before running the test class.
     */
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        System.out.println("CLASS START");
        ListOfUsers = HelperClass.ReadCreateAccountUsers("CreateAccountData.json");
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
        System.out.println("TC14 - START");
        Homepage = new Home_Page("chrome");
        productsPage = new Products_Page();
        CartPage = new Cart_Page();
        SignUp_Page = new SignUp_Page();
        accountCreated_Page = new AccountCreated_Page();
        checkoutPage = new Checkout_Page();
        AccountDeleted_Page = new AccountDeleted_Page();
    }

    /**
     * Teardown after each test method.
     */
    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC14 - END");
        base.BaseDriver.quitDriver(); 
    }
}
