package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case 13 - Verify Product Quantity in Cart
 *
 * This test case validates that when a user sets a specific quantity for a product 
 * before adding it to the cart, the same quantity is reflected in the shopping cart.
 *
 * Application Under Test: Automation Exercise
 * Tools/Framework: Selenium WebDriver + TestNG
 * Design Pattern: Page Object Model (POM)
 *
 * Author: Omar Tarek
 */
public class TC13_ProductQuantityInCart {

    // Page Object references
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;

    /**
     * Test: Verify that product quantity in the cart matches the entered quantity.
     */
    @Test
    public void TC13_ProductQuantityInCart() {
        // Step 1: Navigate to homepage
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");

        // Step 2: Navigate to products
        Homepage.clickProducts();

        // Step 3: View product details for product ID = 1
        productsPage.viewProduct("1");

        // Step 4: Set quantity
        String Quantity = "4";
        productsPage.enterQuantity(Quantity);

        // Step 5: Add product to cart
        productsPage.addToCart();

        // Step 6: Go to cart
        productsPage.clickCart();

        // Step 7: Verify quantity in cart
        assertTrue(CartPage.getProductQuantityInCart("1").contains(Quantity), "Product quantity mismatch");
        System.out.println("Product quantity matches expected value");
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
        System.out.println("CLASS START");
    }

    /**
     * Setup before each test method.
     */
    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC13 - START");
        Homepage = new Home_Page("chrome");
        productsPage = new Products_Page();
        CartPage = new Cart_Page();
    }

    /**
     * Teardown after each test method.
     */
    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC13 - END");
        base.BaseDriver.quitDriver();
    }
}
