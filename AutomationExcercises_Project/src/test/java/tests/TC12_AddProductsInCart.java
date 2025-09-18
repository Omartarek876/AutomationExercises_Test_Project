package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case 12 - Add Products in Cart
 *
 * This test case verifies that multiple products can be added to the cart
 * and ensures that their details (name, price, quantity, and total) are correct.
 *
 * Application Under Test: Automation Exercise
 * Tools/Framework: Selenium WebDriver + TestNG
 * Design Pattern: Page Object Model (POM)
 *
 * Author: Omar Tarek
 */
public class TC12_AddProductsInCart {

    // Page Object references
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;

    /**
     * Test: Add multiple products to the cart and verify their details.
     */
    @Test
    public void TC12_AddProductsInCart() {
        // Step 1: Navigate to homepage
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");

        // Step 2: Navigate to products
        Homepage.clickProducts();

        // Step 3: Add specific products by ID
        String[] productIds = {"1", "2"};  // pick only these
        productsPage.addProductsToCartByIds(productIds);

        // Step 4: Open cart page
        productsPage.clickCart();

        // Step 5: Verify product 1 details
        assertTrue(CartPage.getProductNameInCart("1").contains("Blue Top"), "Product 1 name mismatch");
        assertTrue(CartPage.getProductPriceInCart("1").contains("Rs. 500"), "Product 1 price mismatch");
        assertTrue(CartPage.getProductQuantityInCart("1").contains("1"), "Product 1 quantity mismatch");
        assertTrue(CartPage.getProductTotalInCart("1").contains("Rs. 500"), "Product 1 total mismatch");

        // Step 6: Verify product 2 details
        assertTrue(CartPage.getProductNameInCart("2").contains("Men Tshirt"), "Product 2 name mismatch");
        assertTrue(CartPage.getProductPriceInCart("2").contains("Rs. 400"), "Product 2 price mismatch");
        assertTrue(CartPage.getProductQuantityInCart("2").contains("1"), "Product 2 quantity mismatch");
        assertTrue(CartPage.getProductTotalInCart("2").contains("Rs. 400"), "Product 2 total mismatch");

        System.out.println("all added products, prices, quantities, and totals verified");
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
        System.out.println("TC12 - START");
        Homepage = new Home_Page("chrome");
        productsPage = new Products_Page();
        CartPage = new Cart_Page();
    }

    /**
     * Teardown after each test method.
     */
    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC12 - END");
        base.BaseDriver.quitDriver();
    }
}
