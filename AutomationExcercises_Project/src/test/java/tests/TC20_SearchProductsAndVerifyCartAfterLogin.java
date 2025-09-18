package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case: TC20_SearchProductsAndVerifyCartAfterLogin
 * 
 * Objective:
 *  - Search for products on the site
 *  - Add all search results to cart
 *  - Log in with valid user credentials
 *  - Verify cart still contains previously added products after login
 * 
 * Flow:
 *  1. Navigate to homepage
 *  2. Search for products
 *  3. Add all results to cart
 *  4. Navigate to cart and attempt login
 *  5. Log in with valid credentials
 *  6. Verify cart items persist after login
 * 
 * Author: Omar Tarek
 */
public class TC20_SearchProductsAndVerifyCartAfterLogin {
    
    Home_Page Homepage;
    Products_Page ProductsPage;
    Cart_Page CartPage;
    Login_Page validLogin_Page;
    
    // Array holding user credentials loaded from JSON file
    static LoginUsers[] ListOfUsers;
    
    /**
     * Data Provider returns array of User objects for all test users.
     * @return LoginUsers[] of user credentials
     */
    @DataProvider(name = "validLoginData")
    public LoginUsers[] userDataProvider() {
        return ListOfUsers;
    }
    
    /**
     * Test Method: TC20_SearchProductsAndVerifyCartAfterLogin
     * 
     * @param validLoginUsers user credentials injected from DataProvider
     */
    @Test(dataProvider = "validLoginData")
    public void TC20_SearchProductsAndVerifyCartAfterLogin(LoginUsers validLoginUsers)
    {   
        // Navigate to home page
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        // Navigate to Products page
        Homepage.clickProducts();
        assertEquals(BrowserUtils.GetCurrentLink() ,"https://automationexercise.com/products");

        // Search for a product
        ProductsPage.searchProduct("women");
        System.out.println(ProductsPage.getSearchedProductsHeader());
        assertTrue(ProductsPage.getSearchedProductsHeader().contains("SEARCHED PRODUCTS") , "'SEARCHED PRODUCTS' is not visible ");
        System.out.println("'SEARCHED PRODUCTS' is visible");
        
        // Add all search results to cart
        List<String> addedElements = Homepage.addAllSearchResultsToCart();
        System.out.println("All the products related to search are added into the cart");
        
        // Go to Cart, then Login
        Homepage.clickCart();
        Homepage.clickSignUp_Login();
        assertTrue(validLogin_Page.LoginHeader().contains("Login to your account") , "'Login to your account' is invisible");
        System.out.println("'Login to your account' is visible");
        
        // Perform login
        validLogin_Page.login(validLoginUsers.LoginEmail , validLoginUsers.LoginPassword);
        System.out.println("You logged in successfully as " + validLoginUsers.LoginEmail);
        
        // Verify products remain in cart after login
        validLogin_Page.clickCart();
        CartPage.verifyProductsInCart(addedElements);
    }   
    
    /*
     * Setup executed once before all tests
     * Loads valid user credentials from JSON file
     */
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        System.out.println("CLASS START");
        ListOfUsers = HelperClass.ReadLoginUsers("ValidLoginData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
    }

    /*
     * Teardown executed once after all tests
     */
    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END");
    }

    /*
     * Setup executed before each test method
     */
    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC20 - START");
        Homepage = new Home_Page("chrome");
        ProductsPage = new Products_Page();
        CartPage = new Cart_Page();
        validLogin_Page = new Login_Page();
    }

    /*
     * Teardown executed after each test method
     */
    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC20 - END");
        base.BaseDriver.quitDriver();   
    }
    
}
