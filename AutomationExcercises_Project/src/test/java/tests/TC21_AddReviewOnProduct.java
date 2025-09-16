/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
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
public class TC21_AddReviewOnProduct {
    
    Home_Page Homepage;
    Products_Page ProductsPage;

    // Array holding user credentials loaded from JSON file
    static reviewsData[] ListOfReviewers;
    
        /**
     * Data Provider returns array of User objects for all test users.
     * @return User[] of user credentials
     */
    @DataProvider(name = "validLoginData")
    public reviewsData[] userDataProvider() {
        return ListOfReviewers;
    }
    
    @Test(dataProvider = "validLoginData")
    public void TC21_AddReviewOnProduct(reviewsData Reviewers)
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickProducts();
        assertEquals(BrowserUtils.GetCurrentLink() ,"https://automationexercise.com/products");
        System.out.println("user is navigated to ALL PRODUCTS page successfully");
        
        ProductsPage.viewProduct("1");
        assertTrue(ProductsPage.getReviewSectionHeader().contains("WRITE YOUR REVIEW") , "the review section is not visible" );
        System.out.println("the review section is visible");
        
        ProductsPage.SubmitProductReview(Reviewers.reviewerName, Reviewers.reviewerEmail, Reviewers.reviewText);
        assertTrue(ProductsPage.getReviewSuccessMessage().contains("Thank you for your review.") , "the review is not submitted successfully" );
        System.out.println("the review is submitted successfully");             
}   
    
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        ListOfReviewers = HelperClass.ReadReviewData("ReviewsData.json");
        System.out.println("Number of users loaded: " + ListOfReviewers.length);
        
        Homepage = new Home_Page("chrome");
        ProductsPage = new Products_Page();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
         base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC21 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC21 - END");
    }
    
     
}
