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
public class TC05_RegisterUserWithExistingEmail {
   
    Home_Page Homepage;
    SignUp_Page SignUp_Page;

    
    // Array holding user credentials loaded from JSON file
    static LoginUsers[] ListOfUsers;
    
        /**
     * Data Provider returns array of User objects for all test users.
     * @return User[] of user credentials
     */
    @DataProvider(name = "InvalidLoginData")
    public LoginUsers[] userDataProvider() {
        return ListOfUsers;
    }
    
    
    @Test(dataProvider = "InvalidLoginData")
    public void TC05_RegisterUserWithExistingEmail(LoginUsers InvalidLoginUsers)
    {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation")
                , "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickSignUp_Login();
        assertTrue(SignUp_Page.SignupHeader1().contains("New User Signup!") 
                , "the 'New User Signup!' is invisible");
        System.out.println("'new User SignUp!' is visible");
        
        SignUp_Page.signup("mahmoudahmed", InvalidLoginUsers.LoginEmail);
        assertTrue(SignUp_Page.SignUpWithExistingEmail().contains("Email Address already exist!") , "the error msg is not appeared");
        
    }
    
    
    @BeforeClass
    public void setUpClass()  throws FileNotFoundException, IOException  {
        ListOfUsers = HelperClass.ReadLoginUsers("ValidLoginData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
        
        Homepage = new Home_Page("chrome");
        SignUp_Page = new SignUp_Page();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        base.BaseDriver.quitDriver();   
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC05 - START");
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        System.out.println("TC05 - END");
    }
}
