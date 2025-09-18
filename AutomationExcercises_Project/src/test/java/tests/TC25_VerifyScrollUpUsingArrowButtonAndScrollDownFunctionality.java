package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case 25: Verify Scroll Up Using Arrow Button and Scroll Down Functionality
 * Author: Omar Tarek
 */
public class TC25_VerifyScrollUpUsingArrowButtonAndScrollDownFunctionality {
    
    // Page Object reference
    Home_Page Homepage;

    /**
     * Test Case 25:
     * Verify that the user can scroll down to the footer, 
     * then scroll back up using the arrow button, 
     * and ensure specific text elements are visible.
     */
    @Test
    public void TC25_VerifyScrollUpUsingArrowButtonAndScrollDownFunctionality()
    {   
        // Navigate to homepage
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "the home page is invisible");
        System.out.println("the home page is visible");
        
        // Scroll down to footer and verify "SUBSCRIPTION" section
        Homepage.scrollToFooter();
        System.out.println(Homepage.subscriptionHeader());
        assertTrue(Homepage.subscriptionHeader().contains("SUBSCRIPTION"), 
                "text 'SUBSCRIPTION' is invisible");
        System.out.println("text 'SUBSCRIPTION' is visible");
        
        // Click on the "scroll up" arrow button
        Homepage.clickUpArrow();
        
        // Verify main header text after scrolling up
        assertTrue(Homepage.homePageSecondHeader()
                   .contains("Full-Fledged practice website for Automation Engineers"),
                   "'Full-Fledged practice website for Automation Engineers' text is invisible on screen");
        System.out.println("'Full-Fledged practice website for Automation Engineers' text is visible on screen");
    }   
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        System.out.println("CLASS START");
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END");
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC25 - START");
        // Initialize homepage object before each test
        Homepage = new Home_Page("chrome");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC25 - END");
        // Quit driver after each test
        base.BaseDriver.quitDriver();  
    }
}
