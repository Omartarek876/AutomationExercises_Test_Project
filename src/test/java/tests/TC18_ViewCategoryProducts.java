package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case 18 - View Category Products
 *
 * This test case verifies that all main product categories (Women, Men, Kids)
 * are visible in the left sidebar and that clicking on a subcategory navigates
 * the user to the correct category page.
 *
 * Application Under Test: Automation Exercise
 * Tools/Framework: Selenium WebDriver + TestNG
 * Design Pattern: Page Object Model (POM)
 *
 * Author: Omar Tarek
 */
public class TC18_ViewCategoryProducts {
    
    // Page Object references
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;

    /**
     * Test: Verify that categories and subcategories are visible and navigable.
     */
    @Test
    public void TC18_ViewCategoryProducts() {   
        
        // Step 1: Navigate to homepage
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");
               
        // Step 2: Verify Women category is visible
        assertTrue(Homepage.getCategoryText("Women").contains("WOMEN"), 
                   "Women category is not visible");

        // Step 3: Verify Men category is visible
        assertTrue(Homepage.getCategoryText("Men").contains("MEN"), 
                   "Men category is not visible");

        // Step 4: Verify Kids category is visible
        assertTrue(Homepage.getCategoryText("Kids").contains("KIDS"), 
                   "Kids category is not visible");
        
        System.out.println("All categories are visible in left sidebar.");
        
        // Step 5: Click Men → Jeans subcategory and verify navigation
        Homepage.clickSubCategory("Men", "Jeans");
        assertTrue(Homepage.getSubCategoryTitle("Jeans").contains("MEN - JEANS PRODUCTS"),
                   "User is not navigated to Men - Jeans category page");
        
        // Step 6: Click Women → Dress subcategory and verify navigation
        Homepage.clickSubCategory("Women", "Dress");
        assertTrue(Homepage.getSubCategoryTitle("Dress").contains("WOMEN - DRESS PRODUCTS"),
                   "User is not navigated to Women - Dress category page");
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
        System.out.println("TC18 - START");
        Homepage = new Home_Page("chrome");
        productsPage = new Products_Page();
        CartPage = new Cart_Page();
    }

    /**
     * Teardown after each test method.
     */
    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC18 - END");
        base.BaseDriver.quitDriver();  
    }
}
