package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case: TC22_AddToCartFromRecommendedItems
 * 
 * Objective:
 *  - Verify that user can add products to cart directly from the
 *    "Recommended Items" section displayed at the bottom of the homepage.
 * 
 * Flow:
 *  1. Navigate to homepage
 *  2. Scroll down to "Recommended Items" section
 *  3. Verify section title is displayed correctly
 *  4. Add multiple products from the "Recommended Items" carousel
 *  5. Navigate to Cart
 *  6. Verify that added products are present in the cart
 * 
 * Author: Omar Tarek
 */
public class TC22_AddToCartFromRecommendedItems {
    
    // Page Objects
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;

    /**
     * Test Method: TC22_AddToCartFromRecommendedItems
     */
    @Test
    public void TC22_AddToCartFromRecommendedItems()
    {   
        // Step 1: Navigate to homepage
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "The home page is not visible");
        System.out.println("The home page is visible");
        
        // Step 2: Scroll to Recommended Items section
        Homepage.scrollToBottom();
        assertTrue(Homepage.getRecommendedItemsTitle().contains("RECOMMENDED ITEMS"), 
           "Recommended items section title is incorrect or not visible!");
        System.out.println("Verified Recommended Items title successfully.");

        // Step 3: Select products from Recommended Items and add to cart
        List<String> RecommendedElementsToAdd = Arrays.asList("2", "4");
        List<String> addedElements = Homepage.hoverAndAddMultipleRecommendedProducts(RecommendedElementsToAdd);
        
        // Step 4: Go to cart
        Homepage.clickCart();

        // Step 5: Verify added products are displayed in cart
        CartPage.verifyProductsInCart(addedElements);
    }   
    
    /*
     * Setup executed once before all tests in this class
     */
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        System.out.println("CLASS START");
    }

    /*
     * Teardown executed once after all tests in this class
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
        System.out.println("TC22 - START");
        Homepage = new Home_Page("chrome");
        productsPage = new Products_Page();
        CartPage = new Cart_Page();
    }

    /*
     * Teardown executed after each test method
     */
    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC22 - END");
        base.BaseDriver.quitDriver(); 
    }
    
}
