/**
 * Test Case: TC08 - Verify All Products and Product Detail Page
 *
 * This test validates the functionality of navigating to the Products page 
 * and verifying product details in the Automation Exercise application.
 *
 * Steps:
 *  1. Navigate to Home Page and confirm it is visible.
 *  2. Navigate to Products page and verify the URL.
 *  3. Open the details page for a specific product (e.g., product with ID "1").
 *  4. Validate that all product details are displayed: 
 *     - Name
 *     - Category
 *     - Price
 *     - Availability
 *     - Condition
 *     - Brand
 *
 * Follows the Page Object Model (POM) design pattern.
 *
 * Author: Omar Tarek
 */

package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

public class TC08_VerifiyAllProductsAndProductPage {
    
    // -----------------------------
    // Page Objects
    // -----------------------------
    Home_Page Homepage;
    Products_Page ProductsPage;

    
    // -----------------------------
    // Test Case
    // -----------------------------
    @Test
    public void TC08_VerifiyAllProductsAndProductPage() {   
        // Step 1: Navigate to home page and validate
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");
        
        // Step 2: Navigate to Products page and validate
        Homepage.clickProducts();
        assertEquals(BrowserUtils.GetCurrentLink(),
                "https://automationexercise.com/products",
                "Navigation to Products page failed");
        
        // Step 3: View details of a specific product (id = 1)
        ProductsPage.viewProduct("1");
      
        // Step 4: Validate product details are displayed
        assertFalse(ProductsPage.getProductName().isEmpty(), "Product name is not visible!");
        assertFalse(ProductsPage.getProductCategory().isEmpty(), "Product category is not visible!");
        assertFalse(ProductsPage.getProductPrice().isEmpty(), "Product price is not visible!");
        assertFalse(ProductsPage.getProductAvailability().isEmpty(), "Product availability is not visible!");
        assertFalse(ProductsPage.getProductCondition().isEmpty(), "Product condition is not visible!");
        assertFalse(ProductsPage.getProductBrand().isEmpty(), "Product brand is not visible!");
        
        System.out.println("All the product details are visible");
    }
    
    
    // -----------------------------
    // Setup & Teardown
    // -----------------------------
    @BeforeClass
    public void setUpClass() throws FileNotFoundException, IOException {
        System.out.println("CLASS START");

    }

    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END"); 
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC08 - START");
        Homepage = new Home_Page("chrome");
        ProductsPage = new Products_Page();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC08 - END");
        base.BaseDriver.quitDriver(); 
    }
}
