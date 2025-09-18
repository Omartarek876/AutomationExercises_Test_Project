package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case 15 - Register Before Checkout
 *
 * This test case validates that a user can register an account
 * before starting the checkout process, add products to the cart, 
 * place an order successfully, and then delete the account.
 *
 * Application Under Test: Automation Exercise
 * Tools/Framework: Selenium WebDriver + TestNG
 * Design Pattern: Page Object Model (POM)
 *
 * Author: Omar Tarek
 */
public class TC15_RigesterBeforeCheckout {
    
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
     * Test: Register before checkout process
     * Steps:
     * 1. Navigate to homepage
     * 2. Register a new account
     * 3. Verify account creation and login
     * 4. Add products to cart
     * 5. Proceed to checkout
     * 6. Enter payment details
     * 7. Verify order placement
     * 8. Delete account
     */
    @Test(dataProvider = "usersData")
    public void TC15_RigesterBeforeCheckout(CreateAccountUsers UserTestData)
    {   
        // Step 1: Navigate to homepage
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");
        
        // Step 2: Navigate to signup/login and start registration
        Homepage.clickSignUp_Login();
        assertTrue(SignUp_Page.SignupHeader1().contains("New User Signup!"), 
                "The 'New User Signup!' message is not visible");
        System.out.println("'New User SignUp!' is visible");
        
        // Step 3: Fill initial signup form
        SignUp_Page.signup(UserTestData.SignUpName, UserTestData.SignUpEmail);
        assertTrue(SignUp_Page.SignupHeader2().contains("ENTER ACCOUNT INFORMATION"),
                "The 'ENTER ACCOUNT INFORMATION' message is not visible");
        System.out.println("The SignUp header 'ENTER ACCOUNT INFORMATION' is visible");
        
        // Step 4: Complete registration form
        SignUp_Page.FillAllRegisterationForm(
              UserTestData.gender, UserTestData.SignUpName, UserTestData.password, 
              UserTestData.day, UserTestData.month, UserTestData.year, 
              UserTestData.firstName, UserTestData.lastName, UserTestData.company, 
              UserTestData.address1, UserTestData.address2, UserTestData.country,
              UserTestData.state, UserTestData.city, UserTestData.zipcode, 
              UserTestData.mobileNumber
        );
        System.out.println("The registration process is completed");
        
        // Step 5: Verify account creation success
        assertEquals(BrowserUtils.GetCurrentLink(), 
                "https://automationexercise.com/account_created");
        System.out.println("The account is created");
        accountCreated_Page.clickContinue();

        // Step 6: Verify user is logged in
        System.out.println(Homepage.LoggedAsText());
        assertTrue(Homepage.LoggedAsText().contains(UserTestData.SignUpName));
        System.out.println("You are logged in successfully as " + UserTestData.SignUpName);

        // Step 7: Add products to cart
        Homepage.clickProducts();
        String[] productIds = {"1", "2"};  // select specific products
        productsPage.addProductsToCartByIds(productIds);

        // Step 8: Open cart and verify visibility
        productsPage.clickCart();
        assertTrue(CartPage.getshoppingCartHeader().contains("Shopping Cart"), 
                "Cart page is not displayed");
        System.out.println("Cart page is displayed");
        
        // Step 9: Proceed to checkout
        checkoutPage.clickCheckoutButton();
        // checkoutPage.clickCheckoutAndsign(); // not needed since user is already logged in
        
        checkoutPage.addCommentBeforeCheckout("This is a test");
        checkoutPage.clickPlaceOrderButton();
        
        // Step 10: Enter payment details
        checkoutPage.enterPaymentDetails("Omar Tarek", "4111111111111111", "123", "12", "2028");
        checkoutPage.clickPayAndConfirmOrder(); 
        
        // Step 11: Verify order success
        assertTrue(checkoutPage.getSuccessPaymentMsg().contains("Congratulations!"),  
                "Your order has not been placed successfully!");
        System.out.println("Your order has been placed successfully!");
        checkoutPage.clickContinueAfterSuccess();
        
        // Step 12: Delete account
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
        System.out.println("TC15 - START");               
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
        System.out.println("TC15 - END");
        base.BaseDriver.quitDriver();
    }
}
