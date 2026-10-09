package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case ID: TC26
 * Test Case Name: Verify Scroll Up Without Arrow Button And Scroll Down Functionality
 * Author: Omar Tarek
 */
public class TC26_VerifyScrollUpWIthoutArrowButtonAndScrollDownFunctionality {
    
    // Declare Home_Page object
    Home_Page Homepage;

    @Test
    public void TC26_VerifyScrollUpWIthoutArrowButtonAndScrollDownFunctionality()
    {   
        // Step 1: Navigate to the application URL
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        
        // Step 2: Verify that home page is visible successfully
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        // Step 3: Scroll down to the footer of the page
        Homepage.scrollToFooter();
        
        // Step 4: Verify 'SUBSCRIPTION' text is visible in the footer
        System.out.println(Homepage.subscriptionHeader());
        assertTrue(Homepage.subscriptionHeader().contains("SUBSCRIPTION") , "text 'SUBSCRIPTION' is invisible");
        System.out.println("text 'SUBSCRIPTION' is visible");
        
        // Step 5: Scroll up to the header (without clicking the arrow button)
        Homepage.scrollToHeader();
        
        // Step 6: Verify that the expected header text is visible after scrolling up
        assertTrue(Homepage.homePageSecondHeader().contains("Full-Fledged practice website for Automation Engineers")
                   , "'Full-Fledged practice website for Automation Engineers' text is Invisible on screen");
        System.out.println("'Full-Fledged practice website for Automation Engineers' text is visible on screen");
    }   
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        // Runs once before any test methods in this class
        System.out.println("CLASS START");
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        // Runs once after all test methods in this class
        System.out.println("CLASS END");
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        // Runs before each @Test method
        System.out.println("TC26 - START");
        Homepage = new Home_Page("chrome"); // Launch browser and initialize Home_Page
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        // Runs after each @Test method
        System.out.println("TC26 - END");
        base.BaseDriver.quitDriver(); // Close the browser
    }
}
