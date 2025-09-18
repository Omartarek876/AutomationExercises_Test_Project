/**
 * Test Case: TC10 - Verify Subscription in Homepage
 *
 * This test validates the subscription feature on the home page footer 
 * of the Automation Exercise application.
 *
 * Steps:
 *  1. Navigate to the Home Page and confirm it is visible.
 *  2. Scroll down to the footer and verify the "SUBSCRIPTION" text is visible.
 *  3. Enter an email in the subscription field and submit.
 *  4. Validate the success message: "You have been successfully subscribed!"
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

public class TC10_SubscriptionInHomepage {
    
    // -----------------------------
    // Page Objects
    // -----------------------------
    Home_Page Homepage;

    
    // -----------------------------
    // Test Case
    // -----------------------------
    @Test
    public void TC10_SubscriptionInHomepage() {   
        // Step 1: Navigate to Home Page and validate
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is invisible");
        System.out.println("The home page is visible");
        
        // Step 2: Scroll to footer and validate Subscription text
        Homepage.scrollToFooter();
        System.out.println(Homepage.subscriptionHeader());
        assertTrue(Homepage.subscriptionHeader().contains("SUBSCRIPTION"),
                "Text 'SUBSCRIPTION' is invisible");
        System.out.println("Text 'SUBSCRIPTION' is visible");
        
        // Step 3: Subscribe with an email
        Homepage.sendSubscriptionEmail("ot482002@gmail.com");
        System.out.println(Homepage.getSuccessMessage());
        
        // Step 4: Validate success message
        assertTrue(Homepage.getSuccessMessage().contains("You have been successfully subscribed!"),
                "Success message 'You have been successfully subscribed!' is invisible");
        System.out.println("Success message 'You have been successfully subscribed!' is visible");
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
        System.out.println("TC10 - START");
        Homepage = new Home_Page("chrome");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC10 - END");
        base.BaseDriver.quitDriver();  
    }
}
