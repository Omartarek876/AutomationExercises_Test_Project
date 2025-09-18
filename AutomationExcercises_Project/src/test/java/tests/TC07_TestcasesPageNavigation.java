/*
 * Test Case TC07 - Testcases Page Navigation
 * Verify user can navigate to the Testcases page successfully.
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

public class TC07_TestcasesPageNavigation {
    
    Home_Page Homepage;

    /* Test Case Execution
     * Steps:
     * 1. Navigate to https://automationexercise.com/
     * 2. Verify that the home page is visible successfully.
     * 3. Click on 'Test Cases' button.
     * 4. Verify user is navigated to the Test Cases page successfully.
     */
    @Test
    public void TC07_TestcasesPageNavigation() {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        
        assertTrue(Homepage.homePageheader().contains("Automation"), 
                "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickTestcases();
        System.out.println(Homepage.testcasesPageHeader());
        
        assertTrue(Homepage.testcasesPageHeader().contains(
                "Below is the list of test Cases for you to practice the Automation. Click on the scenario for detailed Test Steps:"), 
                "the user is not navigated to test cases page successfully");
        System.out.println("the user is navigated to test cases page successfully");       
    }
    
    
    // -----------------------------
    // Setup & TearDown
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
        System.out.println("TC07 - START");
        Homepage = new Home_Page("chrome");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC07 - END");
        base.BaseDriver.quitDriver();
    }
}
