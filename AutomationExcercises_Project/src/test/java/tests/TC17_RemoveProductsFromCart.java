package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case 17 - Remove Products from Cart
 *
 * This test case verifies that a user can successfully remove a product
 * from the shopping cart, and that the cart reflects the removal.
 *
 * Application Under Test: Automation Exercise
 * Tools/Framework: Selenium WebDriver + TestNG
 * Design Pattern: Page Object Model (POM)
 *
 * Author: Omar Tarek
 */
public class TC17_RemoveProductsFromCart {
    
    // Page Object references
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;

    /**
     * Test: Verify that products can be added to cart and then removed successfully.
     */
    @Test
    public void TC17_RemoveProductsFromCart() {   
        
        // Step 1: Navigate to homepage
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");
        
        // Step 2: Navigate to products and add a product to cart
        Homepage.clickProducts();
        String[] productIds = {"1"};  // add only product with ID=1
        productsPage.addProductsToCartByIds(productIds);
        
        // Step 3: Open cart and confirm product was added
        productsPage.clickCart();
        String addedProduct = CartPage.getProductNameInCart("1");
        
        // Step 4: Delete the product from cart
        productsPage.deleteFromCart();
        
        // Step 5: Validate that cart is empty
        assertTrue(productsPage.getEmptyCartMessage().contains("empty"),
                "The product was not deleted successfully");
        // Alternative check (commented out): Ensure deleted product name is no longer in the cart
        // assertFalse(productsPage.getProductName().contains(addedProduct),
        //        "The product was not deleted successfully");
        
        System.out.println("The product was deleted successfully");
    }   
    
    /**
     * Setup before running the test class.
     */
    @BeforeClass
    public void setUpClass() throws FileNotFoundException, IOException {
        System.out.println("CLASS START");
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
        System.out.println("TC17 - START");
        Homepage = new Home_Page("chrome");
        productsPage = new Products_Page();
        CartPage = new Cart_Page();
    }

    /**
     * Teardown after each test method.
     */
    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC17 - END");
        base.BaseDriver.quitDriver();
    }
}
