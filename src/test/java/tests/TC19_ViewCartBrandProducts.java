package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;
/**
 *
 * @author Omar Tarek
 */
public class TC19_ViewCartBrandProducts {
    
    Home_Page Homepage;
    Products_Page productsPage;
    Cart_Page CartPage;

    /*
     * Test Case: TC19_ViewCartBrandProducts
     * Objective:
     *  - Verify that user can navigate to brand-specific product pages.
     * Steps:
     *  1. Navigate to home page
     *  2. Verify home page is visible
     *  3. Click on "Products"
     *  4. Select "Madame" brand and verify navigation
     *  5. Select "Polo" brand and verify navigation
     */
    @Test
    public void TC19_ViewCartBrandProducts()
    {   
        // Navigate to site
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        
        // Verify homepage visibility
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        // Navigate to products page
        Homepage.clickProducts();
        
        // Define brand names for validation
        String Brand1 = "Madame";
        String Brand2 = "Polo";

        // Select brand 1 and validate
        Homepage.clickBrand("Madame");
        assertTrue(Homepage.getBrandPageTitle("Madame").contains(Brand1.toUpperCase()) , 
                   "user is not navigated to brand page");
        System.out.println("user is navigated to brand page " + Brand1);
        
        // Select brand 2 and validate
        Homepage.clickBrand("Polo");
        assertTrue(Homepage.getBrandPageTitle("Polo").contains(Brand2.toUpperCase()) , 
                   "user is not navigated to brand page");
        System.out.println("user is navigated to brand page " + Brand2);

    }   
    
    /*
     * Setup executed once before all tests
     */
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        System.out.println("CLASS START");
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
        System.out.println("TC19 - START");
        Homepage = new Home_Page("chrome");
        productsPage = new Products_Page();
        CartPage = new Cart_Page();
    }

    /*
     * Teardown executed after each test method
     */
    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC19 - END");
        base.BaseDriver.quitDriver();   
    }
    
}
