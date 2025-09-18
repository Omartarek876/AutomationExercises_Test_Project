package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case: Verify Address Details in Checkout Page
 * Author: Omar Tarek
 */
public class TC23_VerifyAddressDetailsInCheckoutPage {
    
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
     * Test Case 23:
     * Verify that delivery & billing address details on Checkout Page
     * match the data entered during account registration.
     */
    @Test(dataProvider = "usersData")
    public void TC23_VerifyAddressDetailsInCheckoutPage(CreateAccountUsers UserTestData)
    {   
        // Navigate to homepage
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "the home page is invisible");
        System.out.println("the home page is visible");
        
        // Open signup/login page
        Homepage.clickSignUp_Login();
        assertTrue(SignUp_Page.SignupHeader1().contains("New User Signup!"), 
                "the 'New User Signup!' is invisible");
        System.out.println("'new User SignUp!' is visible");
        
        // Register new account
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
       
        // Add products to cart
        Homepage.clickProducts();
        String[] productIds = {"1", "2"};  // Only select products with these IDs
        List<String> addedProducts = productsPage.addProductsToCartByIds(productIds);
        System.out.println("Products added by IDs: " + addedProducts);
        
        // Verify cart page
        productsPage.clickCart();
        assertTrue(CartPage.getshoppingCartHeader().contains("Shopping Cart"), "cart page is not displayed");
        System.out.println("cart page is displayed");
        
        // Proceed to checkout
        checkoutPage.clickCheckoutButton();
      
        // Verify delivery address matches registered address
        assertTrue(checkoutPage.getDeliveryAddress().contains(UserTestData.address1), 
                "the delivery address is not same address filled at the time registration of account");
        System.out.println("the delivery address matches registration details");
        
        // Verify billing address matches registered address
        assertTrue(checkoutPage.getBillingAddress().contains(UserTestData.address1), 
                "the billing address is not same address filled at the time registration of account");
        System.out.println("the billing address matches registration details");
             
        // Delete account
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
        // Load user data from JSON before tests run
        ListOfUsers = HelperClass.ReadCreateAccountUsers("CreateAccountData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END");
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC23 - START");
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
        System.out.println("TC23 - END");
        // Quit driver after each test
        base.BaseDriver.quitDriver();   
    }
    
}
