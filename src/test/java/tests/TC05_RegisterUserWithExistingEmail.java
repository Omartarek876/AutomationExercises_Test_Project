package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case: TC05_RegisterUserWithExistingEmail
 * 
 * This test validates that a user cannot register with an email
 * address that already exists in the system.
 * 
 * Author: Omar Tarek
 */
public class TC05_RegisterUserWithExistingEmail {
   
    Home_Page homePage;
    SignUp_Page signUpPage;

    // Array holding user credentials loaded from JSON file
    static LoginUsers[] listOfUsers;
    
    /**
     * Data Provider returns array of User objects with existing emails.
     * @return LoginUsers[] of users with already registered emails
     */
    @DataProvider(name = "existingEmailData")
    public LoginUsers[] userDataProvider() {
        return listOfUsers;
    }
    
    @Test(dataProvider = "existingEmailData")
    public void TC05_RegisterUserWithExistingEmail(LoginUsers existingUser) {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        
        assertTrue(homePage.homePageheader().contains("Automation"),
                "The home page is not visible");
        System.out.println("The home page is visible");
        
        homePage.clickSignUp_Login();
        assertTrue(signUpPage.SignupHeader1().contains("New User Signup!"),
                "'New User Signup!' header is not visible");
        System.out.println("'New User Signup!' is visible");
        
        signUpPage.signup("mahmoudahmed", existingUser.LoginEmail);
        assertTrue(signUpPage.SignUpWithExistingEmail().contains("Email Address already exist!"),
                "Error message for existing email did not appear");
        System.out.println("Error message 'Email Address already exist!' is displayed correctly");
    }
    
    @BeforeClass
    public void setUpClass() throws FileNotFoundException, IOException {
        System.out.println("CLASS START");
        listOfUsers = HelperClass.ReadLoginUsers("ValidLoginData.json");
        System.out.println("Number of users loaded: " + listOfUsers.length);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END");
        base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC05 - START");
        homePage = new Home_Page("chrome");
        signUpPage = new SignUp_Page();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        base.BaseDriver.quitDriver();  
        System.out.println("TC05 - END");
    }
}
