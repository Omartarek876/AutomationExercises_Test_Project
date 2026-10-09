package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case 24: Download Invoice After Purchase Order
 * Author: Omar Tarek
 */
public class TC24_DownloadInvoiceAfterPurchaseOrder {
    
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
     * Data Provider: returns array of User objects for all test users.
     * @return User[] of user credentials
     */
    @DataProvider(name = "usersData")
    public CreateAccountUsers[] userDataProvider() {
        return ListOfUsers;
    }
    
    /**
     * Test Case 24:
     * Verify that after placing an order, user can download invoice successfully.
     */
    @Test(dataProvider = "usersData")
    public void TC24_DownloadInvoiceAfterPurchaseOrder(CreateAccountUsers UserTestData)
    {   
        // Navigate to homepage
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "the home page is invisible");
        System.out.println("the home page is visible");
        
        // Add products to cart
        Homepage.clickProducts();
        String[] productIds = {"1", "2"};  // Only select products with these IDs
        productsPage.addProductsToCartByIds(productIds);

        // Open cart page
        productsPage.clickCart();
        assertTrue(CartPage.getshoppingCartHeader().contains("Shopping Cart"), "cart page is not displayed");
        System.out.println("cart page is displayed");
        
        // Proceed to checkout without account
        checkoutPage.clickCheckoutButton();
        checkoutPage.clickCheckoutAndsign();
        
        // Sign up process begins
        assertTrue(SignUp_Page.SignupHeader1().contains("New User Signup!"), 
                "the 'New User Signup!' is invisible");
        System.out.println("'New User Signup!' is visible");
        
        SignUp_Page.signup(UserTestData.SignUpName, UserTestData.SignUpEmail);
        assertTrue(SignUp_Page.SignupHeader2().contains("ENTER ACCOUNT INFORMATION"),
                "the 'ENTER ACCOUNT INFORMATION' is invisible");
        System.out.println("the SignUp header 'ENTER ACCOUNT INFORMATION' is visible");
        
        // Fill registration form
        SignUp_Page.FillAllRegisterationForm(UserTestData.gender, UserTestData.SignUpName, UserTestData.password, UserTestData.day, UserTestData.month, 
              UserTestData.year, UserTestData.firstName, UserTestData.lastName, UserTestData.company, UserTestData.address1, UserTestData.address2, UserTestData.country,
              UserTestData.state, UserTestData.city, UserTestData.zipcode, UserTestData.mobileNumber );
        System.out.println("the registeration process is done");
        
        // Verify account creation
        assertEquals(BrowserUtils.GetCurrentLink() , "https://automationexercise.com/account_created");
        System.out.println("the account is created");
        accountCreated_Page.clickContinue();
        assertTrue(Homepage.LoggedAsText().contains(UserTestData.SignUpName));

        // Reopen cart and checkout
        Homepage.clickCart();
        checkoutPage.clickCheckoutButton();       
        
        // Verify delivery address matches registration
        assertTrue(checkoutPage.getDeliveryAddress().contains(UserTestData.address1), 
                "the delivery address is not same address filled at the time registration of account");
        System.out.println("the delivery address matches registration details");
        
        // Verify billing address matches registration
        assertTrue(checkoutPage.getBillingAddress().contains(UserTestData.address1), 
                "the billing address is not same address filled at the time registration of account");
        System.out.println("the billing address matches registration details");
             
        // Add comment and place order
        checkoutPage.addCommentBeforeCheckout("this is a test");
        checkoutPage.clickPlaceOrderButton();
        
        // Enter payment details
        checkoutPage.enterPaymentDetails("Omar Tarek", "4111111111111111", "123", "12", "2028");
        checkoutPage.clickPayAndConfirmOrder(); 
        
        // Verify order success message
        assertTrue(checkoutPage.getSuccessPaymentMsg().contains("Congratulations!"),  
                "Your order has not been placed successfully!");
        System.out.println("Your order has been placed successfully!");
        
        // Download invoice after successful order
        checkoutPage.clickDownloadInvoiceButton();
        
        // Continue after success and delete account
        checkoutPage.clickContinueAfterSuccess();             
        Homepage.DeleteAccount();
        assertEquals(BrowserUtils.GetCurrentLink() , "https://automationexercise.com/delete_account");
        System.out.println("the account is deleted");
        
        // Verify return to homepage
        AccountDeleted_Page.clickContinue();
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "the home page is invisible");              
    }     
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        System.out.println("CLASS START");
        // Load test users from JSON file before executing tests
        ListOfUsers = HelperClass.ReadCreateAccountUsers("CreateAccountData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END");   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC24 - START");
        // Initialize page objects before each test
        Homepage = new Home_Page("chrome");
        productsPage = new Products_Page();
        CartPage = new Cart_Page();
        SignUp_Page = new SignUp_Page();
        accountCreated_Page = new AccountCreated_Page();
        checkoutPage = new Checkout_Page();
        AccountDeleted_Page = new AccountDeleted_Page();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC24 - END");
        // Close browser driver after each test
        base.BaseDriver.quitDriver();   
    } 
}
