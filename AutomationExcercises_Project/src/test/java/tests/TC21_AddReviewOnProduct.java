package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case: TC21_AddReviewOnProduct
 * 
 * Objective:
 *  - Verify that a user can add a product review successfully
 * 
 * Flow:
 *  1. Navigate to homepage
 *  2. Go to "All Products" page
 *  3. Open a product detail page
 *  4. Verify the "Write Your Review" section is visible
 *  5. Submit a review with provided reviewer data
 *  6. Verify success message appears after review submission
 * 
 * Author: Omar Tarek
 */
public class TC21_AddReviewOnProduct {
    
    // Page Objects
    Home_Page Homepage;
    Products_Page ProductsPage;

    // Array holding review data loaded from JSON file
    static reviewsData[] ListOfReviewers;
    
    /**
     * Data Provider returns array of reviewer data objects.
     * Each reviewer has name, email, and review text.
     * @return reviewsData[] array of reviewers
     */
    @DataProvider(name = "validLoginData")
    public reviewsData[] userDataProvider() {
        return ListOfReviewers;
    }
    
    /**
     * Test Method: TC21_AddReviewOnProduct
     * 
     * @param Reviewers review data injected from DataProvider
     */
    @Test(dataProvider = "validLoginData")
    public void TC21_AddReviewOnProduct(reviewsData Reviewers)
    {   
        // Navigate to home page
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        // Navigate to All Products page
        Homepage.clickProducts();
        assertEquals(BrowserUtils.GetCurrentLink() ,"https://automationexercise.com/products");
        System.out.println("User is navigated to ALL PRODUCTS page successfully");
        
        // Open specific product detail page (ID = 1)
        ProductsPage.viewProduct("1");
        assertTrue(ProductsPage.getReviewSectionHeader().contains("WRITE YOUR REVIEW") , "The review section is not visible" );
        System.out.println("The review section is visible");
        
        // Submit a product review
        ProductsPage.submitProductReview(Reviewers.reviewerName, Reviewers.reviewerEmail, Reviewers.reviewText);
        assertTrue(ProductsPage.getReviewSuccessMessage().contains("Thank you for your review.") , "The review is not submitted successfully" );
        System.out.println("The review is submitted successfully");             
    }   
    
    /*
     * Setup executed once before all tests
     * Loads reviewers' data from JSON file
     */
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        System.out.println("CLASS START");
        ListOfReviewers = HelperClass.ReadReviewData("ReviewsData.json");
        System.out.println("Number of users loaded: " + ListOfReviewers.length);
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
        System.out.println("TC21 - START");
        Homepage = new Home_Page("chrome");
        ProductsPage = new Products_Page();
    }

    /*
     * Teardown executed after each test method
     */
    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC21 - END");
        base.BaseDriver.quitDriver(); 
    }
    
}
