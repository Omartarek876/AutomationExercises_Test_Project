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
 * Test Case ID   : TC06
 * Test Case Name : Contact Us Form
 * Description    : Verify that the user can submit the Contact Us form 
 *                  successfully and navigate back to the home page.
 * Author         : Omar Tarek
 */
public class TC06_ContactUsForm {

    // Page Objects
    Home_Page Homepage;
    ContactUs_page ContactUsPage;
      
    // Array holding Contact Us form data loaded from JSON file
    static ContactUsUsers[] ListOfUsers;
    
    /**
     * Data Provider returns array of ContactUsUsers objects for all test data.
     * @return ContactUsUsers[] array of test data
     */
    @DataProvider(name = "contactUsData")
    public ContactUsUsers[] userDataProvider() {
        return ListOfUsers;
    }
    
    /**
     * Test Case Execution
     * Steps:
     *  1. Navigate to Home Page.
     *  2. Verify that the home page is visible successfully.
     *  3. Click on Contact Us button.
     *  4. Verify 'GET IN TOUCH' is visible.
     *  5. Fill in the Contact Us form with Name, Email, Subject, Message, and File.
     *  6. Verify success message 'Success! Your details have been submitted successfully.' is visible.
     *  7. Click 'Home' button and verify that user is navigated back to home page successfully.
     */
    @Test(dataProvider = "contactUsData")
    public void TC06_ContactUsForm(ContactUsUsers contactUsData) {   
        // Step 1 - Navigate to Home Page
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        
        // Step 2 - Verify Home Page is visible
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The home page is invisible");
        System.out.println("The home page is visible");
        
        // Step 3 - Click on Contact Us
        Homepage.clickContactUs();
        
        // Step 4 - Verify 'GET IN TOUCH' header is visible
        assertTrue(ContactUsPage.ContactUsHeader().contains("GET IN TOUCH"),
                "The header of Contact Us page 'GET IN TOUCH' is invisible");
        
        // Step 5 - Fill Contact Us Form
        ContactUsPage.FillContactUsForm(
                contactUsData.ContactUsFormName,
                contactUsData.ContactUsFormEmail,
                contactUsData.ContactUsFormSubject,
                contactUsData.ContactUsFormMsg,
                contactUsData.ContactUsFormFilePath
        );
        
        // Step 6 - Verify success message is visible
        assertTrue(ContactUsPage.SubmittedFormMsg().contains("Success!"),
                "Success message 'Success! Your details have been submitted successfully.' is invisible");
        System.out.println("Success message 'Success! Your details have been submitted successfully.' is visible");       
        
        // Step 7 - Click Home button and verify navigation
        ContactUsPage.ClickHomeButton();
        assertTrue(Homepage.homePageheader().contains("Automation"),
                "The user does not go to the home page");
        System.out.println("The user goes to the home page successfully");
    }
    
    /**
     * Setup - Runs once before the class.
     * Loads test data and initializes page objects.
     */
    @BeforeClass
    public void setUpClass() throws FileNotFoundException, IOException {
        System.out.println("CLASS START");
        ListOfUsers = HelperClass.ReadContactUsForms("ContactUsData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
    }

    /**
     * Tear Down - Runs once after the class.
     * Quits the WebDriver.
     */
    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END");
    }

    /**
     * Setup Method - Runs before each test method.
     */
    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC06 - START");               
        Homepage = new Home_Page("chrome");
        ContactUsPage = new ContactUs_page();
    }

    /**
     * Tear Down Method - Runs after each test method.
     */
    @AfterMethod
    public void tearDownMethod() throws Exception {
        base.BaseDriver.quitDriver();   
        System.out.println("TC06 - END");
    }
}
