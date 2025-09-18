package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import static org.testng.Assert.*;
import org.testng.annotations.*;
import pages.*;
import utils.*;

/**
 * Test Case: TC01_RegisterUser
 *
 * This test automates the user registration flow on the Automation Exercise application:
 * - Navigates to home page
 * - Signs up a new user
 * - Verifies account creation
 * - Deletes the account
 * - Verifies account deletion
 *
 * Data-driven with user data loaded from JSON file.
 *
 * Author: Omar Tarek
 */
public class TC01_RegisterUser {
    
    Home_Page Homepage;
    SignUp_Page SignUp_Page;
    AccountStatus_Page AccountStatus_Page;
    
    // Array holding user credentials loaded from JSON file
    static CreateAccountUsers[] ListOfUsers;
    
    /**
     * Data Provider returns array of User objects for all test users.
     * @return User[] of user credentials
     */
    @DataProvider(name = "usersData")
    public CreateAccountUsers[] userDataProvider() {
        return ListOfUsers;
    }
    
    @Test(dataProvider = "usersData")
    public void TC01_Register_User(CreateAccountUsers UserTestData) {   
        BrowserUtils.navigateToURL("https://automationexercise.com/");
        assertTrue(Homepage.homePageheader().contains("Automation"), "the home page is invisible");
        System.out.println("the home page is visible");
        
        Homepage.clickSignUp_Login();
        assertTrue(SignUp_Page.SignupHeader1().contains("New User Signup!"), "the 'New User Signup!' is invisible");
        System.out.println("'New User Signup!' is visible");
        
        SignUp_Page.signup(UserTestData.SignUpName, UserTestData.SignUpEmail);
        assertTrue(SignUp_Page.SignupHeader2().contains("ENTER ACCOUNT INFORMATION"), "the 'ENTER ACCOUNT INFORMATION' is invisible");
        System.out.println("the SignUp header 'ENTER ACCOUNT INFORMATION' is visible");
        
        SignUp_Page.FillAllRegisterationForm(
            UserTestData.gender, UserTestData.SignUpName, UserTestData.password, 
            UserTestData.day, UserTestData.month, UserTestData.year, 
            UserTestData.firstName, UserTestData.lastName, UserTestData.company, 
            UserTestData.address1, UserTestData.address2, UserTestData.country,
            UserTestData.state, UserTestData.city, UserTestData.zipcode, UserTestData.mobileNumber
        );
        System.out.println("the registration process is done");
        
        assertTrue(AccountStatus_Page.getAccountCreatedMessage().contains("Congratulations! Your new account has been successfully created!")
        , "the account is not created");
        System.out.println("the account is created successfully");    
        AccountStatus_Page.clickContinue();    
        
        assertTrue(Homepage.LoggedAsText().contains(UserTestData.SignUpName));
        Homepage.DeleteAccount();
        
        assertTrue(AccountStatus_Page.getAccountDeletedMessage().contains("Your account has been permanently deleted!")
        , "the account is not deleted");
        System.out.println("the account is deleted successfully");
        AccountStatus_Page.clickContinue();    

        assertTrue(Homepage.homePageheader().contains("Automation"), "the home page is invisible");
    }
    
    @BeforeClass
    public void setUpClass() throws FileNotFoundException, IOException {
        System.out.println("CLASS START");
        ListOfUsers = HelperClass.ReadCreateAccountUsers("CreateAccountData.json");
        System.out.println("Number of users loaded: " + ListOfUsers.length);
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        System.out.println("CLASS END");
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        System.out.println("TC01 - START");    
        Homepage = new Home_Page("chrome");
        SignUp_Page = new SignUp_Page();
        AccountStatus_Page = new AccountStatus_Page();
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
        base.BaseDriver.quitDriver();   
        System.out.println("TC01 - END");
    }
}
