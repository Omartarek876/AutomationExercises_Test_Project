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
public class TC06_ContactUsForm {
      Home_Page Homepage;
      ContactUs_page ContactUsPage;
      
    // Array holding user credentials loaded from JSON file
    static ContactUsUsers[] ListOfUsers;
    
        /**
     * Data Provider returns array of User objects for all test users.
     * @return User[] of user credentials
     */
    @DataProvider(name = "contactUsData")
    public ContactUsUsers[] userDataProvider() {
        return ListOfUsers;
    }
    
    
    @Test(dataProvider = "contactUsData")
    public void TC06_ContactUsForm(ContactUsUsers contactUsData)
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickContactUs();
        assertTrue(ContactUsPage.ContactUsHeader().contains("GET IN TOUCH") , "the header of ContactUs page 'GET IN TOUCH' is invisible");
    
         
        ContactUsPage.FillContactUsForm(contactUsData.ContactUsFormName , contactUsData.ContactUsFormEmail 
                , contactUsData.ContactUsFormSubject, contactUsData.ContactUsFormMsg 
                , contactUsData.ContactUsFormFilePath);
        
        assertTrue(ContactUsPage.SubmittedFormMsg().contains("Success!") , "success message 'Success! Your details have been submitted successfully.' is invisible");
        System.out.println("success message 'Success! Your details have been submitted successfully.' is visible");       
        ContactUsPage.ClickHomeButton();
        
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the user does not go to the home page");
        System.out.println("the user goes to the home page successfully");
        
    }
    
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        ListOfUsers = HelperClass.ReadContactUsForms("ContactUsData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
        
        Homepage = new Home_Page("chrome");
        ContactUsPage = new ContactUs_page();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC06 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC06 - END");
    }
}
