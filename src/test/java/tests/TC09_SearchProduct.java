/**
 * Test Case: TC09 - Search Product
 *
 * This test validates the search functionality in the Automation Exercise application.
 * Steps:
 *  1. Navigate to Home Page and verify it is visible.
 *  2. Navigate to the Products page and confirm the URL.
 *  3. Search for a product by keyword (e.g., "men").
 *  4. Verify that the "SEARCHED PRODUCTS" section is visible.
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

public class TC09_SearchProduct {
    
    // -----------------------------
    // Page Objects
    // -----------------------------
    Home_Page Homepage;
    Products_Page ProductsPage;

    
    // -----------------------------
    // Test Case
    // -----------------------------
    @Test
    public void TC09_SearchProduct() {   
        // Step 1: Navigate to home page and validate
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");
        
        // Step 2: Go to Products page and validate URL
        Homepage.clickProducts();
        assertEquals(BrowserUtils.GetCurrentLink(),
                "https://automationexercise.com/products",
                "Navigation to Products page failed");
        
        // Step 3: Search for a product
        String searchedProduct = "men";
        ProductsPage.searchProduct(searchedProduct);
        
        // Step 4: Validate searched products section
        System.out.println(ProductsPage.getSearchedProductsHeader());
        assertTrue(ProductsPage.getSearchedProductsHeader().contains("SEARCHED PRODUCTS"),
                "'SEARCHED PRODUCTS' is not visible");
        System.out.println("'SEARCHED PRODUCTS' is visible");
        
        /*
        // Optional: Print all product names from search results
        System.out.println("The searched products for: " + searchedProduct);
        ProductsPage.printAllSearchResultNames();
        System.out.println("All the products related to search are visible");
        */
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
        System.out.println("TC09 - START");
        Homepage = new Home_Page("chrome");
        ProductsPage = new Products_Page();
        
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC09 - END");
        base.BaseDriver.quitDriver(); 
    }
}
